package Cardspackage.Weapons;

import Cardspackage.Weapon;
import interfaces.Acceptable;
import interfaces.Visitor;
import playModel.Mapper;
import playModel.PlayerModel;

public class HeavyAxe extends Weapon implements Acceptable{

	public HeavyAxe() {

		this.Set_Name("Heavy Axe");
		this.Set_Class("Neutral");
		this.Set_Rarity("common");
			this.Set_Mana(1);
		this.setAttack(1);
		this.setDurability(3);
	}

	@Override
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP, Mapper mapper) {
		super.accept(v, taeget, attackerP, targetP, mapper);
		v.visitHeavyAxe(this, taeget, attackerP, targetP);
		return true;
		}

}
