package com.GenZVirus.BetterUX.Client.Events;

import java.util.List;

import com.GenZVirus.BetterUX.BetterUX;
import com.google.common.collect.Lists;

import net.minecraft.util.FoodStats;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = BetterUX.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class ItemToolTipEventsHandler {

	@SubscribeEvent
	public static void heavyTitaniumArmorSet(ItemTooltipEvent event) {
		if (event.getPlayer() == null)
			return;
		if (!(event.getItemStack().getItem().isFood()))
			return;
		FoodStats stats = event.getPlayer().getFoodStats();
		int healing = 0;
		int Saturation = 0;

		healing = event.getItemStack().getItem().getFood().getHealing();
		Saturation = (int) Math.min((float) healing * event.getItemStack().getItem().getFood().getSaturation() * 2.0F, (float) Math.min(healing + stats.getFoodLevel(), 20));

		List<ITextComponent> text = Lists.newArrayList();

		text.add(new TranslationTextComponent(""));

		// Add Food
		text.add(new TranslationTextComponent("\u00A7l" + "Food Value: " + "\u00A7r" + "\u00A76" + healing));

		// Add Saturation
		text.add(new TranslationTextComponent("\u00A7l" + "Saturation Value: " + "\u00A7r" + "\u00A7e" + Saturation));

		event.getToolTip().addAll(text);
	}
}
