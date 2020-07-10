package Cardspackage.Minions;

import Cardspackage.Minion;
import interfaces.Acceptable;
import interfaces.Visitor;

public class TheBlackKnight extends Minion implements Acceptable{
	public TheBlackKnight() {
		this.Set_Name("The Black Knight");
		this.Set_Class("Neutral");
		this.Set_Rarity("legendary");
		this.Set_Mana(6);
		this.setHp(5);
		this.setAttack(4);	
		this.setBattlecry(true);
		this.setTaunt(true);
		this.setDescription("Battlecry: Destroy an enemy minion with Taunt.");
	}

	@Override
	public void accept(Visitor v) {
v.visitTheBlackKnight(this);		
	}
}
