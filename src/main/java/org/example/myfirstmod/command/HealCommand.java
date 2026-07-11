package org.example.myfirstmod.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;

public final class HealCommand {

    private HealCommand() {
    }

    public static void register(CommandDispatcher<CommandSource> dispatcher) {

        dispatcher.register(
                Commands.literal("heal")
                        .requires(source -> source.hasPermission(2))

                        .executes(context -> {

                            ServerPlayerEntity player = context.getSource().getPlayerOrException();

                            heal(player);

                            context.getSource().sendSuccess(
                                    new StringTextComponent("Вы полностью исцелены."),
                                    false
                            );

                            return 1;
                        })

                        .then(
                                Commands.argument("player", EntityArgument.player())

                                        .executes(context -> {

                                            ServerPlayerEntity target =
                                                    EntityArgument.getPlayer(context, "player");

                                            heal(target);

                                            context.getSource().sendSuccess(
                                                    new StringTextComponent(
                                                            "Игрок " + target.getName().getString() + " исцелен."
                                                    ),
                                                    true
                                            );

                                            target.sendMessage(
                                                    new StringTextComponent("Вы были исцелены."),
                                                    target.getUUID()
                                            );

                                            return 1;
                                        })
                        )
        );
    }

    private static void heal(ServerPlayerEntity player) {

        player.setHealth(player.getMaxHealth());

        player.getFoodData().setFoodLevel(20);
        player.getFoodData().setSaturation(20.0F);

        player.clearFire();
    }
}