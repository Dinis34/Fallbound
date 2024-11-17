package Fallbound.Model.Game.Elements;
import Fallbound.Model.Position;


public class Coin extends Element{
    public Coin(Position position) {
        super(position);
    }
    public boolean checkcollision(Element player){
        return this.getPosition().equals(player.getPosition());
    }
}