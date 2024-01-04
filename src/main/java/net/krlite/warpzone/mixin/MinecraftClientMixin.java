package net.krlite.warpzone.mixin;

import net.krlite.warpzone.WarpZone;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
	@Inject(method = "setScreen", at = @At("RETURN"))
	private void setScreen(Screen screen, CallbackInfo ci) {
		if (screen == null) {
			WarpZone.unWarp();
		}
		else if (!WarpZone.EXCLUDED.isExcludedScreen(screen.getClass())) {
			WarpZone.warp();
		}
	}
}
