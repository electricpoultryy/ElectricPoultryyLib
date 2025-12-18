package xyz.stufforstuff.electricpoultrylib.block;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import static xyz.stufforstuff.electricpoultrylib.ElectricPoultryLib.LOGGER;
import static xyz.stufforstuff.electricpoultrylib.ElectricPoultryLib.MODMOD_ID;

public class ModBlockEntityHelper {
    public static <T extends BlockEntity> BlockEntityType<T> createBlockEntity(String blockEntityId, FabricBlockEntityTypeBuilder.Factory<? extends T> blockEntity, BaseEntityBlock baseBlock) {
        return register(blockEntityId, blockEntity, baseBlock);
    }

    private static <T extends BlockEntity>BlockEntityType<T> register(String name, FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory, Block block) {
        Identifier id = Identifier.fromNamespaceAndPath(MODMOD_ID, name);
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, id, FabricBlockEntityTypeBuilder.<T>create(entityFactory, block).build());
    }

    public static void initialize() {
        LOGGER.info("Creating Block Entities");
    }
}
