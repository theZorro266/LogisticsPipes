package logisticspipes.network.packets.orderer;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;

import logisticspipes.LPItems;
import logisticspipes.network.PacketHandler;
import network.rs485.logisticspipes.network.packets.CoordinatesPacket;
import logisticspipes.network.abstractpackets.ModernPacket;
import logisticspipes.pipes.PipeItemsRequestLogisticsMk2;
import logisticspipes.pipes.basic.LogisticsTileGenericPipe;
import logisticspipes.proxy.MainProxy;
import logisticspipes.utils.StaticResolve;

@StaticResolve
public class DiskDropPacket extends CoordinatesPacket {

	public DiskDropPacket(int id) {
		super(id);
	}

	@Override
	public ModernPacket template() {
		return new DiskDropPacket(getId());
	}

	@Override
	public void processPacket(PlayerEntity player) {
		final LogisticsTileGenericPipe pipe = this.getPipe(player.world);
		if (pipe == null) {
			return;
		}
		if (pipe.pipe instanceof PipeItemsRequestLogisticsMk2) {
			if (((PipeItemsRequestLogisticsMk2) pipe.pipe).getDisk() != null) {
				if (((PipeItemsRequestLogisticsMk2) pipe.pipe).getDisk().getItem().equals(LPItems.disk)) {
					if (!((PipeItemsRequestLogisticsMk2) pipe.pipe).getDisk().hasTag()) {
						((PipeItemsRequestLogisticsMk2) pipe.pipe).getDisk().setTag(new CompoundNBT());
					}
				}
			}
			((PipeItemsRequestLogisticsMk2) pipe.pipe).dropDisk();
			MainProxy.sendPacketToPlayer(PacketHandler.getPacket(DiscContent.class).setStack(((PipeItemsRequestLogisticsMk2) pipe.pipe).getDisk()).setBlockPos(pipe.getPos()), player);
		}
	}
}
