package com.ranafangirl.froggyfurniture.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.material.PushReaction;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

@SuppressWarnings("deprecation")
public class TableBlock extends HorizontalBlock implements IWaterLoggable {
    public static final DirectionProperty FACING = HorizontalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    protected static final VoxelShape SHAPE = VoxelShapes.or(Block.box(0.0, 13.0, 0.0, 16.0, 16.0, 16.0), new VoxelShape[] {Block.box(7.0, 0.0, 7.0, 10.0, 13.0, 10.0), Block.box(0.0, 0.0, 0.0, 16.0, 0.1, 16.0) });
    
    public TableBlock(final AbstractBlock.Properties properties) {
        super(properties);
        this.registerDefaultState((this.stateDefinition.any().setValue(FACING, Direction.NORTH)).setValue(WATERLOGGED, false));
    }
    
    public VoxelShape getShape(final BlockState state, final IBlockReader reader, final BlockPos pos, final ISelectionContext context) {
        return TableBlock.SHAPE;
    }
    
    public VoxelShape getCollisionShape(final BlockState state, final IBlockReader reader, final BlockPos pos, final ISelectionContext context) {
        return TableBlock.SHAPE;
    }
    
    public BlockState getStateForPlacement(final BlockItemUseContext context) {
        return (BlockState)this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
    
    protected void createBlockStateDefinition(final StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
        super.createBlockStateDefinition(builder);
    }
    
	public FluidState getFluidState(final BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }
    
    public boolean useShapeForLightOcclusion(final BlockState state) {
        return true;
    }
    
    public PushReaction getPistonPushReaction(final BlockState state) {
        return PushReaction.BLOCK;
    }
}
