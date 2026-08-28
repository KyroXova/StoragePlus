package com.kyroxova.storageplus.network;

import com.kyroxova.storageplus.reference.Reference;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler {

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);

    private static int packetId = 0;

    public static void init() {
        INSTANCE.registerMessage(MessageChangePage.class, MessageChangePage.class, packetId++, Side.SERVER);
    }
}
