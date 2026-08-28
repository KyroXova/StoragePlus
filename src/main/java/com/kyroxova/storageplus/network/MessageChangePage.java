package com.kyroxova.storageplus.network;

import com.kyroxova.storageplus.compartments.container.ContainerCompartment;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageChangePage implements IMessage, IMessageHandler<MessageChangePage, IMessage> {

    private int page;

    public MessageChangePage() {
    }

    public MessageChangePage(int page) {
        this.page = page;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.page = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.page);
    }

    @Override
    public IMessage onMessage(MessageChangePage message, MessageContext ctx) {
        EntityPlayerMP player = ctx.getServerHandler().player;
        if (player != null && player.openContainer instanceof ContainerCompartment) {
            player.getServerWorld().addScheduledTask(() -> {
                if (player.openContainer instanceof ContainerCompartment) {
                    ContainerCompartment container = (ContainerCompartment) player.openContainer;
                    container.setCurrentPage(message.page);
                    container.detectAndSendChanges();
                }
            });
        }
        return null;
    }
}
