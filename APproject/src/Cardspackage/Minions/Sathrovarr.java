package Cardspackage.Minions;

import Cardspackage.Minion;
import interfaces.Acceptable;
import interfaces.Visitor;

public class Sathrovarr extends Minion  implements Acceptable{

	public Sathrovarr() {

		this.Set_Name("Sathrovarr");
		this.Set_Class("Neutral");
		this.Set_Rarity("legendary");
			this.Set_Mana(9);
		this.setHp(5);
		this.setAttack(5);
		this.setDescription("Battlecry:Choose a friendly minion. Add a copy of "
				+ "it to your hand, deck and battlefield.");
		this.setBattlecry(true);
	
	
	}

	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		
	}

}
