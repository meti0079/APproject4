package Cardspackage.Minions;

import Cardspackage.Minion;
import interfaces.Acceptable;
import interfaces.Visitor;
import playModel.Mapper;
import playModel.PlayerModel;

public class KronxDragonhoof extends Minion implements Acceptable{
	public KronxDragonhoof() {
		this.Set_Name("Kronx Dragonhoof");
		this.Set_Class("Neutral");
		this.Set_Rarity("legendary");
		this.Set_Mana(6);
		this.setHp(6);
		this.setAttack(6);	
		this.setDescription("");
	}

	@Override
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP, Mapper mapper) {
		v.visitKronxDragonhoof(this, taeget, attackerP, targetP);
		return super.accept(v, taeget, attackerP, targetP,mapper );
	}
}
