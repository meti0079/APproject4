package server.cardspackage.Spells;

import javax.persistence.Entity;

import server.cardspackage.Spell;
import server.interfaces.Acceptable;
import server.interfaces.Visitor;
import server.playModel.Mapper;
import server.playModel.PlayerModel;
@Entity
public class AstralRift extends Spell implements Acceptable{
	public AstralRift() {
		this.Set_Name("Astral Rift");
		this.Set_Class("Mage");
		this.Set_Rarity("rare");
			this.Set_Mana(2);
		this.setDescription("Add 2 random minions to your hand.");
	}

	@Override
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP, Mapper mapper) {
		v.visitAstralRift(this, taeget, attackerP, targetP);
		return true;}

}
