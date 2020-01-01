package ch.ffhs.dua.tree;

import java.util.List;

/**
 * Knoteninterface f�r B�ume, die traversiert werden m�ssen.
 * @author urs-martin
 *
 * @param <N>
 */
public interface TreeNode<N> 
{
    /** 
     * @return Liefert den Wert des Knotens.
     */
	N value();
    
	/**
	 * @return Gibt eine Liste mit allen Kinderknoten zur�ck.
	 */
    List<TreeNode<N>> children();
}
