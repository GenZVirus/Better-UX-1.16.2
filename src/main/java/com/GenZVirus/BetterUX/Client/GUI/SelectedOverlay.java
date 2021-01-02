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
	public void render(MatrixStack stack, int p_renderButton_1_, int p_renderButton_2_, float p_renderButton_3_) {
		if (!this.isOverlaySelected)
			return;
		mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.COLOR));

		// Square/Rectangle

		AbstractGui.blit(new MatrixStack(), this.x, this.y, 0, 0, 0, 1, this.getHeightRealms(), 1, 1);
		AbstractGui.blit(new MatrixStack(), this.x, this.y, 0, 0, 0, this.getWidth(), 1, 1, 1);
		AbstractGui.blit(new MatrixStack(), this.x + this.getWidth(), this.y, 0, 0, 0, 1, this.getHeightRealms(), 1, 1);
		AbstractGui.blit(new MatrixStack(), this.x, this.y + this.getHeightRealms(), 0, 0, 0, this.getWidth(), 1, 1, 1);

		// Position lines

		AbstractGui.blit(new MatrixStack(), this.x + this.getWidth() / 2, 0, 0, 0, 0, 1, this.y, 1, 1);
		AbstractGui.blit(new MatrixStack(), this.x + this.getWidth() / 2, this.y + this.getHeightRealms(), 0, 0, 0, 1, mc.getMainWindow().getScaledHeight() - this.getHeightRealms() - this.y, 1, 1);
		AbstractGui.blit(new MatrixStack(), 0, this.y + this.getHeightRealms() / 2, 0, 0, 0, this.x, 1, 1, 1);
		AbstractGui.blit(new MatrixStack(), this.x + this.getWidth(), this.y + this.getHeightRealms() / 2, 0, 0, 0, mc.getMainWindow().getScaledWidth() - this.getWidth() - this.x, 1, 1, 1);

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
	public void onClick(double x, double y) {
		List<Widget> buttons = EditOverlay.instance.getButtons();
		for (Widget button : buttons) {
			int widthIn = button.x;
			int heightIn = button.y;
			int width = button.getWidth();
			int height = button.getHeightRealms();
			if (x >= widthIn && x < widthIn + width && y >= heightIn && y < heightIn + height) {
				if (button instanceof SelectedOverlay) {
					((SelectedOverlay) button).onPress();
				}
			}
		}
	}

}
