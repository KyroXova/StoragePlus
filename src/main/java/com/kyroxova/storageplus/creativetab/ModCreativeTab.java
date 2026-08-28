package com.kyroxova.storageplus.creativetab;

import com.kyroxova.storageplus.init.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ModCreativeTab {

    public static final CreativeTabs TAB_STORAGE_PLUS = new CreativeTabs("storageplus") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ModBlocks.COMPARTMENT_WOOD);
        }
    };
}
