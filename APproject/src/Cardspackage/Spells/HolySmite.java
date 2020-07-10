package Cardspackage.Spells;

import Cardspackage.Spell;
import interfaces.Acceptable;
import interfaces.Visitor;

public class HolySmite  extends Spell implements Acceptable{

	public HolySmite() {
		this.Set_Name("Holy Smite");
		this.Set_Class("Priest");
		this.Set_Mana(1);
		this.Set_Rarity("common");
		this.setDescription("Deal 3 damage to a minion.");
	}

	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		
	}

}
