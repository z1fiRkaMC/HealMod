package org.example.myfirstmod.registry;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup {

    public static final ItemGroup HEALMOD_TAB = new ItemGroup("healmod") {

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.LIFE_STEAL_SWORD.get());
        }

    };

}