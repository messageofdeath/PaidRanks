package me.messageofdeath.paidranks.utils.rankmanager;

import java.util.ArrayList;
import me.messageofdeath.paidranks.PaidRanks;
import me.messageofdeath.paidranks.utils.IDHolder;
import me.messageofdeath.paidranks.utils.Utilities;

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
			Rank rankx = getRank(rank);
			this.ranks.remove(rankx);
			this.checkPositions();
			this.instance.getDatabaseManager().getRankDatabase().deleteRank(this.ladder, rankx.getName());
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
	
	//---------------------------- Position Checker ----------------------------
	
		public void checkPositions() {
			this.updatePositions(Utilities.sortIDs(this.toIDHolders()));
		}
		
		public void setPosition(int oldPlace, int newPlace) {
			this.updatePositions(Utilities.setID(oldPlace, newPlace, this.toIDHolders()));
		}
		
		public int getNextPosition() {
			return Utilities.getNextID(this.toIDHolders());
		}
		
		private void updatePositions(ArrayList<IDHolder> holders) {
			for(IDHolder holder : holders) {
				((Rank)holder.getObject()).setPosition(holder.getID());
			}
		}
		
		private ArrayList<IDHolder> toIDHolders() {
			ArrayList<IDHolder> holders = new ArrayList<IDHolder>();
			for(Rank rank : this.ranks) {
				holders.add(new IDHolder(rank.getPosition(), rank));
			}
			return holders;
		}
}
