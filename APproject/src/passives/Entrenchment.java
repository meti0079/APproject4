package passives;

import Cardspackage.Card;
import interfaces.PassiveVisitor;
import playModel.Player;

public class Entrenchment extends Passive{
public Entrenchment() {
setName( "Entrenchment");
setDescription("Your minions have +2 Health.");

}

@Override
public void accept(PassiveVisitor v, Player me, Player enemy, Card x) {
	v.visitEntrenchment(me, enemy, x);
}


}
