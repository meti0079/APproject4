package server.cardspackage.Spells;

import server.cardspackage.Spell;
import server.interfaces.Acceptable;
import server.interfaces.Visitor;
import server.playModel.Mapper;
import server.playModel.PlayerModel;

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
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP, Mapper mapper) {
		v.visitStrengthinNumbers(this, taeget, attackerP, targetP);
		return true;}

}
