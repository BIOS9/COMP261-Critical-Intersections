package minspanningtree;

import common.Graph;
import common.Segment;

import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Set;

public class SpanningTreeSearcher {
    /**
     * Finds the minimum spanning tree for the given graph
     * using Kruskal's algorithm.
     * @param g Graph to generate MST for.
     * @return A set of segments representing the minimum spanning tree.
     */
    public static Set<Segment> findMinimumSpanningTree(final Graph g) {
        DisjointSet<Segment> djSet = new DisjointSet<>();
        PriorityQueue<Segment> fringe = new PriorityQueue<>();
        fringe.addAll(g.getSegments());

        while (!fringe.isEmpty()) {

        }

        return Collections.emptySet();
    }
}
