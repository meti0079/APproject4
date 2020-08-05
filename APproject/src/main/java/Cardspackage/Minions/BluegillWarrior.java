package Cardspackage.Minions;

import Cardspackage.Minion;
import interfaces.Acceptable;
import interfaces.Visitor;
import playModel.PlayerModel;

public class BluegillWarrior extends Minion implements Acceptable {
	public BluegillWarrior() {
		this.Set_Name("Bluegill Warrior");
		this.Set_Class("Neutral");
		this.Set_Rarity("common");
		this.Set_Mana(2);
		this.setHp(1);
		this.setAttack(2);
		this.setDescription("Charge");
		this.setRush(true);
	}

	@Override
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
		v.visitBluegillWarrior(this, taeget, attackerP, targetP);		
		return super.accept(v, taeget, attackerP, targetP);

	}


}
