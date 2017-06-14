package me.messageofdeath.paidranks.database.language;

public enum LanguageSettings {
	
	/**-------------------------------------- General --------------------------------------**/
	Commands_PluginTag("&8[&4PaidRanks&8] &6"), 
	Commands_ErrorTag("&0[&4Error&0] &c"), 
	Commands_NoPermission("You do not have permission for this!"), 
	Commands_WrongArgs("Wrong amount of arguments inputted!"), 
	Commands_MustBeNumeric("The argument '%arg' must be numeric!"),
	Commands_LadderExists("A ladder with the name already exists!"), 
	Commands_LadderDoesNotExist("A ladder with that name does not exist!"), 
	Commands_RankExists("A rank with that name already exists!"), 
	Commands_RankDoesNotExist("A rank with that name does not exist!"),
	
	/**-------------------------------------- PaidRanks Command --------------------------------------**/
	Commands_PaidRanks_Reload_Plugin("You have successfully reloaded the plugin!"), 
	Commands_PaidRanks_Reload_Language("You have successfully reload the language file!"), 
	Commands_PaidRanks_Reload_Ranks("You have successfully reloaded the ranks!"), 
	Commands_PaidRanks_Rank_Add("You have successfully added a rank to the ladder '%ladder'!"), 
	Commands_PaidRanks_Rank_Remove("You have successfully removed a rank from the ladder '%ladder'!"),
	Commands_PaidRanks_Rank_Set("You have set the value of '%type' to '%value' for rank '%rank'!"),
	Commands_PaidRanks_Rank_SetError("The 'type' argument must be 'price', 'perm', or 'id'!"),
	Commands_PaidRanks_Rank_SetPositionError("The Id must be between 1 and %id!"), 
	Commands_PaidRanks_Rank_List_Top("Available Ranks in '%ladder':"), 
	Commands_PaidRanks_Rank_List_Prefix("&8 -&a "), 
	Commands_PaidRanks_Rank_List_Format("&4%position) &6%name &2%cash &c%permission"), 
	Commands_PaidRanks_Rank_NotAvailable("No available ranks to show at the moment."), 
	Commands_PaidRanks_Ladder_Create("You have successfully created a ladder!"), 
	Commands_PaidRanks_Ladder_CreateDefault("This ladder will take the place of the default ladder."), 
	Commands_PaidRanks_Ladder_Remove("You have successfully removed the ladder '%ladder'!"), 
	Commands_PaidRanks_Ladder_RemoveDefault("You have successfully removed the &4default&6 ladder '%ladder'!"), 
	Commands_PaidRanks_Ladder_DefaultLadder("You have successfully set the default ladder to '%ladder'!"),
	Commands_PaidRanks_Ladder_SetWorld("You have successfully changed the world for the ladder!"),
	Commands_PaidRanks_Ladder_SetError("The 'type' argument must be 'default' or 'world'!"),
	Commands_PaidRanks_Ladder_Toggle_RequiresRank("You have toggled 'requiredRank' for '%ladder' to %value."),
	Commands_PaidRanks_Ladder_List_Top("Available Ladders:"), 
	Commands_PaidRanks_Ladder_List_Prefix("&8 -&a "), 
	Commands_PaidRanks_Ladder_List_Format("%name"), 
	Commands_PaidRanks_Ladder_NotAvailable("No available ladders to show at the moment."), 
	Commands_Paidranks_Info_Header("%value Information:"),
	Commands_Paidranks_Info_Values("  &7%name: &a%value"),
	Commands_Paidranks_Info_Prefix(" &8 -&a "),
	Commands_PaidRanks_Help_Top("Available Commands (%page/%totalpages):"), 
	Commands_PaidRanks_Help_Disclaimer("<> are required | [] are not required"), 
	
	/**-------------------------------------- Rankup Command --------------------------------------**/
	Commands_Rankup_HighRank("You are the highest rank possible!"), 
	Commands_Rankup_Ladder_List_Top("Available Ladders:"), 
	Commands_Rankup_Ladder_List_Prefix("&8 -&a "), 
	Commands_Rankup_Ladder_List_Format("%name"), 
	Commands_Rankup_Ladder_NotAvailable("No available ladders to show at the moment."), 
	Commands_Rankup_NextRankTop("Next Rank Information:"), 
	Commands_Rankup_Rankup("You have successfully ranked up to %rank!"), 
	Commands_Rankup_NoPerm("You do not have permission to buy this rank!"), 
	Commands_Rankup_NoMoney("You do not have the required %cash for the rank %rank!"), 
	Commands_Rankup_NoAccount("You do not have an account! We created one for you!"), 
	Commands_Rankup_NotCompatible("Your rank is not compatible with this ladder!"), 
	Commands_Rankup_NoDefault("The default ladder is not configured!"), 
	Commands_Rankup_InGame("You must be in-game to use this command!"),
	Commands_Rankup_InWorld("You need to be in the '%world' world!"),
	Commands_Rankup_Rank_List_Top("Available Ranks in '%ladder':"), 
	Commands_Rankup_Rank_List_Prefix("&8 -&a "), 
	Commands_Rankup_Rank_List_Format("&4%position) &6%name &2%cash"), 
	Commands_Rankup_Rank_NotAvailable("No available ladders to show at the moment.");

	private String setting;
	private final String defaultSetting;

	private LanguageSettings(String defaultSetting) {
		this.defaultSetting = defaultSetting;
	}

	public String getName() {
		return toString();
	}

	public void setSetting(String setting) {
		this.setting = setting;
	}

	public void setDefaultSetting() {
		this.setting = this.defaultSetting;
	}

	public String getSetting() {
		return this.setting;
	}

	public String getDefaultSetting() {
		return this.defaultSetting;
	}
}
