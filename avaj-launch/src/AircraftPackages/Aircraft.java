package AircraftPackages;

import CoordinatesPackages.Coordinates;
import FlyablePackages.Flyable;

public class Aircraft extends Flyable {
    protected long id;
    protected String name;
    protected Coordinates coordinates;

    public Aircraft(long p_id, String p_name, Coordinates p_coordinate) {
        this.id = p_id;
        this.name = p_name;
        this.coordinates = p_coordinate;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return this.getClass().getSimpleName(); //Return the name of class
    }

    @Override
    public void updateConditions() {}

    @Override
    public String toString() {
        return String.format("%s#%s(%d)", this.getClass().getSimpleName(), name, id);
    }

}