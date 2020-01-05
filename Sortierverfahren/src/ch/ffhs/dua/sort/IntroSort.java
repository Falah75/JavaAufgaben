package ch.ffhs.dua.sort;


import static ch.ffhs.dua.sort.QuickSort.partition;

public class IntroSort
{
	/**
	 * Sortiert ein Array der Länge n mit IntroSort.
	 * Das Array wird mit Quicksort sortiert, wenn aber die Rekursionstiefe mehr 
	 * als n * log(n) wird, wird auf HeapSort ungestellt.
	 * @param array
	 */
	public static void sort(int[] array)
	{
		int l = array.length;

		int iN = 1000;
		int iLog = log2(iN);
        int iLevel = 2 * iN * iLog;

		sort(array, 0, l - 1, 2 * l * log2(l));
	}
	
	/**
	 * Sortiert eine Teilstück eines Arrays mit IntroSort.
	 * @param array Zu sortierendes Array
	 * @param start Index des ersten Elementes des zu sortierenden Stücks.
	 * @param end   Index des letzten Elementes des zu sortierenden Stücks.
	 * @param maxDepth
	 */
	public static void sort(int[] array, int start, int end, int maxDepth)
	{
		if (start >= end) return;
		if (maxDepth < 0)
		{
			HeapSort.sort(array, start, end);
		}
		int pivot = QuickSort.findPivot(array, start, end);
		pivot = partition(array, start, end, pivot);
		sort(array, start, pivot - 1, maxDepth - 1);
		sort(array, pivot + 1, end, maxDepth - 1);
	}
	

	/**
	 * Bestimmt den (abgerundeten) Zweierlogarithmus ohne float-Arithmetik.
	 * @param n
	 * @return
	 */
	public static int log2(int n)
	{
		int log = 0;
		while (n > 1)
		{
			n >>>= 1;
			log++;
		}
		return log;
	}
}

