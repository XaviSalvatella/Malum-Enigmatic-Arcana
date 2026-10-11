package net.hazen.enigmatic_arcana;
import com.mojang.logging.LogUtils;
import net.hazen.enigmatic_arcana.Registries.EAItemRegistry;
import net.hazen.enigmatic_arcana.Registries.EACreativeModeTabs;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
@Mod(EnigmaticArcana.MOD_ID)
public class EnigmaticArcana {
    public static final String MOD_ID = "enigmatic_arcana";
    public static final Logger LOGGER = LogUtils.getLogger();
    public EnigmaticArcana() {
        var bus = FMLJavaModLoadingContext.get().getModEventBus();
        EAItemRegistry.register(bus);
        EACreativeModeTabs.register(bus);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, EAConfig.SPEC);
    }
    public static ResourceLocation id(String path) { return new ResourceLocation(MOD_ID, path); }
}
