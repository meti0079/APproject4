package server.cardspackage.Minions;

import server.cardspackage.Minion;
import server.interfaces.Acceptable;
import server.interfaces.Visitor;
import server.playModel.Mapper;
import server.playModel.PlayerModel;

public class SwampKingDred extends Minion  implements Acceptable{
	public SwampKingDred() {
		this.Set_Name("Swamp King Dred");
		this.Set_Class("Hunter");
		this.Set_Rarity("legendary");
		this.Set_Mana(7);
		this.setRush(true);
		this.setHp(9);
		this.setAttack(9);	
		this.setDescription("After your opponent playes a minion ,attack it");


	}

	@Override
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP, Mapper mapper) {
		v.visitSwampKingDred(this, taeget, attackerP, targetP);
		return super.accept(v, taeget, attackerP, targetP,mapper );
	}

}
