/**
 * @author Ross Stanga
 * @version April 6 2017
 */

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class MergeSorter implements  Sorter{
    /*
     * We compare two split sides of a list to sort them by their first elements and so on until all
     * comparisons are done.
     * @param data the list to sort through
     * @return the comparable results
     */
    int count = 0;
    public  <E extends Comparable<E>> int sort(List<E> data){
        return sort(data, new DefaultComparator<E>());
    }
    /*
     * Defining a method that calls the other recursively to compare two objects with comparator
     * @param comparator the comparator object
     * @return count the number of operations
     */
    public <E> int sort(List<E> myA, Comparator<E> comparator) {
        count = 0;
        mergeSortRecur(myA, comparator);
        return  count;
    }
    /*
     * Method is called recursively to compare the split lists and sort the first through ith elements with merging
     * @param data the list to sort through
     * @param comparator the comparator object
     * @return data the result of the sort
     */
    private <E> List<E> mergeSortRecur(List<E> data, Comparator<E> comparator){
        List<E> left = new ArrayList<E>();
        List<E> right = new ArrayList<E>();
        int leftIndex = 0;
        int rightIndex = 0;
        int dataIndex = 0;
        if(data.size() > 1) {
            //splits
            for(int i = 0; i < data.size()/2; i++){
                left.add(data.get(i));
            }
            for(int j = 0; j < data.size()/2 + data.size() % 2;j++){
                right.add(data.get(data.size()/2 + j));
            }
            //recur
            right = mergeSortRecur(right,comparator);
            left = mergeSortRecur(left,comparator);
            //sort
            while (leftIndex < left.size() || rightIndex < right.size()){
                if(leftIndex < left.size() ){
                    if(rightIndex < right.size()){
                        if(comparator.compare(right.get(rightIndex), left.get(leftIndex)) > 0){
                            data.set(dataIndex, left.get(leftIndex));
                            dataIndex++;
                            leftIndex++;
                        }
                        else{
                            data.set(dataIndex, right.get(rightIndex));
                            dataIndex++;
                            rightIndex++;
                        }
                        count++;
                    }
                    else{
                        data.set(dataIndex, left.get(leftIndex));
                        dataIndex++;
                        leftIndex++;
                    }
                }
                else {
                    data.set(dataIndex, right.get(rightIndex));
                    dataIndex++;
                    rightIndex++;
                }
            }
        }
        return data;
    }
}