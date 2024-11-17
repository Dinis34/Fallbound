package Fallbound.Model.Game.Hud;

import Fallbound.Model.Position;

public class CoinDisplay extends Hud{
    private int coincount;
    public CoinDisplay(Position position, int coincount) {
        super(position);
        this.coincount = coincount;
    }
    public int getCoincount() {
        return coincount;
    }
    public void setCoincount(int coincount) {
        this.coincount = coincount;
    }
}
