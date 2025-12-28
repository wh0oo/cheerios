package com.example.cheerios.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public final class CheeriosConfig {

	private static CommentedFileConfig config;

	public static boolean suppressInvalidBlockAttachedEntity = true;

	private CheeriosConfig() {}

	public static void load() {
		Path configPath = FabricLoader.getInstance()
				.getConfigDir()
				.resolve("cheerios.toml");

		config = CommentedFileConfig.builder(configPath)
				.sync()
				.autosave()
				.writingMode(WritingMode.REPLACE)
				.build();

		config.load();

		defineDefaults();
		readValues();

		config.save();
	}

	private static void defineDefaults() {
		if (!config.contains("worldgen.suppress_invalid_block_attached_entity")) {
			config.set("worldgen.suppress_invalid_block_attached_entity", true);
			config.setComment(
				"worldgen.suppress_invalid_block_attached_entity",
				"Suppresses the 'Block-attached entity at invalid position' server log spam"
			);
		}
	}

	private static void readValues() {
		suppressInvalidBlockAttachedEntity =
				config.getOrElse(
						"worldgen.suppress_invalid_block_attached_entity",
						true
				);
	}
}
