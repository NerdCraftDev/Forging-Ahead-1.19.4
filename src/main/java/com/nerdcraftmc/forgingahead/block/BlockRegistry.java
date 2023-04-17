package com.nerdcraftmc.forgingahead.block;

import com.nerdcraftmc.forgingahead.ForgingAhead;
import com.nerdcraftmc.forgingahead.item.ItemRegistry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockRegistry
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ForgingAhead.MODID);

    // Hammer
    // Vanilla item
    // = Vanilla item dust

    // Fyralite dust
    // Vanilla item dust
    // Magma cream
    // = Alloy

    // Hammer
    // Alloy
    // Weapon/Armor

    public static final RegistryObject<Block> FORGE = register("forge", Blocks.SMITHING_TABLE);

    public static RegistryObject<Block> register(String registryName, Block blockToCopy) {
        RegistryObject<Block> block = BLOCKS.register(registryName, () -> new ForgeBlock(BlockBehaviour.Properties.copy(blockToCopy)));
        registerBlockItem(registryName, block);
        return block;
    }

    public static void registerBlockItem(String registryName, RegistryObject<Block> block) {
        ItemRegistry.ITEMS.register(registryName, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
