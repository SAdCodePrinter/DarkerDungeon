package pathFinder;

import java.util.ArrayList;

public class Node {
    Node parent;
    public int col, row;
    int gCost, // Kosten von der Startnode
            hCost, // Kosten von der Endnode
            fCost; // Kosten zusammenaddiert
    boolean start, goal, solid, open, checked;

    public Node(int col, int row) {
        this.col = col;
        this.row = row;
    }

    protected void setAsOpe() {
        this.open = true;
    }

    protected void setAsChecked() {
        this.checked = true;
    }

}
