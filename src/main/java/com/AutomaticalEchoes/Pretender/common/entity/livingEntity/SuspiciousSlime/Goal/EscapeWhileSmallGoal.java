package com.AutomaticalEchoes.Pretender.common.entity.livingEntity.SuspiciousSlime.Goal;

import com.AutomaticalEchoes.Pretender.Pretender;
import com.AutomaticalEchoes.Pretender.common.entity.livingEntity.SuspiciousSlime.SuspiciousSlime;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;


public class EscapeWhileSmallGoal extends Goal{
    private final SuspiciousSlime slime;

    public EscapeWhileSmallGoal(SuspiciousSlime p_33660_) {
        this.slime = p_33660_;
    }

    public boolean canUse() {
        return this.slime.getSize()<4
                && this.slime.getTarget() instanceof Player
                && this.slime.getTarget().hasLineOfSight(slime)
                && !this.slime.isPassenger();
    }

    @Override
    public void start() {
        this.slime.getNavigation().stop();
    }

    public void tick() {
        float rot=this.slime.getTarget().getYRot();
        if(((IMoveControl)slime.getMoveControl()).getJumpDelay() <= 0)
        ((IMoveControl)slime.getMoveControl()).setDirection(Pretender.RANDOM.nextFloat(rot-60,rot+60),false,true);
    }
}
