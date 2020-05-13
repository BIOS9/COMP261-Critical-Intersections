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
            Map<Node, Integer> depthValues = new HashMap<>(); // Stores reach back values for each node. Used for visited check.
            Map<Node, Integer> reachBackValues = new HashMap<>(); // Stores reach back values for each node.
            articulationNodes.addAll(findArticulationPoints(rootNode, depthValues, reachBackValues));
            allNodes.removeAll(depthValues.keySet());
        }

        System.out.println(articulationNodes.size());
        return articulationNodes;
    }

    private static Set<Node> findArticulationPoints(Node rootNode, Map<Node, Integer> depthValues, Map<Node, Integer> reachBackValues) {
        // Fringe stack for the iterative depth first search.
        Stack<FringeElement> fringe = new Stack<>();
        fringe.add(new FringeElement(rootNode));

        Set<Node> articulationNodes = new HashSet<>(); // Set of nodes that are articulation points.
        Set<Node> nodesWithNoChildren = new HashSet<>();
        Map<Node, Stack<Node>> nodeChildren = new HashMap<>();

        System.out.println("Root: " + rootNode.nodeID);
        Graph.testNode = rootNode;

        while (!fringe.empty()) {
            final FringeElement currentElement = fringe.peek();
            Node previousNode = currentElement.getPreviousNode();
            Node currentNode = currentElement.node;
            Stack<Node> children = nodeChildren.get(currentNode);
            if(children == null) {
                children = new Stack<>();
                nodeChildren.put(currentNode, children);
            }


            if (!depthValues.containsKey(currentNode)) {
                //if (previousNode != null)
                //    depth = depthValues.get(previousNode) + 1;

                depthValues.put(currentNode, currentElement.depth);
                reachBackValues.put(currentNode, currentElement.depth);
                children.addAll(
                        currentNode.stream().
                                filter(x -> !x.equals(currentElement.getPreviousNode())).
                                collect(Collectors.toList()));
            } else if (!children.isEmpty()) {
                Node child = children.pop();
                if (depthValues.containsKey(child)) {
                    reachBackValues.put(currentNode, Math.min(reachBackValues.get(currentNode), depthValues.get(child)));
                    if(depthValues.get(currentNode) == 0) {
                        int childDepth = depthValues.get(child);
                        reachBackValues.put(currentNode, childDepth);
                    }
                } else {
                    fringe.add(new FringeElement(child, currentElement, currentElement.depth + 1));
                }
            } else {
                if (!currentNode.equals(rootNode)) {
                    reachBackValues.put(previousNode, Math.min(reachBackValues.get(currentNode), reachBackValues.get(previousNode)));
                    if(previousNode != null && previousNode.equals(rootNode)) {
                        System.out.println("BREAK");
                    }
                    int a = reachBackValues.get(currentNode);
                    int b = depthValues.get(previousNode);
                    if (reachBackValues.get(currentNode) >= depthValues.get(previousNode)) {
                        if(previousNode.stream().count() > 1) // Only add node as AP if it has more than 1 neighbour.
                            articulationNodes.add(previousNode);
                    }
                }
                fringe.remove(currentElement);
            }
        }

        System.out.println("Nodes with no children: " + nodesWithNoChildren.size());

        return articulationNodes;
    }
}
