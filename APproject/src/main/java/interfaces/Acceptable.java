package interfaces;

import playModel.Mapper;
import playModel.PlayerModel;

public interface Acceptable {
boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP, Mapper mapper);
}
