package Cardspackage.Spells;

import Cardspackage.Spell;
import interfaces.Acceptable;
import interfaces.Visitor;
import playModel.Mapper;
import playModel.PlayerModel;

public class Sprint extends Spell implements Acceptable{

	public Sprint() {
		this.Set_Name("Sprint");
		this.Set_Class("Rouge");
		this.Set_Mana(7);
		this.Set_Rarity("epic");
		this.setDescription("Draw 4 cards.");
	}

	@Override
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP, Mapper mapper) {
		v.visitSprint(this, taeget, attackerP, targetP);
		return true;
	}

}
