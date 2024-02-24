package net.krlite.warpzone.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.SleepingChatScreen;

import java.util.ArrayList;
import java.util.stream.Stream;

@Config(name = "warpzone")
@Config.Gui.Background(Config.Gui.Background.TRANSPARENT)
public class WarpZoneConfig implements ConfigData {
	@ConfigEntry.Gui.Excluded
	private static final String minecraftPackage = "net.minecraft.client.gui.screen.";
	public ArrayList<String> excluded = new ArrayList<>(Stream.of(
			ChatScreen.class,
			SleepingChatScreen.class,
			DeathScreen.class
	).map(Class::getSimpleName).toList());

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
