package com.kyroxova.storageplus.compartments.container;

import com.kyroxova.storageplus.compartments.CompartmentType;
import com.kyroxova.storageplus.compartments.tile.TileEntityCompartment;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerCompartment extends Container {

    private final TileEntityCompartment tileEntity;
    private final CompartmentType type;
    private int currentPage = 0;
    private int lastSyncedPage = -1;

    public static final int ROWS_PER_PAGE = 5;
    public static final int COLS_PER_PAGE = 10;

    public ContainerCompartment(InventoryPlayer playerInventory, TileEntityCompartment tileEntity) {
        this.tileEntity = tileEntity;
        this.type = tileEntity.getType();

        tileEntity.openInventory();

        // 50 Compartment Slots (5 rows x 10 columns)
        for (int row = 0; row < ROWS_PER_PAGE; row++) {
            for (int col = 0; col < COLS_PER_PAGE; col++) {
                int slotIndex = col + row * COLS_PER_PAGE;
                this.addSlotToContainer(new SlotCompartment(this, tileEntity, slotIndex, 12 + col * 18, 20 + row * 18));
            }
        }

        // Player Inventory (3 rows x 9 columns)
        int playerInvY = 122;
        int playerInvX = 21;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlotToContainer(new Slot(playerInventory, col + row * 9 + 9, playerInvX + col * 18, playerInvY + row * 18));
            }
        }

        // Player Hotbar (9 slots)
        int hotbarY = 180;
        for (int col = 0; col < 9; col++) {
            this.addSlotToContainer(new Slot(playerInventory, col, playerInvX + col * 18, hotbarY));
        }
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

    public TileEntityCompartment getTileEntity() {
        return tileEntity;
    }

    public CompartmentType getType() {
        return type;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return tileEntity.isUseableByPlayer(player);
    }

    @Override
    public void addCraftingToCrafters(ICrafting crafter) {
        super.addCraftingToCrafters(crafter);
        crafter.sendProgressBarUpdate(this, 0, this.currentPage);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (Object crafter : this.crafters) {
            ICrafting iCrafting = (ICrafting) crafter;
            if (this.lastSyncedPage != this.currentPage) {
                iCrafting.sendProgressBarUpdate(this, 0, this.currentPage);
            }
        }
        this.lastSyncedPage = this.currentPage;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data) {
        if (id == 0) {
            this.currentPage = data;
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack()) {
            ItemStack stackInSlot = slot.getStack();
            itemstack = stackInSlot.copy();

            int compartmentSlotCount = CompartmentType.SLOTS_PER_PAGE;
            int totalContainerSlots = compartmentSlotCount + 36; // 36 = 27 inventory + 9 hotbar

            if (slotIndex < compartmentSlotCount) {
                // Moving from compartment to player inventory
                if (!this.mergeItemStack(stackInSlot, compartmentSlotCount, totalContainerSlots, true)) {
                    return null;
                }
            } else {
                // Moving from player inventory to current compartment page
                if (!this.mergeItemStack(stackInSlot, 0, compartmentSlotCount, false)) {
                    return null;
                }
            }

            if (stackInSlot.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }

            if (stackInSlot.stackSize == itemstack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(player, stackInSlot);
        }

        return itemstack;
    }

    @Override
    public void onContainerClosed(EntityPlayer player) {
        super.onContainerClosed(player);
        this.tileEntity.closeInventory();
    }
}
