package TowerPackages;

import FlyablePackages.Flyable;

import java.util.ArrayList;
import java.util.List;

public class Tower {
    private List<Flyable> observers = new ArrayList<>();

    public void register(Flyable p_flyable) {
        observers.add(p_flyable);
        System.out.println("Tower says: " + p_flyable.getType() + "#" + p_flyable.getName() + "(" + p_flyable.getId() + ") registered to weather tower.");
    };

    public void unregister(Flyable p_flyable) {
        observers.remove(p_flyable);
        System.out.println("Tower says: " + p_flyable.getType() + "#" + p_flyable.getName() + "(" + p_flyable.getId() + ") unregistered from weather tower.");
    };

    public void conditionChanged() {
        for (Flyable p_flyable : observers) {
            p_flyable.updateConditions();
        }
    };
}