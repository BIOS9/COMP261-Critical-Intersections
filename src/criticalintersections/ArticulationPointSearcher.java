package criticalintersections;

import common.Graph;
import common.Node;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class ArticulationPointSearcher {
    /**
     * Finds articulation points in a graph.
     * @param graph Graph of nodes to search.
     * @return A set of nodes which are articulation points.
     */
    public Set<Node> findArticulationPoints(final Graph graph) {
        if (graph == null) {
            return Collections.emptySet();
        }

        // Node to start the depth first search on.
        Node rootNode = graph.getNodes().values().iterator().next();

        // Fringe stack for the iterative depth first search.
        Stack<FringeElement> fringe = new Stack<>();
        fringe.add(new FringeElement(rootNode));

        Set<Node> articulationNodes = new HashSet<>(); // Set of nodes that are articulation points.
        Set<Node> visitedNodes = new HashSet<>();

        while (!fringe.empty()) {

        }

        return articulationNodes;
    }
}
