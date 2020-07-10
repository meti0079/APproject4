package Cardspackage.Minions;

import Cardspackage.Minion;
import interfaces.Acceptable;
import interfaces.Visitor;

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
	public void accept(Visitor v) {
System.out.println("slam");		
	}

	
}
