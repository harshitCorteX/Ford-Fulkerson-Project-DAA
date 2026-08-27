import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.*;
import java.util.List;

public class FordFulkersonWaterFlowGUI extends JFrame {

    private final String[] nodes = {"S", "C1", "C2", "C3", "C4", "C5", "C6", "T"};
    private final Map<String, Integer> indexMap = new HashMap<>();
    private final int n = nodes.length;

    private int[][] capacity = new int[n][n];
    private int[][] residual = new int[n][n];
    private int[][] flow = new int[n][n];

    private final JTextArea traceArea = new JTextArea();
    private final JTextArea flowArea = new JTextArea();
    private final JTextArea residualArea = new JTextArea();
    private final JLabel resultLabel = new JLabel("Maximum Flow: ");
    private final GraphPanel graphPanel = new GraphPanel();

    private final java.util.List<java.util.List<Integer>> augmentingPaths = new ArrayList<>();
    private final java.util.List<Integer> bottlenecks = new ArrayList<>();
    private int currentHighlightIndex = -1;

    public FordFulkersonWaterFlowGUI() {
        setTitle("Maximum Water Flow using Ford-Fulkerson");
        setSize(1200, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        for (int i = 0; i < n; i++) {
            indexMap.put(nodes[i], i);
        }

        buildGraph();
        initializePanels();
    }

    private void buildGraph() {
        addEdge("S", "C1", 12);
        addEdge("S", "C2", 8);
        addEdge("S", "C4", 15);

        addEdge("C1", "C3", 9);
        addEdge("C1", "C5", 6);

        addEdge("C2", "C3", 7);
        addEdge("C2", "C5", 10);
        addEdge("C2", "C4", 5);
        addEdge("C2", "C6", 5);

        addEdge("C4", "C6", 18);

        addEdge("C3", "T", 11);
        addEdge("C5", "T", 14);
        addEdge("C6", "T", 9);

        resetMatrices();
    }

    private void initializePanels() {
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        JButton runButton = new JButton("Run Algorithm");
        JButton nextButton = new JButton("Next Path");
        JButton resetButton = new JButton("Reset");

        topPanel.add(runButton);
        topPanel.add(nextButton);
        topPanel.add(resetButton);
        topPanel.add(resultLabel);

        add(topPanel, BorderLayout.NORTH);

        graphPanel.setPreferredSize(new Dimension(620, 520));
        graphPanel.setBorder(new TitledBorder("Flow Graph Visualization"));
        add(graphPanel, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        configureTextArea(traceArea, "Execution Trace");
        configureTextArea(flowArea, "Final Pipeline Flow");
        configureTextArea(residualArea, "Residual Graph");
        rightPanel.add(new JScrollPane(traceArea));
        rightPanel.add(new JScrollPane(flowArea));
        rightPanel.add(new JScrollPane(residualArea));
        add(rightPanel, BorderLayout.EAST);

        runButton.addActionListener(e -> runFordFulkerson());
        nextButton.addActionListener(e -> showNextPath());
        resetButton.addActionListener(e -> resetApplication());
    }

    private void configureTextArea(JTextArea area, String title) {
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 13));
        area.setBorder(BorderFactory.createTitledBorder(title));
    }

    private void addEdge(String from, String to, int cap) {
        capacity[indexMap.get(from)][indexMap.get(to)] = cap;
    }

    private void resetMatrices() {
        for (int i = 0; i < n; i++) {
            System.arraycopy(capacity[i], 0, residual[i], 0, n);
            Arrays.fill(flow[i], 0);
        }
        augmentingPaths.clear();
        bottlenecks.clear();
        currentHighlightIndex = -1;
    }

    private void runFordFulkerson() {
        resetMatrices();
        traceArea.setText("");
        flowArea.setText("");
        residualArea.setText("");

        int source = indexMap.get("S");
        int sink = indexMap.get("T");
        int maxFlow = 0;
        int step = 1;
        int[] parent = new int[n];

        while (bfs(source, sink, parent)) {
            List<Integer> path = new ArrayList<>();
            int bottle = Integer.MAX_VALUE;
            int v = sink;

            while (v != source) {
                int u = parent[v];
                path.add(v);
                bottle = Math.min(bottle, residual[u][v]);
                v = u;
            }
            path.add(source);
            Collections.reverse(path);

            v = sink;
            while (v != source) {
                int u = parent[v];
                residual[u][v] -= bottle;
                residual[v][u] += bottle;
                flow[u][v] += bottle;
                v = u;
            }

            augmentingPaths.add(path);
            bottlenecks.add(bottle);
            maxFlow += bottle;

            traceArea.append("Step " + step + ": " + pathToString(path)
                    + " | Bottleneck = " + bottle + "\n");
            step++;
        }

        resultLabel.setText("Maximum Flow: " + maxFlow + " ML/day");
        printFinalFlow();
        printResidualGraph();

        if (!augmentingPaths.isEmpty()) {
            currentHighlightIndex = 0;
            graphPanel.repaint();
        }
    }

    private boolean bfs(int source, int sink, int[] parent) {
        Arrays.fill(parent, -1);
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(source);
        visited[source] = true;
        parent[source] = -1;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v = 0; v < n; v++) {
                if (!visited[v] && residual[u][v] > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }
        return visited[sink];
    }

    private void showNextPath() {
        if (augmentingPaths.isEmpty()) return;
        currentHighlightIndex++;
        if (currentHighlightIndex >= augmentingPaths.size()) {
            currentHighlightIndex = 0;
        }
        graphPanel.repaint();
    }

    private void resetApplication() {
        resetMatrices();
        traceArea.setText("");
        flowArea.setText("");
        residualArea.setText("");
        resultLabel.setText("Maximum Flow: ");
        graphPanel.repaint();
    }

    private void printFinalFlow() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (capacity[i][j] > 0) {
                    sb.append(nodes[i]).append(" -> ").append(nodes[j])
                      .append(" = ").append(flow[i][j])
                      .append("/").append(capacity[i][j]).append("\n");
                }
            }
        }
        flowArea.setText(sb.toString());
    }

    private void printResidualGraph() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (residual[i][j] > 0) {
                    sb.append(nodes[i]).append(" -> ").append(nodes[j])
                      .append(" = ").append(residual[i][j]).append("\n");
                }
            }
        }
        residualArea.setText(sb.toString());
    }

    private String pathToString(List<Integer> path) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            sb.append(nodes[path.get(i)]);
            if (i < path.size() - 1) sb.append(" -> ");
        }
        return sb.toString();
    }

    class GraphPanel extends JPanel {
        private final Map<Integer, Point> position = new HashMap<>();

        GraphPanel() {
            position.put(0, new Point(60, 220));   // S
            position.put(1, new Point(180, 80));   // C1
            position.put(2, new Point(180, 220));  // C2
            position.put(3, new Point(370, 80));   // C3
            position.put(4, new Point(310, 380));  // C4
            position.put(5, new Point(370, 220));  // C5
            position.put(6, new Point(500, 380));  // C6
            position.put(7, new Point(580, 220));  // T
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(2));
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON);

            Set<String> highlightedEdges = new HashSet<>();
            if (currentHighlightIndex >= 0 && currentHighlightIndex < augmentingPaths.size()) {
                List<Integer> p = augmentingPaths.get(currentHighlightIndex);
                for (int i = 0; i < p.size() - 1; i++) {
                    highlightedEdges.add(p.get(i) + "-" + p.get(i + 1));
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (capacity[i][j] > 0) {
                        Point a = position.get(i);
                        Point b = position.get(j);
                        String key = i + "-" + j;

                        if (highlightedEdges.contains(key)) {
                            g2.setColor(Color.RED);
                            g2.setStroke(new BasicStroke(4));
                        } else {
                            g2.setColor(new Color(70, 130, 70));
                            g2.setStroke(new BasicStroke(2));
                        }

                        g2.drawLine(a.x, a.y, b.x, b.y);
                        int mx = (a.x + b.x) / 2;
                        int my = (a.y + b.y) / 2;
                        g2.drawString(String.valueOf(capacity[i][j]), mx, my - 5);
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                Point p = position.get(i);
                g2.setColor(new Color(241, 161, 53));
                g2.fillOval(p.x - 28, p.y - 28, 56, 56);
                g2.setColor(Color.BLACK);
                g2.drawOval(p.x - 28, p.y - 28, 56, 56);
                g2.drawString(nodes[i], p.x - 8, p.y + 5);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new FordFulkersonWaterFlowGUI().setVisible(true));
    }
}

