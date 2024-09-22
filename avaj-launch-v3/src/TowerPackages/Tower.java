package TowerPackages;

import FlyablePackages.Flyable;

import java.util.ArrayList;
import java.util.List;

public class Tower {
    private final List<Flyable> observers = new ArrayList<Flyable>();

    public void register(Flyable p_flyable) {
        observers.add(p_flyable);
    }

    public void unregister(Flyable p_flyable) {
        observers.remove(p_flyable);
    }

    protected void conditionChanged() {
        for (int i = 0; i < observers.size(); i++)
            observers.get(i).updateConditions();
    }
}