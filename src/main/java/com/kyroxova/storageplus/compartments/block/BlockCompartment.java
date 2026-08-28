package com.kyroxova.storageplus.compartments.block;

import com.kyroxova.storageplus.StoragePlus;
import com.kyroxova.storageplus.compartments.CompartmentType;
import com.kyroxova.storageplus.compartments.tile.TileEntityCompartment;
import com.kyroxova.storageplus.creativetab.ModCreativeTab;
import com.kyroxova.storageplus.handler.GuiHandler;
import com.kyroxova.storageplus.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.Random;

public class BlockCompartment extends BlockContainer {

    private final CompartmentType type;
    private final Random random = new Random();

    public BlockCompartment(CompartmentType type) {
        super(type == CompartmentType.WOOD ? Material.wood : Material.iron);
        this.type = type;

        this.setBlockName(Reference.MOD_ID + ".compartment_" + type.getName());
        this.setBlockTextureName(Reference.MOD_ID + ":compartments/" + type.getTextureName());
        this.setHardness(type.getHardness());
        this.setResistance(type.getResistance());
        this.setStepSound(type == CompartmentType.WOOD ? soundTypeWood : soundTypeMetal);
        this.setCreativeTab(ModCreativeTab.TAB_STORAGE_PLUS);
    }

    public CompartmentType getType() {
        return type;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileEntityCompartment(this.type);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            TileEntity te = world.getTileEntity(x, y, z);
            if (te instanceof TileEntityCompartment) {
                player.openGui(StoragePlus.instance, GuiHandler.GUI_COMPARTMENT, world, x, y, z);
            }
        }
        return true;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        TileEntity te = world.getTileEntity(x, y, z);

        if (te instanceof TileEntityCompartment) {
            TileEntityCompartment compartment = (TileEntityCompartment) te;

            for (int i = 0; i < compartment.getSizeInventory(); i++) {
                ItemStack stack = compartment.getStackInSlot(i);

                if (stack != null) {
                    float rx = this.random.nextFloat() * 0.8F + 0.1F;
                    float ry = this.random.nextFloat() * 0.8F + 0.1F;
                    float rz = this.random.nextFloat() * 0.8F + 0.1F;

                    while (stack.stackSize > 0) {
                        int amount = this.random.nextInt(21) + 10;
                        if (amount > stack.stackSize) {
                            amount = stack.stackSize;
                        }

                        stack.stackSize -= amount;
                        EntityItem entityItem = new EntityItem(
                            world,
                            (double) x + (double) rx,
                            (double) y + (double) ry,
                            (double) z + (double) rz,
                            new ItemStack(stack.getItem(), amount, stack.getItemDamage())
                        );

                        if (stack.hasTagCompound()) {
                            entityItem.getEntityItem().setTagCompound((NBTTagCompound) stack.getTagCompound().copy());
                        }

                        float factor = 0.05F;
                        entityItem.motionX = this.random.nextGaussian() * (double) factor;
                        entityItem.motionY = this.random.nextGaussian() * (double) factor + 0.2D;
                        entityItem.motionZ = this.random.nextGaussian() * (double) factor;
                        world.spawnEntityInWorld(entityItem);
                    }
                }
            }

            world.func_147453_f(x, y, z, block);
        }

        super.breakBlock(world, x, y, z, block, meta);
    }
}
