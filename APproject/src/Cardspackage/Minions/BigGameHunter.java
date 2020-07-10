package Cardspackage.Minions;

import Cardspackage.Minion;
import interfaces.Acceptable;
import interfaces.Visitor;

public class BigGameHunter extends Minion implements Acceptable{
	public BigGameHunter() {
		this.Set_Name("Big Game Hunter");
		this.Set_Class("Neutral");
		this.Set_Rarity("epic");
		this.Set_Mana(5);
		this.setHp(2);
		this.setAttack(4);
		this.setDescription("Battlecry: Destroy a minion with 7 or more Attack.");
		this.setBattlecry(true);
	}

	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		
	}
}
