package com.kyroxova.storageplus.creativetab;

import com.kyroxova.storageplus.init.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModCreativeTab {

    public static final ItemGroup TAB_STORAGE_PLUS = new ItemGroup("storageplus") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.COMPARTMENT_WOOD.get());
        }
    };
}
