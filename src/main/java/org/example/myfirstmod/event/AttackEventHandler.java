package org.example.myfirstmod.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.example.myfirstmod.registry.ModItems;
import org.example.myfirstmod.util.TemporaryHealthManager;

public class AttackEventHandler {

    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event) {

        Entity source = event.getSource().getEntity();

        if (!(source instanceof ServerPlayerEntity)) {
            return;
        }

        ServerPlayerEntity player = (ServerPlayerEntity) source;

        ItemStack heldItem = player.getMainHandItem();

        if (heldItem.getItem() != ModItems.LIFE_STEAL_SWORD.get()) {
            return;
        }

        float healthAfterHit = event.getEntityLiving().getHealth() - event.getAmount();

        if (healthAfterHit <= 0.0F) {
            return;
        }

        if (player.getRandom().nextFloat() > 0.10F) {
            return;
        }

        TemporaryHealthManager.apply(player);
        player.level.playSound(
                null,
                player.blockPosition(),
                net.minecraft.util.SoundEvents.PLAYER_LEVELUP,
                net.minecraft.util.SoundCategory.PLAYERS,
                0.6F,
                1.4F
        );

        player.sendMessage(
                new StringTextComponent("§5✦ Жизненная сила похищена!"),
                player.getUUID()
        );
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {

        if (event.phase != TickEvent.Phase.END) {
            return;
        }

        if (event.player.level.isClientSide()) {
            return;
        }

        if (!(event.player instanceof ServerPlayerEntity)) {
            return;
        }

        ServerPlayerEntity player = (ServerPlayerEntity) event.player;

        if (!TemporaryHealthManager.hasEffect(player.getUUID())) {
            return;
        }

        if (System.currentTimeMillis() < TemporaryHealthManager.getEndTime(player.getUUID())) {
            return;
        }

        ModifiableAttributeInstance maxHealth =
                player.getAttribute(Attributes.MAX_HEALTH);

        if (maxHealth != null) {
            maxHealth.removeModifier(TemporaryHealthManager.BONUS_HEALTH_UUID);

            if (player.getHealth() > maxHealth.getValue()) {
                player.setHealth((float) maxHealth.getValue());
            }
        }

        TemporaryHealthManager.remove(player.getUUID());
    }

}
