package server.cardspackage.Weapons;

import javax.persistence.Entity;

import server.cardspackage.Weapon;
import server.interfaces.Acceptable;
import server.interfaces.Visitor;
import server.playModel.Mapper;
import server.playModel.PlayerModel;
@Entity
public class BattleAxe extends Weapon implements Acceptable{

	public BattleAxe() {
		Set_Name("Battle Axe");
		setDurability(2);
		Set_Class("Mage");
		Set_Mana(1);
		Set_Rarity("Common");
		setAttack(2);
	}

	@Override
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP, Mapper mapper) {
		super.accept(v, taeget, attackerP, targetP,mapper);
		v.visitBattleAxe(this, taeget, attackerP, targetP);
		return true;
		
	}

}
