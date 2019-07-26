package com.xarmor.events;

import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.xarmor.configs.ConfigManager;
import com.xarmor.XArmor;
import com.xarmor.utils.Utils;

public class InvClick implements Listener {
	XArmor plugin;
	
	public InvClick() {
		plugin = JavaPlugin.getPlugin(XArmor.class);
	}
	
	@EventHandler
	private void invClickEvent(InventoryClickEvent e) {
		if (!(e.getWhoClicked() instanceof Player)) return;
		
		ConfigManager cmMain = plugin.mainConfig;
		Configuration mainConfig = cmMain.getConfig();
		
		// Needs to be 1 tick later otherwise won't register the current leather being placed.
		new BukkitRunnable() {
			@Override
			public void run() {
				int colors[] = Utils.checkLeatherColors(e.getWhoClicked().getEquipment());
				
				if (e.getWhoClicked().hasPermission("xarmor.green") || mainConfig.getBoolean("ignorePermissions")) {
					if (colors[1] > -1) { // Green
						Utils.removePotionEffect(e.getWhoClicked(), PotionEffectType.HEALTH_BOOST);
						e.getWhoClicked().addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, XArmor.INFINITY, colors[1]));
					} else {
						Utils.removePotionEffect(e.getWhoClicked(), PotionEffectType.HEALTH_BOOST);
					}
				}
				
				if (e.getWhoClicked().hasPermission("xarmor.orange") || mainConfig.getBoolean("ignorePermissions")) {
					if (colors[7] > 2) { // Orange
						e.getWhoClicked().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, XArmor.INFINITY, colors[7]));
					} else {
						Utils.removePotionEffect(e.getWhoClicked(), PotionEffectType.FIRE_RESISTANCE);
					}
				}
				
				if (e.getWhoClicked().hasPermission("xarmor.white") || mainConfig.getBoolean("ignorePermissions")) {
					if (colors[8] > -1) { // White
						Utils.removePotionEffect(e.getWhoClicked(), PotionEffectType.DAMAGE_RESISTANCE);
						e.getWhoClicked().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, XArmor.INFINITY, colors[8]));
						Utils.removePotionEffect(e.getWhoClicked(), PotionEffectType.HEALTH_BOOST);
						e.getWhoClicked().addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, XArmor.INFINITY, colors[8]));
					} else {
						Utils.removePotionEffect(e.getWhoClicked(), PotionEffectType.DAMAGE_RESISTANCE);
						Utils.removePotionEffect(e.getWhoClicked(), PotionEffectType.HEALTH_BOOST);
					}
				}

				if (e.getWhoClicked().hasPermission("xarmor.lightblue") || mainConfig.getBoolean("ignorePermissions")) {
					if (colors[12] > -1) { // Light Blue
						Utils.removePotionEffect(e.getWhoClicked(), PotionEffectType.DAMAGE_RESISTANCE);
						e.getWhoClicked().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, XArmor.INFINITY, colors[12]));
					} else {
						Utils.removePotionEffect(e.getWhoClicked(), PotionEffectType.DAMAGE_RESISTANCE);
					}
				}
			}
		}.runTaskLater(JavaPlugin.getPlugin(XArmor.class), 1);
		
		// 5 - HELMET SLOT
		// 6 - CHESTPLATE SLOT
		// 7 - LEGGINGS SLOT
		// 8 - BOOTS SLOT
	}

	@EventHandler
	private void entityDamageEntityEvent(EntityDamageByEntityEvent e) {
		Entity attacker = e.getDamager();
		Entity defender = e.getEntity();

		if (attacker instanceof LivingEntity && defender instanceof LivingEntity) {
			LivingEntity livingAttacker = (LivingEntity) attacker;
			LivingEntity livingDefender = (LivingEntity) defender;

			armorThorns(livingDefender, livingAttacker);
			armorBuffs(livingDefender);
		}
	}

	private void armorBuffs(LivingEntity entity) {
		ConfigManager cmMain = plugin.mainConfig;
		Configuration mainConfig = cmMain.getConfig();
		
		int colors[] = Utils.checkLeatherColors(entity.getEquipment());
		
		if (entity.hasPermission("xarmor.orange") || mainConfig.getBoolean("ignorePermissions")) {
			if (colors[7] > -1) { // Orange
				if (Math.random() < Utils.colorChance(colors[7])) {
					entity.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, XArmor.BASE_DURATION * colors[7], colors[7]));
				}
			}
		}
		
		if (entity.hasPermission("xarmor.white") || mainConfig.getBoolean("ignorePermissions")) {
			if (colors[8] > -1) { // White
				if (Math.random() < Utils.colorChance(colors[8])) {
					entity.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, XArmor.BASE_DURATION * colors[8], colors[8]));
				}
			}
		}
	}

	private void armorThorns(LivingEntity defender, LivingEntity attacker) {
		ConfigManager cmMain = plugin.mainConfig;
		Configuration mainConfig = cmMain.getConfig();
		
		int colors[] = Utils.checkLeatherColors(defender.getEquipment()); // Check if defender has the armor.
		
		if (defender.hasPermission("xarmor.black") || mainConfig.getBoolean("ignorePermissions")) {
			if (colors[0] > -1) { // Black
				attacker.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, XArmor.BASE_DURATION * colors[0], colors[0]));
				attacker.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, XArmor.BASE_DURATION * colors[0], colors[0]));
			}
		}
	}
}
