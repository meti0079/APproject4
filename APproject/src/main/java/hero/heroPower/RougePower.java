package hero.heroPower;

import interfaces.HeroPowerVisitor;
import playModel.Mapper;
import playModel.PlayerModel;

public class RougePower extends HeroPower{
	public RougePower() {
		this.setDescriptoin("Stil 1 card from enemy");
		this.setMana(3);
		this.setMaxUse(1);
		this.setUsed(false);
	}


	public boolean accept(HeroPowerVisitor v, Object target, PlayerModel me, PlayerModel enemy, Mapper mapper) {
		if(super.accept(v, target, me, enemy,mapper))
			return v.visitRougePower(target, me, enemy);
		return false;

	}
}
