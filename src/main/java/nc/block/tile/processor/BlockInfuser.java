package nc.block.tile.processor;

import java.util.Random;

import nc.init.NCBlocks;
import nc.tile.processor.Processors;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockInfuser extends BlockProcessor {

	public BlockInfuser(String unlocalizedName, String registryName, boolean isActive, int guiId) {
		super(unlocalizedName, registryName, "portal", "reddust", null, isActive, guiId);
	}
	
	public TileEntity createNewTileEntity(World world, int meta) {
		return new Processors.TileInfuser();
	}
	
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(NCBlocks.infuser_idle);
	}
	
	public ItemStack getItem(World world, BlockPos pos, IBlockState state) {
		return new ItemStack(NCBlocks.infuser_idle);
	}

	public static void setState(boolean active, World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		TileEntity tile = world.getTileEntity(pos);
		keepInventory = true;
		
		if (active) {
			world.setBlockState(pos, NCBlocks.infuser_active.getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);
		} else {
			world.setBlockState(pos, NCBlocks.infuser_idle.getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);
		}
		keepInventory = false;
		
		if (tile != null) {
			tile.validate();
			world.setTileEntity(pos, tile);
		}
	}
}
