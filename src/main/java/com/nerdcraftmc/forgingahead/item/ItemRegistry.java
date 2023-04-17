package com.nerdcraftmc.forgingahead.item;

import com.nerdcraftmc.forgingahead.ForgingAhead;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ForgingAhead.MODID);

    public static final RegistryObject<Item> FYRALITE_DUST = ITEMS.register("fyralite_dust",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COAL_DUST = ITEMS.register("coal_dust",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COPPER_DUST = ITEMS.register("copper_dust",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> IRON_DUST = ITEMS.register("iron_dust",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GOLD_DUST = ITEMS.register("gold_dust",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LAPIS_DUST = ITEMS.register("lapis_dust",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> QUARTZ_DUST = ITEMS.register("quartz_dust",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> EMERALD_DUST = ITEMS.register("emerald_dust",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_DUST = ITEMS.register("diamond_dust",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_DUST = ITEMS.register("netherite_dust",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COAL_ALLOY = ITEMS.register("coal_alloy",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COPPER_ALLOY = ITEMS.register("copper_alloy",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> IRON_ALLOY = ITEMS.register("iron_alloy",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GOLD_ALLOY = ITEMS.register("gold_alloy",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> REDSTONE_ALLOY = ITEMS.register("redstone_alloy",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LAPIS_LAZULI_ALLOY = ITEMS.register("lapis_alloy",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NETHER_QUARTZ_ALLOY = ITEMS.register("quartz_alloy",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> EMERALD_ALLOY = ITEMS.register("emerald_alloy",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_ALLOY = ITEMS.register("diamond_alloy",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_ALLOY = ITEMS.register("netherite_alloy",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FYRALITE_HOE = ITEMS.register("pink_hoe",
            () -> new HoeItem(new ForgingAheadTier(0, 1000, 10.0F, 8.0F, 20, () -> {
                return Ingredient.of(Items.STICK);
            }), 0, -1.0F, new Item.Properties()));
    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
