package com.kyroxova.storageplus.compartments.block;

import com.kyroxova.storageplus.StoragePlus;
import com.kyroxova.storageplus.compartments.CompartmentType;
import com.kyroxova.storageplus.compartments.tile.TileEntityCompartment;
import com.kyroxova.storageplus.creativetab.ModCreativeTab;
import com.kyroxova.storageplus.handler.GuiHandler;
import com.kyroxova.storageplus.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCompartment extends Block {

    private final CompartmentType type;

    public BlockCompartment(CompartmentType type) {
        super(type == CompartmentType.WOOD ? Material.WOOD : Material.IRON);
        this.type = type;

        this.setUnlocalizedName(Reference.MOD_ID + "." + type.getRegistryName());
        this.setRegistryName(type.getRegistryName());
        this.setHardness(type.getHardness());
        this.setResistance(type.getResistance());
        this.setSoundType(type == CompartmentType.WOOD ? SoundType.WOOD : SoundType.METAL);
        this.setCreativeTab(ModCreativeTab.TAB_STORAGE_PLUS);
    }

    public CompartmentType getType() {
        return type;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityCompartment(this.type);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            TileEntity te = worldIn.getTileEntity(pos);
            if (te instanceof TileEntityCompartment) {
                playerIn.openGui(StoragePlus.instance, GuiHandler.GUI_COMPARTMENT, worldIn, pos.getX(), pos.getY(), pos.getZ());
            }
        }
        return true;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileEntity te = worldIn.getTileEntity(pos);
        if (te instanceof TileEntityCompartment) {
            InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityCompartment) te);
            worldIn.updateComparatorOutputLevel(pos, this);
        }
        super.breakBlock(worldIn, pos, state);
    }
}
