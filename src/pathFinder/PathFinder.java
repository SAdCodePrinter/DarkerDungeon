package pathFinder;

import tile.Tile;

import java.util.ArrayList;

public class PathFinder {
    private final int cols, rows;
    private final int[][] tileMap;
    private final Tile[] collisionTiles;

    Node[][] nodes;
    ArrayList<Node> openList = new ArrayList<>();
    ArrayList<Node> checkedList = new ArrayList<>();
    public ArrayList<Node> pathList = new ArrayList<>();
    Node startNode, goalNode, currentNode;
    boolean goalReached = false;
    int step = 0;

    /**
     * Den Pathfinder haben wir mittels viedeos von Youtube erstellt.
     * Die Hauptquelle ist: https://www.youtube.com/watch?v=2JNEme00ZFA
     *
     * @param cols
     * @param rows
     * @param tileMap
     * @param collisionTiles
     */
    public PathFinder(int cols, int rows, int[][] tileMap, Tile[] collisionTiles) {
        this.cols = cols;
        this.rows = rows;
        this.tileMap = tileMap;
        this.collisionTiles = collisionTiles;

        instantiateNodes();
    }

    private void instantiateNodes() {
        nodes = new Node[cols][rows];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                nodes[col][row] = new Node(col, row);
            }
        }
    }

    public void resetNodes() {

        for (int row = 0; row < rows ; row++) {
            for (int col = 0; col < cols; col++) {
                nodes[col][row].open = false;
                nodes[col][row].checked = false;
                nodes[col][row].solid = false;
            }
        }
        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }

    public void setNodes(int startCol, int startRow, int endCol, int endRow) {
        resetNodes();

        startNode = nodes[startCol][startRow];
        currentNode = startNode;
        goalNode = nodes[endCol][endRow];
        openList.add(currentNode);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                // set solid Nodes
                int tileNum = tileMap[col][row];
                if (collisionTiles[tileNum].collision) {
                    nodes[col][row].solid = true;
                }

                // set Cost
                getCost(nodes[col][row]);
            }
        }

    }

    private void getCost(Node node) {
        int xDist = Math.abs(node.col - startNode.col);
        int yDist = Math.abs(node.row - startNode.row);

        node.gCost = xDist + yDist;

        xDist = Math.abs(node.col - goalNode.col);
        yDist = Math.abs(node.row - goalNode.row);

        node.hCost = xDist + yDist;

        node.fCost = node.gCost + node.hCost;
    }

    public boolean autoSearch() {
        while (!goalReached && step < 800) {

            int col = currentNode.col;
            int row = currentNode.row;

            currentNode.setAsChecked();
            checkedList.add(currentNode);
            openList.remove(currentNode);

            // obere Node:
            if (row - 1 > 0) {
                openNode(nodes[col][row - 1]);
            }
            // linke Node
            if (col - 1 > 0) {
                openNode(nodes[col - 1][row]);
            }
            // untere Node:
            if (row + 1 < rows) {
                openNode(nodes[col][row + 1]);
            }
            // rechte Node:
            if (col + 1 < cols) {
                openNode(nodes[col + 1][row]);
            }

            // FIND THE BEST NODE
            int bestNodeIndex = 0;
            int bestNodefCost = 999;

            for (int i = 0; i < openList.size(); i++) {
                if (openList.get(i).fCost < bestNodefCost) {
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;

                    // Falls F cost gleich ist, nimm G cost
                } else if (openList.get(i).fCost == bestNodefCost &&
                        openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
                    bestNodeIndex = i;
                }
            }

            if (openList.isEmpty()) {
                break;
            }

            currentNode = openList.get(bestNodeIndex);

            if (goalNode == currentNode) {
                goalReached = true;
                trackPath();
            }
            step++;
        }
        return goalReached;
    }

    private void trackPath() {
        Node current = goalNode;

        while (
//                current.parent != null &&
                current != startNode)
        {
            pathList.add(0, current);
//            if (current.col == startNode.col && current.row == startNode.row) {
//                break;
//            }
            current = current.parent;
        }
    }

    private void openNode(Node node) {
        if (!node.open && !node.checked && !node.solid) {
            node.setAsOpe();
            node.parent = currentNode;
            openList.add(node);
        }
    }

}
