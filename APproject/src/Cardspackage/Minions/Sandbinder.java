package Cardspackage.Minions;

import Cardspackage.Minion;
import interfaces.Acceptable;
import interfaces.Visitor;
import playModel.Player;

public class Sandbinder extends Minion implements Acceptable{
	public Sandbinder() {
		this.Set_Name("Sandbinder");
		this.Set_Class("Neutral");
		this.Set_Rarity("epic");
		this.Set_Mana(4);
		this.setHp(4);
		this.setAttack(2);	
		this.setBattlecry(true);
		this.setDescription("Battlecry: Draw an Elemental from your deck.");
	}

	@Override
	public boolean accept(Visitor v, Object taeget, Player attackerP, Player targetP) {
		super.accept(v, taeget, attackerP, targetP);
			v.visitSandbinder(this, taeget, attackerP, targetP);
			return true;
		}
}
