package com.AutomaticalEchoes.Pretender.common.command;

import com.AutomaticalEchoes.Pretender.common.CommonModEvents;
import com.AutomaticalEchoes.Pretender.common.entity.livingEntity.SuspiciousEnderman.SuspiciousEnderman;
import com.AutomaticalEchoes.Pretender.common.netWork.packet.EnderJokeTask;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class EnderJoke {
    private static int AngryJoke(CommandSourceStack context ,Entity joker, @Nullable Entity target, @Nullable Integer structuresId,boolean isAngry) throws CommandSyntaxException {
        CommonModEvents.CHANNEL.sendToServer(new EnderJokeTask(joker.getId(),target!=null? target.getId() : null,structuresId,isAngry));
        return 0;
    }

    public static void register(CommandDispatcher<CommandSourceStack> p_137808_) {
        p_137808_.register(Commands.literal("joke").requires((p_137812_) -> p_137812_.hasPermission(2))
                .then(Commands.argument("joker", EntityArgument.entities()).executes((p_137810_) -> AngryJoke(p_137810_.getSource(), EntityArgument.getEntity(p_137810_, "joker"),null,null,false))
                        .then(Commands.argument("target", EntityArgument.entities()).executes((p_137810_) -> AngryJoke(p_137810_.getSource(), EntityArgument.getEntity(p_137810_, "joker"),EntityArgument.getEntity(p_137810_,"target"),null,false))
                                .then(Commands.argument("structureid", IntegerArgumentType.integer(0, SuspiciousEnderman.STRUCTURES.length)).executes((p_137810_) -> AngryJoke(p_137810_.getSource(), EntityArgument.getEntity(p_137810_, "joker"),EntityArgument.getEntity(p_137810_,"target"),IntegerArgumentType.getInteger(p_137810_,"structureid"),true)))
                                .then(Commands.argument("isangry", BoolArgumentType.bool()).executes((p_137810_) -> AngryJoke(p_137810_.getSource(), EntityArgument.getEntity(p_137810_, "joker"),EntityArgument.getEntity(p_137810_,"target"),null,BoolArgumentType.getBool(p_137810_,"isangry")))))));
    }
}
