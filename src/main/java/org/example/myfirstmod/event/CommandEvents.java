package org.example.myfirstmod.event;

import org.example.myfirstmod.command.HealCommand;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CommandEvents {

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        HealCommand.register(event.getDispatcher());
    }

}