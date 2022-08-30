package com.ranafangirl.froggyfurniture.block;

import com.ranafangirl.froggyfurniture.entity.ChairEntity;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")
public class ChairBlock extends HorizontalBlock implements IWaterLoggable {
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
	protected static final VoxelShape VOX_TOP_BACKBOARD_SOUTH = Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 3.0);
	protected static final VoxelShape VOX_TOP_BACKBOARD_NORTH = Block.box(0.0, 0.0, 13.0, 16.0, 8.0, 16.0);
	protected static final VoxelShape VOX_TOP_BACKBOARD_WEST = Block.box(13.0, 0.0, 0.0, 16.0, 8.0, 16.0);
	protected static final VoxelShape VOX_TOP_BACKBOARD_EAST = Block.box(0.0, 0.0, 0.0, 3.0, 8.0, 16.0);
	protected static final VoxelShape TOP_BACKBOARD_SOUTH = Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 1.0);
	protected static final VoxelShape TOP_BACKBOARD_NORTH = Block.box(0.0, 0.0, 15.0, 16.0, 8.0, 16.0);
	protected static final VoxelShape TOP_BACKBOARD_WEST = Block.box(15.0, 0.0, 0.0, 16.0, 8.0, 16.0);;
	protected static final VoxelShape TOP_BACKBOARD_EAST = Block.box(0.0, 0.0, 0.0, 1.0, 8.0, 16.0);;
	protected static final VoxelShape VOX_BOTTOM_BACKBOARD_SOUTH = VoxelShapes.join(Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 3.0), Block.box(0.0, 0.0, 0.0, 16.0, 10.0, 16.0), IBooleanFunction.OR);
	protected static final VoxelShape VOX_BOTTOM_BACKBOARD_NORTH = VoxelShapes.join(Block.box(0.0, 0.0, 13.0, 16.0, 16.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 10.0, 16.0), IBooleanFunction.OR);
	protected static final VoxelShape VOX_BOTTOM_BACKBOARD_WEST = VoxelShapes.join(Block.box(13.0, 0.0, 0.0, 16.0, 16.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 10.0, 16.0), IBooleanFunction.OR);
	protected static final VoxelShape VOX_BOTTOM_BACKBOARD_EAST = VoxelShapes.join(Block.box(0.0, 0.0, 0.0, 3.0, 16.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 10.0, 16.0), IBooleanFunction.OR);
	protected static final VoxelShape BOTTOM_BACKBOARD_SOUTH = VoxelShapes.join(Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 1.0), Block.box(0.0, 0.0, 0.0, 16.0, 10.0, 16.0), IBooleanFunction.OR);
	protected static final VoxelShape BOTTOM_BACKBOARD_NORTH = VoxelShapes.join(Block.box(0.0, 0.0, 15.0, 16.0, 16.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 10.0, 16.0), IBooleanFunction.OR);
	protected static final VoxelShape BOTTOM_BACKBOARD_WEST = VoxelShapes.join(Block.box(15.0, 0.0, 0.0, 16.0, 16.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 10.0, 16.0), IBooleanFunction.OR);
	protected static final VoxelShape BOTTOM_BACKBOARD_EAST = VoxelShapes.join(Block.box(0.0, 0.0, 0.0, 1.0, 16.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 10.0, 16.0), IBooleanFunction.OR);
	float yOff;

	public ChairBlock(final AbstractBlock.Properties properties, final float yOff) {
		super(properties);
		this.yOff = yOff;
		this.registerDefaultState(((super.defaultBlockState().setValue(FACING, Direction.NORTH)).setValue(HALF, DoubleBlockHalf.LOWER)).setValue(WATERLOGGED, false));
	}

	public VoxelShape getShape(final BlockState state, final IBlockReader reader, final BlockPos pos, final ISelectionContext context) {
		final DoubleBlockHalf height = (DoubleBlockHalf)state.getValue(HALF);
		final Direction facing = (Direction)state.getValue(FACING);

		switch (height) {
		case UPPER:
			switch (facing) {
			case SOUTH:	return ChairBlock.VOX_TOP_BACKBOARD_SOUTH;
			case WEST:	return ChairBlock.VOX_TOP_BACKBOARD_WEST;
			default:	return ChairBlock.VOX_TOP_BACKBOARD_NORTH;
			case EAST:	return ChairBlock.VOX_TOP_BACKBOARD_EAST;
			}

		case LOWER: 
			switch (facing) {
			case SOUTH: return ChairBlock.VOX_BOTTOM_BACKBOARD_SOUTH;
			default:	return ChairBlock.VOX_BOTTOM_BACKBOARD_NORTH;
			case WEST:	return ChairBlock.VOX_BOTTOM_BACKBOARD_WEST;
			case EAST:	return ChairBlock.VOX_BOTTOM_BACKBOARD_EAST;
			}

		default: 
			return super.getShape(state, reader, pos, context);
		}
	}

	public VoxelShape getCollisionShape(final BlockState state, final IBlockReader reader, final BlockPos pos, final ISelectionContext context) {
		final DoubleBlockHalf height = (DoubleBlockHalf)state.getValue(ChairBlock.HALF);
		final Direction facing = (Direction)state.getValue(ChairBlock.FACING);

		switch (height) {
		case UPPER: 
			switch (facing) {
			case SOUTH:	return ChairBlock.TOP_BACKBOARD_SOUTH;
			case WEST:	return ChairBlock.TOP_BACKBOARD_WEST;
			default:	return ChairBlock.TOP_BACKBOARD_NORTH;
			case EAST:	return ChairBlock.TOP_BACKBOARD_EAST;
			}

		case LOWER:
			switch (facing) {
			case SOUTH:	return ChairBlock.BOTTOM_BACKBOARD_SOUTH;
			default:	return ChairBlock.BOTTOM_BACKBOARD_NORTH;
			case WEST:	return ChairBlock.BOTTOM_BACKBOARD_WEST;
			case EAST:	return ChairBlock.BOTTOM_BACKBOARD_EAST;
			}

		default: 
			return super.getCollisionShape(state, reader, pos, context);
		}
	}

	public ActionResultType use(final BlockState state, final World world, final BlockPos pos, final PlayerEntity player, final Hand hand, final BlockRayTraceResult hit) {
		if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
			ChairEntity.create(world, pos, 0.4, player);
		}
		return super.use(state, world, pos, player, hand, hit);
	}

	public void setPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		FluidState fluidstate = world.getFluidState(pos.above());
		world.setBlock(pos.above(), state.setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER)).setValue(HALF, DoubleBlockHalf.UPPER), 3);
	}

	public void playerWillDestroy(final World world, final BlockPos pos, final BlockState state, final PlayerEntity entity) {
		if (!world.isClientSide) {
			if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
				world.destroyBlock(pos.above(), false);
			}
			else {
				world.destroyBlock(pos.below(), false);
			}
		}
		super.playerWillDestroy(world, pos, state, entity);
	}

	public FluidState getFluidState(final BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	protected void createBlockStateDefinition(final StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING, HALF, WATERLOGGED);
		super.createBlockStateDefinition(builder);
}

public BlockState getStateForPlacement(final BlockItemUseContext context) {
	final Direction direction = context.getHorizontalDirection();
	final BlockPos blockpos = context.getClickedPos();
	final BlockPos blockpos2 = blockpos.relative(direction);
	return context.getLevel().getBlockState(blockpos2).canBeReplaced(context) ? ((this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite())).setValue(HALF, DoubleBlockHalf.LOWER)) : null;
}

public BlockState rotate(final BlockState state, final Rotation rot) {
	return (BlockState)state.setValue(FACING, rot.rotate(state.getValue(FACING)));
}

public BlockState mirror(final BlockState state, final Mirror mirrorIn) {
	return state.rotate(mirrorIn.getRotation(state.getValue(ChairBlock.FACING)));
}

public boolean useShapeForLightOcclusion(final BlockState state) {
	return true;
}

public PushReaction getPistonPushReaction(final BlockState state) {
	return PushReaction.BLOCK;
}
}