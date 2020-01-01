package ch.ffhs.dua.tree;

/**
 * Klasse zum Traversieren eines Baumes mit Tiefensuche.
 * Am einfachsten kann die Tiefensuche rekursiv programmiert werden.
 *
 * @param <N>
 */
public abstract class DepthFirstTraverserRec<N> {
    
    public void traverse(TreeNode<N> node) {

        preOperation(node.value());
        if (node.children() != null) {
            for (TreeNode<N> child : node.children()) {
                traverse(child);
            }

        }
        postOperation(node.value());
    }

  /**
     * Operation auf einem Knoten, bevor die Nachkommen besucht wurden.
     * @param value
     */
    abstract protected void preOperation(N value);

   /**
     * Operation auf einem Knoten, nachdem die Nachkommen besucht wurden.
     * @param value
     */
    abstract protected void postOperation(N value);

}   