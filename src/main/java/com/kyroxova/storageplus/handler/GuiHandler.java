package com.kyroxova.storageplus.handler;

import com.kyroxova.storageplus.compartments.client.gui.GuiCompartment;
import com.kyroxova.storageplus.compartments.container.ContainerCompartment;
import com.kyroxova.storageplus.compartments.tile.TileEntityCompartment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

    public static final int GUI_COMPARTMENT = 0;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
        if (ID == GUI_COMPARTMENT && te instanceof TileEntityCompartment) {
            return new ContainerCompartment(player.inventory, (TileEntityCompartment) te);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
        if (ID == GUI_COMPARTMENT && te instanceof TileEntityCompartment) {
            return new GuiCompartment(player.inventory, (TileEntityCompartment) te);
        }
        return null;
    }
}
