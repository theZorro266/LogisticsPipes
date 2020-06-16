package logisticspipes.pipes.upgrades.power;

import logisticspipes.pipes.basic.CoreRoutedPipe;
import logisticspipes.pipes.upgrades.IPipeUpgrade;
import network.rs485.logisticspipes.api.LogisticsModule;

public class RFPowerSupplierUpgrade implements IPipeUpgrade {

	public static String getName() {
		return "power_supplier_rf";
	}

	@Override
	public boolean needsUpdate() {
		return true;
	}

	@Override
	public boolean isAllowedForPipe(CoreRoutedPipe pipe) {
		return true;
	}

	@Override
	public boolean isAllowedForModule(LogisticsModule pipe) {
		return false;
	}

	@Override
	public String[] getAllowedPipes() {
		return new String[] { "all" };
	}

	@Override
	public String[] getAllowedModules() {
		return new String[] {};
	}
}
