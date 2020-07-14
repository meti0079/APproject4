package Cardspackage.Weapons;

import Cardspackage.Weapon;
import interfaces.Acceptable;
import interfaces.Visitor;
import playModel.PlayerModel;

public class BloodFury extends Weapon implements Acceptable{

	public BloodFury() {

		this.Set_Name("Blood Fury");
		this.Set_Class("Neutral");
		this.Set_Rarity("common");
			this.Set_Mana(3);
			this.setDurability(8);
			this.setAttack(3);
	}

	@Override
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
		super.accept(v, taeget, attackerP, targetP);
		v.visitBloodFury(this, taeget, attackerP, targetP);
		return true;}

}
