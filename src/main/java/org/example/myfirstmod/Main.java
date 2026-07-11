package org.example.myfirstmod;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.example.myfirstmod.registry.ModItems;
import org.example.myfirstmod.event.CommandEvents;
import org.example.myfirstmod.event.AttackEventHandler;

@Mod(Main.MOD_ID)
public class Main {

    public static final String MOD_ID = "healmod";

    public Main() {
        ModItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

        MinecraftForge.EVENT_BUS.register(new CommandEvents());
        MinecraftForge.EVENT_BUS.register(new AttackEventHandler());
    }
}