package com.kyroxova.storageplus.compartments.tile;

import com.kyroxova.storageplus.compartments.CompartmentType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCompartment extends TileEntity implements IInventory {

    private CompartmentType type;
    private ItemStack[] inventory;
    private String customName;

    public TileEntityCompartment() {
        this(CompartmentType.WOOD);
    }

    public TileEntityCompartment(CompartmentType type) {
        this.type = type;
        this.inventory = new ItemStack[type.getTotalSlots()];
    }

    public CompartmentType getType() {
        return type;
    }

    public void setType(CompartmentType type) {
        this.type = type;
        ItemStack[] oldInv = this.inventory;
        this.inventory = new ItemStack[type.getTotalSlots()];
        if (oldInv != null) {
            int length = Math.min(oldInv.length, this.inventory.length);
            System.arraycopy(oldInv, 0, this.inventory, 0, length);
        }
    }

    @Override
    public int getSizeInventory() {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        if (slot >= 0 && slot < inventory.length) {
            return inventory[slot];
        }
        return null;
    }

    @Override
    public ItemStack decrStackSize(int slot, int count) {
        if (slot >= 0 && slot < inventory.length && inventory[slot] != null) {
            ItemStack stack;
            if (inventory[slot].stackSize <= count) {
                stack = inventory[slot];
                inventory[slot] = null;
                markDirty();
                return stack;
            } else {
                stack = inventory[slot].splitStack(count);
                if (inventory[slot].stackSize == 0) {
                    inventory[slot] = null;
                }
                markDirty();
                return stack;
            }
        }
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        if (slot >= 0 && slot < inventory.length && inventory[slot] != null) {
            ItemStack stack = inventory[slot];
            inventory[slot] = null;
            return stack;
        }
        return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        if (slot >= 0 && slot < inventory.length) {
            inventory[slot] = stack;
            if (stack != null && stack.stackSize > getInventoryStackLimit()) {
                stack.stackSize = getInventoryStackLimit();
            }
            markDirty();
        }
    }

    @Override
    public String getInventoryName() {
        return hasCustomInventoryName() ? customName : "container.storageplus.compartment_" + type.getName();
    }

    @Override
    public boolean hasCustomInventoryName() {
        return customName != null && !customName.isEmpty();
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        if (worldObj.getTileEntity(xCoord, yCoord, zCoord) != this) {
            return false;
        }
        return player.getDistanceSq((double) xCoord + 0.5D, (double) yCoord + 0.5D, (double) zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory() {
    }

    @Override
    public void closeInventory() {
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return true;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        if (compound.hasKey("CompartmentType")) {
            int typeOrdinal = compound.getInteger("CompartmentType");
            if (typeOrdinal >= 0 && typeOrdinal < CompartmentType.values().length) {
                setType(CompartmentType.values()[typeOrdinal]);
            }
        }

        NBTTagList items = compound.getTagList("Items", 10);
        this.inventory = new ItemStack[type.getTotalSlots()];

        for (int i = 0; i < items.tagCount(); i++) {
            NBTTagCompound itemTag = items.getCompoundTagAt(i);
            int slot = itemTag.getInteger("Slot");
            if (slot >= 0 && slot < inventory.length) {
                inventory[slot] = ItemStack.loadItemStackFromNBT(itemTag);
            }
        }

        if (compound.hasKey("CustomName", 8)) {
            this.customName = compound.getString("CustomName");
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        compound.setInteger("CompartmentType", type.ordinal());

        NBTTagList items = new NBTTagList();
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null) {
                NBTTagCompound itemTag = new NBTTagCompound();
                itemTag.setInteger("Slot", i);
                inventory[i].writeToNBT(itemTag);
                items.appendTag(itemTag);
            }
        }
        compound.setTag("Items", items);

        if (hasCustomInventoryName()) {
            compound.setString("CustomName", customName);
        }
    }
}
