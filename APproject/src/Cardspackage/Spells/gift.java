package Cardspackage.Spells;

import Cardspackage.Spell;
import interfaces.Acceptable;
import interfaces.Visitor;

public class gift extends Spell implements Acceptable{
	public gift() {
		this.Set_Name("gift");
		this.Set_Class("Warlock");
		this.Set_Mana(2);
		this.Set_Rarity("legendary");
		this.setDescription("gift 1 Hp to hero");
	}

	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		
	}
}