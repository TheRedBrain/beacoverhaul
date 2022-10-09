package com.github.theredbrain.beacoverhaul.entity.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class ExtraLootingStatusEffect extends StatusEffect {
    public ExtraLootingStatusEffect() {
        super(StatusEffectCategory.BENEFICIAL, 3381504 /*TODO correct color*/);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
    }
}
