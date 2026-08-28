package com.kyroxova.storageplus.proxy;

import com.kyroxova.storageplus.compartments.block.BlockCompartment;
import com.kyroxova.storageplus.init.ModBlocks;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = "storageplus", value = Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        for (BlockCompartment block : ModBlocks.COMPARTMENTS) {
            Item item = Item.getItemFromBlock(block);
            if (block.getRegistryName() != null) {
                ModelLoader.setCustomModelResourceLocation(
                    item,
                    0,
                    new ModelResourceLocation(block.getRegistryName(), "normal")
                );
            }
        }
    }
}
