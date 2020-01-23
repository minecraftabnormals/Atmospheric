package com.bagel.rosewood.core.registry;

import com.bagel.rosewood.common.blocks.LeafCarpetBlock;
import com.bagel.rosewood.common.blocks.MonkeyBrushBlock;
import com.bagel.rosewood.common.blocks.PassionVineBlock;
import com.bagel.rosewood.common.blocks.RosewoodSaplingBlock;
import com.bagel.rosewood.common.blocks.VerticalSlabBlock;
import com.bagel.rosewood.common.world.gen.trees.RosewoodTree;
import com.bagel.rosewood.core.Rosewood;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.LadderBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.LogBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.TrapDoorBlock;
import net.minecraft.block.WoodButtonBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings("deprecation")
@Mod.EventBusSubscriber(modid = Rosewood.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RosewoodBlocks {
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Rosewood.MODID);
	
	//blocks
	public static final RegistryObject<Block> ROSEWOOD_PLANKS 			= RegistryUtils.createBlock("rosewood_planks", 			() -> new Block(Block.Properties.from(Blocks.OAK_PLANKS)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROSEWOOD_SAPLING 			= RegistryUtils.createBlock("rosewood_sapling", 		() -> new RosewoodSaplingBlock(new RosewoodTree(), Block.Properties.from(Blocks.ACACIA_SAPLING)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> ROSEWOOD_LOG 				= RegistryUtils.createBlock("rosewood_log", 			() -> new LogBlock(null, Block.Properties.from(Blocks.OAK_LOG)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> STRIPPED_ROSEWOOD_LOG 	= RegistryUtils.createBlock("stripped_rosewood_log", 	() -> new LogBlock(null, Block.Properties.from(Blocks.STRIPPED_OAK_LOG)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROSEWOOD 					= RegistryUtils.createBlock("rosewood", 				() -> new RotatedPillarBlock(Block.Properties.from(Blocks.OAK_WOOD)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> STRIPPED_ROSEWOOD 		= RegistryUtils.createBlock("stripped_rosewood", 		() -> new RotatedPillarBlock(Block.Properties.from(Blocks.STRIPPED_OAK_WOOD)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROSEWOOD_LEAVES 			= RegistryUtils.createBlock("rosewood_leaves", 			() -> new LeavesBlock(Block.Properties.from(Blocks.ACACIA_LEAVES)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> ROSEWOOD_SLAB 			= RegistryUtils.createBlock("rosewood_slab", 			() -> new SlabBlock(Block.Properties.from(Blocks.OAK_SLAB)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROSEWOOD_STAIRS 			= RegistryUtils.createBlock("rosewood_stairs", 			() -> new StairsBlock(ROSEWOOD_PLANKS.get().getDefaultState(), Block.Properties.from(Blocks.OAK_STAIRS)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROSEWOOD_PRESSURE_PLATE 	= RegistryUtils.createBlock("rosewood_pressure_plate", 	() -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(Blocks.DARK_OAK_PRESSURE_PLATE)), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> ROSEWOOD_FENCE 			= RegistryUtils.createBlock("rosewood_fence", 			() -> new FenceBlock(Block.Properties.from(Blocks.OAK_FENCE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> ROSEWOOD_TRAPDOOR 		= RegistryUtils.createBlock("rosewood_trapdoor", 		() -> new TrapDoorBlock(Block.Properties.from(Blocks.OAK_TRAPDOOR)), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> ROSEWOOD_FENCE_GATE 		= RegistryUtils.createBlock("rosewood_fence_gate", 		() -> new FenceGateBlock(Block.Properties.from(Blocks.OAK_FENCE_GATE)), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> ROSEWOOD_BUTTON 			= RegistryUtils.createBlock("rosewood_button", 			() -> new WoodButtonBlock(Block.Properties.from(Blocks.OAK_BUTTON)), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> ROSEWOOD_DOOR 			= RegistryUtils.createBlock("rosewood_door", 			() -> new DoorBlock(Block.Properties.from(Blocks.OAK_DOOR)), ItemGroup.REDSTONE);
	
	public static final RegistryObject<Block> PASSION_VINE 				= RegistryUtils.createBlock("passion_vine", 			() -> new PassionVineBlock(Block.Properties.from(Blocks.VINE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PASSION_VINE_BLOCK		= RegistryUtils.createBlock("passion_vine_block", 		() -> new Block(Block.Properties.from(Blocks.DRIED_KELP_BLOCK)), ItemGroup.DECORATIONS);

	public static final RegistryObject<Block> MONKEY_BRUSH 				= RegistryUtils.createBlock("monkey_brush", 			() -> new MonkeyBrushBlock(Effects.ABSORPTION, 6, Block.Properties.from(Blocks.ALLIUM)), ItemGroup.DECORATIONS);
	
	public static final RegistryObject<Block> POTTED_MONKEY_BRUSH		= RegistryUtils.createBlockNoItem("potted_monkey_brush",		() -> new FlowerPotBlock(MONKEY_BRUSH.get(), Block.Properties.from(Blocks.POTTED_ALLIUM)));
	public static final RegistryObject<Block> POTTED_ROSEWOOD_SAPLING	= RegistryUtils.createBlockNoItem("potted_rosewood_sapling",	() -> new FlowerPotBlock(ROSEWOOD_SAPLING.get(), Block.Properties.from(Blocks.POTTED_ALLIUM)));

	//quark compatability
	public static final RegistryObject<Block> VERTICAL_ROSEWOOD_PLANKS 	= RegistryUtils.createBlock("vertical_rosewood_planks", () -> new Block(Block.Properties.from(Blocks.OAK_PLANKS)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROSEWOOD_VERTICAL_SLAB 	= RegistryUtils.createBlock("rosewood_vertical_slab", 	() -> new VerticalSlabBlock(Block.Properties.from(Blocks.OAK_PLANKS)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROSEWOOD_BOOKSHELF 		= RegistryUtils.createBlock("rosewood_bookshelf", 		() -> new Block(Block.Properties.from(Blocks.BOOKSHELF)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROSEWOOD_LADDER 			= RegistryUtils.createBlock("rosewood_ladder", 			() -> new LadderBlock(Block.Properties.from(Blocks.LADDER)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> ROSEWOOD_LEAF_CARPET 		= RegistryUtils.createBlock("rosewood_leaf_carpet", 	() -> new LeafCarpetBlock(Block.Properties.from(ROSEWOOD_LEAVES.get())), ItemGroup.DECORATIONS);
	
	public static final RegistryObject<Block> PASSIONFRUIT_CRATE				= RegistryUtils.createBlock("passionfruit_crate", 				() -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.PURPLE).hardnessAndResistance(1.5F).sound(SoundType.WOOD)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> SHIMMERING_PASSIONFRUIT_CRATE		= RegistryUtils.createBlock("shimmering_passionfruit_crate", 	() -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.GOLD).lightValue(7).hardnessAndResistance(1.5F).sound(SoundType.WOOD)), ItemGroup.DECORATIONS);
}