# avaj-launcher

Frankfurt airport recently discovered that due to frequent weather changes they have a bottleneck on some of the landing tracks. In order to find a solution, they first need to know which scenarios create the worst bottlenecks. So they decided to use a simulator where they configure and analyze multiple scenarios and hope that this will highlight them were the real problem is.

So they reach out to their local top software shop and assign them this task. Here the chief designer starts working on the concept and after analysing all the facets of the software, he makes some design decisions which, he then passes on to you in order to create the simulator.

# UML Class Diagram
![avaj_uml](https://github.com/user-attachments/assets/30686b25-0b82-459b-89af-e55240e3016f)

## Instructions

To implement an aircraft simulation program based on the provided class diagram, all classes must be implemented according to the diagram's specifications. While you can add more classes or attributes if necessary, you must not change access modifiers or the class hierarchy.

### Program Behavior

Your program will take one command-line argument representing the name of a text file containing the scenario to be simulated. Executing the program will generate a `simulation.txt` file that describes the outcome of the simulation.

**Example Usage:**

$ java ro.academyplus.avaj.simulator.Simulator scenario.txt
$ cat simulation.txt

Scenario File Format:

The scenario.txt file should adhere to the following structure:
- First Line: A positive integer representing the number of times the simulation will be run (i.e., the number of weather change triggers).
- Subsequent Lines: Each line describes an aircraft participating in the simulation, formatted as follows:
TYPE NAME LONGITUDE LATITUDE HEIGHT


Weather Generation
There are four types of weather: RAIN, FOG, SUN, and SNOW. Each three-dimensional point has its own weather, and you can use any generation algorithm that considers the point's coordinates.

Aircraft Behavior

JetPlane:
SUN: Latitude increases by 10, Height increases by 2.
RAIN: Latitude increases by 5.
FOG: Latitude increases by 1.
SNOW: Height decreases by 7.

Helicopter:
SUN: Longitude increases by 10, Height increases by 2.
RAIN: Longitude increases by 5.
FOG: Longitude increases by 1.
SNOW: Height decreases by 12.

Baloon:
SUN: Longitude increases by 2, Height increases by 4.
RAIN: Height decreases by 5.
FOG: Height decreases by 3.
SNOW: Height decreases by 15.

Simulation Rules:

- Coordinates must be positive numbers.
- Height must be in the range of 0-100.
- If an aircraft exceeds the height limit, it remains at 100.
- Each aircraft receives a unique ID upon creation. Duplicate IDs are not allowed.
- When an aircraft reaches a height of 0 or below, it lands, unregisters from the weather tower, and logs a message.
- Upon weather changes, each aircraft logs a message in the format: TYPE#NAME(UNIQUE_ID): SPECIFIC_MESSAGE.


Execution

To run the project, execute the following command:

make run

Cleanup

To remove generated files, run:

make clean

# Summary

After executing the program, you can easily print the newly created simulation.txt file to view the results. The output of this program is entirely dependent on the configurations set in the scenario.txt file.
