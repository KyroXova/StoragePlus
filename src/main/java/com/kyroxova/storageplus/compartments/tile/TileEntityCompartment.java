package com.kyroxova.storageplus.compartments.tile;

import com.kyroxova.storageplus.compartments.CompartmentType;
import com.kyroxova.storageplus.compartments.container.ContainerCompartment;
import com.kyroxova.storageplus.init.ModTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class TileEntityCompartment extends TileEntity implements IInventory, INamedContainerProvider {

    private CompartmentType type;
    private NonNullList<ItemStack> inventory;
    private ITextComponent customName;

    public TileEntityCompartment() {
        this(CompartmentType.WOOD);
    }

    public TileEntityCompartment(CompartmentType type) {
        super(ModTileEntities.COMPARTMENT.get());
        this.type = type;
        this.inventory = NonNullList.withSize(type.getTotalSlots(), ItemStack.EMPTY);
    }

    public CompartmentType getType() {
        return type;
    }

    public void setType(CompartmentType type) {
        this.type = type;
        NonNullList<ItemStack> oldInv = this.inventory;
        this.inventory = NonNullList.withSize(type.getTotalSlots(), ItemStack.EMPTY);
        if (oldInv != null) {
            int length = Math.min(oldInv.size(), this.inventory.size());
            for (int i = 0; i < length; i++) {
                this.inventory.set(i, oldInv.get(i));
            }
        }
    }

    @Override
    public int getContainerSize() {
        return inventory.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack stack : inventory) {
            if (!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getItem(int index) {
        if (index >= 0 && index < inventory.size()) {
            return inventory.get(index);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack removeItem(int index, int count) {
        ItemStack stack = ItemStackHelper.removeItem(this.inventory, index, count);
        if (!stack.isEmpty()) {
            this.setChanged();
        }
        return stack;
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        ItemStack stack = ItemStackHelper.takeItem(this.inventory, index);
        if (!stack.isEmpty()) {
            this.setChanged();
        }
        return stack;
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        if (index >= 0 && index < inventory.size()) {
            this.inventory.set(index, stack);
            if (stack.getCount() > this.getMaxStackSize()) {
                stack.setCount(this.getMaxStackSize());
            }
            this.setChanged();
        }
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        if (this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        }
        return player.distanceToSqr((double) this.worldPosition.getX() + 0.5D, (double) this.worldPosition.getY() + 0.5D, (double) this.worldPosition.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public void clearContent() {
        this.inventory.clear();
    }

    @Override
    public ITextComponent getDisplayName() {
        return this.customName != null ? this.customName : new TranslationTextComponent("block.storageplus." + this.type.getRegistryName());
    }

    public void setCustomName(ITextComponent name) {
        this.customName = name;
    }

    @Override
    public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity player) {
        return new ContainerCompartment(windowId, playerInventory, this);
    }

    @Override
    public void load(BlockState state, CompoundNBT compound) {
        super.load(state, compound);

        if (compound.contains("CompartmentType")) {
            int typeOrdinal = compound.getInt("CompartmentType");
            if (typeOrdinal >= 0 && typeOrdinal < CompartmentType.values().length) {
                setType(CompartmentType.values()[typeOrdinal]);
            }
        }

        this.inventory = NonNullList.withSize(this.type.getTotalSlots(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.inventory);

        if (compound.contains("CustomName", 8)) {
            this.customName = ITextComponent.Serializer.fromJson(compound.getString("CustomName"));
        }
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        super.save(compound);

        compound.putInt("CompartmentType", this.type.ordinal());
        ItemStackHelper.saveAllItems(compound, this.inventory);

        if (this.customName != null) {
            compound.putString("CustomName", ITextComponent.Serializer.toJson(this.customName));
        }

        return compound;
    }
}
