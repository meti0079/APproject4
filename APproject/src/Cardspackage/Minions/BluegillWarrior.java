package Cardspackage.Minions;

import Cardspackage.Minion;
import interfaces.Acceptable;
import interfaces.Visitor;

public class BluegillWarrior extends Minion implements Acceptable {
	public BluegillWarrior() {
		this.Set_Name("Bluegill Warrior");
		this.Set_Class("Neutral");
		this.Set_Rarity("common");
			this.Set_Mana(2);
		this.setHp(1);
		this.setAttack(2);
		this.setDescription("Charge");
	}

	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		
	}
	

}
