package criticalintersections;

import common.Node;
import common.Segment;

/**
 * Represents a fringe step/element to be used in a depth first search.
 */
public class FringeElement {
    public final Node node;
    public final FringeElement previousElement;
    public final Segment connectingSegment; // Segment between current node and previous node.
    public final int depth;

    public FringeElement(Node node, FringeElement previousElement, Segment connectingSegment, int depth) {
        this.node = node;
        this.previousElement = previousElement;
        this.connectingSegment = connectingSegment;
        this.depth = depth;
    }

    /**
     * Constructor for the root fringe node.
     */
    public FringeElement(Node node) {
        this.node = node;
        this.previousElement = null;
        this.connectingSegment = null;
        this.depth = 0;
    }
}
