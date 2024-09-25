# Makefile

SRC = *.java
CLASSES = $(SRC:.java=.class)
MAIN = Simulator
JFLAGS = -g

all: $(CLASSES)

%.class: %.java
	javac $(JFLAGS) $<

run: all
	java $(MAIN) scenario.txt

clean:
	rm -f *.class AircraftPackages/*.class CoordinatesPackages/*.class FlyablePackages/*.class LogPackages/*.class TowerPackages/*.class VehiclePackages/*.class WeatherPackages/*.class  simulation.txt
