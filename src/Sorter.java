/**
 * The sorter class has a display to show the various sorting methods it includes.
 * These sorting methods are selectionSort, insertionSort, mergeSort, 
 * and quickSort. Along with these methods, 
 * it has corresponding helper methods: indexOfMin, insert, mergesortHelp, merge, 
 * quicksortHelp, and partition.
 * 
 * @author Aashish Jain
 * 			With Assistance from Richard Page
 * @version December 5, 2014
 */
public class Sorter
{
    
    private SortDisplay display;
    
    public static void main(String [] args)
    {
        Sorter sorter = new Sorter();
    }
    
    // comment me
    public Sorter()
    {
        display = new SortDisplay(this);
    }
    
    /**
     * Finds the index of the smallest value from the range
     * [startIndex, a.length] in the comparable array by 
     * comparing each value with the current smallest value.
     * If any compared value is smaller, it becomes the new "smallest value".
     * 
     * @precondition 0 <= startIndex <= a.length - 1
     * 
     * @param a The array that is searched.
     * @param startIndex The index to start from
     * @return An integer value of the index of the smallest value.
     */
    public int indexOfMin(Comparable [] a, int startIndex)
    {
        int minIndex = startIndex;
        for (int i = startIndex; i < a.length; i++)
        {
            if (a[i].compareTo(a[minIndex]) < 0)
            {
                minIndex = i;
            }
        }
        return minIndex;
    }
    
    /**
     * Sorts an array using the selection sort algorithm. This algorithm simply
     * finds the lowest value in the array and moves it to the first position.
     * It then finds the next lowest value and moves it to the next position. 
     * It does this through the entire array. 
     * 
     * @postcondition The array is sorted from lowest to highest in comparable values. 
     * 
     * @param a The array that is sorted
     */
    public void selectionSort(Comparable[] a)
    {
        for (int i = 0; i < a.length; i++)
        {
            int minIndex = indexOfMin(a, i);
            Comparable ref = a[i];
            a[i] = a[minIndex];
            a[minIndex] = ref;
            display.update();
        }
    }
    
    /**
     * Inserts the element at nextIndex in the correct position through the
     * elements before nextIndex. The elements before nextIndex are in order.
     * This method accomplishes this task by starting from nextIndex and
     * moving all the way to the beginning of the array. If the visited value
     * is greater than the value at nextIndex, it is switched with the value at nextIndex.
     * 
     * @precondition 0 <= nextIndex <= a.length - 1
     * @postcondition The value at nextIndex is inserted at the correct position before itself. 
     * 
     * @param a The array that this method is called upon. 
     * @param nextIndex The index at which the value it stores is supposed to be inserted in the
     * 					correct position at the indices before it. 
     */
    public void insert(Comparable [] a, int nextIndex)
    {
        Comparable valueToInsert = a[nextIndex];
        for (int i = nextIndex; i > -1; i--)
        {
            if (a[i].compareTo(valueToInsert) > 0)
            {
                Comparable ref = a[i];
                a[i] = a[i+1];
                a[i+1] = ref;
                display.update();
            }
        }
    }
    
    /**
     * Completes an insertion sort on the array. Does so by calling insert
     * on all the elements in the array from the index 0 to the index of a.length - 1.
     * 
     * @postcondition the array is sorted in order (lowest to highest).
     * 
     * @param a The array which the insertion sort is performed on
     * 
     */
    public void insertionSort(Comparable[] a)
    {
        for (int k = 0; k < a.length; k++)
        {
            insert(a, k);
        }
    }
    
    /**
     * Completes a merge sort on the array. Does so by calling mergesortHelp
     * on the array from index 0 to array.length - 1. 
     * 
     * @postcondition The array is in order from lowest to highest. 
     * 
     * @param a The array which the merge sort is performed on. 
     */
    public void mergesort(Comparable[] a)
    {
        mergesortHelp(a, 0, a.length - 1);
    }
    
    //Postcondition: a[lowIndex] to a[highIndex] are in increasing order
    
    /**
     * Sorts an array from lowIndex to highIndex using the merge algorithm.
     * This algorithm is recursive, it stops if lowIndex is ever equal to
     * or greater than highIndex. This algorithm works by calling itself on
     * the first 1/2 of the array and the second 1/2 of the array, and then
     * merging the two parts of the array.
     * 
     * @precondition lowIndex and highIndex are in the indecies of the array.
     * @postcondition Comparable[] a is sorted.
     * 
     * @param a The array to be sorted
     * @param lowIndex The lowIndex or left bound of the array.
     * @param highIndex The highIndex or right bound of the array.
     */
    private void mergesortHelp(Comparable[] a, int lowIndex, int highIndex)
    {
        int mid = (lowIndex + highIndex) / 2;
        if (highIndex > lowIndex)
        {	
            mergesortHelp(a, lowIndex,  mid);
            mergesortHelp(a, mid + 1, highIndex);
            merge(a, lowIndex, mid, highIndex);
        }
    }
    
    /**
     * method merge
     * Usage: merge(inputArray, lowIndex, midIndex, highIndex)
     *_______________________________________________
     * Merges the two halves of the input array into one.  The method assumes
     * that each half of the input array is sorted as follows:
     * 
     *                a[lowIndex] to a[midIndex] are in increasing order.
     *                a[midIndex + 1] to a[highIndex] are in increasing order.
     * The method creates an array to hold the output.  It then establishes 
     * two pointers into the two halves of the input array.  The values at the
     * pointer locations are compared, and the smallest is added to the output
     * array.  The corresponding pointer is then increased by one.  In the event
     * either half becomes empty, the remaining values are copied to the output
     * array.
     * Postcondition: a[lowIndex] to a[highIndex] are in increasing order.
     *
     * @param a is the input array of Comparable values
     * @param lowIndex is the index into the array a corresponding to the beginning
     *        of the first half of the array to merge
     * @param midIndex is the index of the last value in the first half of the array
     * @param highIndex is the index of the last value in the second half of the array
     */
    private void merge(Comparable[] a, int lowIndex, int midIndex, int highIndex)
    {
        Comparable[] copy = new Comparable[a.length];
        for (int i = lowIndex; i <= highIndex; i++)
            copy[i] = a[i];
        int left = lowIndex;
        int right = midIndex + 1;
        for (int i = lowIndex; i <= highIndex; i++)
        {
            if (right > highIndex ||
                    (left <= midIndex && copy[left].compareTo(copy[right]) < 0))
            {
                a[i] = copy[left];
                left++;
            }
            else
            {
                a[i] = copy[right];
                right++;
            }
            display.update();
        }
    }
    
    /**
     * Sorts the array using a quicksort algorithm. This algorithm chooses a pivot,
     * partitions the array around the pivot, and then sorts each partition. This method
     * calls upon quicksortHelp to make the parameters more user friendly.
     * 
     * @postcondition The Comparable[] a (array) is sorted. 
     * 
     * @param a The array to be sorted. 
     */
    public void quicksort(Comparable[] a)
    {
        quicksortHelp(a, 0, a.length - 1);
    }
    
    /**
     * Sorts the array using the quicksort algorithm. This algorithm chooses a pivot,
     * partitions the array around the pivot, and then sorts each partition. This method
     * is used in the quicksort method as a helper. This is a recursive method that stops
     * when lowIndex == highIndex or lowIndex > highIndex. 
     * 
     * @precondition lowIndex and highIndex are contained in the indices of the array
     * @postcondition The Comparable[] a is sorted.
     * 
     * @param a The array to be sorted
     * @param lowIndex The left bound or lowIndex of the array.
     * @param highIndex The right bound or highIndex of the array.
     */
    private void quicksortHelp(Comparable[] a, int lowIndex, int highIndex)
    {
        if (lowIndex < highIndex)
        {
            int pivot = partition(a, lowIndex, highIndex);
            quicksortHelp(a, lowIndex, pivot - 1);
            quicksortHelp(a, pivot + 1, highIndex);
        }
    }
    
    /**
     * Method partition
     * Usuage: int pivotIndex = partition(a, lowIndex, highIndex)
     *___________________________________________________________
     *
     *Returns the index of the pivot element defined as follows:
     *                All elements on the left side of the pivot (from lowIndex)
     *                are less than or equal to the pivot.
     *                All elements on the right side of the pivot (through highIndex)
     *                are greater than or equal to the pivot.
     * The computation is performed in place.
     * @param a the array to partion
     * @param lowIndex is the index of the start of the part of array a to consider
     * @param highIndex is the index of the end of the part of array a to consider
     * @return the index of the pivot element in array a
     */
    private int partition(Comparable[] a, int lowIndex, int highIndex)
    {
        int pivot = lowIndex;
        for (int unsorted = lowIndex + 1; unsorted <= highIndex; unsorted++)
        {
            if (a[unsorted].compareTo(a[pivot]) < 0)
            {
                Comparable temp = a[pivot];
                a[pivot] = a[unsorted];
                display.update();
                a[unsorted] = a[pivot + 1];
                display.update();
                a[pivot + 1] = temp;
                display.update();
                pivot++;
            }
        }
        return pivot;
    }
}