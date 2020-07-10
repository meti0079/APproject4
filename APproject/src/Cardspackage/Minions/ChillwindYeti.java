package Cardspackage.Minions;

import Cardspackage.Minion;
import interfaces.Acceptable;
import interfaces.Visitor;

public class ChillwindYeti  extends Minion implements Acceptable{
	public ChillwindYeti() {
		this.Set_Name("Chillwind Yeti");
		this.Set_Class("Neutral");
		this.Set_Rarity("rare");
		this.Set_Mana(4);
		this.setHp(5);
		this.setAttack(4);	
	}

	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		
	}
}
