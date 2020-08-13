package server.cardspackage.Minions;

import javax.persistence.Entity;

import server.cardspackage.Minion;
import server.interfaces.Acceptable;
import server.interfaces.Visitor;
import server.playModel.Mapper;
import server.playModel.PlayerModel;
@Entity
public class Shieldbearer extends Minion implements Acceptable {

	public Shieldbearer() {
		this.Set_Name("Shieldbearer");
		this.Set_Class("Neutral");
		this.Set_Rarity("Common");
		this.Set_Mana(1);
		this.setHp(4);
		this.setAttack(0);
		this.setDescription("Taunt");
		this.setTaunt(true);
		}

	@Override
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP, Mapper mapper) {
		v.visitShieldbearer(this, taeget, attackerP, targetP);
		return super.accept(v, taeget, attackerP, targetP,mapper );
		}
}
