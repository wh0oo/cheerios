package com.example.cheerios;

import com.example.cheerios.config.CheeriosConfig;
import com.example.cheerios.logging.BlockAttachedEntityLogFilter;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;

public class CheeriosMod implements ModInitializer {

	@Override
	public void onInitialize() {
		// Load TOML config
		CheeriosConfig.load();

		// Register Log4j filter
		registerLogFilter();
	}

	private void registerLogFilter() {
		LoggerContext context = (LoggerContext) LogManager.getContext(false);

		BlockAttachedEntityLogFilter filter =
				new BlockAttachedEntityLogFilter();

		context.getConfiguration().addFilter(filter);
		context.updateLoggers();
	}
}

