package com.kyroxova.storageplus.network;

import com.kyroxova.storageplus.compartments.container.ContainerCompartment;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageChangePage {

    private final int page;

    public MessageChangePage(int page) {
        this.page = page;
    }

    public static void encode(MessageChangePage msg, PacketBuffer buffer) {
        buffer.writeInt(msg.page);
    }

    public static MessageChangePage decode(PacketBuffer buffer) {
        return new MessageChangePage(buffer.readInt());
    }

    public static void handle(MessageChangePage msg, Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context ctx = ctxSupplier.get();
        ctx.enqueueWork(() -> {
            ServerPlayerEntity player = ctx.getSender();
            if (player != null && player.containerMenu instanceof ContainerCompartment) {
                ContainerCompartment container = (ContainerCompartment) player.containerMenu;
                container.setCurrentPage(msg.page);
                container.broadcastChanges();
            }
        });
        ctx.setPacketHandled(true);
    }
}
