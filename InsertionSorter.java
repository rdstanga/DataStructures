/*
*@author Ross Stanga
*@version April 4 2017
*/

import java.util.Comparator;
import java.util.List;


public class InsertionSorter implements Sorter{
    /*
     * We take from our unsorted list and place into the order list, comparing each unsorted to the sorted.
    * @param data the list of data to sort through
    * @return count the number of operations
    */
    public <E extends Comparable<E>> int sort(List<E> data){
        int count =0;
        E temp;
        for( int i = 1; i < data.size(); i++){
            for(int j = 0; j < i; j++){

                if(data.get(i).compareTo(data.get(j)) < 0){
                    temp = data.get(j);
                    data.set(j,data.get(i));
                    data.set(i,temp);
                }
                else if(data.get(i).compareTo(data.get(j)) == 0) {
                    temp = data.get(j+1);
                    data.set(j+1,data.get(i));
                    data.set(i,temp);

                }
                count++;
            }
        }

        return count;
    }

    /*
     * Same as before, but now using the comparator object to compare the separate unsorted and sorted objects
    * @param comparator the object that compares the other objects
    * @return count the number of operations
    */
    public <E> int sort(List<E> data, Comparator<E> comparator){
        int  count = 0;
        E temp;
        for(int i = 1; i < data.size(); i++){
            for(int j = 0; j < i; j++){

                if(comparator.compare(data.get(i),(data.get(j))) < 0){
                    temp = data.get(j);
                    data.set(j,data.get(i));
                    data.set(i,temp);
                }
                else if(comparator.compare(data.get(i),(data.get(j))) == 0) {
                    temp = data.get(j+1);
                    data.set(j+1,data.get(i));
                    data.set(i,temp);

                }
                count++;
            }
        }

        return count;
    }




}