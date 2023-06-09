package com.nerdcraftmc.forgingahead.block.entity;

import com.nerdcraftmc.forgingahead.recipe.ForgeRecipe;
import com.nerdcraftmc.forgingahead.screen.ForgeMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class ForgeBlockEntity extends BlockEntity implements MenuProvider
{
    private final ItemStackHandler itemHandler = new ItemStackHandler(4) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 80;
    public ForgeBlockEntity(BlockPos pPos, BlockState pBlockState)
    {
        super(BlockEntityRegistry.FORGE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> ForgeBlockEntity.this.progress;
                    case 1 -> ForgeBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> ForgeBlockEntity.this.progress = pValue;
                    case 1 -> ForgeBlockEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
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
        return new ForgeMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("forge.progress", this.progress);

        super.saveAdditional(pTag);
    }

    @Override
    public void load(@NotNull CompoundTag pTag) {
        super.load(pTag);

        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("forge.progress");
    }

    public void drops() {
        SimpleContainer inventory = getInventory(itemHandler);

        if (this.level != null)
        {
            Containers.dropContents(this.level, this.worldPosition, inventory);
        }
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, ForgeBlockEntity pEntity) {
        if (pLevel.isClientSide()) {
            return;
        }
        if (hasRecipe(pEntity)) {
            pEntity.progress++;
            setChanged(pLevel, pPos, pState);

            if (pEntity.progress >= pEntity.maxProgress) {
                craftItem(pEntity);
            }
        }
        else {
            pEntity.resetProgress();
            setChanged(pLevel, pPos, pState);
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static void craftItem(ForgeBlockEntity pEntity) {
        if (hasRecipe(pEntity)) {
            Optional<ForgeRecipe> recipe = getRecipe(pEntity);
            if (recipe.isPresent()) {
                ItemStack outputItem = recipe.get().output;
                int outputAmount = pEntity.itemHandler.getStackInSlot(3).getCount() + outputItem.getCount();
                pEntity.itemHandler.extractItem(0, 1, false);
                pEntity.itemHandler.extractItem(1, 1, false);
                pEntity.itemHandler.extractItem(2, 1, false);
                pEntity.itemHandler.setStackInSlot(3, new ItemStack(outputItem.getItem(), outputAmount));
                pEntity.resetProgress();
            }
        }
    }

    private static boolean hasRecipe(ForgeBlockEntity pEntity) {
        SimpleContainer inventory = getInventory(pEntity.itemHandler);
        Optional<ForgeRecipe> recipe = getRecipe(pEntity);

        return recipe.isPresent() && inventory.canAddItem(recipe.get().output);
    }

    private static Optional<ForgeRecipe> getRecipe(ForgeBlockEntity pEntity) {
        if (pEntity.level == null) return Optional.empty();
        Level level = pEntity.level;
        SimpleContainer inventory = getInventory(pEntity.itemHandler);

        return level.getRecipeManager().getRecipeFor(ForgeRecipe.Type.INSTANCE, inventory, level);
    }

    private static SimpleContainer getInventory(ItemStackHandler itemHandler) {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        return inventory;
    }
}
