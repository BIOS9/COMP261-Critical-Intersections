package criticalintersections;

import common.Graph;
import common.Node;

import java.util.*;

public class ArticulationPointSearcher {
    /**
     * Finds articulation points in a graph.
     *
     * @param graph Graph of nodes to search.
     * @return A set of nodes which are articulation points.
     */
    public static Set<Node> findArticulationPoints(final Graph graph) {
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
        Map<Node, Integer> reachBackValues = new HashMap<>(); // Stores reach back values for each node.

        while (!fringe.empty()) {
            FringeElement currentElement = fringe.peek();
            Node currentNode = currentElement.node;
            int reachBack = reachBackValues.getOrDefault(currentNode, currentElement.depth);

            visitedNodes.add(currentNode);

            boolean addedChild = false; // Whether any children have been added to the fringe
            for (Node child : currentNode) {
                if (currentElement.previousElement != null && currentElement.previousElement.node.equals(child))
                    continue;

                if (reachBackValues.containsKey(child)) {
                    reachBack = Math.min(reachBack, reachBackValues.get(child));
                }

                if (visitedNodes.contains(child))
                    continue;

                addedChild = true;
                fringe.add(new FringeElement(child, currentElement, currentElement.depth + 1));
            }

            if (!addedChild) {
                ;
                fringe.remove(currentElement); // Remove self from fringe
            }

            //System.out.println("Current element: " + currentNode.toString() + " Depth: " + currentElement.depth + " Reach back: " + reachBack);

            if (reachBack != -1)
                reachBackValues.put(currentNode, reachBack);

            if (reachBack > currentElement.depth) {
                articulationNodes.add(currentNode);
            }
        }

        System.out.println(articulationNodes.size());
        return articulationNodes;
    }
}
