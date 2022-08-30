package com.ranafangirl.froggyfurniture.block;

import java.util.Random;

import javax.annotation.Nullable;

import com.ranafangirl.froggyfurniture.tileentity.LotusCabinetTileEntity;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.piglin.PiglinTasks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

@SuppressWarnings("deprecation")
public class CabinetBlock extends ContainerBlock {
    public static final DirectionProperty FACING = HorizontalBlock.FACING;;
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
    
	public CabinetBlock(final AbstractBlock.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(CabinetBlock.OPEN, false));
    }
    
    @Nullable
    public TileEntity newBlockEntity(final IBlockReader reader) {
        return (TileEntity)new LotusCabinetTileEntity();
    }
    
    public ActionResultType use(final BlockState state, final World world, final BlockPos pos, final PlayerEntity entity, final Hand hand, final BlockRayTraceResult result) {
        if (world.isClientSide) {
            return ActionResultType.SUCCESS;
        }
        final TileEntity tileentity = world.getBlockEntity(pos);
        if (tileentity instanceof LotusCabinetTileEntity) {
            entity.openMenu((LotusCabinetTileEntity)tileentity);
            entity.awardStat(Stats.OPEN_CHEST);
            PiglinTasks.angerNearbyPiglins(entity, true);
        }
        return ActionResultType.CONSUME;
    }
    
	public void onRemove(final BlockState state1, final World world, final BlockPos pos, final BlockState state2, final boolean bool) {
        if (!state1.is(state2.getBlock())) {
            final TileEntity tileentity = world.getBlockEntity(pos);
            if (tileentity instanceof IInventory) {
                InventoryHelper.dropContents(world, pos, (IInventory)tileentity);
                world.updateNeighbourForOutputSignal(pos, this);
            }
            super.onRemove(state1, world, pos, state2, bool);
        }
    }
    
    public void tick(final BlockState state, final ServerWorld world, final BlockPos pos, final Random rand) {
        final TileEntity tileentity = world.getBlockEntity(pos);
        if (tileentity instanceof LotusCabinetTileEntity) {
            ((LotusCabinetTileEntity)tileentity).recheckOpen();
        }
    }
    
    public BlockState getStateForPlacement(final BlockItemUseContext context) {
        return this.defaultBlockState().setValue(CabinetBlock.FACING, context.getHorizontalDirection().getOpposite());
    }
    
    public void setPlacedBy(final World world, final BlockPos pos, final BlockState state, @Nullable final LivingEntity entity, final ItemStack stack) {
        if (stack.hasCustomHoverName()) {
            final TileEntity tileentity = world.getBlockEntity(pos);
            if (tileentity instanceof LotusCabinetTileEntity) {
                ((LotusCabinetTileEntity)tileentity).setCustomName(stack.getHoverName());
            }
        }
    }
    
    public BlockRenderType getRenderShape(final BlockState state) {
        return BlockRenderType.MODEL;
    }
    
    public boolean hasAnalogOutputSignal(final BlockState state) {
        return true;
    }
    
    public int getAnalogOutputSignal(final BlockState state, final World world, final BlockPos pos) {
        return Container.getRedstoneSignalFromBlockEntity(world.getBlockEntity(pos));
    }
    
    public BlockState rotate(final BlockState state, final Rotation rot) {
        return state.setValue(CabinetBlock.FACING, rot.rotate(state.getValue(CabinetBlock.FACING)));
    }
    
    public BlockState mirror(final BlockState state, final Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(CabinetBlock.FACING)));
    }
    
    protected void createBlockStateDefinition(final StateContainer.Builder<Block, BlockState> state) {
        state.add(FACING, OPEN);
    }
    
    public PushReaction getPistonPushReaction(final BlockState state) {
        return PushReaction.BLOCK;
    }
}