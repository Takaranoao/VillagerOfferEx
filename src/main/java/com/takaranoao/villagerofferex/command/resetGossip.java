package com.takaranoao.villagerofferex.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.CommandNode;
import com.takaranoao.villagerofferex.mixin.MixinGossipType;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.village.VillageGossipType;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.mojang.brigadier.arguments.IntegerArgumentType.integer;

public class resetGossip extends TakaCommand{
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
        dispatcher.register(CommandManager.literal("gossip")
                .then(CommandManager.literal("multiplier").then(CommandManager.argument("multiplier", integer())
                        .executes(resetGossip::executesMultiplier)))
                .then(CommandManager.literal("maxvalue").then(CommandManager.argument("maxvalue", integer())
                        .executes(resetGossip::executesMaxValue)))
        );
        dispatcher.register(CommandManager.literal("getgossip")
                .executes(context -> {
                    for (VillageGossipType v : VillageGossipType.values())
                        context.getSource().getPlayer().sendMessage(new LiteralText(_toStringVillageGossipType(v)));
                    return 0;
                })

        );
    }

    private static int executesMaxValue(CommandContext<ServerCommandSource> serverCommandSourceCommandContext) {
        int MaxValue = IntegerArgumentType.getInteger(serverCommandSourceCommandContext, "maxvalue");
        try {
            setMaxValue(MaxValue, VillageGossipType.MAJOR_NEGATIVE);
            setMaxValue(MaxValue * 2, VillageGossipType.MINOR_NEGATIVE);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static String _toStringVillageGossipType(VillageGossipType type) {
        StringBuffer stb = new StringBuffer();
        stb.append("key:");
        stb.append(type.key);
        stb.append(",");

        stb.append("decay:");
        stb.append(type.decay);
        stb.append(",");

        stb.append("maxValue:");
        stb.append(type.maxValue);
        stb.append(",");

        stb.append("maxValue:");
        stb.append(type.maxValue);
        stb.append(",");

        stb.append("shareDecrement:");
        stb.append(type.shareDecrement);

        //stb.append(System.lineSeparator());
        return stb.toString();
    }

    private static int executesMultiplier(CommandContext<ServerCommandSource> serverCommandSourceCommandContext) {
        int multiplier = IntegerArgumentType.getInteger(serverCommandSourceCommandContext, "multiplier");
        try {
            setMultiplier(multiplier * 5, VillageGossipType.MAJOR_NEGATIVE);
            setMultiplier(multiplier, VillageGossipType.MINOR_NEGATIVE);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static void setMultiplier(int newMultiplier, VillageGossipType type) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method invokeMethod = VillageGossipType.class.getMethod("setMultiplier", int.class);
        invokeMethod.invoke(type, newMultiplier);
    }

    private static void setMaxValue(int newMaxValue, VillageGossipType type) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method invokeMethod = VillageGossipType.class.getMethod("setMaxValue", int.class);
        invokeMethod.invoke(type, newMaxValue);
    }
//    // The root of the command. This must be a literal argument.
//dispatcher.register(CommandManager.literal("foo")
//// Then add an argument named bar that is an integer
//        .then(CommandManager.argument("bar", integer())
//            // The command to be executed if the command "foo" is entered with the argument "bar"
//            .executes(ctx -> {
//        System.out.println("Bar is " + IntArgumentType.getInteger(ctx, "bar"));
//        // Return a result. Typically -1 is failure, 0 is pass and 1 is success.
//        return 1;
//    }))
//            // The command "foo" to execute if there are no arguments.
//            .executes(ctx -> {
//        System.out.println("Called foo with no arguments");
//        return 1;
//    })
//            );
}
