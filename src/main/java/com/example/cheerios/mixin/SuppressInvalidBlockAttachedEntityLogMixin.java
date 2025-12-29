package com.example.cheerios.mixin;

import com.example.cheerios.config.CheeriosConfig;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Suppresses the benign but noisy
 * "Block-attached entity at invalid position" error log during worldgen.
 */
@Mixin(Logger.class)
public abstract class SuppressInvalidBlockAttachedEntityLogMixin {

	@Redirect(
		method = "error(Ljava/lang/String;Ljava/lang/Object;)V",
		at = @At(
			value = "INVOKE",
			target = "Lorg/apache/logging/log4j/Logger;error(Ljava/lang/String;Ljava/lang/Object;)V"
		)
	)
	private void cheerios$suppressInvalidBlockAttachedEntity(
			Logger logger,
			String message,
			Object param
	) {
		if (CheeriosConfig.suppressInvalidBlockAttachedEntity
				&& message != null
				&& message.contains("Block-attached entity at invalid position")) {
			return;
		}

		logger.error(message, param);
	}
}
