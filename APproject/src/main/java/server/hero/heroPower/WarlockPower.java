package server.hero.heroPower;

import server.interfaces.HeroPowerVisitor;
import server.playModel.Mapper;
import server.playModel.PlayerModel;

public class WarlockPower extends HeroPower{
	public WarlockPower() {
		this.setDescriptoin("spend 2 mana and do: 1.increase 1 attack and hp to a card  2,add 1 card to player cards");
		this.setMana(2);
		this.setMaxUse(1);
		this.setUsed(false);
	}

	public boolean accept(HeroPowerVisitor v, Object target, PlayerModel me, PlayerModel enemy, Mapper mapper) {

		if(super.accept(v, target, me, enemy, mapper))
			return v.visitWarlockPower(target, me, enemy);
		return false;
	}
}
