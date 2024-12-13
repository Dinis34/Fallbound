package Fallbound.Model;

import java.util.Objects;

import static java.lang.Math.round;

public class Vector {
    private double x;
    private double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Position toPosition() {
        return new Position(round((float) this.x), round((float) this.y));
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Vector add(Vector vector) {
        return new Vector(this.x + vector.getX(), this.y + vector.getY());
    }

    public Vector subtract(Vector vector) {
        return new Vector(this.x - vector.getX(), this.y - vector.getY());
    }

    @Override
    public String toString() {
        return "Vector{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vector vector)) {
            return false;
        }
        return Double.compare(vector.getX(), getX()) == 0 && Double.compare(vector.getY(), getY()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }
}
