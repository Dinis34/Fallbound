package Fallbound.Model;


public class Position {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector toVector() {
        return new Vector(this.x, this.y);
    }

    public Position applyOffset(int offset) {
        return new Position(this.getX(), this.getY() - offset);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Position)) {
            return false;
        }
        return this.x == ((Position) o).x && this.y == ((Position) o).y;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime + x;
        result = prime * result + y;
        return result;
    }
}
