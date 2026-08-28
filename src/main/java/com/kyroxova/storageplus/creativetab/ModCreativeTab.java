package com.kyroxova.storageplus.creativetab;

import com.kyroxova.storageplus.init.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModCreativeTab {

    public static final CreativeTabs TAB_STORAGE_PLUS = new CreativeTabs("storageplus") {
        @Override
        @SideOnly(Side.CLIENT)
        public ItemStack createIcon() {
            return new ItemStack(ModBlocks.COMPARTMENT_WOOD);
        }
    };
}
