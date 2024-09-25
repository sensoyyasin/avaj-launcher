# avaj-launcher

Frankfurt airport recently discovered that due to frequent weather changes they have a bottleneck on some of the landing tracks. In order to find a solution, they first need to know which scenarios create the worst bottlenecks. So they decided to use a simulator where they configure and analyze multiple scenarios and hope that this will highlight them were the real problem is.

So they reach out to their local top software shop and assign them this task. Here the chief designer starts working on the concept and after analysing all the facets of the software, he makes some design decisions which, he then passes on to you in order to create the simulator.

# UML Class Diagram
![avaj_uml](https://github.com/user-attachments/assets/30686b25-0b82-459b-89af-e55240e3016f)

# Execution

To run the project, use:
make run

# Cleanup

To remove generated files, execute:
make clean

After that you can easily print out the simulation.txt file which is brand new created. And you can see the result of this program. The result is totally depends on the scenario.txt file. In Scenario file:

- The first line of the file contains a positive integer number. This number represents the number of times the simulation is run. In our case, this will be the number of times a weather change is triggered.
  
- Each following line describes an aircraft that will be part of the simulation, with this format: TYPE NAME LONGITUDE LATITUDE HEIGHT.
