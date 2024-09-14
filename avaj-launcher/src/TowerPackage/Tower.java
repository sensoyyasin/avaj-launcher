package TowerPackage;

import TransportPackages.Flyable;

import java.util.ArrayList;
import java.util.List;

public class Tower {
    private List<Flyable> observers = new ArrayList<>();

    public void register(Flyable flyable) {
        observers.add(flyable);
        System.out.println("Tower says: " + flyable + " registered to weather tower.");
    }

    public void unregister(Flyable flyable) {
        observers.remove(flyable);
        System.out.println("Tower says: " + flyable + " unregistered to weather tower.");
    }

    public void notifyWeatherChange() {
        for (Flyable flyable : observers) {
            flyable.updateConditions();
            if (flyable.isLand())
                unregister(flyable);
        }
    }
}
