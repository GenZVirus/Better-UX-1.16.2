package com.GenZVirus.BetterUX.Client.Init;

import com.GenZVirus.BetterUX.BetterUX;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundInit {

	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, BetterUX.MOD_ID);

	public static final RegistryObject<SoundEvent> HEARTBEAT = SOUNDS.register("heartbeat", () -> new SoundEvent(new ResourceLocation(BetterUX.MOD_ID, "heartbeat")));
}
