package server.hero.heroPower;

import server.cardspackage.Minion;
import server.interfaces.HeroPowerVisitor;
import server.playModel.Mapper;
import server.playModel.PlayerModel;

public abstract  class HeroPower {

	private int maxUse=1;
	private String descriptoin;
	private boolean used=false;
	private int mana;
	private int use=0;

	public int getUse() {
		return use;
	}
	public void setUse(int use) {
		this.use = use;
	}
	public HeroPower() {

	}
	public  boolean accept(HeroPowerVisitor v, Object target, PlayerModel me, PlayerModel enemy, Mapper mapper) {
		try {
			if(mapper.checkTount(enemy)) {
				if(target.equals(enemy.getHero()) )
					return false;
			if(((Minion)target).isTaunt())
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			return true;	
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
