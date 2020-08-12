package server.cardspackage.Minions;

import server.cardspackage.Minion;
import server.interfaces.Acceptable;
import server.interfaces.Visitor;
import server.playModel.Mapper;
import server.playModel.PlayerModel;

public class SecurityRover extends Minion implements Acceptable{

	public SecurityRover() {
		this.Set_Name("Security Rover");
		this.Set_Class("Neutral");
		this.Set_Rarity("rare");
		this.Set_Mana(6);
		this.setHp(6);
		this.setAttack(2);
		this.setDescription("Whenever this minion takes damage, summon a 2/3 Mech with Taunt.");
		this.setTaunt(true);	
	}

	@Override
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP, Mapper mapper) {
		v.visitSecurityRover(this, taeget, attackerP, targetP);
		return super.accept(v, taeget, attackerP, targetP,mapper );
			}

}
