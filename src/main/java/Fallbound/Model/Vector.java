package Fallbound.Model;

import java.util.Objects;

public class Vector {
    private double x;
    private double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Position toPosition() {
        return new Position((int) this.x, (int) this.y);
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

    public Vector multiply(double scalar) {
        return new Vector(this.x * scalar, this.y * scalar);
    }

    public Vector divide(double scalar) {
        return new Vector(this.x / scalar, this.y / scalar);
    }

    public double magnitude() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public void normalize() {
        double magnitude = this.magnitude();
        if (magnitude != 0) {
            this.x = this.divide(magnitude).getX();
            this.y = this.divide(magnitude).getY();
        }
    }

    public Vector copy() {
        return new Vector(this.x, this.y);
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
