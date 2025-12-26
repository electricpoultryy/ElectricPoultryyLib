package xyz.stufforstuff.electricpoultrylib.item;

import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.block.Block;

import java.util.function.Function;

import static xyz.stufforstuff.electricpoultrylib.ElectricPoultryLib.LOGGER;
import static xyz.stufforstuff.electricpoultrylib.ElectricPoultryLib.MODMOD_ID;

public class ModItemHelper {
    public static Item createItem(String itemId, Item.Properties itemProperties) {
        return register(itemId, Item::new, itemProperties);
    }

    public static Potion createPotion(String potionId, Holder<MobEffect> effect, int duration, int amplifier, Item recipeItem) {
        Potion potion = Registry.register(BuiltInRegistries.POTION, Identifier.fromNamespaceAndPath(MODMOD_ID, potionId), new Potion(potionId, new MobEffectInstance(effect, duration * 20, amplifier)));
        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {builder.addMix(Potions.WATER, recipeItem, BuiltInRegistries.POTION.wrapAsHolder(potion));});
        return potion;
    }

    public static Item createSimpleItem(String itemId) {
        return registerSimple(itemId, Item::new);
    }

    public static Item createCustomItem(String itemId, Function<Item.Properties, Item> itemFactory, Item.Properties itemProperties) {
        return register(itemId, itemFactory, itemProperties);
    }

    public static Item createFuelItem(String itemId, Item.Properties itemProperties, int burnTime) {
        Item item = register(itemId, Item::new, itemProperties);
        FuelRegistryEvents.BUILD.register((builder, context) -> {builder.add(item, burnTime * 20);});
        return item;
    }

    public static Item createFoodItem(String itemId, Item.Properties itemProperties, FoodProperties foodProperties) {
        return register(itemId, Item::new, itemProperties.food(foodProperties));
    }

    public static Item createFoodItemWithEffect(String itemId, Item.Properties itemProperties, Consumable consumable, FoodProperties foodProperties) {
        return register(itemId, Item::new, itemProperties.food(foodProperties, consumable));
    }

    public static Item createSwordItem(String itemId, ToolMaterial toolMaterial, Item.Properties itemProperties, int attackdamage, float attackspeed) {
        return register(itemId, Item::new, itemProperties.sword(toolMaterial, attackdamage, attackspeed));
    }

    public static Item createAxeItem(String itemId, ToolMaterial toolMaterial, Item.Properties itemProperties, int attackdamage, float attackspeed) {
        return register(itemId, Item::new, itemProperties.axe(toolMaterial, attackdamage, attackspeed));
    }

    public static Item createPickaxeItem(String itemId, ToolMaterial toolMaterial, Item.Properties itemProperties, int attackdamage, float attackspeed) {
        return register(itemId, Item::new, itemProperties.pickaxe(toolMaterial, attackdamage, attackspeed));
    }

    public static Item createShovelItem(String itemId, ToolMaterial toolMaterial, Item.Properties itemProperties, int attackdamage, float attackspeed) {
        return register(itemId, Item::new, itemProperties.shovel(toolMaterial, attackdamage, attackspeed));
    }

    public static Item createHoeItem(String itemId, ToolMaterial toolMaterial, Item.Properties itemProperties, int attackdamage, float attackspeed) {
        return register(itemId, Item::new, itemProperties.hoe(toolMaterial, attackdamage, attackspeed));
    }

    public static BlockItem createBlockItem(String itemId, Block block, Item.Properties itemProperties) {
        return registerBlockItem(itemId, block, itemProperties);
    }

    public static Item createArmorItem(String itemId, ArmorMaterial armorMaterial, ArmorType armorType, Item.Properties itemProperties) {
        // Add Durability Your Self When Registering
        return register(itemId, Item::new, itemProperties.humanoidArmor(armorMaterial, armorType));
    }

    public static Item register(String name, Function<Item.Properties, Item> itemFactory, Item.Properties settings) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MODMOD_ID, name));

        Item item = itemFactory.apply(settings.setId(itemKey));

        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    public static Item registerSimple(String name, Function<Item.Properties, Item> itemFactory) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MODMOD_ID, name));

        Item item = itemFactory.apply(new Item.Properties().setId(itemKey));

        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    public static BlockItem registerBlockItem(String name, Block block, Item.Properties settings) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MODMOD_ID, name));

        BlockItem item = new BlockItem(block, settings.setId(itemKey));

        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    public static void initalize() {
        LOGGER.info("Creating Items");
    }
}
