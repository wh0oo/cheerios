package com.example.cheerios;

import com.example.cheerios.config.CheeriosConfig;
import net.fabricmc.api.ModInitializer;

public class CheeriosMod implements ModInitializer {

	@Override
	public void onInitialize() {
		CheeriosConfig.load();
	}
}

