package Cardspackage.Spells;

import Cardspackage.Spell;
import interfaces.Acceptable;
import interfaces.Visitor;

public class Swarmoflocusts extends Spell implements Acceptable{

	public Swarmoflocusts() {
		
		this.Set_Name("Swarm of locusts");
		this.Set_Class("Neutral");
		this.Set_Mana(6);
		this.Set_Rarity("Rare");
		this.setDescription("ummon seven 1/1 Locusts with Rush.");
		this.setRush(true);
	}

	@Override
	public void accept(Visitor v) {
v.visitSwarmoflocusts(this);		
	}

}
