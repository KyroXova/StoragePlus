package com.kyroxova.storageplus.compartments.block;

import com.kyroxova.storageplus.compartments.CompartmentType;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockCompartment extends Block {

    private final CompartmentType type;

    public BlockCompartment(CompartmentType type) {
        super(AbstractBlock.Properties.of(type == CompartmentType.WOOD ? Material.WOOD : Material.METAL)
                .strength(type.getHardness(), type.getResistance())
                .sound(type == CompartmentType.WOOD ? SoundType.WOOD : SoundType.METAL));
        this.type = type;
    }

    public CompartmentType getType() {
        return type;
    }
}
