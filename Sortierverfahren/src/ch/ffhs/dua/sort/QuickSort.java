package ch.ffhs.dua.sort;

import java.util.Arrays;

public class QuickSort
{
    private static int s_iCompareCounts = 0;
    private static int s_iSwapCounts = 0;

	/**
	 * Sortiert ein Array durch Quicksort.
	 * @param p_iArray Zu sortierendes Array.
	 */
	public static void sort(int[] p_iArray)
	{
        if (p_iArray != null)
        {
            sort(p_iArray, 0, p_iArray.length - 1);
        }
	}

	/**
	 * Sortiert ein Teilstück eines Arrays durch Quicksort.
	 * @param p_iArray zu sortierenden Array
	 * @param p_iFirst Index des ersten Elementes des Teils, das sortiert werden muss.
	 * @param p_iLast   Index des letzen Elementes des Teils, das sortiert werden muss.
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

        // quick sort recursion
		int iPivotIndex = findPivot(p_iArray, p_iFirst, p_iLast);
        int iNewPivotIndex = partition(p_iArray, p_iFirst, p_iLast, iPivotIndex);

        sort(p_iArray,p_iFirst, iNewPivotIndex - 1); // do not sort the pivot element again
        sort(p_iArray, iNewPivotIndex + 1, p_iLast); // do not sort the pivot element again
	}

	/**
	 * Schwellwert, bei welcher Arraygrösse in der Rekursion InsertSort
	 * statt Quicksort aufgerufen werden sollte.
	 */
	static int THRESHOLD = 4; // TODO finden Sie einen sinnvollen Wert

	/**
	 * Modifiziertes Quicksorts.
	 * Wenn die Grösse des zu sortierenden Arrays in der Rekursion
	 * einen Schwellwert unterschreitet, wird InsertSort statt Quicksort
	 * aufgerufen.
	 * @param p_iArray Zu sortierendes Array
	 */
	public static void sortPlus(int[] p_iArray)
	{
        if (p_iArray != null)
        {
            sortPlus(p_iArray, THRESHOLD);
        }
	}

    public static void sortPlus(int[] p_iArray, int p_iThreshold)
    {
        if (p_iArray != null)
        {
            sortPlus(p_iArray, 0, p_iArray.length - 1, p_iThreshold);
        }
    }


	/**
	 * Modifiziertes Quicksorts zum Sortieren eines Teilstücks eines Arrays.
	 * Wenn die Grösse des zu sortierenden Arrays in der Rekursion
	 * einen Schwellwert unterschreitet, wird InsertSort statt Quicksort
	 * aufgerufen.
	 * @param p_iArray Zu sortierendes Array
	 * @param p_iFirst Index des ersten  Elementes des zu sortierenden teilstücks.
	 * @param p_iLast   Index des letzten Elementes des zu sortierenden teilstücks.
     * @param p_iThreshold Level zum Umschalten des Algorithmus
	 */
	public static void sortPlus(int[] p_iArray, int p_iFirst, int p_iLast, int p_iThreshold)
	{
	    int iSize = p_iLast - p_iFirst;
	    if (iSize <= p_iThreshold)
        {
            // too small to do quicksort, use insert sort
            InsertSort.sort(p_iArray, p_iFirst, p_iLast );
        }
        else
        {
            int i = p_iFirst;
            int j = p_iLast;
            int iPivotIndex = findPivot(p_iArray, p_iFirst, p_iLast);


            int iPivotValue = p_iArray[iPivotIndex];
            while(i <= j)
            {
                while(less(p_iArray[i], iPivotValue))
                {
                    i++;
                }
                while(less(iPivotValue, p_iArray[j]))
                {
                    j--;
                }

                if(i <= j)
                {
                    swap(p_iArray, i, j);
                    i++;
                    j--;
                }
            }

            if(less(p_iFirst, j))
            {
                sortPlus(p_iArray,p_iFirst, j, p_iThreshold);
            }

            if(less(i, p_iLast))
            {
                sortPlus(p_iArray, i, p_iLast, p_iThreshold);
            }
        }

	}

	/**
	 * Hilfsmethode für Quicksort.
	 * Ein Teilstück eines Arrays wird geteilt, so dass alle Elemente,
	 * die kleiner als ein gewisses Pivot-Elements sind, links stehen
	 * und alle Elemente, die grösser als das Pivot-Element rechts stehen.
     *
     * Partition Algorithm according Robert Sedgewick, Algortihms 4th edition.
     *
	 * @param p_iArray Array zum Umordnen.
	 * @param p_iFirstIndex Indes des ersten  Elements des Teilstücks, das geteilt werden muss.
	 * @param p_iLastIndex   Index des letztes Elements des Teilstücks, das geteilt werden muss.
	 * @param p_iPivotIndex   Index des PiotElements
	 * @return Index des Piot-Element nach der Partitionierung.
	 */
    static int partition(int[] p_iArray, int p_iFirstIndex, int p_iLastIndex, int p_iPivotIndex)
    {
        // save the pivot element in the first element cell
        swap(p_iArray, p_iPivotIndex, p_iFirstIndex);

        int iPivotValue = p_iArray[p_iFirstIndex];
        int iLeftIndex  = p_iFirstIndex;
        int iRightIndex = p_iLastIndex + 1; // plus one as the init value for the --iRightIndex statement


        while (true) // exit the loop with break statement
        {
            // Scan left side
            while (less(p_iArray[++iLeftIndex], iPivotValue))
            {
                if (iLeftIndex == p_iLastIndex)
                {
                    break;
                }
            }
            // scan right side
            while (less(iPivotValue, p_iArray[--iRightIndex]))
            {
                if (iRightIndex == p_iFirstIndex)
                {
                    break;
                }
            }
            // check for scan complete, and exchange.
            if (iLeftIndex >= iRightIndex)
            {
                break;
            }
            // exchange found elements
            swap(p_iArray, iLeftIndex, iRightIndex);
        }

        swap(p_iArray, p_iFirstIndex, iRightIndex); // restore pivot element

        return iRightIndex;
    }


	/**
	 * Hilfsmethode zum Finden eines Pivot-Elementes für Quicksort.
	 * Zu einem Array und den zwei Indices start und end wird
	 * der Index eines möglichen Pivot-Elementes angegeben
	 * @param p_iArray
	 * @param p_iFirstIndex
	 * @param p_iLastIndex
	 * @return Index eines Pivot-Elementes
	 */
	static int findPivot(int[] p_iArray, int p_iFirstIndex, int p_iLastIndex)
	{
	    int iArrayRangeSize = (p_iLastIndex - p_iFirstIndex);
		return p_iFirstIndex +  (iArrayRangeSize / 2);  // save for arithmetic overflow
//        return 0; // worst case simulation
	}


	public static void main(String[] args)
	{
		int[] input = {2,34,6,32,3,431,34,1,2,56,6};
        System.out.println(Arrays.toString(input));

        QuickSort.resetCounters();
		QuickSort.sort(input);
		System.out.println(Arrays.toString(input));
        System.out.println("Compares " + QuickSort.getCompareCounts());
        System.out.println("Swaps    " + QuickSort.getSwapCounts());
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
