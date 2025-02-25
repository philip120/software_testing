package practice1.HeapsortStudent.src;

import java.util.ArrayList;
import java.util.List;

/*
 * Creating a positive maximum binary heap list and adding-removing elements in it
 */
public class TestHeap {

    public static void main(String[] args) {
        List<Integer> heapList = new ArrayList<>();
        heapList.add(1);
        heapList.add(2);
        heapList.add(5);
        heapList.add(7);
        heapList.add(6);
        heapList.add(8);
        heapList.add(11);
        heapList.add(10);
        heapList.add(3);
        heapList.add(4);
        heapList.add(9);
        heapList.add(1);
        heapList.add(0);
        System.out.println("List before heapifying:");
        System.out.println(heapList);
        Heap heap = new Heap(heapList);
        System.out.println("After heapifying: ");
        heap.printAsList();
        heap.printAsTree();
        heap.addElem(13);
        heap.addElem(4);
        System.out.println("After adding elements: ");
        heap.printAsList();
        heap.printAsTree();
        heap.removeElem(10);
        heap.removeElem(99);
        System.out.println("After removing elements: ");
        heap.printAsList();
        heap.printAsTree();
        System.out.println("After removing root element: ");
        heap.removeLargest();
        heap.printAsList();
        heap.printAsTree();
    }
}
