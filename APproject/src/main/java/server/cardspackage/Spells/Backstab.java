package server.cardspackage.Spells;

import server.cardspackage.Spell;
import server.interfaces.Acceptable;
import server.interfaces.Visitor;
import server.playModel.Mapper;
import server.playModel.PlayerModel;

public class Backstab extends Spell  implements Acceptable{
	public Backstab() {
		this.Set_Name("Backstab");
		this.Set_Class("Rouge");
		this.Set_Mana(0);
		this.Set_Rarity("common");
		this.setDescription("Deal 2 damage to enemy hero.");
	}

	@Override
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP, Mapper mapper) {
		v.visitBackstab(this, taeget, attackerP, targetP);
		return true;}
	
}
