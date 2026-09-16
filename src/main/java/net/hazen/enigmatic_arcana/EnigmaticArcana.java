package net.hazen.enigmatic_arcana;

import net.hazen.enigmatic_arcana.Items.Equipment.Curios.CustomCurios.AgroconicBulwark.AgroconicBulwark;
import net.hazen.enigmatic_arcana.Items.Equipment.Curios.Spellbooks.Antonomos.Antonomos;
import net.hazen.enigmatic_arcana.Items.Utils.EAArmorMaterials;
import net.hazen.enigmatic_arcana.Registries.*;
import net.hazen.hazentouvelib.Items.Curios.GenericCurioRenderer;
import net.hazen.hazentouvelib.Items.Curios.Wings.WingCurioItem;
import net.hazen.hazentouvelib.Items.Curios.Wings.WingCurioRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

@Mod(EnigmaticArcana.MOD_ID)
public class EnigmaticArcana {
    public static final String MOD_ID = "enigmatic_arcana";
    public static final Logger LOGGER = LogUtils.getLogger();


    public EnigmaticArcana(IEventBus modEventBus, ModContainer modContainer) {


        EAItemRegistry.register(modEventBus);
        EAArmorMaterials.register(modEventBus);
        EACreativeModeTabs.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(this);
        modContainer.registerConfig(ModConfig.Type.COMMON, EAConfig.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }


    public static ResourceLocation id(@NotNull String path)
    {
        return ResourceLocation.fromNamespaceAndPath(EnigmaticArcana.MOD_ID, path);
    }

    @EventBusSubscriber(value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            event.enqueueWork(() -> {
                EAItemRegistry.getEAItems().stream().filter(item -> item.get() instanceof WingCurioItem).forEach((item) -> CuriosRendererRegistry.register(item.get(), WingCurioRenderer::new));
                EAItemRegistry.getEAItems().stream().filter(item -> item.get() instanceof Antonomos).forEach((item) -> CuriosRendererRegistry.register(item.get(), GenericCurioRenderer::new));
                EAItemRegistry.getEAItems().stream().filter(item -> item.get() instanceof AgroconicBulwark).forEach((item) -> CuriosRendererRegistry.register(item.get(), GenericCurioRenderer::new));


            });

        }
    }
}
