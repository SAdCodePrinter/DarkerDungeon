package pathFinder;

import java.util.ArrayList;

public class PathFinder {
    int maxCol, maxRow;
    ArrayList<Integer> solidObjekts;
    int[][] tileMap;

    Node[][] nodes;
    Node startNode, goalNode, currentNode;
    ArrayList<Node> openList = new ArrayList<>();
    ArrayList<Node> checkedList = new ArrayList<>();
    public ArrayList<Node> pathList = new ArrayList<>();
    boolean goalReached = false;
    int step = 0;

    public PathFinder(int[][] tileMap, ArrayList<Integer> solidObjekts) {
        int tmp = tileMap.length;
        maxCol = tileMap.length;
        maxRow = tileMap[0].length;

        nodes = new Node[maxCol][maxRow];
        this.solidObjekts = solidObjekts;
        this.tileMap = tileMap;

        instantiateNodes();
    }

    private void instantiateNodes() {

        for (int i = 0; i < maxCol; i++) {
            for (int j = 0; j < maxRow; j++) {
                nodes[i][j] = new Node(i, j);
            }
        }
    }

    public void resetNodes() {
        for (int i = 0; i < maxCol; i++) {
            for (int j = 0; j < maxRow; j++) {
                nodes[i][j].open = false;
                nodes[i][j].checked = false;
                nodes[i][j].solid = false;
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


        for (int i = 0; i < maxCol; i++) {
            for (int j = 0; j < maxRow; j++) {
                for (int solidObjekt : solidObjekts) {
                    if (tileMap[i][j] == solidObjekt) {
                        nodes[i][j].solid = true;
                        break;
                    }
                }

                // set Cost
                getCost(nodes[i][j]);
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
        while (!goalReached && step < 500) {

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
            if (row + 1 < maxRow) {
                openNode(nodes[col][row + 1]);
            }
            // rechte Node:
            if (col + 1 < maxCol) {
                openNode(nodes[col + 1][row]);
            }

            // FIND THE BEST NODE
            int bestNodeIndex = 0;
            int bestNodefCost = 999;

            for (int i = 0; i < openList.size(); i++) {
                if (openList.get(i).fCost < bestNodefCost) {
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;
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

        while (current.parent != null) {
            pathList.add(0, current);
            if (current.col == startNode.col && current.row == startNode.row) {
                break;
            }
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
