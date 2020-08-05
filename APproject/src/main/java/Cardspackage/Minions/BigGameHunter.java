package Cardspackage.Minions;

import Cardspackage.Minion;
import interfaces.Acceptable;
import interfaces.Visitor;
import playModel.PlayerModel;

public class BigGameHunter extends Minion {
	public BigGameHunter() {
		this.Set_Name("Big Game Hunter");
		this.Set_Class("Neutral");
		this.Set_Rarity("epic");
		this.Set_Mana(5);
		this.setHp(2);
		this.setAttack(4);
		this.setDescription("Battlecry: Destroy a minion with 7 or more Attack.");
		this.setBattlecry(true);
	}

	@Override
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
			v.visitBigGameHunter(this, taeget, attackerP, targetP);
			return super.accept(v, taeget, attackerP, targetP);
	}
}
