package ch.ffhs.dua.permutations;

import static java.lang.System.arraycopy;
/**
 * arbeit von Dario und Falah
 */
public final class Permutation
{
    private Permutation()
    {
        // prevent instatiation of this utility class
    }


    /**
     * Crate an array of permutations of the interger numbers {0,1,2,3,...,N-1}.
     *
     * @param p_iN the number of elements to create the permutations
     * @return the permutations as an array of integers
     * <p><img style="width: 50%; padding: 4px; border-radius: 2px; box-shadow: 0 0 0 7px #fff, 0 0 0 8px #ddd;" alt="perm()" src="doc-files\perm.jpg"></p>
     * The method is recursively creating a temporary array 1 step smaller than the requested one and inserts <br>
     * the additional value column-wise from column 0 to n-1. <br>
     * p_iN=1 returns a 1x1 array with value 0 <br>
     * p_iN=0 returns a 0x0 array i.e. an empty array (not null) <br>
     */
    public static int[][] permutations(int p_iN)
    {
        int[][] resultArray = new int[0][];
        switch (p_iN)
        {
            case 0:
            {
                // permutations of N==0 returns not null but an empty array
                resultArray = new int[0][];
                break;
            }
            case 1:
            {
                // permutations of N==1 returns an array 1x1 with value 0
                resultArray = new int[][]{{0}};
                break;
            }
            case 2:
            {
                // recursion stop criteria, return 2x2 array filled
                resultArray = new int[][]{{0, 1}, {1, 0}};
                break;
            }
            default:
            {
                // get the recursive sub-array...
                int[][] tempArray = permutations(p_iN - 1);

                // ...and copy the values into the result array at different locations
                int iResultNrOfCols =     p_iN;
                int iResultNrOfRows = fak(p_iN);

                resultArray = new int[iResultNrOfRows][iResultNrOfCols];

                for (int iTargetRow = 0; iTargetRow < iResultNrOfRows; iTargetRow++)
                {
                    // use modulo calculations to get the row/col index values
                    int iSourceRow =  iTargetRow % fak(p_iN - 1);
                    int iInsertCol = (iTargetRow / fak(p_iN - 1));
                    int iInsertValue = p_iN - 1;

                    resultArray[iTargetRow] = insertCell(tempArray[iSourceRow], iInsertCol,  iInsertValue);
                }
                break;
            }

        }

        return resultArray;
    }

    /**
     * Calculate the factorial of the given number
     *
     * @param p_iN the number to calculate the factorial
     * @return the factorial of the param
     */
    static int fak(int p_iN)
    {
        int iResult = 1;
        for (int i = 1; i <= p_iN; i++)
        {
            iResult = iResult * i;
        }
        return iResult;
    }


    /**
     * Exchange 2 cells of the given array
     *
     * @param p_Array   the array which should contain the 2 cells
     * @param p_iIndex1 index of the first cell to swap
     * @param p_iIndex2 index of the second cell to swap
     */
    static void swap(int[] p_Array, int p_iIndex1, int p_iIndex2)
    {
        int iLength = p_Array.length;
        boolean bSwapPossible = (p_iIndex1 != p_iIndex2) && (p_iIndex1 < iLength) && (p_iIndex2 < iLength);
        if (bSwapPossible)
        {
            int iTempValue = p_Array[p_iIndex1];
            p_Array[p_iIndex1] = p_Array[p_iIndex2];
            p_Array[p_iIndex2] = iTempValue;
        }
    }

    /**
     * Insert a given cell into a given array at the defined column number and return the
     * combined array which has a size 1 larger than the given array
     *
     * @param p_SourceArray the array of integers as the source array
     * @param p_iColumn     the column number of the insertion
     * @param p_iValue      value of the inserted cell
     * @return              the combined array
     * <p><img style="width: 50%; padding: 4px; border-radius: 2px; box-shadow: 0 0 0 7px #fff, 0 0 0 8px #ddd;" alt="insert()" src="doc-files\insert.jpg"></p>
     * The method creates a new array 1 cell larger than the given one and copies the values into it so<br>that the new element is at the given location
     */
    static int[] insertCell(int[] p_SourceArray, int p_iColumn, int p_iValue)
    {
        int iSourceLength = p_SourceArray.length;
        int iTargetLength = iSourceLength + 1;
        int[] targetArray = new int[iTargetLength];
        int iInsertionIndex = iSourceLength; // as default at the most right position
        if (p_iColumn < iSourceLength)
        {
            // if the given index points to a valid position, use it
            iInsertionIndex = p_iColumn;
        }

        // copy left side of insertion
        arraycopy(p_SourceArray, 0, targetArray, 0 , iInsertionIndex);

        // insertion
        targetArray[iInsertionIndex] = p_iValue;

        // copy right side of insertion
        arraycopy(p_SourceArray, iInsertionIndex, targetArray, iInsertionIndex+1 , (iSourceLength-iInsertionIndex));
        return targetArray;
    }

}
