package net.krlite.warpzone;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.krlite.warpzone.config.WarpZoneConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WarpZone implements ClientModInitializer {
	public static final String NAME = "Warp Zone", ID = "warpzone";
	public static final Logger LOGGER = LoggerFactory.getLogger(ID);
	public static final ConfigHolder<WarpZoneConfig> CONFIG_HOLDER;
	public static final WarpZoneConfig CONFIG;

	private static final double warpFov = 411.0 / 493.0, unWarpFov = 1, warpSpeed = 4.0 / 19.0;
	private static double fov, fovTarget;

	static {
		AutoConfig.register(WarpZoneConfig.class, Toml4jConfigSerializer::new);
		CONFIG_HOLDER = AutoConfig.getConfigHolder(WarpZoneConfig.class);
		CONFIG = CONFIG_HOLDER.get();
	}

	@Override
	public void onInitializeClient() {
		fov = fovTarget = unWarpFov;
		ClientTickEvents.END_CLIENT_TICK.register(client -> fov += (fovTarget - fov()) * warpSpeed);
	}

	public static double fov() {
		return fov;
	}

	public static void warp() {
		fovTarget = warpFov;
	}

	public static void unWarp() {
		fovTarget = unWarpFov;
	}
}
