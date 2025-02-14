/*******************************************************************************
 * AbyssalCraft
 * Copyright (c) 2012 - 2016 Shinoow.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 * 
 * Contributors:
 *     Shinoow -  implementation
 ******************************************************************************/
package com.shinoow.abyssalcraft.client.handlers;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.shinoow.abyssalcraft.AbyssalCraft;
import com.shinoow.abyssalcraft.client.model.player.ModelStarSpawnPlayer;
import com.shinoow.abyssalcraft.common.network.PacketDispatcher;
import com.shinoow.abyssalcraft.common.network.server.FireMessage;
import com.shinoow.abyssalcraft.common.util.EntityUtil;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class AbyssalCraftClientEventHooks {

	private ModelStarSpawnPlayer model = new ModelStarSpawnPlayer();

	Minecraft mc = Minecraft.getMinecraft();

	@SubscribeEvent
	public void renderPlayer(RenderPlayerEvent.SetArmorModel event){

		if(EntityUtil.isPlayerCoralium(event.entityPlayer) && !event.entityPlayer.isInvisible())
			renderStarSpawnPlayer(event.entityPlayer, event.partialRenderTick);

	}

	private void renderStarSpawnPlayer(EntityPlayer player, float partialTicks){

		mc.renderEngine.bindTexture(new ResourceLocation("abyssalcraft:textures/model/tentacles.png"));

		for (int j = 0; j < 1; ++j) {
			GL11.glColor4f(1F, 1F, 1F, 1F);
			float f10 = player.prevRotationYawHead + (player.rotationYawHead - player.prevRotationYawHead) * partialTicks - (player.prevRenderYawOffset + (player.renderYawOffset - player.prevRenderYawOffset) * partialTicks);
			float f2 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * partialTicks;
			GL11.glPushMatrix();
			GL11.glFrontFace(GL11.GL_CW);
			GL11.glRotatef(f10, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(f2, 1.0F, 0.0F, 0.0F);
			GL11.glTranslatef(0, -0.22F, 0);
			model.renderTentacles(0.0625F, player);
			GL11.glFrontFace(GL11.GL_CCW);
			GL11.glPopMatrix();
		}
	}

	@SubscribeEvent
	public void onUpdateFOV(FOVUpdateEvent event) {
		float fov = event.fov;

		if( event.entity.isUsingItem() && event.entity.getItemInUse().getItem() == AbyssalCraft.corbow) {
			int duration = event.entity.getItemInUseDuration();
			float multiplier = duration / 20.0F;

			if( multiplier > 1.0F )
				multiplier = 1.0F;
			else
				multiplier *= multiplier;

			fov *= 1.0F - multiplier * 0.15F;
		}

		event.newfov = fov;
	}

	@SubscribeEvent
	public void onMouseEvent(MouseEvent event) {
		int button = event.button - 100;
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer player = mc.thePlayer;
		World world = mc.theWorld;
		int key = mc.gameSettings.keyBindAttack.getKeyCode();

		if (button == key && Mouse.isButtonDown(button + 100))
			if (mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK){
				MovingObjectPosition mop = mc.objectMouseOver;
				if (world.getBlock(mop.blockX, mop.blockY, mop.blockZ) != null)
					extinguishFire(player, mop.blockX, mop.blockY, mop.blockZ, world, event);
			}
	}

	private void extinguishFire(EntityPlayer player, int x, int y, int z, World world, Event event) {
		if (world.getBlock(x, y + 1, z) == AbyssalCraft.mimicFire ||
				world.getBlock(x, y + 1, z) == AbyssalCraft.Coraliumfire ||
				world.getBlock(x, y + 1, z) == AbyssalCraft.dreadfire ||
				world.getBlock(x, y + 1, z) == AbyssalCraft.omotholfire)
			if (event instanceof MouseEvent || event instanceof PlayerInteractEvent) {
				PacketDispatcher.sendToServer(new FireMessage(x, y + 1, z));
				event.setCanceled(true);
			}
	}
}