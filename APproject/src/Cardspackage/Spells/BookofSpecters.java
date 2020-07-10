package Cardspackage.Spells;

import Cardspackage.Spell;
import interfaces.Acceptable;
import interfaces.Visitor;

public class BookofSpecters extends Spell implements Acceptable{
	public BookofSpecters() {
		this.Set_Name("Book of Specters");
		this.Set_Class("Neutral");
		this.Set_Mana(2);
		this.Set_Rarity("epic");
		this.setDescription("Draw 3 cards. Discard any spells drawn.");
	}

	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		
	}
}
