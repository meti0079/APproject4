package Cardspackage.Spells;

import Cardspackage.Spell;
import interfaces.Acceptable;
import interfaces.Visitor;
import playModel.Mapper;
import playModel.PlayerModel;

public class FriendlySmith extends Spell implements Acceptable{
	public FriendlySmith() {
		this.Set_Name("Friendly Smith");
		this.Set_Class("Rouge");
		this.Set_Mana(1);
		this.Set_Rarity("common");
		setDescription("Discover a weapon from any class."
				+ " Add it to your Adventure Deck "
				+ "with +2/+2.");
	}

	@Override
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP, Mapper mapper) {
		v.visitFriendlySmith(this, taeget, attackerP, targetP);
		return true;
		}	
}
