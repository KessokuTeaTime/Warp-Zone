package net.krlite.warpzone.config;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.SleepingChatScreen;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class WarpZoneExcluded {
	public WarpZoneExcluded() {
		load();
	}

	private static final File storage = FabricLoader.getInstance().getConfigDir().resolve("warp-zone-excluded.txt").toFile();
	private static final ArrayList<String> excluded = new ArrayList<>();
	private static final String minecraftPackage = "net.minecraft.client.gui.screen.";

	static {
		excluded.add(ChatScreen.class.getSimpleName());
		excluded.add(SleepingChatScreen.class.getSimpleName());
		excluded.add(DeathScreen.class.getSimpleName());
	}

	private void load() {
		if (storage.exists()) {
			try {
				excluded.clear();
				excluded.addAll(FileUtils.readLines(storage, Charset.defaultCharset()));
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
		else {
			storage.getParentFile().mkdirs();
			save();
		}
	}

	private void save() {
		try {
			FileUtils.writeLines(storage, excluded);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public boolean isExcludedScreen(Class<? extends Screen> screen) {
		return excluded.stream().anyMatch(excludedScreen -> {
			if (screen.getName().startsWith(minecraftPackage)) {
				return excludedScreen.replace(minecraftPackage, "")
							   .equals(screen.getName().replace(minecraftPackage, ""));
			}
			else return screen.getName().equals(excludedScreen);
		});
	}
}
