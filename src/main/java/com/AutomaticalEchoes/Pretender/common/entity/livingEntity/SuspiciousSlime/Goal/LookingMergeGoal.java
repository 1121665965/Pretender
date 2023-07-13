package com.AutomaticalEchoes.Pretender.common.entity.livingEntity.SuspiciousSlime.Goal;

import com.AutomaticalEchoes.Pretender.common.entity.livingEntity.SuspiciousSlime.SuspiciousSlime;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.phys.AABB;

import java.util.List;
import java.util.function.Predicate;

public class LookingMergeGoal extends Goal {
    private final SuspiciousSlime slime;
    private Entity mergeTarget;
    public LookingMergeGoal(SuspiciousSlime suspiciousSlime){
        this.slime = suspiciousSlime;
    }

    @Override
    public boolean canUse() {
        if(slime.level instanceof ServerLevel serverLevel &&  slime.isMergable()){
            List<Entity> entities = serverLevel.getEntities(slime, new AABB(slime.getX() - 10, slime.getY() - 3, slime.getZ() - 10, slime.getX() + 10, slime.getY() + 3, slime.getZ() + 10), new Predicate<Entity>() {
                @Override
                public boolean test(Entity entity) {
                    return entity instanceof Slime;
                }
            });
            if(entities.isEmpty()) return false;
            this.mergeTarget = entities.get(0);
            return true;
        }
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        return mergeTarget.isAlive() && !slime.hasLineOfSight(mergeTarget);
    }

    @Override
    public void start() {

    }

    @Override
    public void tick() {
        if (mergeTarget != null && mergeTarget instanceof Slime slime1 && mergeTarget.isAlive()) {
            if(slime.distanceTo(mergeTarget) < 1) {
                slime.tryToMerge(slime1);
                return;
            }
            this.slime.lookAt(mergeTarget, 10.0F, 10.0F);
            ((IMoveControl)this.slime.getMoveControl()).setDirection(this.slime.getYRot(), true,false);
        }else {
            stop();
        }
    }
}