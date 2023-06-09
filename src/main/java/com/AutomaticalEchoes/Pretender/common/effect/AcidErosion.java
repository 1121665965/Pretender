package com.AutomaticalEchoes.Pretender.common.effect;

import com.AutomaticalEchoes.Pretender.api.IFluidFunction;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;


public class AcidErosion extends MobEffect {
    private static final int[] INT={0,1,2,3};
    public AcidErosion(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }

    @Override
    public void applyEffectTick(LivingEntity p_19467_, int p_19468_) {
        if(p_19467_ instanceof Player player){
            IFluidFunction.HurtArmor(player);
        }
    }

    @Override
    public boolean isDurationEffectTick(int p_19455_, int p_19456_) {
        return p_19455_ % 20 ==0;
    }
}
