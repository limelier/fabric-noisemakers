package net.limelier.noisemakers

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Hand
import net.minecraft.util.Identifier
import net.minecraft.util.TypedActionResult
import net.minecraft.util.registry.Registry
import net.minecraft.world.World

// For support join https://discord.gg/v6v4pMv

class Noisemaker(settings: Settings, private val sound: SoundEvent) : Item(settings) {
    override fun use(world: World?, user: PlayerEntity, hand: Hand?): TypedActionResult<ItemStack> {
        user.playSound(sound, 1.0F, 1.0F)
        return TypedActionResult.success(user.getStackInHand(hand))
    }
}


val CREEPER_NOISEMAKER = Noisemaker(
    FabricItemSettings()
        .maxCount(16),
    SoundEvents.ENTITY_CREEPER_PRIMED
)

val NOISEMAKER_GROUP: ItemGroup = FabricItemGroupBuilder
    .create(Identifier("noisemakers", "noisemakers"))
    .icon { ItemStack(CREEPER_NOISEMAKER) }
    .appendItems {
        it.add(ItemStack(CREEPER_NOISEMAKER))
    }
    .build()


@Suppress("unused")
fun init() {
    // This code runs as soon as Minecraft is in a mod-load-ready state.
    // However, some things (like resources) may still be uninitialized.
    // Proceed with mild caution.

    Registry.register(
        Registry.ITEM,
        Identifier("noisemakers", "noisemaker"),
        CREEPER_NOISEMAKER
    )
}

