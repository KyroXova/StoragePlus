package com.kyroxova.storageplus.init;

import com.kyroxova.storageplus.creativetab.ModCreativeTab;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "storageplus");

    public static final RegistryObject<Item> COMPARTMENT_WOOD = ITEMS.register(
            "compartment_wood", () -> new BlockItem(ModBlocks.COMPARTMENT_WOOD.get(), new Item.Properties().tab(ModCreativeTab.TAB_STORAGE_PLUS)));
    public static final RegistryObject<Item> COMPARTMENT_COPPER = ITEMS.register(
            "compartment_copper", () -> new BlockItem(ModBlocks.COMPARTMENT_COPPER.get(), new Item.Properties().tab(ModCreativeTab.TAB_STORAGE_PLUS)));
    public static final RegistryObject<Item> COMPARTMENT_IRON = ITEMS.register(
            "compartment_iron", () -> new BlockItem(ModBlocks.COMPARTMENT_IRON.get(), new Item.Properties().tab(ModCreativeTab.TAB_STORAGE_PLUS)));
    public static final RegistryObject<Item> COMPARTMENT_STEEL = ITEMS.register(
            "compartment_steel", () -> new BlockItem(ModBlocks.COMPARTMENT_STEEL.get(), new Item.Properties().tab(ModCreativeTab.TAB_STORAGE_PLUS)));
    public static final RegistryObject<Item> COMPARTMENT_GOLD = ITEMS.register(
            "compartment_gold", () -> new BlockItem(ModBlocks.COMPARTMENT_GOLD.get(), new Item.Properties().tab(ModCreativeTab.TAB_STORAGE_PLUS)));
    public static final RegistryObject<Item> COMPARTMENT_DARK_STEEL = ITEMS.register(
            "compartment_darksteel", () -> new BlockItem(ModBlocks.COMPARTMENT_DARK_STEEL.get(), new Item.Properties().tab(ModCreativeTab.TAB_STORAGE_PLUS)));
    public static final RegistryObject<Item> COMPARTMENT_DIAMOND = ITEMS.register(
            "compartment_diamond", () -> new BlockItem(ModBlocks.COMPARTMENT_DIAMOND.get(), new Item.Properties().tab(ModCreativeTab.TAB_STORAGE_PLUS)));
    public static final RegistryObject<Item> COMPARTMENT_STAINLESS_STEEL = ITEMS.register(
            "compartment_stainless_steel", () -> new BlockItem(ModBlocks.COMPARTMENT_STAINLESS_STEEL.get(), new Item.Properties().tab(ModCreativeTab.TAB_STORAGE_PLUS)));
    public static final RegistryObject<Item> COMPARTMENT_EMERALD = ITEMS.register(
            "compartment_emerald", () -> new BlockItem(ModBlocks.COMPARTMENT_EMERALD.get(), new Item.Properties().tab(ModCreativeTab.TAB_STORAGE_PLUS)));
}
