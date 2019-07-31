import java.util.Comparator;
import java.util.List;
/*
* @author Ross Stanga
* @version April 10 2017
*
*/
public class BinarySearcher implements Searcher{/*
 * Our binary searcher uses the default comparison that a binary searcher would implement
 * @param data the list to search
 * @param the result of the search
 * @return the result of the search
 */
    public <E extends Comparable<E>> int[] search(List<? extends E> data, E key) {

        return search(data, key, new DefaultComparator<E>());

    }
    /*
    * Same as before, except now we are using the comparator to compare the two sides of the list.
    * @param data the list to search through
    * @param key the result
    * @param the Comparator object
    * @return the array that holds the index and the count of operations
    */
    public <E> int[] search( List<? extends E> data, E key, Comparator<E> comparator){
        int low = 0;
        int high = data.size() - 1;
        int index = (low + high + 1) / 2;
        int count = 0;
        while (low <= high){
            count++;
            final int comparison = comparator.compare(key,data.get(index));
            if (comparison == 0){
                return new int[]{index,count};
            }
            else if (comparison < 0){
                // Key is on the left side.
                high = index - 1;
            }
            else {
                low = index + 1;
            }
            index = (low + high + 1) / 2;
        }

        return new int[]{-1,count};
    }

}

