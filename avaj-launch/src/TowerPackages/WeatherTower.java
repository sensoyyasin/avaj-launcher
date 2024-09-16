package TowerPackages;

import CoordinatesPackages.Coordinates;

public class WeatherTower extends Tower {

    public String getWeather(Coordinates p_coordinates) {
        int height = p_coordinates.getHeight();
        int latitude = p_coordinates.getLatitude();

        //0-500m genellikle sicak. 500-2000m yagisli ve bulutlu. >2000m genellikle soguk ve karli
        //0-23 enlemleri sicak(Ekvator) ve nemli, 23-66 enlemleri mevsimsel degiskenlik.(Orta enlem) 66-90 enlemleri genellikle soguk(kutuplar)

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