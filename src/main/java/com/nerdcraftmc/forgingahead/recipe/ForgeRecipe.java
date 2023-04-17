package com.nerdcraftmc.forgingahead.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nerdcraftmc.forgingahead.ForgingAhead;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class ForgeRecipe implements Recipe<SimpleContainer>
{
    private final ResourceLocation id;
    public final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;

    public ForgeRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
    }

    @Override
    public boolean matches(@NotNull SimpleContainer pContainer, @NotNull Level pLevel)
    {
        if (pLevel.isClientSide()) {
            return false;
        }

        return recipeItems.get(0).test(pContainer.getItem(0)) && recipeItems.get(1).test(pContainer.getItem(1));
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients()
    {
        return recipeItems;
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull SimpleContainer pContainer, @NotNull RegistryAccess registryAccess)
    {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight)
    {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem(@NotNull RegistryAccess registryAccess)
    {
        return output.copy();
    }

    @Override
    public @NotNull ResourceLocation getId()
    {
        return id;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer()
    {
        return Serializer.INSTANCE;
    }

    @Override
    public @NotNull RecipeType<?> getType()
    {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<ForgeRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        @SuppressWarnings("unused")
        public static final String ID = "forge";
    }


    public static class Serializer implements RecipeSerializer<ForgeRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        @SuppressWarnings("unused")
        public static final ResourceLocation ID = new ResourceLocation(ForgingAhead.MODID, "forge");

        @Override
        public @NotNull ForgeRecipe fromJson(@NotNull ResourceLocation pRecipeId, @NotNull JsonObject pSerializedRecipe) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output"));

            JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new ForgeRecipe(pRecipeId, output, inputs);
        }

        @Override
        public ForgeRecipe fromNetwork(@NotNull ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

            //noinspection Java8ListReplaceAll
            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buf));
            }

            ItemStack output = buf.readItem();
            return new ForgeRecipe(id, output, inputs);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, ForgeRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());

            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.toNetwork(buf);
            }

            buf.writeItemStack(recipe.output, false);
        }
    }
}
