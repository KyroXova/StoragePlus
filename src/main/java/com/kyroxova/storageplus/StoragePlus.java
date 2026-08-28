package com.kyroxova.storageplus;

import com.kyroxova.storageplus.compartments.client.gui.GuiCompartment;
import com.kyroxova.storageplus.init.ModBlocks;
import com.kyroxova.storageplus.init.ModContainers;
import com.kyroxova.storageplus.init.ModItems;
import com.kyroxova.storageplus.init.ModTileEntities;
import com.kyroxova.storageplus.network.PacketHandler;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("storageplus")
public class StoragePlus {

    public static final String MOD_ID = "storageplus";
    private static final Logger LOGGER = LogManager.getLogger();

    public StoragePlus() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModTileEntities.TILE_ENTITIES.register(modEventBus);
        ModContainers.CONTAINERS.register(modEventBus);

        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("Storage Plus (MC 1.16.5) common setup...");
        event.enqueueWork(PacketHandler::init);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        LOGGER.info("Storage Plus (MC 1.16.5) client setup...");
        event.enqueueWork(() -> {
            ScreenManager.register(ModContainers.COMPARTMENT.get(), GuiCompartment::new);
        });
    }
}
