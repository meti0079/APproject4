package server.cardspackage.Spells;

import server.cardspackage.Spell;
import server.interfaces.Acceptable;
import server.interfaces.Visitor;
import server.playModel.Mapper;
import server.playModel.PlayerModel;

public class BookofSpecters extends Spell implements Acceptable{
	public BookofSpecters() {
		this.Set_Name("Book of Specters");
		this.Set_Class("Neutral");
		this.Set_Mana(2);
		this.Set_Rarity("epic");
		this.setDescription("Draw 3 cards. Discard any spells drawn.");
	}

	@Override
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP, Mapper mapper) {
		v.visitBookofSpecters(this, taeget, attackerP, targetP);
		return true;}
}
