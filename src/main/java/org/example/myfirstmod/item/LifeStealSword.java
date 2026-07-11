package org.example.myfirstmod.item;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SwordItem;

public class LifeStealSword extends SwordItem {

    public LifeStealSword() {

        super(
                ItemTier.DIAMOND,
                3,
                -2.4F,
                new Properties().tab(ItemGroup.TAB_COMBAT)
        );

    }
    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

}
