package Cardspackage.Minions;

import Cardspackage.Minion;
import interfaces.Acceptable;
import interfaces.Visitor;
import playModel.Player;

public class SwampKingDred extends Minion  implements Acceptable{
	public SwampKingDred() {
		this.Set_Name("Swamp King Dred");
		this.Set_Class("Hunter");
		this.Set_Rarity("legendary");
		this.Set_Mana(7);
		this.setHp(9);
		this.setAttack(9);	
		this.setDescription("After your opponent playes a minion ,attack it");


	}

	@Override
	public boolean accept(Visitor v, Object taeget, Player attackerP, Player targetP) {
		if(super.accept(v, taeget, attackerP, targetP)) {
			v.visitSwampKingDred(this, taeget, attackerP, targetP);
			return true;
		}
		return false;
	}

}
