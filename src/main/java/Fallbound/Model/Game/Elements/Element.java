package Fallbound.Model.Game.Elements;

import Fallbound.Model.Position;

public class Element {
    private Position position;

    public Element(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(!(o instanceof Element)){
            return false;
        }
        return this.getPosition().equals(((Element) o).getPosition());
    }

    @Override
    public int hashCode() {
        return position.hashCode();
    }
}
