package com.takaranoao.villagerofferex.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.village.TradeOffer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.lang.reflect.Field;

import static com.takaranoao.villagerofferex.command.offerex.canBuyItemOverFlow;
import static com.takaranoao.villagerofferex.command.offerex.itemStackFix;

@Mixin(TradeOffer.class)
public abstract class MixinTradeOffer {
    @Final @Shadow private ItemStack firstBuyItem;
    @Final @Shadow private ItemStack secondBuyItem;
    @Final @Shadow private ItemStack sellItem;
    @Shadow private int uses;
    @Shadow @Final private int maxUses;
    @Shadow private boolean rewardingPlayerExperience;
    @Shadow private int specialPrice;
    @Shadow private int demandBonus;
    @Shadow private float priceMultiplier;
    @Shadow private int traderExperience;

//Lnet/minecraft/village/TradeOffer;getAdjustedFirstBuyItem()Lnet/minecraft/item/ItemStack;
    /**
     * @author Takaranoao
     * @reason
     */

    @Overwrite
    public ItemStack getAdjustedFirstBuyItem() {
        int i = this.firstBuyItem.getCount();
        ItemStack itemStack = this.firstBuyItem.copy();
        int j = Math.max(0, MathHelper.floor((float) (i * this.demandBonus) * this.priceMultiplier));
        if(!canBuyItemOverFlow) {
            itemStack.setCount(MathHelper.clamp(i + j + this.specialPrice, 1, this.firstBuyItem.getItem().getMaxCount()));
        }else {

            if(itemStackFix){
                try {
                    Field unEmptyField = itemStack.getClass().getDeclaredField("unEmpty");
                    unEmptyField.setAccessible(true);
                    unEmptyField.set(itemStack,true);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            itemStack.setCount(i + j + this.specialPrice);
        }
        return itemStack;
    }
}
