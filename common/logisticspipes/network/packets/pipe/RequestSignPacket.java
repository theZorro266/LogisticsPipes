package logisticspipes.network.packets.pipe;

import net.minecraft.entity.player.PlayerEntity;

import network.rs485.logisticspipes.network.packets.CoordinatesPacket;
import logisticspipes.network.abstractpackets.ModernPacket;
import logisticspipes.pipes.basic.CoreRoutedPipe;
import logisticspipes.pipes.basic.LogisticsTileGenericPipe;
import logisticspipes.utils.StaticResolve;

@StaticResolve
public class RequestSignPacket extends CoordinatesPacket {

	public RequestSignPacket(int id) {
		super(id);
	}

	@Override
	public void processPacket(PlayerEntity player) {
		LogisticsTileGenericPipe pipe = this.getPipe(player.getEntityWorld());
		if (pipe == null) {
			return;
		}
		((CoreRoutedPipe) pipe.pipe).sendSignData(player, false);
	}

	@Override
	public ModernPacket template() {
		return new RequestSignPacket(getId());
	}
}
