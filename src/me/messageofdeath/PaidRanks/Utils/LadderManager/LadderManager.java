package me.messageofdeath.PaidRanks.Utils.LadderManager;

import java.util.ArrayList;
import me.messageofdeath.PaidRanks.PaidRanks;

public class LadderManager {
	private PaidRanks instance;
	private ArrayList<Ladder> ladders;

	public LadderManager(PaidRanks instance) {
		this.instance = instance;
		this.ladders = new ArrayList<Ladder>();
	}

	public void addLadder(Ladder ladder) {
		if (!hasLadder(ladder.getName())) {
			this.ladders.add(ladder);
		}
	}

	public void removeLadder(String ladder) {
		if (hasLadder(ladder)) {
			this.ladders.remove(getLadder(ladder));
			this.instance.getDatabaseManager().getRankDatabase().deleteLadder(ladder);
			this.instance.getDatabaseManager().getRankDatabase().saveDatabase();
		}
	}

	public boolean hasLadder(String ladder) {
		return getLadder(ladder) != null;
	}

	public Ladder getLadder(String ladderX) {
		for (Ladder ladder : this.ladders) {
			if (ladder.getName().equalsIgnoreCase(ladderX)) {
				return ladder;
			}
		}
		return null;
	}

	public ArrayList<Ladder> getLadders() {
		ArrayList<Ladder> ladders = new ArrayList<Ladder>();
		ladders.addAll(this.ladders);
		return ladders;
	}

	public void reset() {
		this.ladders.clear();
	}

	public void setDefaultLadder(String ladder) {
		for (Ladder ladderx : this.ladders) {
			ladderx.setDefault(ladderx.getName().equalsIgnoreCase(ladder));
		}
	}

	public void checkDefaultLadder() {
		boolean hasDefault = false;
		for (Ladder ladder : this.ladders) {
			if (ladder.isDefault()) {
				if (!hasDefault) {
					hasDefault = true;
				} else {
					ladder.setDefault(false);
				}
			}
		}
	}

	public boolean hasDefaultLadder() {
		return getDefaultLadder() != null;
	}

	public Ladder getDefaultLadder() {
		for (Ladder ladder : this.ladders) {
			if (ladder.isDefault()) {
				return ladder;
			}
		}
		return null;
	}
}
