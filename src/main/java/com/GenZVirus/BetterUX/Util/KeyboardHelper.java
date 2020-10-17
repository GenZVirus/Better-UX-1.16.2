package com.GenZVirus.BetterUX.Util;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class KeyboardHelper {

	// This class is the KeyBoard helper it checks for keys

	private static final long WINDOW = Minecraft.getInstance().getMainWindow().getHandle();

	// Checks if the up is being held down

	@OnlyIn(Dist.CLIENT)
	public static boolean isHoldingUP() {
		return InputMappings.isKeyDown(WINDOW, GLFW.GLFW_KEY_UP);
	}

	// Checks if the down is being held down

	@OnlyIn(Dist.CLIENT)
	public static boolean isHoldingDOWN() {
		return InputMappings.isKeyDown(WINDOW, GLFW.GLFW_KEY_DOWN);
	}

	// Checks if the right is being held down

	@OnlyIn(Dist.CLIENT)
	public static boolean isHoldingRIGHT() {
		return InputMappings.isKeyDown(WINDOW, GLFW.GLFW_KEY_RIGHT);
	}

	// Checks if the left is being held down

	@OnlyIn(Dist.CLIENT)
	public static boolean isHoldingLEFT() {
		return InputMappings.isKeyDown(WINDOW, GLFW.GLFW_KEY_LEFT);
	}
}
