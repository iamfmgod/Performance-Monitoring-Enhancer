package iamfmgod.performancemonitoringenhanc.network;

import iamfmgod.performancemonitoringenhanc.hud.HudManager;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketTpsPing implements IMessage {
    private float tps;
    private int ping;

    public PacketTpsPing() {}

    public PacketTpsPing(float tps, int ping) {
        this.tps = tps;
        this.ping = ping;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.tps = buf.readFloat();
        this.ping = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeFloat(tps);
        buf.writeInt(ping);
    }

    public static class Handler implements IMessageHandler<PacketTpsPing, IMessage> {
        @Override
        public IMessage onMessage(PacketTpsPing message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() ->
                    HudManager.updateTpsPing(message.tps, message.ping)
            );
            return null;
        }
    }
}