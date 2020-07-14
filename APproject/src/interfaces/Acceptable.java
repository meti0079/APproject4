package interfaces;

import playModel.PlayerModel;

public interface Acceptable {
public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP);
}
