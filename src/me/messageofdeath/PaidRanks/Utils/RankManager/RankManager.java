package me.messageofdeath.PaidRanks.Utils.RankManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import me.messageofdeath.PaidRanks.PaidRanks;

public class RankManager {
	private PaidRanks instance;
	private String ladder;
	private ArrayList<Rank> ranks;

	public RankManager(PaidRanks instance, String ladder) {
		this.instance = instance;
		this.ladder = ladder;
		this.ranks = new ArrayList<Rank>();
	}

	public String getLadder() {
		return this.ladder;
	}

	public void addRank(Rank rank) {
		if (!hasRank(rank.getName())) {
			this.ranks.add(rank);
		}
	}

	public void removeRank(String rank) {
		if (hasRank(rank)) {
			this.ranks.remove(getRank(rank));
			this.instance.getDatabaseManager().getRankDatabase().saveDatabase();
		}
	}

	public boolean hasRank(String rank) {
		return getRank(rank) != null;
	}
	
	public boolean hasRank(int position) {
		return getRank(position) != null;
	}

	public Rank getRank(String rank) {
		for (Rank rankx : this.ranks) {
			if (rankx.getName().equalsIgnoreCase(rank)) {
				return rankx;
			}
		}
		return null;
	}

	public ArrayList<Rank> getRanks() {
		ArrayList<Rank> ranks = new ArrayList<Rank>();
		ranks.addAll(this.ranks);
		return ranks;
	}

	public String toString() {
		String i = "";
		for (Rank rank : this.ranks) {
			if (!i.isEmpty()) {
				i = i + ";";
			}
			i = i + rank.toString();
		}
		return i;
	}

	/*public static ArrayList<Rank> toRanks(String input) {
		if ((!input.isEmpty()) && (input != null)) {
			ArrayList<Rank> ranks = new ArrayList<Rank>();
			String[] arrayOfString;
			int j = (arrayOfString = input.split(";")).length;
			for (int i = 0; i < j; i++) {
				String rank = arrayOfString[i];
				ranks.add(Rank.toRank(rank));
			}
			return ranks;
		}
		return new ArrayList<Rank>();
	}*/

	public void setPosition(Rank rank, int position) {
		ArrayList<Rank> ranks = this.ranks;
		for (Rank rankx : ranks) {
			if (rankx.getPosition() >= position) {
				rankx.setPosition(rankx.getPosition() + 1);
			}
		}
		for (Rank rankx : ranks) {
			if (rankx.getName().equalsIgnoreCase(rank.getName())) {
				rankx.setPosition(position);
				break;
			}
		}
		checkPositions();
	}

	public void checkPositions() {
		ArrayList<Rank> ranks = this.ranks;
		Collections.sort(ranks, comparePosition());
		if (!ranks.isEmpty()) {
			int lastPosition = ((Rank) ranks.get(0)).getPosition();
			int difference = 0;
			Rank rankx = null;
			for (int i = 0; i < ranks.size(); i++) {
				rankx = (Rank) ranks.get(i);
				if (lastPosition != rankx.getPosition()) {
					difference = rankx.getPosition() - lastPosition;
					if (difference > 1) {
						for (int x = i; x < ranks.size(); x++) {
							((Rank) ranks.get(x)).setPosition(((Rank) ranks.get(x)).getPosition() - difference);
						}
					}
					difference = rankx.getPosition() - lastPosition;
					if (difference == 0) {
						for (int x = i; x < ranks.size(); x++) {
							((Rank) ranks.get(x)).setPosition(((Rank) ranks.get(x)).getPosition() + 1);
						}
					}
					lastPosition = rankx.getPosition();
				}
			}
			Collections.sort(ranks, comparePosition());
			this.ranks = ranks;
		}
	}

	public int getNextPosition() {
		int i = 1;
		ArrayList<Integer> ids = new ArrayList<Integer>();
		for (Rank rank : this.ranks) {
			ids.add(Integer.valueOf(rank.getPosition()));
		}
		for (;;) {
			if (!ids.contains(Integer.valueOf(i))) {
				return i;
			}
			i++;
		}
	}

	public Rank getRequiredRank(String rank) {
		return hasRank(rank) ? getRank(getRank(rank).getPosition() - 1) : null;
	}

	public Rank getNextRank(String r) {
		if(!this.ranks.isEmpty()) {
			if(hasRank(r)) {
				if(hasRank(getRank(r).getPosition() + 1)) {
					return getRank(getRank(r).getPosition() + 1);
				}
			}else{
				return this.ranks.get(0);
			}
		}
		return null;
	}

	private Rank getRank(int position) {
		for (Rank rankx : getRanks()) {
			if (rankx.getPosition() == position) {
				return rankx;
			}
		}
		return null;
	}

	private static Comparator<Rank> comparePosition() {
		return new Comparator<Rank>() {
			public int compare(Rank rank1, Rank rank2) {
				if (rank1.getPosition() > rank2.getPosition()) {
					return 1;
				}
				if (rank1.getPosition() < rank2.getPosition()) {
					return -1;
				}
				return 0;
			}
		};
	}
}
