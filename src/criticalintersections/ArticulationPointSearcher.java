package criticalintersections;

import common.Graph;
import common.Node;
import sun.java2d.pipe.SpanClipRenderer;

import java.util.*;
import java.util.stream.Collectors;

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

        Set<Node> allNodes = new HashSet<>(graph.getNodes().values());
        Set<Node> articulationNodes = new HashSet<>(); // Set of nodes that are articulation points.

        // Keep looping until all nodes have been visited
        while (!allNodes.isEmpty()) {
            Node rootNode = allNodes.iterator().next(); // Node to start the depth first search on.
            System.out.println(rootNode);
            // Passing these values in here because they are used to check which nodes were visited to deal with disconnected graphs
            // Storing these outside of the node class because I dont want to modify it.
            Map<Node, Integer> depthValues = new HashMap<>(); // Stores reach back values for each node. Used for visited check.
            Map<Node, Integer> reachBackValues = new HashMap<>(); // Stores reach back values for each node.
            articulationNodes.addAll(findArticulationPoints(rootNode, depthValues, reachBackValues));
            allNodes.removeAll(depthValues.keySet());
        }

        System.out.println("Found " + articulationNodes.size() + " articulation points");
        return articulationNodes;
    }

    private static Set<Node> findArticulationPoints(Node rootNode, Map<Node, Integer> depthValues, Map<Node, Integer> reachBackValues) {
        // Fringe stack for the iterative depth first search.
        Stack<FringeElement> fringe = new Stack<>();
        fringe.add(new FringeElement(rootNode));

        Set<Node> articulationNodes = new HashSet<>(); // Set of nodes that are articulation points.
        Map<Node, Stack<Node>> nodeChildren = new HashMap<>();
        Set<Node> rootDirectChildren = rootNode.stream().collect(Collectors.toSet()); // Direct children of the root node
        int rootChildProcessCount = 0; // Number of times a root child was processed directly from the root node
        // If the next child of the root is unvisited when the root gets to it, the root is an AP

        while (!fringe.empty()) {
            final FringeElement currentElement = fringe.peek();
            final Node previousNode = currentElement.getPreviousNode();
            final Node currentNode = currentElement.node;

            // Children of the current node
            Stack<Node> children = nodeChildren.get(currentNode);
            // Initialize children if not already set
            if(children == null) {
                children = new Stack<>();
                nodeChildren.put(currentNode, children);
            }

            if (!depthValues.containsKey(currentNode)) { // If the node is unvisited
                depthValues.put(currentNode, currentElement.depth);
                reachBackValues.put(currentNode, currentElement.depth);
                children.addAll(
                        currentNode.stream().
                                filter(x -> !x.equals(currentElement.getPreviousNode())).
                                collect(Collectors.toList()));
                if(rootDirectChildren.contains(currentNode) && previousNode == rootNode)
                    rootChildProcessCount++;
            } else if (!children.isEmpty()) { // If there are children of this node that still need to be visited
                Node child = children.pop();
                if (depthValues.containsKey(child)) {
                    reachBackValues.put(currentNode, Math.min(reachBackValues.get(currentNode), depthValues.get(child)));
                } else {
                    fringe.add(new FringeElement(child, currentElement, currentElement.depth + 1));
                }
            } else { // The node and the children have been fully processed, check for AP
                if (!currentNode.equals(rootNode)) {
                    reachBackValues.put(previousNode, Math.min(reachBackValues.get(currentNode), reachBackValues.get(previousNode)));
                    if (reachBackValues.get(currentNode) >= depthValues.get(previousNode)) {
                        if(previousNode.stream().count() > 1) // Only add node as AP if it has more than 1 neighbour.
                            articulationNodes.add(previousNode);
                    }
                }
                fringe.remove(currentElement);
            }
        }

        // If the root node only processed 1 unvisted node, the root is AP
        if(rootChildProcessCount <= 1)
            articulationNodes.remove(rootNode);

        return articulationNodes;
    }
}
