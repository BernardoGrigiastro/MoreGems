package com.kwpugh.more_gems.mixin;

import com.kwpugh.more_gems.init.EnchantmentInit;
import com.kwpugh.more_gems.util.PlayerSpecialAbilities;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity
{
    @Shadow public abstract boolean giveItemStack(ItemStack stack);

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world)
    {
        super(entityType, world);
    }

    @Inject(method = "attack", at = @At(value = "HEAD"))
    private void attackQuickening(Entity target, CallbackInfo ci)
    {
        PlayerEntity self = ((PlayerEntity) (Object) this);
        int level = EnchantmentHelper.getLevel(EnchantmentInit.QUICKENING, self.getEquippedStack(EquipmentSlot.MAINHAND));

        if(level > 0)
        {
            PlayerSpecialAbilities.giveQuickening(world, self, target, level);
        }
    }

    @Inject(method = "damage", at = @At(value="HEAD"), cancellable = true)
    private void damageVoidEscape(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir)
    {
        PlayerEntity self = (PlayerEntity) (Object) this;
        int level = EnchantmentHelper.getLevel(EnchantmentInit.VOID_ESCAPE, self.getEquippedStack(EquipmentSlot.FEET));

        if(level > 0)
        {
            self.fallDistance = 0.0F;

            if(source.isOutOfWorld())
            {
                PlayerSpecialAbilities.giveVoidEscape(world, self);
                cir.setReturnValue(false);
            }
        }
    }

    // Used by Bound enchantment, works with ServerPlayerEntityMixinBound
    @Inject(method = "dropItem(Lnet/minecraft/item/ItemStack;ZZ)Lnet/minecraft/entity/ItemEntity;", at = @At("HEAD"), cancellable = true)
    private void moregemsDropItem(ItemStack stack, boolean throwRandomly, boolean retainOwnership, CallbackInfoReturnable<ItemEntity> cir)
    {
        if (!stack.isEmpty())
        {
            if(EnchantmentHelper.getLevel(EnchantmentInit.BOUND, stack) > 0)
            {
                this.giveItemStack(stack);
                cir.cancel();
            }
        }
    }
}