package Cardspackage.Minions;

import Cardspackage.Minion;
import interfaces.Acceptable;
import interfaces.Visitor;

public class Dreadscale extends Minion  implements Acceptable{
public Dreadscale() {
	this.Set_Name("Dreadscale");
	this.Set_Class("Warlock");
	this.Set_Rarity("legendary");
		this.Set_Mana(3);
	this.setHp(2);
	this.setAttack(4);
	this.setDescription("Deal 1 damage to each minion.");

	
}

@Override
public void accept(Visitor v) {
	// TODO Auto-generated method stub
	
}



}
