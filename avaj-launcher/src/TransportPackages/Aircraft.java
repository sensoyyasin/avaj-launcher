package TransportPackages;

import CoordinatesPackage.Coordinates;

public abstract class Aircraft extends Flyable {
    private Long id;
    private String name;
    private Coordinates coordinates;

    public Aircraft(Long id, String name, Coordinates coordinates) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "Aircraft [id=" + id + ", name=" + name + ", coordinates=" + coordinates + "]";
    }

    public void land() {
        System.out.println(this + " is landing..");
    }
}
