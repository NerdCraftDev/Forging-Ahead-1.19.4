package com.nerdcraftmc.forgingahead.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ForgeBlockEntity extends BlockEntity implements MenuProvider
{
    public ForgeBlockEntity(BlockPos pPos, BlockState pBlockState)
    {
        super(BlockEntityRegistry.FORGE.get(), pPos, pBlockState);
    }

    @Override
    public @NotNull Component getDisplayName()
    {
        return Component.literal("Forge");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pPlayerInventory, @NotNull Player pPlayer)
    {
        return null;
    }

    @Override
    public void onLoad()
    {
        super.onLoad();
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap)
    {
        return super.getCapability(cap);
    }
}
