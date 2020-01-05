package ch.ffhs.dua.sort;


import java.util.Arrays;

public class HeapSort
{
    private static int s_iCompareCounts = 0;
    private static int s_iSwapCounts = 0;

    /**
     * Sortiert ein Array mit Heapsort.
     * @param p_iArray
     */
    public static void sort(int[] p_iArray)
    {
        if (p_iArray != null)
        {
            sort(p_iArray, 0, p_iArray.length - 1);
        }
    }

    /**
     * Sortiert ein Teilstück eines Array s mit Heapsort.
     * @param p_iArray
     * @param p_iFirst Index des ersten  Elementes des zu sortierenden Teils.
     * @param p_iLast Index des letzten Elementes des zu sortierenden Teils.
     */
    public static void sort(int[] p_iArray, int p_iFirst, int p_iLast)
    {
        // early exit decisions
        if (p_iArray == null)
        {
            return; // null-array
        }
        if (p_iArray.length <= 0)
        {
            return; // empty-array
        }
        if (p_iFirst >= p_iLast)
        {
            return; // single element or negative range
        }
        if (p_iFirst < 0 || p_iLast >= p_iArray.length)
        {
            return; // argument exceed the array
        }

        if (! isHeapConsistent(p_iArray, p_iFirst, p_iLast))
        {
            makeHeap(p_iArray, p_iFirst, p_iLast);
        }
        boolean bJustForTest = isHeapConsistent(p_iArray, p_iFirst, p_iLast);

        // sorting loop
        for(int iSortIndex = p_iLast; iSortIndex > p_iFirst; iSortIndex--)
        {
            swap(p_iArray, p_iFirst, iSortIndex);
            int iLastHeapElement = iSortIndex - 1;
            makeHeap(p_iArray, p_iFirst, iLastHeapElement);
            bJustForTest = isHeapConsistent(p_iArray, p_iFirst, iLastHeapElement);
        }
    }

    /**
     * Erzeugt aus einem angegebenen Teilstück einen Heap.
     * @param array
     * @param start Index des ersten Elementes, aus dem ein Heap erzeugt werden sollte.
     *              Das ist auch der Index der Wurzel des Heaps; die Kinder der Wurzel
     *              liegen dann an den Position start+1 und start+2.
     * @param end   Index des letzten Elementes des Stücks, aus dem ein Heap erzeugt werden sollte.
     */
    public static void makeHeapOld(int[] array, int start, int end)
    {
        for(int i = (end -1 )/ 2; i >= start; i--)
            sink(array,start,end-1, i);
    }

    public static void makeHeap(int[] p_iArray, int p_iFirst, int p_iLast)
    {
        // early exit decisions
        if (p_iArray == null)
        {
            return; // null-array
        }
        if (p_iArray.length <= 0)
        {
            return; // empty-array
        }
        if (p_iFirst >= p_iLast)
        {
            return; // single element or negative range
        }
        if (p_iFirst < 0 || p_iLast >= p_iArray.length)
        {
            return; // argument exceed the array
        }

        // sorting loop of heap elements
        int iRootNode = p_iFirst;
        for (int iArrayIndex = iRootNode; iArrayIndex <= p_iLast; iArrayIndex++)
        {
            int iChildNodeIndex = iArrayIndex;
            while (iChildNodeIndex > iRootNode)
            {
                int iParentNodeIndex = getParentIndex(iChildNodeIndex, iRootNode);
                if (less(p_iArray[iParentNodeIndex], p_iArray[iChildNodeIndex]))
                {
                    // parent is < child -> swap it and continue with the new parent as child
                    swap(p_iArray, iParentNodeIndex, iChildNodeIndex);
                    iChildNodeIndex = iParentNodeIndex;
                }
                else
                {
                    // parent is >= child -> end this sinking loop
                    break;
                }
            }
        }
    }


    /**
     *   Check the given array range for being consistent in terms of the rules
     *   for a binary heap
     *
     * @param p_iArray
     * @param p_iFirst
     * @param p_iLast
     * @return
     */
    public static boolean isHeapConsistent(int[] p_iArray, int p_iFirst, int p_iLast)
    {
        // early exit decisions
        if (p_iArray == null)
        {
            return false; // null-array
        }
        if (p_iArray.length <= 0)
        {
            return false; // empty-array
        }
        if (p_iFirst > p_iLast)
        {
            return false; // negative range
        }
        if (p_iFirst < 0 || p_iLast >= p_iArray.length)
        {
            return false; // argument exceed the array
        }
        if (p_iFirst == p_iLast)
        {
            return true; // single element always consistent
        }

        // checking loop
        boolean bIsConsistent = true;
        int iOffset = p_iFirst;

        for (int iIndex = p_iLast; (iIndex > p_iFirst) && bIsConsistent; iIndex--)
        {
            int iParentNode = getParentIndex(iIndex, iOffset);

            if (less(p_iArray[iParentNode], p_iArray[iIndex]))
            {
                // parent node smaller than child node i.e. inconsistent heap
                bIsConsistent = false;
            }
        }

        return bIsConsistent;
    }


    public static int getParentIndex(int p_iChildIndex, int p_iOffset)
    {
        int iMappedChild = p_iChildIndex - p_iOffset + 1;
        int iParentIndex = (iMappedChild / 2) + p_iOffset - 1;
        return iParentIndex;
    }

    public static int getLeftChildIndex(int p_iParentIndex, int p_iOffset)
    {
        int iMappedParentIndex = p_iParentIndex - p_iOffset + 1;
        int iChildIndex = (iMappedParentIndex * 2) + p_iOffset - 1;
        return iChildIndex;
    }

    public static int getRightChildIndex(int p_iParentIndex, int p_iOffset)
    {
        return getLeftChildIndex(p_iParentIndex, p_iOffset) + 1;
    }




    /**
     * Hilfsmethode für Heapsort:
     * Aus einem Teilstück eines Arrays mit den Grenzen start und end
     * sollte ein Heap erzeugt werden. Für Indices grösser als index
     * sei die Heap-Eigenschaft bereits erfüllt.
     * Die Methode ordnet das Stück zwischen index und end so um,
     * dass die Heapeigenschaft für alle Elemente erfüllt ist.
     * @param array
     * @param start
     * @param end
     * @param index
     */
    static void sink(int[] array, int start, int end, int index)
    {
        int left = leftChild(index, 1);
        int right = rightChild(index, 1);
        int max;

        if(left <= end && array[left] > array[index])
            max = left;
        else
            max = index;

        if(right <= end && array[right] > array[max])
            max = right;

        if(max != index)
        {
            swap(array, index, max);
            sink(array, start, end, max);
        }
    }

    /**
     * Entfernt das Wurzelelement eines Heaps, baut den Heap um,
     * so dass er nach dem Entfernen wieder ein Heap ist (mit einem Element weniger),
     * und setzt das ehemalige Wurzelelement an die vormals letzte Stelle im Heap
     * (die nun nicht mehr zum Heap gehört).
     * @param array Ein Array, das als Teilstück einen heap enthält.
     * @param start Indes der Wurzel des heaps
     * @param end   Index des letzten Heap-Elements.
     */
    public static void removeHeapRoot(int[] p_iArray, int p_iFirst, int p_iLast)
    {
        swap(p_iArray, p_iFirst, p_iLast);
        makeHeap(p_iArray, p_iFirst, p_iLast - 1);
    }

    public static void removeHeapRootOld(int[] array, int start, int end)
    {
        makeHeap(array, start + 1, end);
    }


    /**
     * Berechnet den Index des linken Kindelementes in einem Heap.
     * @param parentIndex
     * @param offset Offset für Heap-Eigenschaft: entspricht
     *         dem Index der Heapwurzel + 1
     * @return Index des linken Kindes
     */
    static int leftChild(int parentIndex, int offset)
    {
        return 2 * parentIndex + offset;
    }

    /**
     * Berechnet den Index des rechten Kindelementes in einem Heap.
     * @param parentIndex
     * @param offset Offset für Heap-Eigenschaft: entspricht
     *         dem Index der Heapwurzel + 1
     * @return Index des rechten Kindes
     */
    static int rightChild(int parentIndex, int offset)
    {
        return leftChild(parentIndex, offset) + 1;
    }


    /**
     * Litttle test routine for immediate results
     * @param args
     */
    public static void main(String[] args)
    {
        int[] arr = {1,20,32,19,5,23,3,5};
        System.out.println("Before Heap Sort:" );
        System.out.println(Arrays.toString(arr));

        HeapSort.sort(arr);

        System.out.println("=====================");
        System.out.println("After Heap Sort: ");
        System.out.println(Arrays.toString(arr));
    }


    private static boolean less(int p_iFirst, int p_iSecond)
    {
        s_iCompareCounts++;
        return p_iFirst < p_iSecond;
    }

    private static void swap(int[] p_iArray, int p_iFirst, int p_iSecond)
    {
        s_iSwapCounts++;
        int iTemp = p_iArray[p_iFirst];
        p_iArray[p_iFirst] = p_iArray[p_iSecond];
        p_iArray[p_iSecond] = iTemp;
    }

    public static void resetCounters()
    {
        s_iCompareCounts = 0;
        s_iSwapCounts = 0;
    }
    public static int getCompareCounts()
    {
        return s_iCompareCounts;
    }
    public static int getSwapCounts()
    {
        return s_iSwapCounts;
    }





}
