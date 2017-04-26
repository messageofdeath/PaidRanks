package me.messageofdeath.PaidRanks.Utils.zRequired.Permissions;

import me.messageofdeath.PaidRanks.PaidRanks;

public class PermissionsManager {
	
	private PaidRanks plugin;
	private Permissions permissions;
	
	public PermissionsManager(PaidRanks plugin) {
		this.plugin = plugin;
	}

	public void startPermissions() {
		if(this.plugin.getServer().getPluginManager().isPluginEnabled("PermissionsEx")) {
			this.permissions = new Permissions_PermissionsEx(this.plugin);
			this.plugin.log("Attempting to hook with PermissionsEx!", true);
			this.plugin.log(this.permissions.setupPermissions() ? "Successfully hooked with PermissionsEx!" : "Failed to hook with PermissionsEx!", true);
		}else if(this.plugin.getServer().getPluginManager().isPluginEnabled("LuckPerms")) {
			this.permissions = new Permissions_LuckPerms(this.plugin);
			this.plugin.log("Attempting to hook with LuckPerms!", true);
			this.plugin.log(this.permissions.setupPermissions() ? "Successfully hooked with LuckPerms!" : "Failed to hook with LuckPerms!", true);
		}else{
			this.plugin.logError("PermissionsManager", "Permissions", "startPermissions()", "No supported Permissions plugin!");
		}
	}
	
	public boolean isPermissionsActivated() {
		return this.permissions != null;
	}
	
	public Permissions getPermissions() {
		return this.permissions;
	}
}
