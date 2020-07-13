package hero.heroPower;

import interfaces.HeroPowerVisitor;
import playModel.Player;

public class MagePower extends HeroPower{
	public MagePower() {
		this.setDescriptoin("deal 1 damage");
		this.setMana(2);
		this.setMaxUse(1);
		this.setUsed(false);
	}

	public void accept(HeroPowerVisitor v, Object target, Player me, Player enemy) {
		v.visitMagePower(target, me, enemy);
	}
}
