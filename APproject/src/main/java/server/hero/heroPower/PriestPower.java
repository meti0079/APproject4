package server.hero.heroPower;

import server.interfaces.HeroPowerVisitor;
import server.playModel.Mapper;
import server.playModel.PlayerModel;

public class PriestPower extends HeroPower{
	public PriestPower() {
		this.setDescriptoin("restore 4 health");
		this.setMana(2);
		this.setMaxUse(1);
		this.setUsed(false);
	}

	public boolean accept(HeroPowerVisitor v, Object target, PlayerModel me, PlayerModel enemy, Mapper mapper) {
		if(super.accept(v, target, me, enemy, mapper))
			return v.visitPriestPower(target, me, enemy);
		return false;

	}
}
