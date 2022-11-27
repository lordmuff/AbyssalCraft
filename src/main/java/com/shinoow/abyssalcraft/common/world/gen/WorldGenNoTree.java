/*******************************************************************************
 * AbyssalCraft
 * Copyright (c) 2012 - 2022 Shinoow.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 * 
 * Contributors:
 *     Shinoow -  implementation
 ******************************************************************************/
package com.shinoow.abyssalcraft.common.world.gen;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTrees;

public class WorldGenNoTree extends WorldGenTrees {

	public WorldGenNoTree() {
		super(false);
	}

	@Override
<<<<<<< HEAD:src/main/java/com/shinoow/abyssalcraft/common/world/TeleporterDarkRealm.java
	public void placeInPortal(Entity entity, double par2, double par4, double par6, float par8){
		entity.setPosition(par2, 80, par6);
		entity.motionX = entity.motionY = entity.motionZ = 0;
=======
	public boolean generate(World world, Random rand, BlockPos blockPos) {
		return false;
>>>>>>> 54a7175b70eb8c48882850eecae51e8de11510bf:src/main/java/com/shinoow/abyssalcraft/common/world/gen/WorldGenNoTree.java
	}
}
