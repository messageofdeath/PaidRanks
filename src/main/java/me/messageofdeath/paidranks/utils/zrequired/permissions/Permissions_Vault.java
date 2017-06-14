package me.messageofdeath.paidranks.utils.zrequired.permissions;

import me.messageofdeath.paidranks.PaidRanks;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.UUID;

public class Permissions_Vault extends Permissions {

    private Permission perm;

    public Permissions_Vault(PaidRanks plugin) {
        super(plugin);
    }

    @Override
    public void addGroup(UUID uuid, String world, String group) {
        perm.playerAddGroup(world, super.getOfflinePlayer(uuid), group);
    }

    @Override
    public void removeGroup(UUID uuid, String world, String group) {
        perm.playerRemoveGroup(world, super.getOfflinePlayer(uuid), group);
    }

    @Override
    public boolean hasGroup(UUID uuid, String world, String group) {
        return perm.playerInGroup(world, super.getOfflinePlayer(uuid), group);
    }

    @Override
    public String[] getGroups(UUID uuid, String world) {
        return perm.getPlayerGroups(world, super.getOfflinePlayer(uuid));
    }

    @Override
    public int amountOfGroups(UUID uuid, String world) {
        return getGroups(uuid, world).length;
    }

    @Override
    public boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = plugin.getServer().getServicesManager().getRegistration(Permission.class);
        perm = rsp.getProvider();
        return perm != null;
    }
}
