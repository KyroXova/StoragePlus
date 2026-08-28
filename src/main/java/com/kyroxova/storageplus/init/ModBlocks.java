package com.kyroxova.storageplus.init;

import com.kyroxova.storageplus.compartments.CompartmentType;
import com.kyroxova.storageplus.compartments.block.BlockCompartment;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "storageplus");

    public static final RegistryObject<BlockCompartment> COMPARTMENT_WOOD = BLOCKS.register(
            "compartment_wood", () -> new BlockCompartment(CompartmentType.WOOD));
    public static final RegistryObject<BlockCompartment> COMPARTMENT_COPPER = BLOCKS.register(
            "compartment_copper", () -> new BlockCompartment(CompartmentType.COPPER));
    public static final RegistryObject<BlockCompartment> COMPARTMENT_IRON = BLOCKS.register(
            "compartment_iron", () -> new BlockCompartment(CompartmentType.IRON));
    public static final RegistryObject<BlockCompartment> COMPARTMENT_STEEL = BLOCKS.register(
            "compartment_steel", () -> new BlockCompartment(CompartmentType.STEEL));
    public static final RegistryObject<BlockCompartment> COMPARTMENT_GOLD = BLOCKS.register(
            "compartment_gold", () -> new BlockCompartment(CompartmentType.GOLD));
    public static final RegistryObject<BlockCompartment> COMPARTMENT_DARK_STEEL = BLOCKS.register(
            "compartment_darksteel", () -> new BlockCompartment(CompartmentType.DARK_STEEL));
    public static final RegistryObject<BlockCompartment> COMPARTMENT_DIAMOND = BLOCKS.register(
            "compartment_diamond", () -> new BlockCompartment(CompartmentType.DIAMOND));
    public static final RegistryObject<BlockCompartment> COMPARTMENT_STAINLESS_STEEL = BLOCKS.register(
            "compartment_stainless_steel", () -> new BlockCompartment(CompartmentType.STAINLESS_STEEL));
    public static final RegistryObject<BlockCompartment> COMPARTMENT_EMERALD = BLOCKS.register(
            "compartment_emerald", () -> new BlockCompartment(CompartmentType.EMERALD));

    public static RegistryObject<BlockCompartment> getCompartmentBlock(CompartmentType type) {
        switch (type) {
            case COPPER: return COMPARTMENT_COPPER;
            case IRON: return COMPARTMENT_IRON;
            case STEEL: return COMPARTMENT_STEEL;
            case GOLD: return COMPARTMENT_GOLD;
            case DARK_STEEL: return COMPARTMENT_DARK_STEEL;
            case DIAMOND: return COMPARTMENT_DIAMOND;
            case STAINLESS_STEEL: return COMPARTMENT_STAINLESS_STEEL;
            case EMERALD: return COMPARTMENT_EMERALD;
            case WOOD:
            default:
                return COMPARTMENT_WOOD;
        }
    }
}
