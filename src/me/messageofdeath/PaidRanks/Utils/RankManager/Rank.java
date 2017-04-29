package me.messageofdeath.PaidRanks.Utils.RankManager;

public class Rank {
	
	private String name;
	private String permission;
	private int position;
	private double price;

	public Rank(String name, String permission, int position, double price) {
		this.name = name;
		this.permission = permission;
		this.position = position;
		this.price = price;
	}

	public String getName() {
		return this.name;
	}

	public String getPermission() {
		return this.permission;
	}

	public int getPosition() {
		return this.position;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean hasPermission() {
		return (!this.permission.equalsIgnoreCase("")) && (!this.permission.equalsIgnoreCase("noPerm")) && (this.permission != null);
	}

	public String toString() {
		return getName() + "," + getPermission() + "," + getPosition() + "," + getPrice();
	}

	public static Rank toRank(String input) {
		String[] args = input.split(",");
		return args.length == 4 ? new Rank(args[0], args[1], Integer.parseInt(args[2]), Double.parseDouble(args[3])) : null;
	}
}
