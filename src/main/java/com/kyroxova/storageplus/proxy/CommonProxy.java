package com.kyroxova.storageplus.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        // Register items, blocks, and tile entities
    }

    public void init(FMLInitializationEvent event) {
        // Register recipes and event listeners
    }

    public void postInit(FMLPostInitializationEvent event) {
        // Late registrations
    }
}
