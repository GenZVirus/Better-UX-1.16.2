package com.GenZVirus.BetterUX.Client.GUI;

import java.util.List;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.text.TranslationTextComponent;

public class SelectedOverlay extends Widget {

	private Minecraft mc = Minecraft.getInstance();
	public boolean isOverlaySelected = false;
	public int scaledX;
	public int scaledY;

	public SelectedOverlay(int xIn, int yIn, int widthIn, int heightIn, String msg, int scaledX, int scaledY) {
		super(xIn, yIn, widthIn, heightIn, new TranslationTextComponent(msg));
		this.scaledX = scaledX;
		this.scaledY = scaledY;
	}

	@Override
	public void func_230430_a_(MatrixStack stack, int p_renderButton_1_, int p_renderButton_2_, float p_renderButton_3_) {
		if (!this.isOverlaySelected)
			return;
		mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.COLOR));

		// Square/Rectangle

		AbstractGui.func_238464_a_(new MatrixStack(), this.field_230690_l_, this.field_230691_m_, 0, 0, 0, 1, this.func_238483_d_(), 1, 1);
		AbstractGui.func_238464_a_(new MatrixStack(), this.field_230690_l_, this.field_230691_m_, 0, 0, 0, this.func_230998_h_(), 1, 1, 1);
		AbstractGui.func_238464_a_(new MatrixStack(), this.field_230690_l_ + this.func_230998_h_(), this.field_230691_m_, 0, 0, 0, 1, this.func_238483_d_(), 1, 1);
		AbstractGui.func_238464_a_(new MatrixStack(), this.field_230690_l_, this.field_230691_m_ + this.func_238483_d_(), 0, 0, 0, this.func_230998_h_(), 1, 1, 1);

		// Position lines

		AbstractGui.func_238464_a_(new MatrixStack(), this.field_230690_l_ + this.func_230998_h_() / 2, 0, 0, 0, 0, 1, this.field_230691_m_, 1, 1);
		AbstractGui.func_238464_a_(new MatrixStack(), this.field_230690_l_ + this.func_230998_h_() / 2, this.field_230691_m_ + this.func_238483_d_(), 0, 0, 0, 1, mc.getMainWindow().getScaledHeight() - this.func_238483_d_() - this.field_230691_m_, 1, 1);
		AbstractGui.func_238464_a_(new MatrixStack(), 0, this.field_230691_m_ + this.func_238483_d_() / 2, 0, 0, 0, this.field_230690_l_, 1, 1, 1);
		AbstractGui.func_238464_a_(new MatrixStack(), this.field_230690_l_ + this.func_230998_h_(), this.field_230691_m_ + this.func_238483_d_() / 2, 0, 0, 0, mc.getMainWindow().getScaledWidth() - this.func_230998_h_() - this.field_230690_l_, 1, 1, 1);

	}

	public void onPress() {
		for (Widget button : EditOverlay.instance.getButtons()) {
			if (button instanceof SelectedOverlay) {
				((SelectedOverlay) button).isOverlaySelected = false;
			}
		}
		this.isOverlaySelected = true;
	}

	public void addX(int number) {
	}

	public void addY(int number) {
	}

	public void substractX(int number) {
	}

	public void substractY(int number) {
	}

	public void setX(int X) {
	}

	public void setY(int Y) {
	}

	@Override
	public void func_230982_a_(double x, double y) {
		List<Widget> buttons = EditOverlay.instance.getButtons();
		for (Widget button : buttons) {
			int widthIn = button.field_230690_l_;
			int heightIn = button.field_230691_m_;
			int width = button.func_230998_h_();
			int height = button.func_238483_d_();
			if (x >= widthIn && x < widthIn + width && y >= heightIn && y < heightIn + height) {
				if (button instanceof SelectedOverlay) {
					((SelectedOverlay) button).onPress();
				}
			}
		}
	}

}
