package ch.ffhs.dua.sort;


import java.util.Arrays;

public class InsertSort
{
    private static int s_iCompareCounts = 0;
    private static int s_iSwapCounts = 0;

    /**
     * Sortiert ein Array durch Einfügen
     * @param p_iArray Das zu sortierende Array.
     */
    public static void sort(int[] p_iArray)
    {
        if (p_iArray != null)
        {
            sort(p_iArray, 0, p_iArray.length - 1);
        }
    }

    /**
     * Sortiert einen durch zwei Grenzen angebenen Teil eines Arrays durch Einfügen.
     * Arrayelemente ausserhalb der Grenzen werden nicht verschoben.
     * @param p_iArray
     * @param p_iFirst Index des ersten  Elementes des Teils, das Sortiert werden muss.
     * @param p_iLast   Index des letzten Elementes des Teils, das sortiert werden muss.
     */
    public static void sort(int[] p_iArray, int p_iFirst, int p_iLast)
    {
        // early exit decisions
        if (p_iArray == null)
        {
            return;
        }
        if (p_iArray.length <= 0)
        {
            return;
        }
        if (p_iFirst >= p_iLast)
        {
            return;
        }
        if (p_iFirst < 0 || p_iLast >= p_iArray.length)
        {
            return;
        }

        // sort loop
        for (int iToSort = p_iFirst + 1; iToSort <= p_iLast; iToSort++)
        {
            for (int iToCompare = iToSort; iToCompare > p_iFirst && less(p_iArray[iToCompare], p_iArray[iToCompare-1]); iToCompare--)
            {
                swap(p_iArray, iToCompare, iToCompare - 1);
            }
        }

    }

    public static void main(String[] args)
    {
        int[] input = {2,34,6,32,3,431,34,1,2,56,6};
        System.out.println(Arrays.toString(input));

        InsertSort.resetCounters();
        InsertSort.sort(input);
        System.out.println(Arrays.toString(input));
        System.out.println("Compares " + InsertSort.getCompareCounts());
        System.out.println("Swaps    " + InsertSort.getSwapCounts());
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


