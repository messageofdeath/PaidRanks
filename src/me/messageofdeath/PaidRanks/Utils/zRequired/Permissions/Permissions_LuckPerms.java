package me.messageofdeath.PaidRanks.Utils.zRequired.Permissions;

import java.util.List;
import java.util.UUID;

import me.lucko.luckperms.api.context.MutableContextSet;
import me.lucko.luckperms.common.core.model.Group;
import me.lucko.luckperms.common.core.model.PermissionHolder;
import me.lucko.luckperms.common.core.model.User;
import me.lucko.luckperms.common.plugin.LuckPermsPlugin;
import me.lucko.luckperms.exceptions.ObjectAlreadyHasException;
import me.messageofdeath.PaidRanks.PaidRanks;

public class Permissions_LuckPerms extends Permissions {
	
	private LuckPermsPlugin luckPlugin;

	public Permissions_LuckPerms(PaidRanks plugin) {
		super(plugin);
	}

	@Override
	public void addGroup(UUID uuid, String world, String groupName) {
		User user = this.luckPlugin.getUserManager().get(uuid);
		Group group = this.luckPlugin.getGroupManager().getIfLoaded(groupName);
		PermissionHolder permissionHolder = (PermissionHolder)user;
		permissionHolder.setInheritGroup(group, MutableContextSet.create());
		try {
			this.luckPlugin.getApiProvider().getUser(user.getName()).setPrimaryGroup(groupName);
		} catch (ObjectAlreadyHasException e) {
		}
		this.luckPlugin.getStorage().saveUser((User)permissionHolder);
	}

	@Override
	public void removeGroup(UUID uuid, String world, String groupName) {
		User user = this.luckPlugin.getUserManager().get(uuid);
		Group group = this.luckPlugin.getGroupManager().getIfLoaded(groupName);
		PermissionHolder permissionHolder = (PermissionHolder)user;
		permissionHolder.unsetInheritGroup(group, MutableContextSet.create());
		this.luckPlugin.getStorage().saveUser((User)permissionHolder);
	}

	@Override
	public boolean hasGroup(UUID uuid, String world, String groupName) {
		return this.plugin.getServer().getPlayer(uuid).hasPermission("group." + groupName);
	}

	@Override
	public String[] getGroups(UUID uuid, String world) {
		List<String> s = this.luckPlugin.getApiProvider().getUser(uuid).getGroupNames();
		String[] out = new String[s.size()];
		for(int i = 0; i < s.size(); i++) {
			out[i] = s.get(i);
		}
		return out;
	}

	@Override
	public int amountOfGroups(UUID uuid) {
		return this.luckPlugin.getApiProvider().getUser(uuid).getGroupNames().size();
	}

	@Override
	public boolean setupPermissions() {
		this.luckPlugin = (LuckPermsPlugin)super.plugin.getServer().getPluginManager().getPlugin("LuckPerms");
		if(this.luckPlugin != null) {
			return true;
		}
		return false;
	}
}
