package Cardspackage.Spells;

import Cardspackage.Spell;
import interfaces.Acceptable;
import interfaces.Visitor;

public class AstralRift extends Spell implements Acceptable{
	public AstralRift() {
		this.Set_Name("Astral Rift");
		this.Set_Class("Mage");
		this.Set_Rarity("rare");
			this.Set_Mana(2);
		this.setDescription("Add 2 random minions to your hand.");
		
	}

	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		
	}

}
