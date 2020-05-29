package com.takaranoao.villagerofferex.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class offerex extends TakaCommand {
    public static boolean canBuyItemOverFlow = false;
    public static boolean itemStackFix = false;

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated){
        dispatcher.register(CommandManager.literal("offerex")
                .then(
                        CommandManager.literal("buyItemOverFlow")
                                .then(
                                        CommandManager.argument("bool", BoolArgumentType.bool())
                                                .executes(offerex::executesBuyItemOverFlow)
                                )
                )
                .then(
                        CommandManager.literal("itemStackFix")
                                .then(
                                        CommandManager.argument("bool", BoolArgumentType.bool())
                                                .executes(offerex::executesItemStackFix)
                                )
                )
        );
    }

    private static int executesItemStackFix(CommandContext<ServerCommandSource> serverCommandSourceCommandContext) {
        itemStackFix = BoolArgumentType.getBool(serverCommandSourceCommandContext,"bool");
        return 0;
    }

    private static int executesBuyItemOverFlow(CommandContext<ServerCommandSource> serverCommandSourceCommandContext) {
        canBuyItemOverFlow = BoolArgumentType.getBool(serverCommandSourceCommandContext,"bool");
        return 0;
    }
}
