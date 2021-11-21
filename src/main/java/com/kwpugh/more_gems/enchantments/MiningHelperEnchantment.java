package com.kwpugh.more_gems.enchantments;

import com.kwpugh.more_gems.items.areatools.ModHammer;
import com.kwpugh.more_gems.items.baseclasses.ModPaxel;
import com.kwpugh.more_gems.items.baseclasses.ModPickaxe;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;

public class MiningHelperEnchantment extends Enchantment
{
	public MiningHelperEnchantment(Rarity weight, EnchantmentTarget enchantmentTarget, EquipmentSlot[] slotTypes)
	{
		super(weight, enchantmentTarget, slotTypes);
	}

	@Override
	public int getMinPower(int level)
	{
		return 10 * (level - 1);
	}

	@Override
	public int getMaxPower(int level)
	{
		return super.getMinPower(level) + 50;
	}

	@Override
	public int getMaxLevel()
	{
	    return 1;
	}

	@Override
	public boolean isAcceptableItem(ItemStack stack)
	{
		Item tool = stack.getItem();

		if(tool instanceof PickaxeItem ||  tool instanceof ModPaxel || tool instanceof ModPickaxe || tool instanceof ModHammer)
		{
			return this.type.isAcceptableItem(stack.getItem());
		}
		else
		{
			return false;
		}
	}
}
