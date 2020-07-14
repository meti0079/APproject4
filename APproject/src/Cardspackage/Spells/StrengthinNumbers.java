package Cardspackage.Spells;

import Cardspackage.Spell;
import interfaces.Acceptable;
import interfaces.Visitor;
import playModel.PlayerModel;

public class StrengthinNumbers  extends Spell implements Acceptable{

	public StrengthinNumbers() {
		this.Set_Name("Strength in Numbers");
		this.Set_Class("Neutral");
		this.Set_Mana(1);
		this.Set_Rarity("common");
		this.setDescription("Sidequest: Spend 10 Mana on minions Reward: Summon a minion from your ");
		this.setQuest(true);
	}

	@Override
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
		v.visitStrengthinNumbers(this, taeget, attackerP, targetP);
		return true;}

}
