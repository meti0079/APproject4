package playModel;

import Cardspackage.Card;

public class Quest {
	private Card reward;
	private int mission=0;
	private int have=0;
	private String type;
	public int getHave() {
		return have;
	}
	public void setHave(int have) {
		this.have = have;
	}

	public Quest(Card revard, int mission, String type) {
		super();
		this.reward = revard;
		this.mission = mission;
		this.type=type;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Card getReward() {
		return reward;
	}

	public void setReward(Card reward) {
		this.reward = reward;
	}

	public int getMission() {
		return mission;
	}
	public void setMission(int mission) {
		this.mission = mission;
	}
}
