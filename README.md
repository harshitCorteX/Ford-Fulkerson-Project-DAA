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
  
## 📺 Application in Action

<img width="1002" height="615" alt="Screenshot 2026-08-27 224639" src="https://github.com/user-attachments/assets/61c86a6e-2589-451a-92e6-ec44679c1961" />
<img width="787" height="565" alt="Screenshot 2026-08-27 224715" src="https://github.com/user-attachments/assets/08278d07-5e38-4f5d-9969-24f954851a36" />
<img width="772" height="561" alt="Screenshot 2026-08-27 224648" src="https://github.com/user-attachments/assets/6105b685-407a-4bb0-848b-ad76d98d8234" />
<img width="856" height="566" alt="Screenshot 2026-08-27 224655" src="https://github.com/user-attachments/assets/16c6762d-8286-4c80-a3ab-bb0ad9c3d6e1" />
<img width="841" height="558" alt="Screenshot 2026-08-27 224700" src="https://github.com/user-attachments/assets/20e6c5d6-8943-4d67-a661-0f5b4416f0f6" />
<img width="800" height="572" alt="Screenshot 2026-08-27 224708" src="https://github.com/user-attachments/assets/28d4e8f0-e10d-410b-9c6c-7109dbf82455" />




1. Clone the repository:
   ```bash
   git clone https://github.com/harshitcortex/Ford-Fulkerson-Project-DAA.git
