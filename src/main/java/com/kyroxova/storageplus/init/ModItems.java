package com.kyroxova.storageplus.init;

import com.kyroxova.storageplus.compartments.block.BlockCompartment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = "storageplus")
public class ModItems {

    public static final List<Item> ITEMS = new ArrayList<>();

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        for (BlockCompartment block : ModBlocks.COMPARTMENTS) {
            ItemBlock itemBlock = new ItemBlock(block);
            if (block.getRegistryName() != null) {
                itemBlock.setRegistryName(block.getRegistryName());
            }
            event.getRegistry().register(itemBlock);
            ITEMS.add(itemBlock);
        }
    }
}
