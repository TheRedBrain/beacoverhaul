package com.github.theredbrain.redBrainsTweaks.mixin.client.sound;

import net.minecraft.client.sound.MusicType;
import net.minecraft.sound.MusicSound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MusicType.class)
public class MusicTypeMixin {

    @Shadow
    @Final
    @Mutable
    public static MusicSound END = new MusicSound(SoundEvents.MUSIC_END, 100, 200, true);

    @Shadow
    @Final
    @Mutable
    public static MusicSound CREATIVE = new MusicSound(SoundEvents.MUSIC_CREATIVE, 100, 200, false);

    @Inject(method = "createIngameMusic", at = @At("HEAD"), cancellable = true)
    private static void createIngameMusic(SoundEvent event, CallbackInfoReturnable<MusicSound> cir) {
        cir.setReturnValue(new MusicSound(event, 100, 200, false));
    }
}
