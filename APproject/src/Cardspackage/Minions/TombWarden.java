package Cardspackage.Minions;

import Cardspackage.Minion;
import interfaces.Acceptable;
import interfaces.Visitor;
import playModel.Player;

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
	public boolean accept(Visitor v, Object taeget, Player attackerP, Player targetP) {
		v.visitTombWarden(this,  taeget,attackerP, targetP);		
		super.accept(v, taeget, attackerP, targetP);
			return true;

	}

}
