package hero.heroPower;

import interfaces.HeroPowerVisitor;
import playModel.Mapper;
import playModel.PlayerModel;

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
