# Ford-Fulkerson-Project-DAA

## GUI Based Solution for Maximum Water Flow Using Ford-Fulkerson Algorithm

This project is a **GUI-based implementation of the Ford-Fulkerson Algorithm** designed to solve the **Maximum Flow Problem** in a water distribution network. The main objective of the project is to determine the maximum amount of water that can be transported from a **source node** to a **destination (sink) node** through a network of pipes with different capacities.

The project is developed using **Java** and **Java Swing** concepts to provide an interactive and user-friendly graphical interface. Instead of executing the algorithm only through the command line, the GUI allows users to enter the required network information and visualize the flow calculation in an easier and more understandable way.

### Project Features

* Implementation of the **Ford-Fulkerson Maximum Flow Algorithm**.
* Java-based graphical user interface using **Java Swing**.
* Representation of a water distribution network using nodes and edges.
* Support for different pipe capacities between nodes.
* Calculation of the maximum possible water flow from the source to the sink.
* Interactive input and visualization of the network.
* Demonstrates the practical application of **Data Structures and Algorithms (DAA)** concepts.
* Provides a simple way to understand how augmenting paths are used to increase the overall flow.

### How the Algorithm Works

The Ford-Fulkerson Algorithm works by repeatedly finding an available path from the source to the sink in the network. This path is called an **augmenting path**. The algorithm determines the minimum remaining capacity along this path and adds that amount to the total flow.

After each flow update, the remaining capacities of the corresponding edges are modified. The process continues until there is no possible path from the source to the sink with available capacity. At this point, the current flow represents the **maximum possible flow** through the network.

### Technologies Used

* **Java** – Core programming language used to implement the application.
* **Java Swing** – Used to create the graphical user interface.
* **Data Structures and Algorithms** – Used for graph representation and implementation of the Ford-Fulkerson algorithm.

### Objective

The primary objective of this project is to combine theoretical knowledge of **graph algorithms** with practical Java programming and GUI development. The project demonstrates how the Ford-Fulkerson Algorithm can be applied to a real-world problem such as **water distribution and pipeline flow management**.

Overall, this project provides an interactive demonstration of the Maximum Flow Problem while helping users understand the working, implementation, and practical significance of the **Ford-Fulkerson Algorithm**.

