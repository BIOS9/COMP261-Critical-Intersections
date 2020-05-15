package minspanningtree;

import java.util.HashMap;
import java.util.Map;

/**
 * Disjoint set data-structure.
 * @author Matthew Corfiatis
 */
public class DisjointSet <T> {
    private Map<T, SetNode<T>> nodes = new HashMap<>();

    /**
     * Checks if two items are in the same set.
     */
    public boolean find(T item1, T item2) {
        if(item1 == null)
            throw new IllegalArgumentException("item1 must not be null");
        if(item2 == null)
            throw new IllegalArgumentException("item2 must not be null");

        SetNode<T> node1 = nodes.get(item1);
        if(node1 == null) // Nodes cannot be in the same set if item1 doesnt exist in the ds
            return false;

        SetNode<T> node2 = nodes.get(item2);
        if(node2 == null) // Nodes cannot be in the same set if item2 doesnt exist in the ds
            return false;

        return node1.getRoot() == node2.getRoot();
    }

    /**
     * Merges two items into one set.
     */
    public void union(T item1, T item2) {
        if(item1 == null)
            throw new IllegalArgumentException("item1 must not be null");
        if(item2 == null)
            throw new IllegalArgumentException("item2 must not be null");

        SetNode<T> node1 = nodes.get(item1);
        if(node1 == null) {
            node1 = new SetNode<>();
            nodes.put(item1, node1);
        }

        SetNode<T> node2 = nodes.get(item2);
        if(node2 == null) {
            node2 = new SetNode<>();
            nodes.put(item2, node2);
        }

        SetNode<T> root1 = node1.getRoot();
        SetNode<T> root2 = node2.getRoot();

        // Do nothing if the elements are already in the same set.
        if(root1 == root2)
            return;

        if(root1.getDepth() < root2.getDepth()) {
            root1.setParent(root2);
        } else {
            root2.setParent(root1);
            if(root1.getDepth() == root2.getDepth())
                root1.setDepth(root1.getDepth() + 1);
        }
    }

    /**
     * A node for the reversed tree.
     */
    private class SetNode<T> {
        private SetNode<T> parent;
        private int depth;

        public SetNode(SetNode<T> parent, int depth) {
            if(parent == null)
                throw new IllegalArgumentException("parent must not be null");
            this.parent = parent;
            this.depth = depth;
        }

        public SetNode() {
            parent = this;
            depth = 0;
        }

        public boolean isRoot() {
            return parent == this;
        }

        public SetNode<T> getParent() {
            return parent;
        }

        public int getDepth() {
            return depth;
        }

        public void setDepth(int depth) {
            this.depth = depth;
        }

        public void setParent(SetNode<T> parent) {
            if(parent == null)
                throw new IllegalArgumentException("parent must not be null");
            this.parent = parent;
        }

        public SetNode<T> getRoot() {
            if(isRoot()) {
                return this;
            } else {
                return getParent().getRoot();
            }
        }
    }
}
