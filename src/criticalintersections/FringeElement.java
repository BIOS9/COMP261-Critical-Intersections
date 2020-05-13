package criticalintersections;

import common.Node;
import common.Segment;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Represents a fringe step/element to be used in a depth first search.
 */
public class FringeElement {
    public final Node node;
    public final FringeElement previousElement;
    public final int depth;

    public FringeElement(Node node, FringeElement previousElement, int depth) {
        this.node = node;
        this.previousElement = previousElement;
        this.depth = depth;
    }

    /**
     * Constructor for the root fringe node.
     */
    public FringeElement(Node node) {
        this.node = node;
        this.previousElement = null;
        this.depth = 0;
    }

    public Node getPreviousNode() {
        if(previousElement == null)
            return null;
        return previousElement.node;
    }
}
