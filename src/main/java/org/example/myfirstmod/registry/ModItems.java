package org.example.myfirstmod.registry;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.example.myfirstmod.Main;
import org.example.myfirstmod.item.LifeStealSword;

public final class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);

    public static final RegistryObject<Item> LIFE_STEAL_SWORD =
            ITEMS.register("life_steal_sword", LifeStealSword::new);

    private ModItems() {

    }
}
