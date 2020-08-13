package server.cardspackage.Weapons;

import javax.persistence.Entity;

import server.cardspackage.Weapon;
import server.interfaces.Acceptable;
import server.interfaces.Visitor;
import server.playModel.Mapper;
import server.playModel.PlayerModel;
@Entity
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
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP, Mapper mapper) {
		super.accept(v, taeget, attackerP, targetP, mapper);
		v.visitBloodFury(this, taeget, attackerP, targetP);
		return true;}

}
