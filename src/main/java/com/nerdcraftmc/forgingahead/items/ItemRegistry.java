package com.nerdcraftmc.forgingahead.items;

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
    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
