package server.hero.heroPower;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import server.cardspackage.Minion;
import server.interfaces.HeroPowerVisitor;
import server.playModel.Mapper;
import server.playModel.PlayerModel;
@Entity
public abstract  class HeroPower {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private int maxUse=1;
	@Column
	private String descriptoin;
	@Column
	private boolean used=false;
	@Column
	private int mana;
	@Column
	private int use=0;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
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
