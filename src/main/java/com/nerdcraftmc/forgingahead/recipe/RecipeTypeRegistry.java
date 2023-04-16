package com.nerdcraftmc.forgingahead.recipe;

import com.nerdcraftmc.forgingahead.ForgingAhead;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RecipeTypeRegistry
{
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ForgingAhead.MODID);

    public static final RegistryObject<RecipeSerializer<ForgeRecipe>> FORGE_SERIALIZER =
            SERIALIZERS.register("forge", () -> ForgeRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
