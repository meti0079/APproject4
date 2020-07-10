package Cardspackage.Minions;

import Cardspackage.Minion;
import interfaces.Acceptable;
import interfaces.Visitor;

public class ThrallmarFarseer extends Minion implements Acceptable{
	public ThrallmarFarseer() {
		this.Set_Name("Thrallmar Farseer");
		this.Set_Class("neutral");
		this.Set_Rarity("common");
			this.Set_Mana(3);
		this.setHp(3);
		this.setAttack(2);	
		this.setDescription("windfury");
		this.setWindfury(true);
	}


	@Override
	public void accept(Visitor v) {
v.visitThrallmarFarseer(this);		
	}
}
