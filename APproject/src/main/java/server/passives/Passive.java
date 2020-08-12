package server.passives;

import server.cardspackage.Card;
import server.interfaces.PassiveVisitor;
import server.playModel.PlayerModel;

public abstract class Passive {
	private String description;
	private String name;
	public abstract void accept(PassiveVisitor v,PlayerModel me, PlayerModel enemy , Card x);

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



}
