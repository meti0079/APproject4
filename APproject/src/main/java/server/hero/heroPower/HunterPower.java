package server.hero.heroPower;

import javax.persistence.Entity;

import server.interfaces.HeroPowerVisitor;
import server.playModel.Mapper;
import server.playModel.PlayerModel;
@Entity
public class HunterPower extends HeroPower{
	public HunterPower() {
		this.setDescriptoin("after ypr opponent play a card 1 dame to it");
		this.setMana(2);
		this.setMaxUse(1);
		this.setUsed(false);
	}


	public boolean accept(HeroPowerVisitor v, Object target, PlayerModel me, PlayerModel enemy, Mapper mapper) {
		if(super.accept(v, target, me, enemy, mapper))
			return v.visitHunterPower(target, me, enemy);
		return false;
	}


}
