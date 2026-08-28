package com.kyroxova.storageplus.network;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PacketHandler {

    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation("storageplus", "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    private static int packetId = 0;

    public static void init() {
        INSTANCE.messageBuilder(MessageChangePage.class, packetId++)
                .encoder(MessageChangePage::encode)
                .decoder(MessageChangePage::decode)
                .consumer(MessageChangePage::handle)
                .add();
    }
}
