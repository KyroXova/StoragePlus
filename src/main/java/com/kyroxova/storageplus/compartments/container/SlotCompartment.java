package com.kyroxova.storageplus.compartments.container;

import com.kyroxova.storageplus.compartments.CompartmentType;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
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
    public ItemStack getStack() {
        int index = getRealSlotIndex();
        if (index < inventory.getSizeInventory()) {
            return inventory.getStackInSlot(index);
        }
        return null;
    }

    @Override
    public void putStack(ItemStack stack) {
        int index = getRealSlotIndex();
        if (index < inventory.getSizeInventory()) {
            inventory.setInventorySlotContents(index, stack);
            this.onSlotChanged();
        }
    }

    @Override
    public ItemStack decrStackSize(int amount) {
        int index = getRealSlotIndex();
        if (index < inventory.getSizeInventory()) {
            return inventory.decrStackSize(index, amount);
        }
        return null;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        int index = getRealSlotIndex();
        return index < inventory.getSizeInventory() && inventory.isItemValidForSlot(index, stack);
    }
}
