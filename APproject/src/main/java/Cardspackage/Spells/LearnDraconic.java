package Cardspackage.Spells;

import Cardspackage.Spell;
import interfaces.Acceptable;
import interfaces.Visitor;
import playModel.Mapper;
import playModel.PlayerModel;

public class LearnDraconic extends Spell implements Acceptable{

	public LearnDraconic() {
		this.Set_Name("Learn Draconic");
		this.Set_Class("Neutral");
		this.Set_Mana(1);
		this.Set_Rarity("Common");
		this.setDescription("Sidequest: Spend 8 Mana on spells. Reward: Summon a 6/6 Dragon.");
		this.setQuest(true);
	}

	@Override
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP, Mapper mapper) {
		v.visitLearnDraconic(this, taeget, attackerP, targetP);
		return true;}

}
