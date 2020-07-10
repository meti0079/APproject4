package Cardspackage.Spells;

import Cardspackage.Spell;
import interfaces.Acceptable;
import interfaces.Visitor;

public class Polymorph extends Spell implements Acceptable{
public Polymorph() {
	this.Set_Name("Polymorph");
	this.Set_Class("Mage");
	this.Set_Mana(4);
	this.Set_Rarity("rare");
	setDescription("Transform a minion into a 1/1 Sheep.");
}

@Override
public void accept(Visitor v) {
	// TODO Auto-generated method stub
	
}

}
