package xyz.stufforstuff.electricpoultrylib.block;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import xyz.stufforstuff.electricpoultrylib.item.ModItemHelper;

import java.util.function.Function;

import static xyz.stufforstuff.electricpoultrylib.ElectricPoultryLib.LOGGER;
import static xyz.stufforstuff.electricpoultrylib.ElectricPoultryLib.MODMOD_ID;

public class ModBlockHelper {
    public static Block createBlock(String blockId, BlockBehaviour.Properties blockProperties, float strength, SoundType soundtype, boolean shouldRegisterItem) {
        return register(blockId, Block::new, blockProperties.strength(strength).sound(soundtype), shouldRegisterItem);
    }

    public static Block createCustomBlock(String blockId, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties blockProperties, float strength, SoundType soundtype, boolean shouldRegisterItem) {
        return register(blockId, blockFactory, blockProperties.strength(strength).sound(soundtype), shouldRegisterItem);
    }

    public static BaseEntityBlock createBlockEntityBlock(String blockId, Function<BlockBehaviour.Properties, BaseEntityBlock> blockFactory, BlockBehaviour.Properties blockProperties, float strength, SoundType soundtype, boolean shouldRegisterItem) {
        return registerBlockEntityBlock(blockId, blockFactory, blockProperties.strength(strength).sound(soundtype), shouldRegisterItem);
    }

    public static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties settings, boolean shouldRegisterItem) {
        ResourceKey<Block> blockKey = keyOfBlock(name);

        Block block = blockFactory.apply(settings.setId(blockKey));

        if (shouldRegisterItem) {
            ModItemHelper.createBlockItem(name, block, new Item.Properties().useBlockDescriptionPrefix());
        }

        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }
    public static BaseEntityBlock registerBlockEntityBlock(String name, Function<BlockBehaviour.Properties, BaseEntityBlock> blockFactory, BlockBehaviour.Properties settings, boolean shouldRegisterItem) {
        ResourceKey<Block> blockKey = keyOfBlock(name);

        BaseEntityBlock block = blockFactory.apply(settings.setId(blockKey));

        if (shouldRegisterItem) {
            ModItemHelper.createBlockItem(name, block, new Item.Properties().useBlockDescriptionPrefix());
        }

        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }
    private static ResourceKey<Block> keyOfBlock(String name) {
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MODMOD_ID, name));
    }
    public static void initialize() {
        LOGGER.info("Creating Items");
    }
}
