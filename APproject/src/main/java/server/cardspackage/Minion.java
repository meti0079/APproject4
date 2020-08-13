package server.cardspackage;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.swing.JOptionPane;

import server.cardspackage.Minions.SecurityRover;
import server.hero.Heros;
import server.interfaces.Visitor;
import server.playModel.Mapper;
import server.playModel.PlayerModel;
@Entity
public abstract  class Minion extends Card{
	
	@Column
	private int HP;
	@Column
	private int attack;
	public Minion() {
		this.setType("Minion");

	}

	/**
	 * @return the hP
	 */
	public int getHP() {
		return HP;
	}

	/**
	 * @param hP the hP to set
	 */
	public void setHP(int hP) {
		HP = hP;
	}

	public int getAttack() {
		return this.attack;
	}
	@Override
	public int getHp() {
		return this.HP;
	}
	@Override
	public void setAttack(int x) {
		attack=x;
	}
	@Override
	public void setHp(int x) {
		HP=x;
	}
	@Override
	public String getType() {
		return "Minion" ;
	}
	@Override
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP, Mapper mapper) {
		if(taeget == null) {
			return false;
		}

		try {
			if(taeget instanceof Heros) {
				if(!mapper.checkTount(targetP)&& taeget.equals(targetP.getHero())) {
					((Heros) taeget).setHP(((Heros) taeget).get_HP()-this.getAttack());
					return true;
				}	
			}else {	
				if(mapper.checkTount(targetP)&&mapper.validCard(targetP, (Card) taeget)) {
					if(taeget instanceof Minion &&((Minion) taeget).isTaunt()) {
						((Minion) taeget).setHp(((Minion) taeget).getHp()-this.getAttack());
						this.setHp(this.getHp()-((Minion) taeget).getAttack());
						if(taeget instanceof SecurityRover)
							((SecurityRover)taeget).accept(v, null, targetP, attackerP,mapper);
						attackerP.checkCard(targetP,v, mapper);
						targetP.checkCard(attackerP, v, mapper);
						return true;
					}else {
						JOptionPane.showMessageDialog(null, "chose a tount target");						
						return false;
					}
				}
				if (taeget instanceof Minion && mapper.validCard(targetP, (Card) taeget)){
					((Minion) taeget).setHp(((Minion) taeget).getHp()-this.getAttack());
					this.setHp(this.getHp()-((Minion) taeget).getAttack());	
					attackerP.checkCard(targetP,v, mapper);
					targetP.checkCard(attackerP, v,mapper);
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
