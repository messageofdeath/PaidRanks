package me.messageofdeath.paidranks.database.databases;

import java.util.ArrayList;
import me.messageofdeath.paidranks.PaidRanks;
import me.messageofdeath.paidranks.utils.laddermanager.Ladder;
import me.messageofdeath.paidranks.utils.rankmanager.Rank;
import me.messageofdeath.paidranks.utils.zrequired.database.YamlDatabase;
import org.bukkit.plugin.java.JavaPlugin;

public class RankDatabase {
	
	private PaidRanks instance;
	private YamlDatabase database;

	public RankDatabase(JavaPlugin instance) {
		this.instance = ((PaidRanks) instance);
	}

	public void initDatabase() {
		this.database = new YamlDatabase(this.instance, "ranks");
		this.database.onStartUp();
	}

	public void loadDatabase() {
		boolean hasDefault = false;
		String pre = "Ladders";
		for (String ladder : this.database.getSection(pre, new ArrayList<String>())) {
			Ladder ladderx = new Ladder(this.instance, ladder, database.getString("Ladders."+ladder+".World", null));
			pre += "." + ladder + ".Ranks";
			ArrayList<String> ranks = this.database.getSection(pre, new ArrayList<String>());
			pre += ".";
			for(String rank : ranks) {
				pre = "Ladders."+ladder+".Ranks."+rank+".";
				ladderx.addRank(rank, this.database.getString(pre + "Perm", "noPerm"), this.database.getInteger(pre + "Position", 0), 
						this.database.getInteger(pre + "Price", 0));
			}
			pre = "Ladders." + ladder + ".";
			ladderx.setRequiresRank(this.database.getBoolean(pre + "RequiresRank", false));
			boolean isDefault = this.database.getBoolean(pre + "Default", false);
			if(hasDefault && isDefault) {//There is a default ladder currently & This is a default ladder
				this.instance.logError("Ladders on load", "RankDatabase", "loadDatabase()", "There are multiple ladders with 'default' classification. Removing 'default' "
						+ "classification from '"+ladder+"' ladder.");
				isDefault = false;
			}
			if(!hasDefault && isDefault) {//No previous default ladders & This is a default ladder
				hasDefault = true;
			}
			ladderx.setDefault(isDefault);
			ladderx.checkPositions();
			this.instance.getLadderManager().addLadder(ladderx);
			pre = "Ladders";
		}
	}

	public void deleteLadder(String ladder) {
		this.database.set("Ladders." + ladder, null);
	}
	
	public void deleteRank(String ladder, String rank) {
		this.database.set("Ladders." + ladder + ".Ranks." + rank, null);
	}

	public void saveDatabase() {
		instance.log("Saving Database", true);
		for (Ladder ladder : this.instance.getLadderManager().getLadders()) {
			String pre = "Ladders." + ladder.getName() + ".";
			this.database.set(pre + "Default", ladder.isDefault());
			this.database.set(pre + "World", ladder.getWorld());
			this.database.set(pre + "RequiresRank", ladder.isRequiresRank());
			for(Rank rank : ladder.getRanks()) {
				pre = "Ladders." + ladder.getName() + ".Ranks." + rank.getName() + ".";
				this.database.set(pre + "Perm", rank.getPermission());
				this.database.set(pre + "Position", rank.getPosition());
				this.database.set(pre + "Price", rank.getPrice());
			}
		}
	}
}
