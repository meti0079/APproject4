package Cardspackage.Minions;

import Cardspackage.Minion;
import interfaces.Acceptable;
import interfaces.Visitor;

public class Sandbinder extends Minion implements Acceptable{
	public Sandbinder() {
		this.Set_Name("Sandbinder");
		this.Set_Class("Neutral");
		this.Set_Rarity("epic");
		this.Set_Mana(4);
		this.setHp(4);
		this.setAttack(2);	
		this.setBattlecry(true);
		this.setDescription("Battlecry: Draw an Elemental from your deck.");
	}

	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		
	}
}
