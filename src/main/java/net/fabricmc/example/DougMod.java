package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;

public class DougMod implements ModInitializer {
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		
		Client c = new Client();
		c.init();

		System.out.println("Hello Fabric world!");
	}
}
