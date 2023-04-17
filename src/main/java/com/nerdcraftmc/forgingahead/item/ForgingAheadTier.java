package com.nerdcraftmc.forgingahead.item;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ForgingAheadTier implements Tier
{
    private final int level;
    private int uses;
    private float speed;
    private float attackDamageBonus;
    private int enchantmentValue;
    private final Supplier<Ingredient> repairIngredient;

    ForgingAheadTier(int level, int durability, float miningSpeed, float attackDamageBonus, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.level = level;
        this.uses = durability;
        this.speed = miningSpeed;
        this.attackDamageBonus = attackDamageBonus;
        this.enchantmentValue = enchantability;
        this.repairIngredient = repairIngredient;
    }

    public int getUses() {
        return this.uses;
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getAttackDamageBonus() {
        return this.attackDamageBonus;
    }

    public int getLevel() {
        return this.level;
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public @NotNull Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
