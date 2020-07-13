package hero.heroPower;

import interfaces.HeroPowerVisitor;
import playModel.Player;

public class RougePower extends HeroPower{
	public RougePower() {
		this.setDescriptoin("Stil 1 card from enemy");
		this.setMana(3);
		this.setMaxUse(1);
		this.setUsed(false);
	}


	public void accept(HeroPowerVisitor v, Object target, Player me, Player enemy) {
		v.visitRougePower(target, me, enemy);
	}
}
