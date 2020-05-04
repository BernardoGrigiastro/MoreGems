package com.kwpugh.more_gems.items.juju;

import java.util.List;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public class ItemTourmalineJuju extends Item
{	
	public ItemTourmalineJuju(Settings settings)
	{
		super(settings);
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected)
	{
		StatusEffectInstance effect = new StatusEffectInstance(StatusEffects.NIGHT_VISION, 8, 0, false, false);
		LivingEntity player = (LivingEntity) entity;
		{
			player.addStatusEffect(effect);
		}
	}
	 
	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
		tooltip.add(new TranslatableText("item.more_gems.tourmaline_juju.tip1").formatted(Formatting.GREEN));
	} 
}
