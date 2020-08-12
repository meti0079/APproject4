package server.cardspackage.Minions;

import server.cardspackage.Minion;
import server.interfaces.Acceptable;
import server.interfaces.Visitor;
import server.playModel.Mapper;
import server.playModel.PlayerModel;

public class Gruul extends Minion  implements Acceptable{
	public Gruul() {
		this.Set_Name("Gruul");
		this.Set_Class("Neutral");
		this.Set_Rarity("legendary");
		this.Set_Mana(8);
		this.setHp(7);
		this.setAttack(7);	
		this.setDescription("At the end of each turn, gain +1/+1 .");
	}

	@Override
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP, Mapper mapper) {
		v.visitGruul(this, taeget, attackerP, targetP);
		return super.accept(v, taeget, attackerP, targetP,mapper );

	}
}
