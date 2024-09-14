package TransportPackages;

import CoordinatesPackage.Coordinates;

public abstract class Flyable {
    private Coordinates coordinates;

    public Flyable(){}

    public Flyable(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public abstract void updateConditions();

    public boolean isLand() {
        return coordinates.getHeight() <= 0;
    }

    public void land() {
        System.out.println(this.getClass().getSimpleName() + " landing at " + coordinates);
    }

    public String toString() {
        return this.getClass().getSimpleName() + " at " + coordinates;
    }
}
