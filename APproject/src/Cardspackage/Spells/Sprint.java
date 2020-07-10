package Cardspackage.Spells;

import Cardspackage.Spell;
import interfaces.Acceptable;
import interfaces.Visitor;

public class Sprint extends Spell implements Acceptable{

	public Sprint() {
		this.Set_Name("Sprint");
		this.Set_Class("Rogue");
		this.Set_Mana(7);
		this.Set_Rarity("epic");
		this.setDescription("Draw 4 cards.");
	
	}

	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		
	}

}
