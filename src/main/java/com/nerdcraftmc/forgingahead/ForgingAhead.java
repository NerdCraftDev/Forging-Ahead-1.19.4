package com.nerdcraftmc.forgingahead;

import com.nerdcraftmc.forgingahead.block.BlockRegistry;
import com.nerdcraftmc.forgingahead.block.entity.BlockEntityRegistry;
import com.nerdcraftmc.forgingahead.item.ItemRegistry;
import com.nerdcraftmc.forgingahead.recipe.RecipeTypeRegistry;
import com.nerdcraftmc.forgingahead.screen.ForgeScreen;
import com.nerdcraftmc.forgingahead.screen.MenuRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ForgingAhead.MODID)
public class ForgingAhead
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "forgingahead";

    public ForgingAhead()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ItemRegistry.register(modEventBus);
        BlockRegistry.BLOCKS.register(modEventBus);
        BlockEntityRegistry.register(modEventBus);
        RecipeTypeRegistry.register(modEventBus);
        MenuRegistry.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void addCreative(CreativeModeTabEvent.BuildContents event) {
        if (event.getTab() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ItemRegistry.FYRALITE_DUST);
            event.accept(ItemRegistry.COAL_DUST);
            event.accept(ItemRegistry.COPPER_DUST);
            event.accept(ItemRegistry.IRON_DUST);
            event.accept(ItemRegistry.GOLD_DUST);
            event.accept(ItemRegistry.LAPIS_LAZULI_DUST);
            event.accept(ItemRegistry.NETHER_QUARTZ_DUST);
            event.accept(ItemRegistry.EMERALD_DUST);
            event.accept(ItemRegistry.DIAMOND_DUST);
            event.accept(ItemRegistry.NETHERITE_DUST);
            event.accept(ItemRegistry.COAL_ALLOY);
            event.accept(ItemRegistry.COPPER_ALLOY);
            event.accept(ItemRegistry.IRON_ALLOY);
            event.accept(ItemRegistry.GOLD_ALLOY);
            event.accept(ItemRegistry.REDSTONE_ALLOY);
            event.accept(ItemRegistry.LAPIS_LAZULI_ALLOY);
            event.accept(ItemRegistry.NETHER_QUARTZ_ALLOY);
            event.accept(ItemRegistry.EMERALD_ALLOY);
            event.accept(ItemRegistry.DIAMOND_ALLOY);
            event.accept(ItemRegistry.NETHERITE_ALLOY);
        }
        else if (event.getTab() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(BlockRegistry.FORGE);
        }
    }
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            MenuScreens.register(MenuRegistry.FORGE_MENU.get(), ForgeScreen::new);
        }
    }
}
