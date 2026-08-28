package com.kyroxova.storageplus.compartments.block;

import com.kyroxova.storageplus.compartments.CompartmentType;
import com.kyroxova.storageplus.creativetab.ModCreativeTab;
import com.kyroxova.storageplus.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockCompartment extends Block {

    private final CompartmentType type;

    public BlockCompartment(CompartmentType type) {
        super(type == CompartmentType.WOOD ? Material.wood : Material.iron);
        this.type = type;

        this.setBlockName(Reference.MOD_ID + ".compartment_" + type.getName());
        this.setBlockTextureName(Reference.MOD_ID + ":compartments/" + type.getTextureName());
        this.setHardness(type.getHardness());
        this.setResistance(type.getResistance());
        this.setStepSound(type == CompartmentType.WOOD ? soundTypeWood : soundTypeMetal);
        this.setCreativeTab(ModCreativeTab.TAB_STORAGE_PLUS);
    }

    public CompartmentType getType() {
        return type;
    }
}
