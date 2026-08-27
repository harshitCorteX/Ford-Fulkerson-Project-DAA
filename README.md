# 🌊 Ford-Fulkerson Project (DAA)

A GUI-based solution built in Java to solve the Maximum Flow Problem in a water distribution network using the Ford-Fulkerson Algorithm.

📌 About the Project
This project provides an interactive graphical interface for calculating the maximum amount of water that can be transported from a source node to a destination (sink) node through a network of pipes with varying capacities. Rather than relying on command-line execution, the application leverages Java Swing to allow users to input network data and visualize flow calculations intuitively.

✨ Project Features
- **Ford-Fulkerson Algorithm:** Core implementation for solving the Maximum Flow Problem via augmenting paths.
- **Java Swing GUI:** Interactive and user-friendly graphical interface for smooth navigation and visualization.
- **Network Representation:** Models water distribution networks using nodes (junctions/stations) and weighted edges (pipes).
- **Custom Capacities:** Supports variable pipe capacities between nodes.
- **Visual Flow Calculation:** Demonstrates practical applications of Data Structures and Algorithms (DAA) concepts.

⚙️ How the Algorithm Works
- **Find Augmenting Paths:** Repeatedly searches for an available path from the source node to the sink node in the residual network.
- **Calculate Residual Capacity:** Determines the minimum remaining capacity along the identified path.
- **Update Flow:** Adds this capacity amount to the total flow and updates the residual capacities of the corresponding edges.
- **Termination:** The process repeats until no further augmenting paths with available capacity exist, yielding the maximum possible flow.

🛠️ Technologies Used
- **Java:** Core programming language.
- **Java Swing:** Graphical User Interface framework.
- **Data Structures & Algorithms (DAA):** Graph representation and algorithmic logic.

🚀 Getting Started
### Prerequisites
- Java Development Kit (JDK) installed on your system.
### Running the Application
1. Clone the repository:
   ```bash
   git clone [https://github.com/harshitcortex/Ford-Fulkerson-Project-DAA.git](https://github.com/harshitcortex/Ford-Fulkerson-Project-DAA.git)
