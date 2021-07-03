package logisticspipes.network.packets.block;

import net.minecraft.entity.player.PlayerEntity;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.FMLClientHandler;

import logisticspipes.blocks.LogisticsSecurityTileEntity;
import logisticspipes.gui.GuiSecurityStation;
import logisticspipes.network.abstractpackets.IntegerCoordinatesPacket;
import logisticspipes.network.abstractpackets.ModernPacket;
import logisticspipes.proxy.MainProxy;
import logisticspipes.utils.StaticResolve;

@StaticResolve
public class SecurityStationCC extends IntegerCoordinatesPacket {

	public SecurityStationCC(int id) {
		super(id);
	}

	@Override
	public ModernPacket template() {
		return new SecurityStationCC(getId());
	}

	@Override
	public void processPacket(PlayerEntity player) {
		LogisticsSecurityTileEntity tile = this.getTileAs(player.world, LogisticsSecurityTileEntity.class);
		if (tile instanceof LogisticsSecurityTileEntity) {
			if (MainProxy.isClient(player.world)) {
				tile.setClientCC(getInteger() == 1);
				handleClientSide(player);
			} else {
				tile.changeCC();
			}
		}
	}

	@OnlyIn(Dist.CLIENT)
	private void handleClientSide(PlayerEntity player) {
		if (FMLClientHandler.instance().getClient().currentScreen instanceof GuiSecurityStation) {
			((GuiSecurityStation) FMLClientHandler.instance().getClient().currentScreen).refreshCheckBoxes();
		}
	}
}
