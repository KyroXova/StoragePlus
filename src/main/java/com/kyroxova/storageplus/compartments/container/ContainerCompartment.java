package com.kyroxova.storageplus.compartments.container;

import com.kyroxova.storageplus.compartments.CompartmentType;
import com.kyroxova.storageplus.compartments.tile.TileEntityCompartment;
import com.kyroxova.storageplus.init.ModContainers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.IContainerListener;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IntReferenceHolder;

public class ContainerCompartment extends Container {

    private final IInventory containerInventory;
    private final TileEntityCompartment tileEntity;
    private final CompartmentType type;
    private int currentPage = 0;

    public static final int ROWS_PER_PAGE = 5;
    public static final int COLS_PER_PAGE = 10;

    public static ContainerCompartment createClientContainer(int windowId, PlayerInventory playerInventory, PacketBuffer data) {
        int typeOrdinal = data.readInt();
        CompartmentType type = (typeOrdinal >= 0 && typeOrdinal < CompartmentType.values().length)
                ? CompartmentType.values()[typeOrdinal]
                : CompartmentType.WOOD;
        return new ContainerCompartment(windowId, playerInventory, new Inventory(type.getTotalSlots()), type, null);
    }

    public ContainerCompartment(int windowId, PlayerInventory playerInventory, TileEntityCompartment tileEntity) {
        this(windowId, playerInventory, tileEntity, tileEntity.getType(), tileEntity);
    }

    public ContainerCompartment(int windowId, PlayerInventory playerInventory, IInventory containerInventory, CompartmentType type, TileEntityCompartment tileEntity) {
        super(ModContainers.COMPARTMENT.get(), windowId);
        this.containerInventory = containerInventory;
        this.type = type;
        this.tileEntity = tileEntity;

        checkContainerSize(containerInventory, type.getTotalSlots());
        containerInventory.startOpen(playerInventory.player);

        // 50 Compartment Slots (5 rows x 10 columns) at (8, 22)
        for (int row = 0; row < ROWS_PER_PAGE; row++) {
            for (int col = 0; col < COLS_PER_PAGE; col++) {
                int slotIndex = col + row * COLS_PER_PAGE;
                this.addSlot(new SlotCompartment(this, containerInventory, slotIndex, 8 + col * 18, 22 + row * 18));
            }
        }

        // Player Inventory (3 rows x 9 columns) at (17, 125)
        int playerInvX = 17;
        int playerInvY = 125;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, playerInvX + col * 18, playerInvY + row * 18));
            }
        }

        // Player Hotbar (9 slots) at (17, 183)
        int hotbarY = 183;
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInventory, col, playerInvX + col * 18, hotbarY));
        }

        this.addDataSlot(new IntReferenceHolder() {
            @Override
            public int get() {
                return getCurrentPage();
            }

            @Override
            public void set(int value) {
                setCurrentPage(value);
            }
        });
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int page) {
        if (page >= 0 && page < type.getTotalPages()) {
            this.currentPage = page;
        }
    }

    public int getTotalPages() {
        return type.getTotalPages();
    }

    public IInventory getTileEntity() {
        return containerInventory;
    }

    public CompartmentType getType() {
        return type;
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return containerInventory.stillValid(player);
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity player, int slotIndex) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotIndex);

        if (slot != null && slot.hasItem()) {
            ItemStack stackInSlot = slot.getItem();
            itemstack = stackInSlot.copy();

            int compartmentSlotCount = CompartmentType.SLOTS_PER_PAGE;
            int totalContainerSlots = compartmentSlotCount + 36; // 36 = 27 inventory + 9 hotbar

            if (slotIndex < compartmentSlotCount) {
                // Moving from compartment to player inventory
                if (!this.moveItemStackTo(stackInSlot, compartmentSlotCount, totalContainerSlots, true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                // Moving from player inventory to current compartment page
                if (!this.moveItemStackTo(stackInSlot, 0, compartmentSlotCount, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (stackInSlot.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stackInSlot.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, stackInSlot);
        }

        return itemstack;
    }

    @Override
    public void removed(PlayerEntity player) {
        super.removed(player);
        this.containerInventory.stopOpen(player);
    }
}
