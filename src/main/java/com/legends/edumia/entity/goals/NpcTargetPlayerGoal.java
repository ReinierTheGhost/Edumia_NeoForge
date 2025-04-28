package com.legends.edumia.entity.goals;

import com.legends.edumia.entity.NpcEntity;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;

public class NpcTargetPlayerGoal  extends NearestAttackableTargetGoal<Player> {
    NpcEntity mob;

    public NpcTargetPlayerGoal(NpcEntity mob) {
        super(mob, Player.class, true);
        this.mob = mob;
    }

    @Override
    public boolean canUse() {
        return super.canUse() && canContinue();
    }

    @Override
    public boolean canContinueToUse() {
        return super.canContinueToUse() && canContinue();
    }

    private boolean canContinue(){
        if(this.mob.getTarget() != null && this.mob.getTarget().isAlive()){
            if(this.mob.getTarget() instanceof Player player){
                if(player.isCreative() || player.distanceTo(this.mob) > 50)
                    return false;
                return true;
            }
        }
        return false;
    }
}
