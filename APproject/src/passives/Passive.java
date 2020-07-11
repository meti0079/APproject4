package passives;

import interfaces.PassiveVisitor;

public abstract class Passive {
	private String description;
	private String name;
public abstract void accept();

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
