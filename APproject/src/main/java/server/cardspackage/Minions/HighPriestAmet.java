package server.cardspackage.Minions;

import javax.persistence.Entity;

import server.cardspackage.Minion;
import server.interfaces.Acceptable;
import server.interfaces.Visitor;
import server.playModel.Mapper;
import server.playModel.PlayerModel;
@Entity
public class HighPriestAmet extends Minion  implements Acceptable{
	public HighPriestAmet() {
		this.Set_Name("High Priest Amet");
		this.Set_Class("Priest");
		this.Set_Rarity("legendary");
		this.Set_Mana(4);
		this.setHp(7);
		this.setAttack(2);	
		this.setDescription("whenever you summon this set a minion Health equal to this .");
	}

	@Override
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP, Mapper mapper) {
		v.visitHighPriestAmet(this, taeget, attackerP, targetP);
		return super.accept(v, taeget, attackerP, targetP,mapper );
	}
}
