package mdc.components.cards.actioncards;

import mdc.components.cards.ICard;
import mdc.components.cards.properties.AbstractPropertyCard;
import mdc.components.piles.drawpile.DrawPile;
import mdc.components.piles.actionpile.ActionPile;
import mdc.components.players.Player;

/**
 * 放在成套土地上加租金
 * @para name:名字
 * @para turnMoney:放入银行多少钱
 * @para value:加多少钱
 * @para isActing:判断当前行动卡是否在生效
 */
public class House extends AbstractActionCard {
    private int turnMoney;
    private int value;
    private boolean isActing;


    public House(int turnMoney){
        this.turnMoney=turnMoney;
        this.value=value;
        isActing=true;
    }

    @Override
    public void deal(DrawPile pile) {
        pile.addCard(this);
    }

    public void play(ActionPile pile, Player player, AbstractPropertyCard card, House house){
        if (isActing){
            if (player.getOwnProperty().ifFullSet(card)){
                player.getOwnProperty().addHouse(house,card.getColor());
                pile.addCards(this);
            }
        }
    }

    @Override
    public int getTurnMoney() {
        return turnMoney;
    }

    @Override
    public boolean isActing() {
        return isActing;
    }

    @Override
    public void setActing(boolean act) {
        isActing=act;
    }

    @Override
    public void discard(DrawPile pile) {
        pile.addCard((ICard) this);
    }

    public int getValue() {
        return value;
    }
}
