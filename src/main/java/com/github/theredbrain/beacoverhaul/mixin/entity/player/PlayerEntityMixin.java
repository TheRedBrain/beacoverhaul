package com.github.theredbrain.beacoverhaul.mixin.entity.player;

import com.github.theredbrain.beacoverhaul.block.entity.RespawnAnchorBlockMixinDuck;
import net.minecraft.block.BedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RespawnAnchorBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject(method = "findRespawnPosition", at = @At("HEAD"), cancellable = true)
    private static void checkIfRespawnAnchorHasPyramid(ServerWorld world, BlockPos pos, float angle, boolean forced, boolean alive, CallbackInfoReturnable<Optional<Vec3d>> cir) {
        BlockState blockState = world.getBlockState(pos);
        Block block = blockState.getBlock();
        if (block instanceof RespawnAnchorBlock && (Integer)blockState.get(RespawnAnchorBlock.CHARGES) > 0 /*&& RespawnAnchorBlock.isNether(world) */&& ((RespawnAnchorBlockMixinDuck)block).isStillValid(world, pos)) {
            Optional<Vec3d> optional = RespawnAnchorBlock.findRespawnPosition(EntityType.PLAYER, world, pos);
            if (!alive && optional.isPresent()) {
                world.setBlockState(pos, (BlockState)blockState.with(RespawnAnchorBlock.CHARGES, (Integer)blockState.get(RespawnAnchorBlock.CHARGES) - 1), 3);
            }

            cir.setReturnValue(optional);
        } else if (block instanceof BedBlock && BedBlock.isBedWorking(world)) {
            cir.setReturnValue(BedBlock.findWakeUpPosition(EntityType.PLAYER, world, pos, angle));
        } else if (!forced) {
            cir.setReturnValue(Optional.empty());
        } else {
            boolean bl = block.canMobSpawnInside();
            boolean bl2 = world.getBlockState(pos.up()).getBlock().canMobSpawnInside();
            cir.setReturnValue(bl && bl2 ? Optional.of(new Vec3d((double)pos.getX() + 0.5D, (double)pos.getY() + 0.1D, (double)pos.getZ() + 0.5D)) : Optional.empty());
        }
    }
}
