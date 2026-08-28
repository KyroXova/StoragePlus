package com.kyroxova.storageplus.creativetab;

import com.kyroxova.storageplus.init.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ModCreativeTab {

    public static final CreativeTabs TAB_STORAGE_PLUS = new CreativeTabs("storageplus") {
        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(ModBlocks.COMPARTMENT_WOOD);
        }
    };
}
