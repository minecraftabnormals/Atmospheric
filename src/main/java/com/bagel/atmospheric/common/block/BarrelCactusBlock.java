package com.bagel.atmospheric.common.block;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.pathfinding.PathType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class BarrelCactusBlock extends Block implements net.minecraftforge.common.IPlantable, IGrowable {
   public static final IntegerProperty AGE = BlockStateProperties.AGE_0_3;
   
   private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
		   Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 4.0D, 10.0D), 
		   Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D), 
		   Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D), 
		   Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)};
   
   private static final VoxelShape[] COLLISION_BY_AGE = new VoxelShape[]{
		   Block.makeCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 3.0D, 9.0D), 
		   Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 7.0D, 11.0D), 
		   Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 11.0D, 13.0D), 
		   Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D)};

   public BarrelCactusBlock(Block.Properties properties) {
      super(properties);
      this.setDefaultState(this.getDefaultState().with(AGE, Integer.valueOf(0)));
   }

   public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
	   if (!worldIn.isAreaLoaded(pos, 1)) return; // Forge: prevent growing cactus from loading unloaded chunks with block update
	   if (!state.isValidPosition(worldIn, pos)) {
          worldIn.destroyBlock(pos, true);
       } else {
    	   int j = state.get(AGE);
    	   if(j < 3 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, true)) {
    		 	  worldIn.setBlockState(pos, state.with(AGE, Integer.valueOf(j + 1)));
    	   } 
    	   net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
       }
   }
   
   @Override
   public void grow(World worldIn, Random rand, BlockPos pos, BlockState state) {
	   int i = state.get(AGE);
	   if (i < 3 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt(3) == 0)) {
		   worldIn.setBlockState(pos, state.with(AGE, Integer.valueOf(i + 1)));
		   net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);	
	   }	
	}

   public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
      return COLLISION_BY_AGE[state.get(AGE)];
   }

   public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
      return SHAPE_BY_AGE[state.get(AGE)];
   }

   public boolean isSolid(BlockState state) {
      return true;
   }

   @SuppressWarnings("deprecation")
   public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
      if (!stateIn.isValidPosition(worldIn, currentPos)) {
         worldIn.getPendingBlockTicks().scheduleTick(currentPos, this, 1);
      }

      return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
   }

   public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
      BlockState soil = worldIn.getBlockState(pos.down());
      return soil.canSustainPlant(worldIn, pos.down(), Direction.UP, this) && !worldIn.getBlockState(pos.up()).getMaterial().isLiquid();
   }

   public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
      if (entityIn.isLiving()) {
    	  LivingEntity living = (LivingEntity) entityIn;
    	  living.addPotionEffect(new EffectInstance(Effects.WEAKNESS, ((state.get(AGE) + 1) * 60)));
      }
      entityIn.attackEntityFrom(DamageSource.CACTUS, 0.5F * state.get(AGE));
   }

   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.CUTOUT;
   }

   protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
      builder.add(AGE);
   }

   public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
      return false;
   }

   @Override
   public net.minecraftforge.common.PlantType getPlantType(IBlockReader world, BlockPos pos) {
      return net.minecraftforge.common.PlantType.Desert;
   }

   @Override
   public BlockState getPlant(IBlockReader world, BlockPos pos) {
      return getDefaultState();
   }
   
   @Nullable
   @Override
   public PathNodeType getAiPathNodeType(BlockState state, IBlockReader world, BlockPos pos, @Nullable MobEntity entity) {
       return  PathNodeType.DAMAGE_CACTUS;
   }
   
   public boolean canGrow(IBlockReader world, BlockPos pos, BlockState state, boolean isClient) {
		return true;
	}

	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
		return true;
	}
}
