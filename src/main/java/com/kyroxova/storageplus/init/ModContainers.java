package com.kyroxova.storageplus.init;

import com.kyroxova.storageplus.compartments.container.ContainerCompartment;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainers {

    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, "storageplus");

    public static final RegistryObject<ContainerType<ContainerCompartment>> COMPARTMENT = CONTAINERS.register(
            "compartment", () -> IForgeContainerType.create(ContainerCompartment::createClientContainer));
}
