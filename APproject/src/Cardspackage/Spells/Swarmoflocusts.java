package Cardspackage.Spells;

import Cardspackage.Spell;
import interfaces.Acceptable;
import interfaces.Visitor;
import playModel.Player;

public class Swarmoflocusts extends Spell implements Acceptable{

	public Swarmoflocusts() {
		
		this.Set_Name("Swarm of locusts");
		this.Set_Class("Neutral");
		this.Set_Mana(6);
		this.Set_Rarity("Rare");
		this.setDescription("summon seven 1/1 Locusts with Rush.");
		this.setRush(true);
	}

	@Override
	public boolean accept(Visitor v, Object taeget, Player attackerP, Player targetP) {
		v.visitSwarmoflocusts(this, taeget, attackerP, targetP);
		return true;}
}
