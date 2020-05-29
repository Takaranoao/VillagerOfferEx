package com.takaranoao.villagerofferex.mixin;
import net.minecraft.village.VillageGossipType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
@Mixin(VillageGossipType.class)
public abstract class MixinGossipType{;
    @Shadow
    public int multiplier;
    @Shadow
    public int maxValue;
    @Shadow
    public int decay;
    @Shadow
    public int shareDecrement;
    @Shadow
    public String key;

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public void setDecay(int decay) {
        this.decay = decay;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public void setShareDecrement(int shareDecrement) {
        this.shareDecrement = shareDecrement;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
