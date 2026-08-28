package com.kyroxova.storageplus;

import com.kyroxova.storageplus.proxy.CommonProxy;
import com.kyroxova.storageplus.reference.Reference;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(
    modid = Reference.MOD_ID,
    name = Reference.MOD_NAME,
    version = Reference.VERSION,
    acceptedMinecraftVersions = Reference.ACCEPTED_MC_VERSIONS,
    dependencies = Reference.DEPENDENCIES
)
public class StoragePlus {

    @Mod.Instance(Reference.MOD_ID)
    public static StoragePlus instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        logger.info("Storage Plus (MC 1.7.9) pre-initialization...");
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        logger.info("Storage Plus (MC 1.7.9) initialization...");
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        logger.info("Storage Plus (MC 1.7.9) post-initialization...");
        proxy.postInit(event);
    }
}
