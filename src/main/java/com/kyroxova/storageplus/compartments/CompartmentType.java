package com.kyroxova.storageplus.compartments;

public enum CompartmentType {
    WOOD("wood", "compartment_wood", 2.0F, 5.0F, 100, 2),
    COPPER("copper", "compartment_copper", 3.0F, 6.0F, 200, 4),
    IRON("iron", "compartment_iron", 5.0F, 10.0F, 250, 5),
    STEEL("steel", "compartment_steel", 6.0F, 12.0F, 350, 7),
    GOLD("gold", "compartment_gold", 3.0F, 6.0F, 350, 7),
    DARK_STEEL("darksteel", "compartment_darksteel", 8.0F, 15.0F, 400, 8),
    DIAMOND("diamond", "compartment_diamond", 5.0F, 12.0F, 500, 10),
    EMERALD("emerald", "compartment_emerald", 5.0F, 12.0F, 550, 11),
    STAINLESS_STEEL("stainless_steel", "compartment_stainless_steel", 7.0F, 14.0F, 650, 13);

    public static final int SLOTS_PER_PAGE = 50;

    private final String name;
    private final String registryName;
    private final float hardness;
    private final float resistance;
    private final int totalSlots;
    private final int totalPages;

    CompartmentType(String name, String registryName, float hardness, float resistance, int totalSlots, int totalPages) {
        this.name = name;
        this.registryName = registryName;
        this.hardness = hardness;
        this.resistance = resistance;
        this.totalSlots = totalSlots;
        this.totalPages = totalPages;
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

    public int getTotalSlots() {
        return totalSlots;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
