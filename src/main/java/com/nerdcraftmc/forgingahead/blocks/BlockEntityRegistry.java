package com.nerdcraftmc.forgingahead.blocks;

import com.nerdcraftmc.forgingahead.ForgingAhead;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityRegistry
{
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ForgingAhead.MODID);

    @SuppressWarnings("ConstantConditions")
    public static final RegistryObject<BlockEntityType<ForgeBlockEntity>> FORGE =
            BLOCK_ENTITIES.register("forge", () -> BlockEntityType.Builder.of(ForgeBlockEntity::new,
                    BlockRegistry.FORGE.get()).build(null));

    public static void register(IEventBus bus) {
        BLOCK_ENTITIES.register(bus);
    }
}
