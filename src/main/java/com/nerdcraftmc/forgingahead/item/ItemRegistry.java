package com.nerdcraftmc.forgingahead.item;

import com.nerdcraftmc.forgingahead.ForgingAhead;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ForgingAhead.MODID);

    public static final RegistryObject<Item> FYRALITE_POWDER = ITEMS.register("fyralite_powder",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COAL_POWDER = ITEMS.register("coal_powder",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COPPER_POWDER = ITEMS.register("copper_powder",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> IRON_POWDER = ITEMS.register("iron_powder",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GOLD_POWDER = ITEMS.register("gold_powder",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LAPIS_LAZULI_POWDER = ITEMS.register("lapis_powder",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NETHER_QUARTZ_POWDER = ITEMS.register("quartz_powder",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> EMERALD_POWDER = ITEMS.register("emerald_powder",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_POWDER = ITEMS.register("diamond_powder",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_POWDER = ITEMS.register("netherite_powder",
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

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
