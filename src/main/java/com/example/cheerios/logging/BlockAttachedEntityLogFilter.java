package com.example.cheerios.logging;

import com.example.cheerios.config.CheeriosConfig;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.filter.AbstractFilter;

public final class BlockAttachedEntityLogFilter extends AbstractFilter {

	private static final String TARGET =
			"Block-attached entity at invalid position";

	@Override
	public Result filter(LogEvent event) {
		if (!CheeriosConfig.suppressInvalidBlockAttachedEntity) {
			return Result.NEUTRAL;
		}

		if (event.getMessage() != null) {
			String message = event.getMessage().getFormattedMessage();
			if (message != null && message.contains(TARGET)) {
				return Result.DENY;
			}
		}

		return Result.NEUTRAL;
	}
}
