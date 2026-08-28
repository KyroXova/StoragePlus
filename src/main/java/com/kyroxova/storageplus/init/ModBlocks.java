package com.kyroxova.storageplus.init;

import com.kyroxova.storageplus.compartments.CompartmentType;
import com.kyroxova.storageplus.compartments.block.BlockCompartment;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class ModBlocks {

    public static Block COMPARTMENT_WOOD;
    public static Block COMPARTMENT_COPPER;
    public static Block COMPARTMENT_IRON;
    public static Block COMPARTMENT_STEEL;
    public static Block COMPARTMENT_GOLD;
    public static Block COMPARTMENT_DARK_STEEL;
    public static Block COMPARTMENT_DIAMOND;
    public static Block COMPARTMENT_STAINLESS_STEEL;
    public static Block COMPARTMENT_EMERALD;

    public static void init() {
        COMPARTMENT_WOOD = registerBlock(new BlockCompartment(CompartmentType.WOOD), "compartment_wood");
        COMPARTMENT_COPPER = registerBlock(new BlockCompartment(CompartmentType.COPPER), "compartment_copper");
        COMPARTMENT_IRON = registerBlock(new BlockCompartment(CompartmentType.IRON), "compartment_iron");
        COMPARTMENT_STEEL = registerBlock(new BlockCompartment(CompartmentType.STEEL), "compartment_steel");
        COMPARTMENT_GOLD = registerBlock(new BlockCompartment(CompartmentType.GOLD), "compartment_gold");
        COMPARTMENT_DARK_STEEL = registerBlock(new BlockCompartment(CompartmentType.DARK_STEEL), "compartment_darksteel");
        COMPARTMENT_DIAMOND = registerBlock(new BlockCompartment(CompartmentType.DIAMOND), "compartment_diamond");
        COMPARTMENT_STAINLESS_STEEL = registerBlock(new BlockCompartment(CompartmentType.STAINLESS_STEEL), "compartment_stainless_steel");
        COMPARTMENT_EMERALD = registerBlock(new BlockCompartment(CompartmentType.EMERALD), "compartment_emerald");
    }

    private static Block registerBlock(Block block, String name) {
        GameRegistry.registerBlock(block, name);
        return block;
    }
}
