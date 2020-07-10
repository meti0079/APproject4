package Cardspackage.Spells;

import Cardspackage.Spell;
import interfaces.Acceptable;
import interfaces.Visitor;

public class Backstab extends Spell  implements Acceptable{
	public Backstab() {
		this.Set_Name("Backstab");
		this.Set_Class("Rouge");
		this.Set_Mana(0);
		this.Set_Rarity("common");
		this.setDescription("Deal 2 damage to an undamaged minion.");
	}

	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		
	}
	
}
