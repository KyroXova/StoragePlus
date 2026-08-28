package com.kyroxova.storageplus.compartments.container;

import com.kyroxova.storageplus.compartments.CompartmentType;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class SlotCompartment extends Slot {

    private final ContainerCompartment container;
    private final int slotIndexInPage;

    public SlotCompartment(ContainerCompartment container, IInventory inventory, int slotIndexInPage, int xPosition, int yPosition) {
        super(inventory, slotIndexInPage, xPosition, yPosition);
        this.container = container;
        this.slotIndexInPage = slotIndexInPage;
    }

    public int getRealSlotIndex() {
        return container.getCurrentPage() * CompartmentType.SLOTS_PER_PAGE + slotIndexInPage;
    }

    @Override
    public ItemStack getItem() {
        int index = getRealSlotIndex();
        if (index < container.getTileEntity().getContainerSize()) {
            return container.getTileEntity().getItem(index);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public void set(ItemStack stack) {
        int index = getRealSlotIndex();
        if (index < container.getTileEntity().getContainerSize()) {
            container.getTileEntity().setItem(index, stack);
            this.setChanged();
        }
    }

    @Override
    public ItemStack remove(int amount) {
        int index = getRealSlotIndex();
        if (index < container.getTileEntity().getContainerSize()) {
            return container.getTileEntity().removeItem(index, amount);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        int index = getRealSlotIndex();
        return index < container.getTileEntity().getContainerSize() && container.getTileEntity().canPlaceItem(index, stack);
    }
}
