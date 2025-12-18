package xyz.stufforstuff.electricpoultrylib.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import static xyz.stufforstuff.electricpoultrylib.ElectricPoultryLib.MODMOD_ID;

public class ModItemGroupHelper {
    public static ResourceKey<CreativeModeTab> createItemGroup(String groupId, ItemStack icon, Component title) {
        ResourceKey<CreativeModeTab> tabKey = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), Identifier.fromNamespaceAndPath(MODMOD_ID, groupId));
        CreativeModeTab tab = FabricItemGroup.builder().icon(() -> icon).title(title).build();
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, tabKey, tab);
        return tabKey;
    }
}
