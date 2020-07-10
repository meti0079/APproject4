package Cardspackage.Minions;

import Cardspackage.Minion;
import interfaces.Acceptable;
import interfaces.Visitor;

public class CurioCollector extends Minion implements Acceptable{

	public CurioCollector() {
		this.Set_Name("Curio Collector");
		this.Set_Class("Neutral");
		this.Set_Rarity("rare");
		this.Set_Mana(5);
		this.setHp(4);
		this.setAttack(4);	
		this.setDescription("Whenever you draw a card, gain +1/+1.");
	}

	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		
	}

}
