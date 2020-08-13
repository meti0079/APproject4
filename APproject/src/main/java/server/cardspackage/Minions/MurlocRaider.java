package server.cardspackage.Minions;

import javax.persistence.Entity;

import server.cardspackage.Minion;
import server.interfaces.Acceptable;
import server.interfaces.Visitor;
import server.playModel.Mapper;
import server.playModel.PlayerModel;
@Entity
public class MurlocRaider extends Minion implements Acceptable{
	public MurlocRaider() {
		this.Set_Name("Murloc Raider");
		this.Set_Class("Neutral");
		this.Set_Rarity("common");
		this.Set_Mana(1);
		this.setHp(1);
		this.setAttack(2);	
	}

	@Override
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP, Mapper mapper) {
		v.visitMurlocRaider(this, taeget, attackerP, targetP);
		return super.accept(v, taeget, attackerP, targetP,mapper );
	}

}
