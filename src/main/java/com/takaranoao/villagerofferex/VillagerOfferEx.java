package com.takaranoao.villagerofferex;

import com.takaranoao.villagerofferex.command.offerex;
import com.takaranoao.villagerofferex.command.resetGossip;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;

public class VillagerOfferEx implements ModInitializer {
    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register(resetGossip::register);
        CommandRegistrationCallback.EVENT.register(offerex::register);

    }
}
