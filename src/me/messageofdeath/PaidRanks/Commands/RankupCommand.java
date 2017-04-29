package me.messageofdeath.PaidRanks.Commands;

import java.util.ArrayList;
import java.util.UUID;
import me.messageofdeath.PaidRanks.Database.Language.LanguageSettings;
import me.messageofdeath.PaidRanks.PaidRanks;
import me.messageofdeath.PaidRanks.Utils.API.PaidRanksAPI;
import me.messageofdeath.PaidRanks.Utils.LadderManager.Ladder;
import me.messageofdeath.PaidRanks.Utils.LadderManager.LadderManager;
import me.messageofdeath.PaidRanks.Utils.RankManager.Rank;
import me.messageofdeath.PaidRanks.Utils.zRequired.Commands.Command;
import me.messageofdeath.PaidRanks.Utils.zRequired.Commands.IssuedCommand;
import me.messageofdeath.PaidRanks.Utils.zRequired.Commands.MessageCommand;
import me.messageofdeath.PaidRanks.Utils.zRequired.Commands.Messenger;
import me.messageofdeath.PaidRanks.Utils.zRequired.PageLists.Option;
import me.messageofdeath.PaidRanks.Utils.zRequired.PageLists.PageList;
import org.bukkit.ChatColor;

public class RankupCommand extends MessageCommand {
	
	private PaidRanks instance;
	private PageList list;
	private LadderManager manager;

	public RankupCommand(PaidRanks instance) {
		this.instance = instance;
		this.list = new PageList(5);
		this.manager = this.instance.getLadderManager();
		setupHelp();
		this.messenger = new Messenger(this.instance.getPrefix());
	}

	@Command(name = "rankup")
	public void issue(IssuedCommand cmd) {
		this.manager = this.instance.getLadderManager();
		if (cmd.getLength() == 0) {
			rankup(cmd, this.instance.getLadderManager().getDefaultLadder() != null
					? this.instance.getLadderManager().getDefaultLadder().getName() : "asdfghjkl");
		} else if (cmd.getLength() == 1) {
			if (cmd.getArg(0).equalsIgnoreCase("ladder")) {
				super.wrongArgs(cmd);
			} else if (cmd.getArg(0).equalsIgnoreCase("rank")) {
				super.wrongArgs(cmd);
			} else if (cmd.getArg(0).equalsIgnoreCase("help")) {
				help(cmd, 0);
			} else {
				rankup(cmd, cmd.getArg(0));
			}
		} else if (cmd.getLength() == 2) {
			if (cmd.getArg(0).equalsIgnoreCase("ladder")) {
				if (cmd.getArg(1).equalsIgnoreCase("list")) {
					listLadders(cmd);
				} else {
					help(cmd, 0);
				}
			} else if (cmd.getArg(0).equalsIgnoreCase("rank")) {
				if (cmd.getArg(1).equalsIgnoreCase("list")) {
					listRanks(cmd, this.instance.getLadderManager().getDefaultLadder().getName());
				} else if (cmd.getArg(1).equalsIgnoreCase("next")) {
					infoNextRank(cmd, this.instance.getLadderManager().getDefaultLadder().getName());
				} else {
					help(cmd, 0);
				}
			} else {
				help(cmd, 0);
			}
		} else if (cmd.getLength() == 3) {
			if (cmd.getArg(0).equalsIgnoreCase("ladder")) {
				super.wrongArgs(cmd);
			} else if (cmd.getArg(0).equalsIgnoreCase("rank")) {
				if (cmd.getArg(1).equalsIgnoreCase("list")) {
					listRanks(cmd, cmd.getArg(2));
				} else if (cmd.getArg(1).equalsIgnoreCase("next")) {
					infoNextRank(cmd, cmd.getArg(2));
				} else {
					help(cmd, 0);
				}
			} else {
				help(cmd, 0);
			}
		} else {
			help(cmd, 0);
		}
	}

	private void rankup(IssuedCommand cmd, String ladder) {
		if (cmd.getSender().hasPermission("paidranks.commands.ru.rankup")) {
			if (cmd.isPlayer()) {
				if (!ladder.equalsIgnoreCase("asdfghjkl")) {
					if (this.instance.getLadderManager().hasLadder(ladder)) {
						UUID uuid = cmd.getPlayer().getUniqueId();
						String world = PaidRanksAPI.isRanksGlobal() ? null : cmd.getPlayer().getWorld().getName();
						Ladder ladderx = this.manager.getLadder(ladder);
						String group = getApplicableGroup(PaidRanksAPI.getGroups(uuid, world), ladderx.getRanks());
						if (!ladderx.isRequiresRank() || (ladderx.isRequiresRank() && ladderx.hasRank(group))) {
							if (ladderx.getNextRank(group) != null) {
								Rank rankx = ladderx.getNextRank(group);
								if (PaidRanksAPI.hasAccount(uuid)) {
									if (PaidRanksAPI.hasEnoughMoney(uuid, rankx.getPrice())) {
										if ((!rankx.hasPermission()) || ((rankx.hasPermission()) && (cmd.getSender().hasPermission(rankx.getPermission())))) {
											PaidRanksAPI.withdrawMoney(uuid, rankx.getPrice());
											PaidRanksAPI.setGroup(uuid, world, group, rankx.getName());
											super.msgPrefix(cmd, LanguageSettings.Commands_Rankup_Rankup.getSetting().replace("%rank", rankx.getName()));
										} else {
											super.error(cmd, LanguageSettings.Commands_Rankup_NoPerm.getSetting());
										}
									} else {
										super.error(cmd, LanguageSettings.Commands_Rankup_NoMoney.getSetting().replace("%cash", PaidRanksAPI.getFormat(rankx.getPrice()))
												.replace("%rank", rankx.getName()));
									}
								} else {
									PaidRanksAPI.createAccount(uuid);
									super.error(cmd, LanguageSettings.Commands_Rankup_NoAccount.getSetting());
								}
							} else {
								super.error(cmd, LanguageSettings.Commands_Rankup_HighRank.getSetting());
							}
						} else {
							super.error(cmd, LanguageSettings.Commands_Rankup_NotCompatible.getSetting());
						}
					} else {
						super.error(cmd, LanguageSettings.Commands_LadderDoesNotExist.getSetting());
						this.listLadders(cmd);
					}
				} else {
					super.error(cmd, LanguageSettings.Commands_Rankup_NoDefault.getSetting());
				}
			} else {
				super.error(cmd, LanguageSettings.Commands_Rankup_InGame.getSetting());
			}
		} else {
			super.noPerm(cmd);
		}
	}

	private void listLadders(IssuedCommand cmd) {
		if (cmd.getSender().hasPermission("paidranks.commands.ru.list.ladder")) {
			super.msgPrefix(cmd, LanguageSettings.Commands_Rankup_Ladder_List_Top.getSetting());
			String prefix = LanguageSettings.Commands_Rankup_Ladder_List_Prefix.getSetting();
			if (!this.manager.getLadders().isEmpty()) {
				if (this.manager.hasDefaultLadder()) {
					super.msg(cmd, prefix + LanguageSettings.Commands_Rankup_Ladder_List_Format.getSetting()
							.replace("%name", this.manager.getDefaultLadder().getName()) + " &3Default");
				}
				for (Ladder ladder : this.manager.getLadders()) {
					if (!ladder.isDefault()) {
						super.msg(cmd, prefix + LanguageSettings.Commands_Rankup_Ladder_List_Format.getSetting().replace("%name", ladder.getName()));
					}
				}
			} else {
				super.msg(cmd, prefix + LanguageSettings.Commands_Rankup_Ladder_NotAvailable.getSetting());
			}
		} else {
			super.noPerm(cmd);
		}
	}

	private void listRanks(IssuedCommand cmd, String ladder) {
		if (cmd.getSender().hasPermission("paidranks.commands.ru.list.rank")) {
			if (this.manager.hasLadder(ladder)) {
				Ladder ladderx = this.manager.getLadder(ladder);
				super.msgPrefix(cmd, LanguageSettings.Commands_Rankup_Rank_List_Top.getSetting().replace("%ladder", ladderx.getName()));
				String prefix = LanguageSettings.Commands_Rankup_Rank_List_Prefix.getSetting();
				if (!ladderx.getRanks().isEmpty()) {
					for (Rank rank : ladderx.getRanks()) {
						super.msg(cmd, prefix + LanguageSettings.Commands_Rankup_Rank_List_Format.getSetting()
										.replace("%position", rank.getPosition() + "")
										.replace("%name", rank.getName())
										.replace("%cash", PaidRanksAPI.getFormat(rank.getPrice())));
					}
				} else {
					super.msg(cmd, prefix + LanguageSettings.Commands_Rankup_Rank_NotAvailable.getSetting());
				}
			} else {
				super.error(cmd, LanguageSettings.Commands_LadderDoesNotExist.getSetting());
			}
		} else {
			super.noPerm(cmd);
		}
	}

	private void infoNextRank(IssuedCommand cmd, String ladder) {
		if (cmd.getSender().hasPermission("paidranks.commands.ru.rank.next")) {
			if (cmd.isPlayer()) {
				if (this.manager.hasLadder(ladder)) {
					UUID uuid = cmd.getPlayer().getUniqueId();
					Ladder ladderx = this.manager.getLadder(ladder);
					String group = getApplicableGroup(PaidRanksAPI.getGroups(cmd.getPlayer().getUniqueId(), null), ladderx.getRanks());
					Rank groupx = null;
					if (!ladderx.isRequiresRank() || ladderx.isRequiresRank() && ladderx.hasRank(group)) {
						groupx = ladderx.getNextRank(group);
					} else {
						super.error(cmd, LanguageSettings.Commands_Rankup_NotCompatible.getSetting());
						return;
					}
					if (groupx != null) {
						String prefix = "  &6";
						String suffix = "&7";
						super.msgPrefix(cmd, LanguageSettings.Commands_Rankup_NextRankTop.getSetting());
						super.msg(cmd, prefix + "Rank:" + suffix + " " + groupx.getName());
						super.msg(cmd, prefix + "Price:" + suffix + " " + PaidRanksAPI.getFormat(groupx.getPrice()));
						super.msg(cmd, prefix + "Permission:" + " " + ((!groupx.hasPermission()) || ((groupx.hasPermission())
								&& (cmd.getSender().hasPermission(groupx.getPermission()))) ? "&aYes" : "&cNo"));
						super.msg(cmd, prefix + "Able to buy:" + " " + (PaidRanksAPI.hasEnoughMoney(uuid, groupx.getPrice()) ? "&aYes" : "&cNo"));
					} else {
						super.error(cmd, LanguageSettings.Commands_Rankup_HighRank.getSetting());
					}
				} else {
					super.error(cmd, LanguageSettings.Commands_Rankup_InGame.getSetting());
				}
			} else {
				super.error(cmd, LanguageSettings.Commands_LadderDoesNotExist.getSetting());
			}
		} else {
			super.noPerm(cmd);
		}
	}
	
	private String getApplicableGroup(String[] playerGroups, ArrayList<Rank> ladderGroups) {
		for(String playerRank : playerGroups) {
			for(Rank rank : ladderGroups) {
				if(rank.getName().equalsIgnoreCase(playerRank)) {
					return playerRank;
				}
			}
		}
		return playerGroups[0];
	}

	private void setupHelp() {
		this.list.addOption(new Option("/ru help - This screen.", "paidranks.commands.ru.help"));
		this.list.addOption(new Option("/ru [ladderName] - Rankup the default/specified ladder.", "paidranks.commands.ru.rankup"));
		this.list.addOption(new Option("/ru ladder list - List all the available ladders.", "paidranks.commands.ru.list.ladder"));
		this.list.addOption(new Option("/ru rank list [ladderName] - List all the available ranks within the default/specified ladder.", "paidranks.commands.ru.list.rank"));
		this.list.addOption(new Option("/ru rank next [ladderName] - Gives you information about your next rank within the default/specified ladder.", "paidranks.commands.ru.rank.next"));
	}

	private void help(IssuedCommand cmd, int page) {
		if (cmd.getSender().hasPermission("paidranks.commands.ru.help")) {
			page = this.list.checkPage(cmd.getSender(), page);
			super.msgPrefix(cmd, "Available Commands (" + (page + 1) + "/" + this.list.getTotalPages(cmd.getSender()) + "):");
			super.msg(cmd, "<> are required | [] are not required");
			String dud = ChatColor.DARK_GRAY + "    - ";
			for (String m : this.list.getOptions(cmd.getSender(), page)) {
				m = m.replace("/", ChatColor.DARK_GREEN + "/").replace("-", ChatColor.AQUA + "-" + ChatColor.GREEN);
				int index = m.indexOf(' ');
				if (index < m.length()) {
					String prefix = m.substring(0, index) + ChatColor.GREEN;
					super.msg(cmd, this.instance.getColorized(dud + prefix + m.substring(index)));
				} else {
					super.msg(cmd, this.instance.getColorized(dud + m));
				}
			}
		} else {
			super.noPerm(cmd);
		}
	}
}
