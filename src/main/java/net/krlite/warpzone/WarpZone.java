package net.krlite.warpzone;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.krlite.warpzone.config.WarpZoneExcluded;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WarpZone implements ModInitializer {
	public static final String NAME = "Warp Zone", ID = "warp-zone";
	public static final Logger LOGGER = LoggerFactory.getLogger(ID);
	public static final WarpZoneExcluded EXCLUDED = new WarpZoneExcluded();
	private static final double warpFov = 411.0 / 493.0, unWarpFov = 1, warpSpeed = 4.0 / 19.0;
	private static double fov, fovTarget;

	@Override
	public void onInitialize() {
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
