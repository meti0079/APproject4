package playModel;

import Cardspackage.Card;

public class Quest {
	private Card reward;
	private int mission;
	public Quest(Card revard, int mission) {
		super();
		this.reward = revard;
		this.mission = mission;
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
