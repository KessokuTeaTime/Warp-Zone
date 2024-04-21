package band.kessokuteatime.warpzone;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import band.kessokuteatime.warpzone.config.WarpZoneConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WarpZone implements ClientModInitializer {
	public static final String NAME = "Warp Zone", ID = "warpzone";
	public static final Logger LOGGER = LoggerFactory.getLogger(ID);
	public static final ConfigHolder<WarpZoneConfig> CONFIG_HOLDER;
	public static final WarpZoneConfig CONFIG;

	private static final double unwarpFov = 1, warpSpeed = 1.0 / 1.85;
	private static double fov, fovTarget;

	static {
		AutoConfig.register(WarpZoneConfig.class, Toml4jConfigSerializer::new);
		CONFIG_HOLDER = AutoConfig.getConfigHolder(WarpZoneConfig.class);
		CONFIG = CONFIG_HOLDER.get();
	}

	@Override
	public void onInitializeClient() {
		fov = fovTarget = unwarpFov;
		ClientTickEvents.END_CLIENT_TICK.register(client -> fov += (fovTarget - fov()) * warpSpeed);
	}

	public static double fov() {
		return fov;
	}

	public static void warp() {
		fovTarget = CONFIG.fovMultiplierDouble();
	}

	public static void unwarp() {
		fovTarget = unwarpFov;
	}
}
