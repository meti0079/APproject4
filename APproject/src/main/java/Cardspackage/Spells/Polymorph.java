package Cardspackage.Spells;

import Cardspackage.Spell;
import interfaces.Acceptable;
import interfaces.Visitor;
import playModel.Mapper;
import playModel.PlayerModel;

public class Polymorph extends Spell implements Acceptable{
public Polymorph() {
	this.Set_Name("Polymorph");
	this.Set_Class("Mage");
	this.Set_Mana(4);
	this.Set_Rarity("rare");
	setDescription("Transform a minion into a 1/1 Sheep.");
}

@Override
public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP, Mapper mapper) {
	v.visitPolymorph(this, taeget, attackerP, targetP);
	return true;
	}

}
