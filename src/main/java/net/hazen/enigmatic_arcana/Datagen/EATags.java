package net.hazen.enigmatic_arcana.Datagen;

import net.hazen.enigmatic_arcana.EnigmaticArcana;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class EATags {

    /*
     *** Items
     */

    // Dark Arts School Focus
    public static final TagKey<Item> DARK_ARTS_FOCUS = ItemTags
            .create(ResourceLocation.parse(new ResourceLocation(EnigmaticArcana.MOD_ID, "focus/dark_arts_focus").toString()));

    public static final TagKey<Item> IDLE_ARMOR = ItemTags
            .create(ResourceLocation.parse(new ResourceLocation(EnigmaticArcana.MOD_ID, "armor/idle_armor").toString()));



    public static class Blocks {

        public static final TagKey<Block> NEEDS_AGROCONIC_TOOL = BlockTags
                .create(ResourceLocation.parse(new ResourceLocation(EnigmaticArcana.MOD_ID, "needs_agroconic_tool").toString()));

        public static final TagKey<Block> INCORRECT_FOR_AGROCONIC_TOOl = BlockTags
                .create(ResourceLocation.parse(new ResourceLocation(EnigmaticArcana.MOD_ID, "incorrect_for_agroconic_tool").toString()));

    }



}