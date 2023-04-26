package net.krlite.warpzone.mixin;

import net.krlite.warpzone.WarpZone;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
	@ModifyArg(
			method = "renderWorld",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/client/render/GameRenderer;getBasicProjectionMatrix(D)Lnet/minecraft/util/math/Matrix4f;",
					ordinal = 0
			)
	)
	private double modifyFov(double fov) {
		return fov * WarpZone.fov();
	}

	@Inject(method = "renderWorld", at = @At("HEAD"))
	private void renderWorld(float tickDelta, long limitTime, MatrixStack matrixStack, CallbackInfo ci) {
		matrixStack.scale((float) (1 / WarpZone.fov()), 1, (float) WarpZone.fov());
	}
}
