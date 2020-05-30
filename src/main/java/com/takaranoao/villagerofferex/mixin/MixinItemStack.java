package com.takaranoao.villagerofferex.mixin;

import net.minecraft.item.ItemStack;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Mixin(ItemStack.class)
public abstract class MixinItemStack {
    public boolean unEmpty = false;
//    @Shadow
//    public static ItemStack EMPTY;
//    @Shadow
//    private int count;
//    /**
//     * @author Takaranoao
//     * @reason
//     */
//    @Overwrite
//    public boolean isEmpty() {
//        if ((Object)this == EMPTY) {
//            return true;
//        } else if (this.getItem() != null && this.getItem() != Items.AIR) {
//            if(this.unEmpty) {
//                return false;
//            }
//            return this.count <= 0;
//        } else {
//            return true;
//        }
//    }
//    @Shadow
//    public abstract Item getItem();
    @Inject(method = "isEmpty", at = @At(value = "FIELD", target = "Lnet/minecraft/item/ItemStack;count:I", opcode = Opcodes.GETFIELD), cancellable = true)
    public void afterIsEmpty(CallbackInfoReturnable<Boolean> cir) {
        if (this.unEmpty) {
            cir.setReturnValue(false);
        }
    }
}
