package Cardspackage.Minions;

import Cardspackage.Minion;
import interfaces.Acceptable;
import interfaces.Visitor;
import playModel.Player;

public class CurioCollector extends Minion implements Acceptable{

	public CurioCollector() {
		this.Set_Name("Curio Collector");
		this.Set_Class("Neutral");
		this.Set_Rarity("rare");
		this.Set_Mana(5);
		this.setHp(4);
		this.setAttack(4);	
		this.setDescription("Whenever you draw a card, gain +1/+1.");
	}

	@Override
	public boolean accept(Visitor v, Object taeget, Player attackerP, Player targetP) {
		v.visitCurioCollector(this, taeget, attackerP, targetP);
		super.accept(v, taeget, attackerP, targetP);
				return true;
		
		
	}

}
