package net.krlite.warpzone.mixin;

import net.krlite.warpzone.WarpZone;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
	@Shadow protected abstract double getFov(Camera camera, float tickDelta, boolean changingFov);

	@Redirect(method = "renderWorld", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/GameRenderer;getFov(Lnet/minecraft/client/render/Camera;FZ)D"))
	private double modifyFov(GameRenderer gameRenderer, Camera camera, float tickDelta, boolean changingFov) {
		return getFov(camera, tickDelta, changingFov) * WarpZone.fov();
	}
}
