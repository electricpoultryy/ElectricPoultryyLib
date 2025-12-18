package xyz.stufforstuff.electricpoultrylib;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.stufforstuff.electricpoultrylib.util.InitHelper;

public class ElectricPoultryLib implements ModInitializer {
	public static final String MOD_ID = "electricpoultrylib";

    public static String MODMOD_ID = MOD_ID;

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
        InitHelper.registerAll();

		LOGGER.info("Hello Fabric world!");
	}
}