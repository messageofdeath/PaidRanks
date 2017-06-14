package me.messageofdeath.paidranks.utils.api;

import java.util.UUID;

import me.messageofdeath.paidranks.PaidRanks;
import me.messageofdeath.paidranks.utils.laddermanager.Ladder;
import me.messageofdeath.paidranks.utils.rankmanager.Rank;
import me.messageofdeath.paidranks.utils.zrequired.economy.Economy;
import me.messageofdeath.paidranks.utils.zrequired.permissions.Permissions;

public class PaidRanksAPI {
	
	private static PaidRanks instance;
	private static Economy economy;
	private static Permissions permissions;

	public static Rank getRank(String ladder, String rank) {
		return getLadder(ladder).getRank(rank);
	}

	public static Rank getNextRank(String ladder, String rank) {
		return getLadder(ladder).getNextRank(rank);
	}

	public static double getPrice(String ladder, String rank) {
		return getLadder(ladder).getRank(rank).getPrice();
	}

	public static String getPriceFormatted(String ladder, String rank) {
		return economy.getFormat(getLadder(ladder).getRank(rank).getPrice());
	}

	public static double getNextPrice(String ladder, String rank) {
		return getLadder(ladder).getNextRank(rank).getPrice();
	}

	public static String getNextPriceFormatted(String ladder, String rank) {
		return economy.getFormat(getLadder(ladder).getRank(rank).getPrice());
	}

	public static boolean hasRank(String ladder, String rank) {
		return getLadder(ladder).hasRank(rank);
	}

	public static boolean hasLadder(String ladder) {
		return instance.getLadderManager().hasLadder(ladder);
	}

	private static Ladder getLadder(String ladder) {
		return instance.getLadderManager().getLadder(ladder);
	}
	
	//-------------------------- Economy --------------------------
	
	public static boolean isEconomyActivated() {
		return economy != null;
	}
	
	public static void withdrawMoney(UUID player, double amount) {
		economy.withdrawMoney(player, amount);
	}
	
	public static void depositMoney(UUID player, double amount) {
		economy.depositMoney(player, amount);
	}
	
	public static boolean hasEnoughMoney(UUID player, double amount) {
		return economy.hasEnoughMoney(player, amount);
	}

	public static void setInstance(PaidRanks instance) {
		PaidRanksAPI.instance = instance;
	}
	
	public static boolean hasAccount(UUID player) {
		return economy.hasAccount(player);
	}
	
	public static void createAccount(UUID player) {
		economy.createAccount(player);
	}
	
	public static String getFormat(double amount) {
		return economy.getFormat(amount);
	}
	
	//-------------------------- Permissions --------------------------
	
	public static void addGroup(UUID uuid, String world, String groupName) {
		permissions.addGroup(uuid, world, groupName);
	}
	
	public static void removeGroup(UUID uuid, String world, String groupName) {
		permissions.removeGroup(uuid, world, groupName);
	}
	
	public static void setGroup(UUID uuid, String world, String oldGroup, String newGroup) {
		permissions.setGroup(uuid, world, oldGroup, newGroup);
	}
	
	public static boolean hasGroup(UUID uuid, String world, String groupName) {
		return permissions.hasGroup(uuid, world, groupName);
	}
	
	public static String[] getGroups(UUID uuid, String world) {
		return permissions.getGroups(uuid, world);
	}
	
	public static int amountOfGroups(UUID uuid, String world) {
		return permissions.amountOfGroups(uuid, world);
	}
	
	public static void setEconomy(Economy economy) {
		PaidRanksAPI.economy = economy;
	}
	
	public static void setPermissions(Permissions permissions) {
		PaidRanksAPI.permissions = permissions;
	}
}
