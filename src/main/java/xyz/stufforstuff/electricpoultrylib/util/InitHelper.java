package xyz.stufforstuff.electricpoultrylib.util;

import xyz.stufforstuff.electricpoultrylib.ElectricPoultryLib;
import xyz.stufforstuff.electricpoultrylib.block.ModBlockEntityHelper;
import xyz.stufforstuff.electricpoultrylib.block.ModBlockHelper;
import xyz.stufforstuff.electricpoultrylib.effect.ModEffectHelper;
import xyz.stufforstuff.electricpoultrylib.item.ModItemHelper;

public class InitHelper {
    public static void setModId(String modId) {
        ElectricPoultryLib.MODMOD_ID = modId;
    }

    public static void registerAll() {
        ModItemHelper.initalize();
        ModBlockHelper.initialize();
        ModBlockEntityHelper.initialize();
        ModEffectHelper.initialize();
    }
}
