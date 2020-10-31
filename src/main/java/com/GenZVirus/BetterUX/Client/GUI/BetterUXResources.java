package com.GenZVirus.BetterUX.Client.GUI;

import com.GenZVirus.BetterUX.BetterUX;

import net.minecraft.util.ResourceLocation;

public class BetterUXResources {

	// Overlays
		public static String BAR_LEFT = "bar_left.png";
		public static String BAR_RIGHT = "bar_right.png";
		public static String EXP_BAR = "exp_bar.png";
		public static String EXP_BAR_FILL = "exp_bar_fill.png";
		public static String HEALTH_BAR_FILL = "health_bar_fill.png";
		public static String POISONED_HEALTH_BAR_FILL = "poisoned_health_bar_fill.png";
		public static String WITHERING_HEALTH_BAR_FILL = "withering_health_bar_fill.png";
		public static String FOOD_BAR_FILL = "food_bar_fill.png";
		public static String HUNGER_BAR_FILL = "hunger_bar_fill.png";
		public static String SATURATION_BAR_FILL = "saturation_bar_fill.png";
		public static String SHIELD_BAR = "shield_bar.png";
		public static String ARMOR_LEFT = "shield_armor_left.png";
		public static String ARMOR_RIGHT = "shield_armor_right.png";
		public static String FIRE = "fire.png";
		public static String WATER_BREATHING_BAR = "water_breathing_bar.png";
		public static String WATER_BREATHING_BAR_FILL = "water_breathing_bar_fill.png";
		
		// Vampirism
		public static String BLOOD_BAR_FILL = "blood_bar_fill.png";
		
		//Discord
		public static String DISCORD = "discord.png";
		public static String DISCORD_BACKGROUND = "discord_background.png";
		
		//Editing
		public static String COLOR = "selectedcolor.png";
		
		// Boss Bar
		public static String BOSS_BAR_BACKGROUND = "boss_bar_background.png";
		public static String BOSS_BAR_HEALTH = "boss_bar_health.png";
		public static String BOSS_BAR_OVERLAY = "boss_bar_overlay.png";
	
	public static ResourceLocation getResourceOf(String name) {
		String path = "textures/gui/" + BetterOverlay.Texture + "/" + name;
		return new ResourceLocation(BetterUX.MOD_ID, path);
	}
	
}
