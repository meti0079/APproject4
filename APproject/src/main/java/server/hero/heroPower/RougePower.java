package server.hero.heroPower;

import javax.persistence.Entity;

import server.interfaces.HeroPowerVisitor;
import server.playModel.Mapper;
import server.playModel.PlayerModel;
@Entity
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
