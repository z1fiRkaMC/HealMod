package org.example.myfirstmod.item;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SwordItem;
import org.example.myfirstmod.registry.ModItemGroup;

public class LifeStealSword extends SwordItem {

    public LifeStealSword() {

        super(
                ItemTier.DIAMOND,
                3,
                -2.4F,
                new Properties().tab(ModItemGroup.HEALMOD_TAB)
        );

    }
    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

}
