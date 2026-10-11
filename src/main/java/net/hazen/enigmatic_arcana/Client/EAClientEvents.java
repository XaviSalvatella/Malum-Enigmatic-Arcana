package net.hazen.enigmatic_arcana.Client;
import net.hazen.enigmatic_arcana.EnigmaticArcana;
import net.hazen.enigmatic_arcana.Registries.EAItemRegistry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;
@Mod.EventBusSubscriber(modid = EnigmaticArcana.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EAClientEvents {
    @SubscribeEvent public static void setup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            CuriosRendererRegistry.register(EAItemRegistry.ANTONOMOS.get(), () -> new EACurioRenderer(false));
            CuriosRendererRegistry.register(EAItemRegistry.AGROCONIC_BULWARK.get(), () -> new EACurioRenderer(true));
            CuriosRendererRegistry.register(EAItemRegistry.AGROCONIC_WINGS.get(), () -> new EACurioRenderer(true));
        });
    }
}
