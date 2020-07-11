package Cardspackage.Spells;

import Cardspackage.Spell;
import interfaces.Acceptable;
import interfaces.Visitor;
import playModel.Player;

public class BookofSpecters extends Spell implements Acceptable{
	public BookofSpecters() {
		this.Set_Name("Book of Specters");
		this.Set_Class("Neutral");
		this.Set_Mana(2);
		this.Set_Rarity("epic");
		this.setDescription("Draw 3 cards. Discard any spells drawn.");
		this.setNeedTarget(false);
	}

	@Override
	public boolean accept(Visitor v, Object taeget, Player attackerP, Player targetP) {
		v.visitBookofSpecters(this, taeget, attackerP, targetP);
		return true;}
}
