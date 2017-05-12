package me.messageofdeath.PaidRanks.Utils.LadderManager;

import java.util.ArrayList;
import me.messageofdeath.PaidRanks.PaidRanks;
import me.messageofdeath.PaidRanks.Utils.RankManager.Rank;
import me.messageofdeath.PaidRanks.Utils.RankManager.RankManager;

public class Ladder {
	
	private PaidRanks instance;
	private String name;
	private boolean isDefault;
	private boolean isRequiresRank;
	private RankManager rankManager;

	public Ladder(PaidRanks instance, String name) {
		this.instance = instance;
		this.name = name;
		this.rankManager = new RankManager(this.instance, this.name);
	}

	public String getName() {
		return this.name;
	}

	public boolean isDefault() {
		return this.isDefault;
	}

	public boolean isRequiresRank() {
		return this.isRequiresRank;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	public void setRequiresRank(boolean isRequiresRank) {
		this.isRequiresRank = isRequiresRank;
	}

	public void addRank(String rank, String permission, double price) {
		checkPositions();
		addRank(rank, permission, getNextPosition(), price);
	}

	public void addRank(String rank, String permission, int position, double price) {
		this.rankManager.addRank(new Rank(rank, permission, position, price));
	}

	public void removeRank(String rank) {
		this.rankManager.removeRank(rank);
	}

	public boolean hasRank(String rank) {
		return this.rankManager.hasRank(rank);
	}

	public Rank getRank(String rank) {
		return this.rankManager.getRank(rank);
	}

	public ArrayList<Rank> getRanks() {
		return this.rankManager.getRanks();
	}

	public void setPosition(int oldPosition, int newPosition) {
		this.rankManager.setPosition(oldPosition, newPosition);
	}

	public void checkPositions() {
		this.rankManager.checkPositions();
	}

	private int getNextPosition() {
		return this.rankManager.getNextPosition();
	}

	public Rank getRequiredRank(String rank) {
		return this.rankManager.getRequiredRank(rank);
	}

	public Rank getNextRank(String rank) {
		return this.rankManager.getNextRank(rank);
	}

	public String toString() {
		return this.rankManager.toString();
	}
}
