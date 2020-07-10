package Cardspackage.Minions;

import Cardspackage.Minion;
import interfaces.Acceptable;
import interfaces.Visitor;

public class SeaGiant extends Minion  implements Acceptable{
	public SeaGiant() {
		this.Set_Name("Sea Giant");
		this.Set_Class("Neutral");
		this.Set_Rarity("epic");
		this.Set_Mana(10);
		this.setHp(8);
		this.setAttack(8);
		this.setDescription("Costs (1) less for each other minion on the battlefield.");
	}

	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		
	}
}
