package com.kyroxova.storageplus.compartments.block;

import com.kyroxova.storageplus.compartments.CompartmentType;
import com.kyroxova.storageplus.creativetab.ModCreativeTab;
import com.kyroxova.storageplus.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockCompartment extends Block {

    private final CompartmentType type;

    public BlockCompartment(CompartmentType type) {
        super(type == CompartmentType.WOOD ? Material.WOOD : Material.IRON);
        this.type = type;

        this.setUnlocalizedName(Reference.MOD_ID + "." + type.getRegistryName());
        this.setRegistryName(type.getRegistryName());
        this.setHardness(type.getHardness());
        this.setResistance(type.getResistance());
        this.setSoundType(type == CompartmentType.WOOD ? SoundType.WOOD : SoundType.METAL);
        this.setCreativeTab(ModCreativeTab.TAB_STORAGE_PLUS);
    }

    public CompartmentType getType() {
        return type;
    }
}
