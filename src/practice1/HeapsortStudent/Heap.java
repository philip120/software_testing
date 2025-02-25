package practice1.HeapsortStudent;

import java.util.ArrayList;
import java.util.List;

/* Maximum binary heap for positive integers
 * @author Hiie-Helen Raju
 */
public class Heap {

    List<Integer> valueList;

    public Heap(List<Integer> originalList) {
        ArrayList<Integer> heapList = new ArrayList<>(originalList);
        this.valueList = heapList;
        heapify();
    }
       
    /*
     * turn the list to binary heap valueList by rearranging elements
     */
    private void heapify(){
        int lastIndex = valueList.size()-1;
        int parentIndex = elemParentIndex(lastIndex);
        for (int i = parentIndex; i >= 0 ; i-- ){
            BubbleDown(i);
        }
    }
    
    /*
     * methods to calculate the indexes of the elements in the valueList and to get elements from given indexes
     * @param index of the element
     * @return desired index of value of desired element
     */
    private int leftChildIndex(int index){
        if (valueList.size() > (2*index)+1){ //HERE IS THE FIX
            return 2*index+1; //AND HERE
        }
        else return -1;
    }

    private int leftChildElem(int index){
        if (leftChildIndex(index) > -1){
            return (valueList.get(leftChildIndex(index)));
        }
        else return -1;
    }

    private int rightChildIndex(int index){
        if (valueList.size() > ((2*index)+2)){ //FIX HERE
            return (2*index)+2; //FIX HERE
        }
        else return -1;
    }

    private int rightChildElem(int index){
        if (rightChildIndex(index) > -1){
            return valueList.get(rightChildIndex(index));
        }
        else return -1;
    }

    private int elemParent (int index){
        if ( index > 0){
            return valueList.get(elemParentIndex(index));
        }
        else return -1;
    }

    private int elemParentIndex(int index){
        if (index > 0 ){
            return (index-1)/2;
        }
        else return -1;
    }

    /* Moves smaller elements down in the heap valueList tree, does nothing to other elements
     * @param index of the current element
     */
    private void BubbleDown(int index) {
        if (valueList.size() > index) {
            int currentElem = valueList.get(index);

            int leftChildIndex = leftChildIndex(index);
            int rightChildIndex = rightChildIndex(index);

            int leftChild = Integer.MIN_VALUE;
            int rightChild = Integer.MIN_VALUE;

            if (leftChildIndex != -1) {
                leftChild = valueList.get(leftChildIndex);
            }

            if (rightChildIndex != -1) {
                rightChild = valueList.get(rightChildIndex);
            }

            if ((leftChildIndex != -1 && currentElem < leftChild) ||
                    (rightChildIndex != -1 && currentElem < rightChild)) {

                if (rightChildIndex != -1 && leftChild < rightChild) {
                    // Swap with right child and recurse
                    valueList.set(index, rightChild);
                    valueList.set(rightChildIndex, currentElem);
                    BubbleDown(rightChildIndex);
                } else {
                    // Swap with left child and recurse
                    valueList.set(index, leftChild);
                    valueList.set(leftChildIndex, currentElem);
                    BubbleDown(leftChildIndex);
                }
            }
        }
    }


    /* Moves larger elements up in heap valueList tree, does nothing to other elements
     * @param index of current element
     */
    private void BubbleUp(int index){
        if (valueList.size() > index && elemParentIndex(index) > -1){
            int currentElem = valueList.get(index);
            int parent = elemParent(index);
            int parentIndex = elemParentIndex(index);
            if (currentElem > parent){
                valueList.set(index, parent);
                valueList.set(parentIndex,currentElem);
                BubbleUp(parentIndex);
            }
        }
    }

    /* Adds elements to heap valueList and moves them to correct location
     * @param element to be added to the heap valueList
     */
    public void addElem(int elem) {
        valueList.add(elem);
        BubbleUp(valueList.size()-1);
    }

    /* Removes and element from valueList and rearrange the rest of it to match valueList tree structure
     * @param value of the element to be removed.
     */
    public void removeElem(int elem) {
        int lastElem = valueList.get(valueList.size()-1);
        int indexOfRemoveElem = valueList.indexOf(elem);

        if (indexOfRemoveElem == valueList.size()-1){
            valueList.remove(valueList.size()-1);
        }
        else if (indexOfRemoveElem < 0){
            return;
        }
        else{
            valueList.remove(valueList.size()-1);
            valueList.set(indexOfRemoveElem, lastElem);
            int parentOfRemoved = elemParent(indexOfRemoveElem);

            if (indexOfRemoveElem > 0 && lastElem > parentOfRemoved) {
                BubbleUp(indexOfRemoveElem);
            } else {
                BubbleDown(indexOfRemoveElem);
            }
        }
    }

    public void printAsList() {
        System.out.println(valueList);
    }

    /* Prints the valueList as a sideways tree
     * @param valueList of a heap; index of the current element; depth of the current runthrough of the recursion
     */
    private void printHeap(List<Integer>heap, int index, int depth){
        if (index < heap.size()){
            printHeap(heap, 2*index+2, depth+1);
            String spaces = new String(new char[depth]).replace("\0", "   "); // Print 2 spaces for each depth level
            System.out.println(spaces + heap.get(index));
            printHeap(heap, 2*index+1, depth+1);
        }
    }

    public void printAsTree() {
        printHeap(valueList, 0, 0);
    }

    /* Removes the largest (root) element of the heap valueList
     * @return largest element of the heap value list
     */
    public int removeLargest() {
        if (valueList.size() < 1) {
            return -1;
        }
        int largestElem = valueList.get(0);
        int largestIndex = 0;
        int lastElem = valueList.get(valueList.size()-1);

        if (valueList.size() == 1){
            valueList.remove(valueList.size()-1);
            return largestElem;
        }
        valueList.remove(valueList.size()-1);
        valueList.set(largestIndex, lastElem);
        BubbleDown(0);
        return largestElem;
    }
}
