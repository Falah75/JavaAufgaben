package ch.ffhs.dua.tree;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;


/**
 * Ein Iterator, der in Depth-First Reihenfolge alle Werte 
 * der Knoten eines Baumes ausgibt.
 *
 * @param <N> Typ des Knotenwertes.
 */


public class TreeIterator<N> implements Iterator<N> {
    private final Stack<TreeNode<N>> stack = new Stack();

    
    public TreeIterator(TreeNode<N> node) {
        // TODO
        stack.clear();
        stack.push(node);
    }

    @Override
    public boolean hasNext() {
        //TODO
        return !stack.empty();
    }

    @Override
    public N next() {
        // TODO

        TreeNode<N> node = stack.pop();

        List<TreeNode<N>> children = node.children();

        if (children != null) {
            int size = children.size();
            for (int i = size - 1; i >= 0; i--) {
                stack.push(children.get(i));
            }
        }
        return node.value();
    }

    // remove() muss nicht implementiert werden.
}

