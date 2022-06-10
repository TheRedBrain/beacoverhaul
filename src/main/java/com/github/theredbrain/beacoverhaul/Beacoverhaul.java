package com.github.theredbrain.beacoverhaul;

import com.github.theredbrain.beacoverhaul.entity.effect.ExtraLootingStatusEffect;
import com.github.theredbrain.beacoverhaul.entity.effect.PlayerKillStatusEffect;
import com.github.theredbrain.beacoverhaul.entity.effect.TrueSightStatusEffect;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Beacoverhaul implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("beacoverhaul");
	public static final StatusEffect EXTRA_LOOTING = new ExtraLootingStatusEffect();
	public static final StatusEffect PLAYER_KILL = new PlayerKillStatusEffect();
	public static final StatusEffect TRUE_SIGHT = new TrueSightStatusEffect();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		//TODO
		// change recipe and texture of respawn anchor to include netherite block?

		LOGGER.info("The beacons are li..., I mean overhauled!");
		registerEffects();
	}

	private void registerEffects() {
		Registry.register(Registry.STATUS_EFFECT, new Identifier("beacoverhaul", "extra_looting"), EXTRA_LOOTING);
		Registry.register(Registry.STATUS_EFFECT, new Identifier("beacoverhaul", "player_kill"), PLAYER_KILL);
		Registry.register(Registry.STATUS_EFFECT, new Identifier("beacoverhaul", "true_sight"), TRUE_SIGHT);
	}
}
