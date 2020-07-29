package com.shinoow.abyssalcraft.client.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import com.shinoow.abyssalcraft.api.block.ACBlocks;
import com.shinoow.abyssalcraft.api.item.ACItems;
import com.shinoow.abyssalcraft.common.world.data.NecromancyWorldSavedData;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraftforge.oredict.OreDictionary;

public class GuiFaceBook extends GuiScreen {

	private static final ResourceLocation bookGuiTextures = new ResourceLocation("abyssalcraft:textures/gui/necronomicon.png");

	private List<Tuple<String, Integer>> data = new ArrayList<>();

	public final int guiWidth = 255;
	public final int guiHeight = 192;

	public GuiFaceBook() {
		if(Minecraft.getMinecraft().world != null)
			data = NecromancyWorldSavedData.get(Minecraft.getMinecraft().world).getClientData();
	}

	@Override
	public void initGui()
	{
		Keyboard.enableRepeatEvents(true);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(bookGuiTextures);
		int k = (width - guiWidth) / 2;
		byte b0 = 2;
		drawTexturedModalRect(k, b0, 0, 0, guiWidth, guiHeight);
		super.drawScreen(mouseX, mouseY, partialTicks);

		fontRenderer.drawString("Name | Crystal Size", k + 20, b0 + 16, 0);
		
		for(int i = 0; i < data.size(); i++) {
			Tuple<String, Integer> dat = data.get(i);
			fontRenderer.drawString(dat.getFirst(), k + 20, b0 + 32 + (16 * i), 0);
			renderItem(k + 20 + 60, b0 + 26 + (16 * i), getStackForSize(dat.getSecond()), mouseX, mouseY);
		}
	}

	private ItemStack getStackForSize(int size) {
		switch(size) {
		case 0:
			return new ItemStack(ACItems.crystal_shard);
		case 1:
			return new ItemStack(ACItems.crystal);
		case 2:
			return new ItemStack(ACBlocks.crystal_cluster);
		default:
			return new ItemStack(ACItems.crystal_shard);
		}
	}

	@Override
	public void onGuiClosed()
	{
		Keyboard.enableRepeatEvents(false);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	public void renderItem(int xPos, int yPos, ItemStack stack, int mx, int my)
	{
		if(stack == null || stack.isEmpty()) return;

		if(stack.getItemDamage() == OreDictionary.WILDCARD_VALUE)
			stack.setItemDamage(0);

		RenderItem render = mc.getRenderItem();
		//		if(mx > xPos && mx < xPos+16 && my > yPos && my < yPos+16)
		//			tooltipStack = stack;

		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
		RenderHelper.enableGUIStandardItemLighting();
		GlStateManager.enableRescaleNormal();
		GlStateManager.enableDepth();
		render.renderItemAndEffectIntoGUI(stack, xPos, yPos);
		render.renderItemOverlayIntoGUI(mc.fontRenderer, stack, xPos, yPos, null);
		RenderHelper.disableStandardItemLighting();
		GlStateManager.popMatrix();

		GlStateManager.disableLighting();
	}
}
