package Cardspackage.Minions;

import Cardspackage.Minion;
import interfaces.Acceptable;
import interfaces.Visitor;

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
	public void accept(Visitor v) {
		v.visitSwampKingDred(this);		
	}

}
// checked