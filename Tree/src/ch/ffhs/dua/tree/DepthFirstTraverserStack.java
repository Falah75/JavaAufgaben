package ch.ffhs.dua.tree;

import java.util.List;
import java.util.Stack;

/**
 * Klasse zum Traversieren eines Baumes mit Tiefensuche.
 * Diese Implementierung verwende keine Rekursion, sondern einen Stack.
 *
 * @param <N>
 */


public abstract class DepthFirstTraverserStack<N> {
    Stack<TreeNode<N>> stack = new Stack();

    
    public void traverse(TreeNode<N> root) {
        if (root == null) return;

        stack.push(root);
        while (!stack.empty()) {
            TreeNode<N> node = stack.pop();
            visitNode(node.value());
            List<TreeNode<N>> children = node.children();

            if (children != null) {
                int size = children.size();
                for (int i = size - 1; i >= 0; i--) {
                    stack.push(children.get(i));
                }
            }
        }
    }

    /**
     * Operation auf einem Knoten bei der Traversierung; 
     * die Operation wird durchgeführt,
     * bevor die Nachkommen besucht werden.
     * @param value
     */

    
    abstract protected void visitNode(N value);

}   
