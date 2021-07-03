package logisticspipes.network.packets.pipe;

import java.util.List;

import net.minecraft.entity.player.PlayerEntity;

import lombok.Getter;
import lombok.Setter;

import network.rs485.logisticspipes.network.packets.CoordinatesPacket;
import logisticspipes.network.abstractpackets.ModernPacket;
import logisticspipes.pipes.basic.CoreRoutedPipe;
import logisticspipes.pipes.basic.LogisticsTileGenericPipe;
import logisticspipes.utils.StaticResolve;
import network.rs485.logisticspipes.util.LPDataInput;
import network.rs485.logisticspipes.util.LPDataOutput;

@StaticResolve
public class PipeSignTypes extends CoordinatesPacket {

	@Getter
	@Setter
	private List<Integer> types;

	public PipeSignTypes(int id) {
		super(id);
	}

	@Override
	public void processPacket(PlayerEntity player) {
		LogisticsTileGenericPipe pipe = this.getPipe(player.getEntityWorld(), LTGPCompletionCheck.PIPE);
		if (pipe == null || !pipe.isInitialized()) {
			return;
		}
		((CoreRoutedPipe) pipe.pipe).handleSignPacket(types);
	}

	@Override
	public void writeData(LPDataOutput output) {
		super.writeData(output);
		output.writeCollection(types, LPDataOutput::writeInt);
	}

	@Override
	public void readData(LPDataInput input) {
		super.readData(input);
		types = input.readArrayList(LPDataInput::readInt);
	}

	@Override
	public ModernPacket template() {
		return new PipeSignTypes(getId());
	}
}
