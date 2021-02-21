package com.GenZVirus.BetterUX;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.GenZVirus.BetterUX.Client.File.XMLFileJava;
import com.GenZVirus.BetterUX.Client.GUI.BetterOverlay;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("betterux")
@Mod.EventBusSubscriber(modid = BetterUX.MOD_ID, bus = Bus.MOD)
public class BetterUX {

	// Directly reference a log4j logger.
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LogManager.getLogger();

	/*
	 * Mod id reference
	 */
	public static final String MOD_ID = "betterux";

	/*
	 * Instance of ButterUX
	 */
	public static BetterUX instance;

	public BetterUX() {

		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		modEventBus.addListener(this::setup);
		modEventBus.addListener(this::doClientStuff);
		instance = this;

		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setup(final FMLCommonSetupEvent event) {
	}

	private void doClientStuff(final FMLClientSetupEvent event) {
		XMLFileJava.load();
		if (ModList.get().isLoaded("vampirism")) {
			BetterOverlay.isVampirismLoaded = true;
		}
		if (ModList.get().isLoaded("survive")) {
			BetterOverlay.isSurviveLoaded = true;
		}
	}

	@SubscribeEvent
	public void onServerStarting(FMLServerStartingEvent event) {
	}
}
