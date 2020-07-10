package Cardspackage.Minions;

import Cardspackage.Minion;
import interfaces.Acceptable;
import interfaces.Visitor;

public class TombWarden  extends Minion implements Acceptable{


	public TombWarden() {
		this.Set_Name("Tomb Warden");
		this.Set_Class("Neutral");
		this.Set_Rarity("common");
		this.Set_Mana(8);
		this.setHp(6);
		this.setAttack(3);
		this.setDescription("Taunt . Battlecry: Summon a copy of this minion");
		this.setBattlecry(true);
		this.setTaunt(true);
	}

	@Override
	public void accept(Visitor v) {
		v.visitTombWarden(this);		
	}

}
