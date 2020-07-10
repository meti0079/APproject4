package Cardspackage.Minions;

import Cardspackage.Minion;
import interfaces.Acceptable;
import interfaces.Visitor;

public class HighPriestAmet extends Minion  implements Acceptable{
	public HighPriestAmet() {
		this.Set_Name("High Priest Amet");
		this.Set_Class("Priest");
		this.Set_Rarity("legendary");
		this.Set_Mana(4);
		this.setHp(7);
		this.setAttack(2);	
		this.setDescription("whenever you summon a minion set its Health equal to this minion.");

	}

	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		
	}
}
