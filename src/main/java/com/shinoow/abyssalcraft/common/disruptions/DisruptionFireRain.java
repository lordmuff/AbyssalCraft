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
package com.shinoow.abyssalcraft.common.disruptions;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.shinoow.abyssalcraft.api.energy.disruption.DisruptionEntry;

public class DisruptionFireRain extends DisruptionEntry {

	public DisruptionFireRain() {
		super("fireRain", null);
	}

	@Override
	public void disrupt(World world, int x, int y, int z, List<EntityPlayer> players) {
		EntityLargeFireball fireball1 = positionFireball(world, x + 2.5, y + 10, z + 2.5, 0, -0.5, 0);
		EntityLargeFireball fireball2 = positionFireball(world, x - 2.5, y + 10, z + 2.5, 0, -0.5, 0);
		EntityLargeFireball fireball3 = positionFireball(world, x + 2.5, y + 10, z - 2.5, 0, -0.5, 0);
		EntityLargeFireball fireball4 = positionFireball(world, x - 2.5, y + 10, z - 2.5, 0, -0.5, 0);
		EntityLargeFireball fireball5 = positionFireball(world, x + 2.5, y + 10, z, 0, -0.5, 0);
		EntityLargeFireball fireball6 = positionFireball(world, x - 2.5, y + 10, z, 0, -0.5, 0);
		EntityLargeFireball fireball7 = positionFireball(world, x, y + 10, z + 2.5, 0, -0.5, 0);
		EntityLargeFireball fireball8 = positionFireball(world, x, y + 10, z - 2.5, 0, -0.5, 0);

		if(!world.isRemote){
			world.spawnEntityInWorld(fireball1);
			world.spawnEntityInWorld(fireball2);
			world.spawnEntityInWorld(fireball3);
			world.spawnEntityInWorld(fireball4);
			world.spawnEntityInWorld(fireball5);
			world.spawnEntityInWorld(fireball6);
			world.spawnEntityInWorld(fireball7);
			world.spawnEntityInWorld(fireball8);
		}
	}

	private EntityLargeFireball positionFireball(World world, double x, double y, double z, double accx, double accy, double accz){
		EntityLargeFireball fireball = new EntityLargeFireball(world);
		fireball.setLocationAndAngles(x, y, z, fireball.rotationYaw, fireball.rotationPitch);
		fireball.setPosition(x, y, z);
		double d6 = MathHelper.sqrt_double(accx * accx + accy * accy + accz * accz);
		fireball.accelerationX = accx / d6 * 0.1D;
		fireball.accelerationY = accy / d6 * 0.1D;
		fireball.accelerationZ = accz / d6 * 0.1D;

		return fireball;
	}
}
