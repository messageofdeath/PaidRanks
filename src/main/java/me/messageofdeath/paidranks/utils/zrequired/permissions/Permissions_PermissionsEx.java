package me.messageofdeath.paidranks.utils.zrequired.permissions;

import java.util.UUID;

import me.messageofdeath.paidranks.PaidRanks;
import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Permissions_PermissionsEx extends Permissions {

	public Permissions_PermissionsEx(PaidRanks plugin) {
		super(plugin);
	}

	@Override
	public void addGroup(UUID player, String world, String groupName) {
		PermissionGroup group = PermissionsEx.getPermissionManager().getGroup(groupName);
		PermissionUser user = PermissionsEx.getPermissionManager().getUser(player);
		if(group != null && user != null) {
			user.addGroup(group, world);
		}
	}

	@Override
	public void removeGroup(UUID player, String world, String groupName) {
		PermissionGroup group = PermissionsEx.getPermissionManager().getGroup(groupName);
		PermissionUser user = PermissionsEx.getPermissionManager().getUser(player);
		if(group != null && user != null) {
			user.removeGroup(group, world);
		}
	}

	@Override
	public boolean hasGroup(UUID player, String world, String group) {
		return PermissionsEx.getPermissionManager().getUser(player).inGroup(group, world);
	}

	@Override
	public String[] getGroups(UUID uuid, String world) {
		PermissionUser user = PermissionsEx.getPermissionManager().getUser(uuid);
		return user != null ? user.getParentIdentifiers(world).toArray(new String[0]) : null;
	}

	@Override
	public boolean setupPermissions() {
		return true;
	}
}
