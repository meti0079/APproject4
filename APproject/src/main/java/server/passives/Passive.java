package server.passives;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import server.cardspackage.Card;
import server.interfaces.PassiveVisitor;
import server.playModel.PlayerModel;
@Entity
public abstract class Passive {
	@Column
	private String description;
	@Id
	private String name;
	public Passive() {
		// TODO Auto-generated constructor stub
	}
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
