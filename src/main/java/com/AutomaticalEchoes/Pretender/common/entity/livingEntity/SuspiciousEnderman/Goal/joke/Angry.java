package com.AutomaticalEchoes.Pretender.common.entity.livingEntity.SuspiciousEnderman.Goal.joke;

import com.AutomaticalEchoes.Pretender.Pretender;
import com.AutomaticalEchoes.Pretender.api.Joke;
import com.AutomaticalEchoes.Pretender.api.JokeCase;
import com.AutomaticalEchoes.Pretender.common.entity.livingEntity.SuspiciousEnderman.SuspiciousEnderman;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class Angry extends Joke<Player> {
    private @Nullable BlockPos.MutableBlockPos angryJokePos = null;
    private @Nullable Integer SelectedStructuresID = null;

    public Angry(SuspiciousEnderman suspiciousEnderman) {
        super(suspiciousEnderman);
    }

    @Override
    public Case Case() {
        return Case.EMPTY;
    }

    @Override
    public boolean canJoke() {
        return false;
    }

    @Override
    public void doJoke(Player target) {
        if(this.suspiciousEnderman.isAngry() && this.suspiciousEnderman.level instanceof ServerLevel serverLevel){
            if(SelectedStructuresID ==null){
                int i = Pretender.RANDOM.nextInt(SuspiciousEnderman.STRUCTURES.length + 1);
                angryJokePos = i!=0? suspiciousEnderman.PreparePos(serverLevel,i) : null;
            }else {
                angryJokePos = suspiciousEnderman.PreparePos(serverLevel,SelectedStructuresID);
            }
        }
        angryJoke(target,angryJokePos);
    }

    @Override
    public @Nullable Predicate<LivingEntity> TargetSelector() {
        return null;
    }

    public boolean angryJoke(Player player,@Nullable BlockPos.MutableBlockPos blockpos$mutableblockpos){
        if(!suspiciousEnderman.isAngry()) return false;
        return blockpos$mutableblockpos != null? angryJokeStructures(player,blockpos$mutableblockpos) : angryJokeDimension(player);
    }

    public boolean angryJokeDimension(Player player){
        if(suspiciousEnderman.level instanceof ServerLevel serverLevel){
            ServerLevel level = serverLevel.getServer().getLevel(Level.END);
            if(level !=null){
                suspiciousEnderman.doHurtTarget(player);
                player.changeDimension(level);
                return true;
            }
        }
        return false;
    }

    public boolean angryJokeStructures(Player player, BlockPos.MutableBlockPos blockpos$mutableblockpos){
        if(suspiciousEnderman.level instanceof ServerLevel serverLevel ){
            suspiciousEnderman.doHurtTarget(player);
            player.teleportTo(blockpos$mutableblockpos.getX(),blockpos$mutableblockpos.getY(),blockpos$mutableblockpos.getZ());
            return true;
        }
        return false;
    }
}