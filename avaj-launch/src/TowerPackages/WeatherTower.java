package TowerPackages;

import CoordinatesPackages.Coordinates;

public class WeatherTower extends Tower {

    public String getWeather(Coordinates p_coordinates) {
        int height = p_coordinates.getHeight();
        int latitude = p_coordinates.getLatitude();

        if (height < 10) {
            if (latitude < 23)
                return "SUN"; //Ekvatora yakın
            else if (latitude < 66)
                return "FOG"; //Orta enlem
            else
                return "RAIN"; //Kutuplara yakın
        }

        else if (height < 50) {
            if (latitude < 23)
                return "RAIN"; //Ekvatora yakın
            else if (latitude < 66)
                return "FOG"; //Orta enlem
            else
                return "SNOW"; //Kutuplara yakın + soguk
        }
        else
            return "SNOW";
    }
    void changeWeather() {
        conditionChanged();
    };
}
