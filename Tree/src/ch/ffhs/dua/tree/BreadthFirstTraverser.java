package ch.ffhs.dua.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Analog zu den Traversierungen in 6.2.4 aus [SG] implementiere man rekursiv 
 * eine Traversierung für beliebige (nicht nur binäre) Bäume.
 * Traverser-Klasse für Breitensuche.
 * Ein Traverser mit Breitensuche besucht zuerst die Wurzel,
 * dann die Kinder der Wurzel, dann die Enkel usw.
 *
 * @param <N>
 */
public abstract class BreadthFirstTraverser<N> {

    /**
     * Methode zum Traversieren eines Baumes.
     * @param node Wurzelknoten des Baumes.
     */

    
    public void traverse(TreeNode<N> node) {
        //TODO
        Queue<TreeNode<N>> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            TreeNode<N> head = queue.remove();
            visitNode(head.value());

            List<TreeNode<N>> children = head.children();

            if (children != null) {
                for (TreeNode<N> child : children) {
                    queue.add(child);
                }
            }
        }

    }

    /**
     
    /**
     * Diese Methode gibt an, was beim travsersieren gemacht werden sollte.
     * @param value
     */
    protected abstract void visitNode(N value);

}   