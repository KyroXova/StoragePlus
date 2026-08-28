package com.kyroxova.storageplus.init;

import com.kyroxova.storageplus.compartments.CompartmentType;
import com.kyroxova.storageplus.compartments.block.BlockCompartment;
import com.kyroxova.storageplus.compartments.tile.TileEntityCompartment;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = "storageplus")
public class ModBlocks {

    public static final List<BlockCompartment> COMPARTMENTS = new ArrayList<>();

    public static BlockCompartment COMPARTMENT_WOOD;
    public static BlockCompartment COMPARTMENT_COPPER;
    public static BlockCompartment COMPARTMENT_IRON;
    public static BlockCompartment COMPARTMENT_STEEL;
    public static BlockCompartment COMPARTMENT_GOLD;
    public static BlockCompartment COMPARTMENT_DARK_STEEL;
    public static BlockCompartment COMPARTMENT_DIAMOND;
    public static BlockCompartment COMPARTMENT_STAINLESS_STEEL;
    public static BlockCompartment COMPARTMENT_EMERALD;

    static {
        COMPARTMENT_WOOD = register(new BlockCompartment(CompartmentType.WOOD));
        COMPARTMENT_COPPER = register(new BlockCompartment(CompartmentType.COPPER));
        COMPARTMENT_IRON = register(new BlockCompartment(CompartmentType.IRON));
        COMPARTMENT_STEEL = register(new BlockCompartment(CompartmentType.STEEL));
        COMPARTMENT_GOLD = register(new BlockCompartment(CompartmentType.GOLD));
        COMPARTMENT_DARK_STEEL = register(new BlockCompartment(CompartmentType.DARK_STEEL));
        COMPARTMENT_DIAMOND = register(new BlockCompartment(CompartmentType.DIAMOND));
        COMPARTMENT_STAINLESS_STEEL = register(new BlockCompartment(CompartmentType.STAINLESS_STEEL));
        COMPARTMENT_EMERALD = register(new BlockCompartment(CompartmentType.EMERALD));
    }

    private static BlockCompartment register(BlockCompartment block) {
        COMPARTMENTS.add(block);
        return block;
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        for (BlockCompartment block : COMPARTMENTS) {
            event.getRegistry().register(block);
        }
        GameRegistry.registerTileEntity(TileEntityCompartment.class, new ResourceLocation("storageplus", "compartment"));
    }
}
