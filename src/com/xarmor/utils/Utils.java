package com.xarmor.utils;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffectType;

public class Utils {
	public final static Color COLOR_BLACK = Color.fromRGB(0x191919); // Checked
	public final static Color COLOR_GREEN = Color.fromRGB(0x667F33); // Checked
	public final static Color COLOR_LIME = Color.fromRGB(0x4C7219); // Checked
	public final static Color COLOR_RED = Color.fromRGB(0x85602C); // Checked
	public final static Color COLOR_GRAY = Color.fromRGB(0x68563C); // Checked
	public final static Color COLOR_LIGHT_GRAY = Color.fromRGB(0x80776A); // Checked
	public final static Color COLOR_YELLOW = Color.fromRGB(0xB2AE4E); // Checked
	public final static Color COLOR_ORANGE = Color.fromRGB(0xC59640); // Checked
	public final static Color COLOR_WHITE = Color.fromRGB(0xFFFFFF); // Checked
	public final static Color COLOR_PURPLE = Color.fromRGB(0xBB7A8C); // Checked
	public final static Color COLOR_CYAN = Color.fromRGB(0xAF96B7); // Checked
	public final static Color COLOR_PINK = Color.fromRGB(0xD67C98); // Checked
	public final static Color COLOR_LIGHT_BLUE = Color.fromRGB(0xB6C1B5); // Checked
	public final static Color COLOR_MAGENTA = Color.fromRGB(0x9E71CF); // Checked

	public static void removePotionEffect(LivingEntity entity, PotionEffectType type) {
		if (entity.hasPotionEffect(type)) {
			entity.removePotionEffect(type);
		}
	}
	
	public static double colorChance(int colorPower) {
		double percent = (colorPower + 1) / 100.0;
		return percent * 0.25;
	}
	
	/**
	 * 0 - Black // 1 - Green // 2 - Lime // 3 - Red // 4 - Gray // 5 - Light Gray
	 * // 6 - Yellow // 7 - Orange // 8 - White // 9 - Purple // 10 - Cyan // 11 -
	 * Pink // 12 - Light Blue // 13 - Magenta
	 */
	public static int[] checkLeatherColors(EntityEquipment equip) {
		LeatherArmorMeta helmetMeta = Utils.getLeatherHelmetMeta(equip.getHelmet());
		LeatherArmorMeta chestplateMeta = Utils.getLeatherChestplateMeta(equip.getChestplate());
		LeatherArmorMeta leggingsMeta = Utils.getLeatherLeggingsMeta(equip.getLeggings());
		LeatherArmorMeta bootsMeta = Utils.getLeatherBootsMeta(equip.getBoots());
		
		Object colors[] = { Utils.COLOR_BLACK, Utils.COLOR_GREEN, Utils.COLOR_LIME, Utils.COLOR_RED, Utils.COLOR_GRAY,
				Utils.COLOR_LIGHT_GRAY, Utils.COLOR_YELLOW, Utils.COLOR_ORANGE, Utils.COLOR_WHITE, Utils.COLOR_PURPLE,
				Utils.COLOR_CYAN, Utils.COLOR_PINK, Utils.COLOR_LIGHT_BLUE, Utils.COLOR_MAGENTA };
		int[] color = new int[colors.length];
		
		/*
		 * Potion effects start at level 0.
		 */
		for (int i = 0; i < colors.length; i++) {
			color[i] = -1;
		}
		
		for (int i = 0; i < colors.length; i++) {
			if (Utils.checkLeatherMetaColor(helmetMeta, colors[i]))
				color[i]++;
			if (Utils.checkLeatherMetaColor(chestplateMeta, colors[i]))
				color[i]++;
			if (Utils.checkLeatherMetaColor(leggingsMeta, colors[i]))
				color[i]++;
			if (Utils.checkLeatherMetaColor(bootsMeta, colors[i]))
				color[i]++;
		}
	
		return color;
	}

	public static boolean checkLeatherMetaColor(LeatherArmorMeta meta, Object color) {
		return meta != null && meta.getColor().equals(color);
	}

	public static LeatherArmorMeta getLeatherHelmetMeta(ItemStack helmet) {
		if (helmet != null && helmet.getType() == Material.LEATHER_HELMET) {
			return (LeatherArmorMeta) helmet.getItemMeta();
		}
		return null;
	}

	public static LeatherArmorMeta getLeatherChestplateMeta(ItemStack chestplate) {
		if (chestplate != null && chestplate.getType() == Material.LEATHER_CHESTPLATE) {
			return (LeatherArmorMeta) chestplate.getItemMeta();
		}
		return null;
	}

	public static LeatherArmorMeta getLeatherLeggingsMeta(ItemStack leggings) {
		if (leggings != null && leggings.getType() == Material.LEATHER_LEGGINGS) {
			return (LeatherArmorMeta) leggings.getItemMeta();
		}
		return null;
	}

	public static LeatherArmorMeta getLeatherBootsMeta(ItemStack boots) {
		if (boots != null && boots.getType() == Material.LEATHER_BOOTS) {
			return (LeatherArmorMeta) boots.getItemMeta();
		}
		return null;
	}
}
