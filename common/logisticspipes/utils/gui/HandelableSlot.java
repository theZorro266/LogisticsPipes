package logisticspipes.utils.gui;

import javax.annotation.Nonnull;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

import logisticspipes.interfaces.ISlotClick;

public class HandelableSlot extends Slot {

	private final ISlotClick _handler;

	public HandelableSlot(IInventory inventory, int slotId, int xCoord, int yCoord, ISlotClick handler) {
		super(inventory, slotId, xCoord, yCoord);
		_handler = handler;
	}

	@Override
	public boolean isItemValid(@Nonnull ItemStack par1ItemStack) {
		return par1ItemStack.isEmpty();
	}

	@Nonnull
	public ItemStack getProvidedStack() {
		return _handler.getResultForClick();
	}

	@Override
	public boolean canTakeStack(PlayerEntity player) {
		return false;
	}

}
