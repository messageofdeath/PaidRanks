package me.messageofdeath.PaidRanks.Database.Databases;

import java.util.ArrayList;
import me.messageofdeath.PaidRanks.PaidRanks;
import me.messageofdeath.PaidRanks.Utils.LadderManager.Ladder;
import me.messageofdeath.PaidRanks.Utils.RankManager.Rank;
import me.messageofdeath.PaidRanks.Utils.zRequired.Database.YamlDatabase;
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
		String pre = "Ladders";
		for (String ladder : this.database.getSection(pre, new ArrayList<String>())) {
			Ladder ladderx = new Ladder(this.instance, ladder);
			pre += "." + ladder + ".Ranks";
			instance.log(pre, true);
			ArrayList<String> ranks = this.database.getSection(pre, new ArrayList<String>());
			pre += ".";
			instance.log(pre, true);
			for(String rank : ranks) {
				pre = "Ladders."+ladder+".Ranks."+rank+".";
				ladderx.addRank(rank, this.database.getString(pre + "Perm", "noPerm"), this.database.getInteger(pre + "Position", 0), 
						this.database.getInteger(pre + "Price", 0));
			}
			pre = "Ladders." + ladder + ".";
			instance.log(pre, true);
			ladderx.setRequiresRank(this.database.getBoolean(pre + "RequiresRank", false));
			ladderx.setDefault(this.database.getBoolean(pre + "Default", false));
			this.instance.getLadderManager().addLadder(ladderx);
		}
	}

	public void deleteLadder(String ladder) {
		this.database.set("Ladders." + ladder, null);
	}

	public void saveDatabase() {
		instance.log("Saving Database", true);
		for (Ladder ladder : this.instance.getLadderManager().getLadders()) {
			String pre = "Ladders." + ladder.getName() + ".";
			this.database.set(pre + "Default", Boolean.valueOf(ladder.isDefault()));
			this.database.set(pre + "RequiresRank", Boolean.valueOf(ladder.isRequiresRank()));
			for(Rank rank : ladder.getRanks()) {
				pre = "Ladders." + ladder.getName() + ".Ranks." + rank.getName() + ".";
				this.database.set(pre + "Perm", rank.getPermission());
				this.database.set(pre + "Position", rank.getPosition());
				this.database.set(pre + "Price", rank.getPrice());
			}
		}
	}
}



















