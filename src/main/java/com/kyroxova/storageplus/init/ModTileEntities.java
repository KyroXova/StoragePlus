package com.kyroxova.storageplus.init;

import com.kyroxova.storageplus.compartments.tile.TileEntityCompartment;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntities {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, "storageplus");

    public static final RegistryObject<TileEntityType<TileEntityCompartment>> COMPARTMENT = TILE_ENTITIES.register(
            "compartment",
            () -> TileEntityType.Builder.of(
                    TileEntityCompartment::new,
                    ModBlocks.COMPARTMENT_WOOD.get(),
                    ModBlocks.COMPARTMENT_COPPER.get(),
                    ModBlocks.COMPARTMENT_IRON.get(),
                    ModBlocks.COMPARTMENT_STEEL.get(),
                    ModBlocks.COMPARTMENT_GOLD.get(),
                    ModBlocks.COMPARTMENT_DARK_STEEL.get(),
                    ModBlocks.COMPARTMENT_DIAMOND.get(),
                    ModBlocks.COMPARTMENT_STAINLESS_STEEL.get(),
                    ModBlocks.COMPARTMENT_EMERALD.get()
            ).build(null)
    );
}
