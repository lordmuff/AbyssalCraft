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
package com.shinoow.abyssalcraft.common.blocks.tile;

import com.shinoow.abyssalcraft.common.blocks.BlockTieredEnergyCollector;

import net.minecraft.tileentity.TileEntity;

<<<<<<<< HEAD:src/main/java/com/shinoow/abyssalcraft/common/blocks/BlockGatekeeperMinionSpawner.java
import com.shinoow.abyssalcraft.common.blocks.tile.TileEntityGatekeeperMinionSpawner;

public class BlockGatekeeperMinionSpawner extends BlockContainer {

	public BlockGatekeeperMinionSpawner() {
		super(Material.rock);
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityGatekeeperMinionSpawner();
========
public class TileEntityTieredEnergyCollector extends TileEntityEnergyCollector {

	@Override
	public int getMaxEnergy() {
		int base = 1000;

		return (int) (base * (1.5 + 0.5 * ((BlockTieredEnergyCollector)getBlockType()).TYPE.getMeta()));
	}

	@Override
	public TileEntity getContainerTile() {

		return this;
>>>>>>>> 54a7175b70eb8c48882850eecae51e8de11510bf:src/main/java/com/shinoow/abyssalcraft/common/blocks/tile/TileEntityTieredEnergyCollector.java
	}
}