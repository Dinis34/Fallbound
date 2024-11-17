package Fallbound.Model.Game.Elements;

import Fallbound.Model.Vector;

public class Element {
    private Vector position;

    public Element(Vector position) {
        this.position = position;
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Element)) {
            return false;
        }
        return this.getPosition().equals(((Element) o).getPosition());
    }

    @Override
    public int hashCode() {
        return position.hashCode();
    }
}
