package xyz.stufforstuff.electricpoultrylib.effect;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;

import static xyz.stufforstuff.electricpoultrylib.ElectricPoultryLib.LOGGER;
import static xyz.stufforstuff.electricpoultrylib.ElectricPoultryLib.MODMOD_ID;

public class ModEffectHelper {
    public static MobEffect createEffect(String effectId, MobEffect effect) {
        return Registry.register(BuiltInRegistries.MOB_EFFECT, Identifier.fromNamespaceAndPath(MODMOD_ID, effectId), effect);
    }

    public static void initialize() {
        LOGGER.info("Creating Effects");
    }
}
