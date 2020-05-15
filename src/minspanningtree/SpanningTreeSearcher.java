package minspanningtree;

import common.Graph;
import common.Node;
import common.Segment;
import gui.GUI;

import java.util.*;

public class SpanningTreeSearcher {
    /**
     * Finds the minimum spanning tree for the given graph
     * using Kruskal's algorithm.
     * @param g Graph to generate MST for.
     * @return A set of segments representing the minimum spanning tree.
     */
    public static Set<Segment> findMinimumSpanningTree(final Graph g) {
        DisjointSet<Node> djSet = new DisjointSet<>();
        PriorityQueue<Segment> fringe = new PriorityQueue<>();
        Set<Segment> minimumSpanningTree = new HashSet<>();
        fringe.addAll(g.getSegments());

        while (!fringe.isEmpty()) {
            Segment segment = fringe.poll();

            if(!djSet.find(segment.start, segment.end)) {
                djSet.union(segment.start, segment.end);
                minimumSpanningTree.add(segment);
            }
        }

        return minimumSpanningTree;
    }
}
