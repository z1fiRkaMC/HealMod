package org.example.myfirstmod.util;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.ServerPlayerEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class TemporaryHealthManager {

    public static final UUID BONUS_HEALTH_UUID =
            UUID.fromString("6ec6cb8d-5d5b-4c3b-b2c3-67db62a94d56");

    public static final AttributeModifier BONUS_HEALTH =
            new AttributeModifier(
                    BONUS_HEALTH_UUID,
                    "life_steal_bonus",
                    4.0D,
                    AttributeModifier.Operation.ADDITION
            );

    private static final Map<UUID, Long> ACTIVE_EFFECTS = new HashMap<>();

    private TemporaryHealthManager() {

    }

    public static boolean hasEffect(UUID uuid) {
        return ACTIVE_EFFECTS.containsKey(uuid);
    }

    public static void refresh(UUID uuid) {
        ACTIVE_EFFECTS.put(uuid, System.currentTimeMillis() + 30000);
    }

    public static long getEndTime(UUID uuid) {
        return ACTIVE_EFFECTS.getOrDefault(uuid, 0L);
    }

    public static void remove(UUID uuid) {
        ACTIVE_EFFECTS.remove(uuid);
    }

    public static void apply(ServerPlayerEntity player) {

        ModifiableAttributeInstance maxHealth =
                player.getAttribute(Attributes.MAX_HEALTH);

        if (maxHealth == null) {
            return;
        }

        if (!maxHealth.hasModifier(BONUS_HEALTH)) {

            maxHealth.addTransientModifier(BONUS_HEALTH);

            player.setHealth(
                    Math.min(
                            player.getHealth() + 4.0F,
                            (float) maxHealth.getValue()
                    )
            );
        }

        refresh(player.getUUID());
    }

}