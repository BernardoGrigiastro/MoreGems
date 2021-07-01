package com.kwpugh.more_gems;

import com.kwpugh.more_gems.config.MoreGemsConfig;
import com.kwpugh.more_gems.init.BlockInit;
import com.kwpugh.more_gems.init.ContainerInit;
import com.kwpugh.more_gems.init.ItemInit;
import com.kwpugh.more_gems.init.EnchantmentInnit;
import com.kwpugh.more_gems.init.LootTableInit;
import com.kwpugh.more_gems.world.ModConfiguredFeatures;

import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeInfo;
import top.theillusivec4.curios.api.SlotTypePreset;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import me.sargunvohra.mcmods.autoconfig1u.serializer.PartitioningSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class MoreGems implements ModInitializer
{
	public static final String MOD_ID = "more_gems";
	public static final ItemGroup MORE_GEMS_GROUP = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "more_gems_group"), () -> new ItemStack(ItemInit.ALEXANDRITE));
	public static final MoreGemsConfig CONFIG = AutoConfig.register(MoreGemsConfig.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new)).getConfig();

    @Override
    public void onInitialize()
    {
    	BlockInit.registerBlocks();
    	BlockInit.registerBlockItems();
    	ItemInit.registerItems();
    	ContainerInit.registerContainer();
    	ModConfiguredFeatures.Features();
    	LootTableInit.registerLoot ();
    	EnchantmentInnit.registerEnchantments();

    	CuriosApi.enqueueSlotType(SlotTypeInfo.BuildScheme.REGISTER, SlotTypePreset.CHARM.getInfoBuilder().size(4).build());
    }
}