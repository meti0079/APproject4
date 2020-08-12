package server.cardspackage.Spells;

import server.cardspackage.Spell;
import server.interfaces.Acceptable;
import server.interfaces.Visitor;
import server.playModel.Mapper;
import server.playModel.PlayerModel;

public class gift extends Spell implements Acceptable{
	public gift() {
		this.Set_Name("gift");
		this.Set_Class("Warlock");
		this.Set_Mana(2);
		this.Set_Rarity("legendary");
		this.setDescription("gift 1 Hp to hero");
	}

	@Override
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP, Mapper mapper) {
		v.visitgift(this, taeget, attackerP, targetP);
		return true;	
	}
}