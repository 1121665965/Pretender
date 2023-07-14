package com.AutomaticalEchoes.Pretender.register;

import com.AutomaticalEchoes.Pretender.Pretender;
import com.AutomaticalEchoes.Pretender.api.ICauldronInteraction;
import com.AutomaticalEchoes.Pretender.api.IFunction;
import com.AutomaticalEchoes.Pretender.common.block.ILayeredCauldronBlock;
import com.AutomaticalEchoes.Pretender.common.block.NonNewtonianFluidBlock;
import com.AutomaticalEchoes.Pretender.common.entity.blockEntity.SusSlimeBase;
import com.mojang.datafixers.types.Type;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class BlockRegister {
    public static final DeferredRegister<Block> DEFERRED_REGISTER =DeferredRegister.create(ForgeRegistries.BLOCKS , Pretender.MOD_ID);
    public static final RegistryObject<LiquidBlock> ACIDITY = DEFERRED_REGISTER.register("acidity",() -> new LiquidBlock(FluidRegister.ACIDITY, BlockBehaviour.Properties.of(Material.WATER).lightLevel(value -> 4).noCollission().strength(100.0F).noLootTable()));
    public static final RegistryObject<NonNewtonianFluidBlock> SUSPICIOUS_WATER = DEFERRED_REGISTER.register("suspicious_water",() -> new NonNewtonianFluidBlock(BlockBehaviour.Properties.of(Material.WATER).lightLevel(value -> 9).noCollission().strength(100.0F).noLootTable()));
    public static final RegistryObject<NonNewtonianFluidBlock> SUSPICIOUS_SLIME_BLOCK = DEFERRED_REGISTER.register("suspicious_slime_block",
            () -> new NonNewtonianFluidBlock(BlockBehaviour
                    .Properties.of(Material.CLAY, MaterialColor.GRASS)
                    .sound(SoundType.SLIME_BLOCK)
                    .isViewBlocking(BlockRegister::never)
                    .noOcclusion()).Fluid(FluidRegister.MUCUS).BucketPickupItem(ItemsRegister.MUCUS_BUCKET).CustomCustomCollisionShape(IFunction.BlockFunction::EmptyWithSlime));

    public static final RegistryObject<LayeredCauldronBlock> ACIDITY_CAULDRON_BLOCK = DEFERRED_REGISTER.register("acidity_cauldron_block",() ->  new ILayeredCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON), LayeredCauldronBlock.RAIN, ICauldronInteraction.ACIDITY).FluidFunction(IFunction.FluidFunction::HurtArmor));
    public static final RegistryObject<LayeredCauldronBlock> MUCUS_CAULDRON_BLOCK = DEFERRED_REGISTER.register("mucus_cauldron_block",() ->  new LayeredCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON), LayeredCauldronBlock.RAIN, ICauldronInteraction.MUCUS));
    public static final RegistryObject<LayeredCauldronBlock> SUS_WATER_CAULDRON_BLOCK = DEFERRED_REGISTER.register("sus_water_cauldron_block",() ->  new LayeredCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON), LayeredCauldronBlock.RAIN, ICauldronInteraction.SUS_WATER));

    private static boolean never(BlockState p_50806_, BlockGetter p_50807_, BlockPos p_50808_) {
        return false;
    }

    public class BlockEntityRegister{
        public static final DeferredRegister<BlockEntityType<?>> DEFERRED_REGISTER =DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES , Pretender.MOD_ID);
        public static final RegistryObject<BlockEntityType<SusSlimeBase>> SUS_SLIME_BASE = DEFERRED_REGISTER.register("sus_slime_base", () -> {
            return BlockEntityType.Builder.of(SusSlimeBase::Create, SUSPICIOUS_SLIME_BLOCK.get()).build(null);
        });
    }

}
