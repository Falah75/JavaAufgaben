package ch.ffhs.dua.binSearch;

/**
 * arbeit von Falah und Dario
 */



public class BinSearch
{
    private static int iterationCounter = 0;

    /**
     * @param array
     * @param value
     * @return Ein Paar mit kleinestem und grösstem Index oder new Pair(-1,-1).
     */
    public static Pair search(int[] array, int value)
    {
        iterationCounter = 0;
        System.out.println("\n\n Start search value: " + value + " array length: " + array.length);
        int lowest = 0;
        int highest = array.length - 1;

        
        //Schleife, solange der niedrigste Index kleiner als der höchste ist
       
        
        while (lowest < highest)
        {
            iterationCounter++;
	 
            // get the center index
	 
            int centerIndex = lowest + (highest - lowest) / 2;

            
	 // Check wenn value ist kleiner als center
	 
            if (value < array[centerIndex])
            {
                highest = centerIndex - 1;
            }

            // check wenn value grösser
	 
            else if (value > array[centerIndex])
            {
                lowest = centerIndex + 1;
            }

            // Rechne kleinsten und grössten
	 
            else
            {
                Pair result = new Pair(lowestIndex(array,value,lowest,highest),highestIndex(array,value,lowest,highest));
                System.out.println("iterationCounter: "+iterationCounter);
                return result;
            }
        }

        // lowest index is not bigger then highest, check if value is on this index and return
        
        if (lowest == highest && value == array[lowest])
        {
            System.out.println("iterationCounter: " + iterationCounter);
            return new Pair(lowest, highest);
        }

        System.out.println("iterationCounter: " + iterationCounter);
        return null;

    }

    /**
     * Get the lowest index of the array
     *
     * @param array
     * @param value
     * @param lowestIndex
     * @param highestIndex
     * @return
     */
    public static int lowestIndex(int[] array, int value, int lowestIndex, int highestIndex)
    {

        for (int i = lowestIndex; i <= highestIndex; i++)
        {
            iterationCounter++;
            if (value == array[i])
            {
                return i;
            }
        }
        return -1;
    }

    /**
     * Get the highest index of the array
     *
     * @param array
     * @param value
     * @param lowestIndex
     * @param hightestIndex
     * @return
     */
    public static int highestIndex(int[] array, int value, int lowestIndex, int hightestIndex)
    {

        for (int i = hightestIndex; i >= lowestIndex; i--)
        {
            iterationCounter++;
            if (value == array[i])
            {
                return i;
            }
        }
        return -1;
    }

}
