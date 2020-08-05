package hero.heroPower;

import interfaces.HeroPowerVisitor;
import playModel.PlayerModel;

public class MagePower extends HeroPower{
	public MagePower() {
		this.setDescriptoin("deal 1 damage");
		this.setMana(2);
		this.setMaxUse(1);
		this.setUsed(false);
	}

	public boolean accept(HeroPowerVisitor v, Object target, PlayerModel me, PlayerModel enemy) {
		if(super.accept(v, target, me, enemy))
			return v.visitMagePower(target, me, enemy);
		return false;
	}
}
