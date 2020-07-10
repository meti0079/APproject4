package Cardspackage.Spells;

import Cardspackage.Spell;
import interfaces.Acceptable;
import interfaces.Visitor;

public class ArcaneShot extends Spell implements Acceptable{

	public ArcaneShot() {
		this.Set_Name("Arcane Shot");
		this.Set_Class("Hunter");
		this.Set_Mana(1);
		this.Set_Rarity("common");
		this.setDescription("Deal 2 damage.");	
		
	}

	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		
	}
		
}
