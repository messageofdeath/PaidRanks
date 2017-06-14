package me.messageofdeath.paidranks.utils.zrequired.economy;

import me.messageofdeath.paidranks.PaidRanks;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.UUID;

public class Economy_Vault extends Economy {

    private net.milkbowl.vault.economy.Economy econ;

    public Economy_Vault(PaidRanks plugin) {
        super(plugin);
    }

    @Override
    public void withdrawMoney(UUID uuid, double amount) {
        econ.withdrawPlayer(super.getOfflinePlayer(uuid), amount);
    }

    @Override
    public void depositMoney(UUID uuid, double amount) {
        econ.depositPlayer(super.getOfflinePlayer(uuid), amount);
    }

    @Override
    public boolean hasEnoughMoney(UUID uuid, double amount) {
        return econ.has(super.getOfflinePlayer(uuid), amount);
    }

    @Override
    public boolean hasAccount(UUID uuid) {
        return econ.hasAccount(super.getOfflinePlayer(uuid));
    }

    @Override
    public void createAccount(UUID uuid) {
        econ.createPlayerAccount(super.getOfflinePlayer(uuid));
    }

    @Override
    public String getFormat(double amount) {
        return econ.format(amount);
    }

    @Override
    public boolean setupEconomy() {
        RegisteredServiceProvider<net.milkbowl.vault.economy.Economy> rsp = plugin.getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        econ = rsp.getProvider();
        return rsp.getProvider() != null;
    }
}
