import java.util.List;
import java.lang.Comparable;
public class Sorters2120 {

    public static <T extends Comparable<T> > void bubbleSort(List<T> theList) {
        int lastToConsider = theList.size();
        while (lastToConsider > 1) {
            for (int j=0; j<lastToConsider-1; j++) {
                if (theList.get(j).compareTo(theList.get(j+1))  >  0 ) {
                    swap(theList,j,j+1);
                }
            }
            lastToConsider--;
        }
    }

    private static <T extends Comparable<T> > void swap(List<T> theList, int i1, int i2) {

        T temp = theList.get(i1);
        theList.set(i1,theList.get(i2));
        theList.set(i2,temp);
    }

    private static int startIndex = 0;
    private static int comparedIndex = startIndex+1;

    public static <T extends Comparable<T> > void selectionSort(List<T> theList)
    {
        T minElement = theList.get(startIndex);
        T maxElement = theList.get(comparedIndex);
        
        if( minElement.compareTo(maxElement) == -1 )//less than
            {
                //System.out.println("Through");
                if(comparedIndex < theList.size()-1)
                {
                    comparedIndex++;
                    selectionSort(theList);
                }
            }
        else if(minElement.compareTo(maxElement) == 1 )//greater than
        {
            swap(theList, startIndex, comparedIndex);
            if(comparedIndex < theList.size()-1)
            {
                comparedIndex++;
                minElement = maxElement;
                selectionSort(theList);
                
            }
        }

        /*for(startIndex = 0; startIndex < theList.size()-1; startIndex++)
        {
            minElement = theList.get(startIndex);
            comparedIndex = startIndex+1;

            if( minElement.compareTo(theList.get(comparedIndex)) == -1 )//less than
            {
                comparedIndex++;
                selectionSort(theList);
            }
            else if(minElement.compareTo(theList.get(comparedIndex)) == 1)//greater than
            {
                minElement = theList.get(comparedIndex);
                swap(theList, comparedIndex, startIndex);
                selectionSort(theList);
            }
            else//if they're equal
            {
                swap(theList, comparedIndex, startIndex+1);
            }
        }*/

       /* else if( minElement.compareTo(theList.get(comparedIndex)) == 1)
        {
            swap(theList, startIndex, comparedIndex);
            comparedIndex++;
        }*/
        
    }

    public static <T extends Comparable<T> > void mergeSort(List<T> theList) {
        recursiveMergeSortHelper(theList,0,theList.size());
    }

    private static <T extends Comparable<T> > void recursiveMergeSortHelper(List<T> theList, int first, int last)
    {
        if(last < theList.size()+1)
        {
            int middle = (last-1)/2;
            if(theList.get(first).compareTo(theList.get(last-1)) == -1)//if its in order or the same
            {
                if(first < middle)
                {
                    recursiveMergeSortHelper(theList, first, middle);
                }
            }
            else if(theList.get(first).compareTo(theList.get(last-1)) == 1)//if its out of order
            {
                swap(theList, first, middle);
                if(first < middle)
                {
                    recursiveMergeSortHelper(theList, first, middle);
                    System.out.println("after recursion");
                }
            }
        }
        
    }

    public static <T extends Comparable<T> > int indexOf(T searchItem, List<T> theList) {

        return recursiveBinarySearcher(searchItem, theList, 0, theList.size()-1);

    }

    private static <T extends Comparable<T> > int recursiveBinarySearcher(T searchItem, List<T> theList, int first, int last) {

        // stubbed
        return 0;

    }
}
