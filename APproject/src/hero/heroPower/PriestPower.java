package hero.heroPower;

import interfaces.HeroPowerVisitor;
import playModel.PlayerModel;

public class PriestPower extends HeroPower{
	public PriestPower() {
		this.setDescriptoin("restore 4 health");
		this.setMana(2);
		this.setMaxUse(1);
		this.setUsed(false);
	}

	public boolean accept(HeroPowerVisitor v, Object target, PlayerModel me, PlayerModel enemy) {
		if(super.accept(v, target, me, enemy))
			return v.visitPriestPower(target, me, enemy);
		return false;

	}
}
