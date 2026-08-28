package com.kyroxova.storageplus.compartments;

public enum CompartmentType {
    WOOD("wood", "compartment_wood", 2.0F, 3.0F, 54),
    COPPER("copper", "compartment_copper", 3.0F, 6.0F, 63),
    IRON("iron", "compartment_iron", 5.0F, 6.0F, 72),
    STEEL("steel", "compartment_steel", 5.0F, 8.0F, 81),
    GOLD("gold", "compartment_gold", 3.0F, 6.0F, 90),
    DARK_STEEL("darksteel", "compartment_darksteel", 6.0F, 9.0F, 99),
    DIAMOND("diamond", "compartment_diamond", 5.0F, 6.0F, 108),
    STAINLESS_STEEL("stainless_steel", "compartment_stainless_steel", 5.5F, 8.5F, 117),
    EMERALD("emerald", "compartment_emerald", 5.0F, 6.0F, 135);

    private final String name;
    private final String registryName;
    private final float hardness;
    private final float resistance;
    private final int slotCount;

    CompartmentType(String name, String registryName, float hardness, float resistance, int slotCount) {
        this.name = name;
        this.registryName = registryName;
        this.hardness = hardness;
        this.resistance = resistance;
        this.slotCount = slotCount;
    }

    public String getName() {
        return name;
    }

    public String getRegistryName() {
        return registryName;
    }

    public float getHardness() {
        return hardness;
    }

    public float getResistance() {
        return resistance;
    }

    public int getSlotCount() {
        return slotCount;
    }
}
