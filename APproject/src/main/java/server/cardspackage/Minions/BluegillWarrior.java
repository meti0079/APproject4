package server.cardspackage.Minions;

import javax.persistence.Entity;

import server.cardspackage.Minion;
import server.interfaces.Acceptable;
import server.interfaces.Visitor;
import server.playModel.Mapper;
import server.playModel.PlayerModel;
@Entity
public class BluegillWarrior extends Minion implements Acceptable {
	public BluegillWarrior() {
		this.Set_Name("Bluegill Warrior");
		this.Set_Class("Neutral");
		this.Set_Rarity("common");
		this.Set_Mana(2);
		this.setHp(1);
		this.setAttack(2);
		this.setDescription("Charge");
		this.setRush(true);
	}

	@Override
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP, Mapper mapper) {
		v.visitBluegillWarrior(this, taeget, attackerP, targetP);		
		return super.accept(v, taeget, attackerP, targetP,mapper );
	}


}
