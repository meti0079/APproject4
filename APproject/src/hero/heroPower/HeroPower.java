package hero.heroPower;

import interfaces.HeroPowerVisitor;
import playModel.Player;

public  class HeroPower {

	private int maxUse=1;
	private String descriptoin;
	private boolean used=false;
	private int mana;

	public HeroPower() {

	}
	public int getMaxUse() {
		return maxUse;
	}
	public void setMaxUse(int maxUse) {
		this.maxUse = maxUse;
	}
	public String getDescriptoin() {
		return descriptoin;
	}
	public void setDescriptoin(String descriptoin) {
		this.descriptoin = descriptoin;
	}
	public boolean isUsed() {
		return used;
	}
	public void setUsed(boolean used) {
		this.used = used;
	}
	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}
}
