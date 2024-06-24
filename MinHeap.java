//Levon Khachatryan
//Project #4
import java.util.*;

public class MinHeap< E extends Comparable<E> >
{
   private ArrayList<E> heap;  //holds elements in the minheap
   private int lastnode;
   private int size;
   

 /* ---------------------PUBLIC METHODS ------------------------*/ 
   public MinHeap() //Creates an empty heap
   { 
    heap = new ArrayList < E > ();
    lastnode = -1;
    size = 0;
   }
               
  
   
   public MinHeap(E[] a) // Initialize heap with elements in a
   { 
//1.	Add the values from a directly into the ArrayList heap in the class MinHeap
//2.	Set size and lastNode
//3.	Use the private heapify()  method in MinHeap to convert heap into a minheap.
    heap = new ArrayList<E>();
    for (int i = 0; i < a.length; i++) {
        heap.add(a[i]);
    }
    lastnode = a.length - 1;
    size = a.length;
    heapify();
}
   
   public void printMinHeap(String s)  // print heap, lastnode and size. Label
   { 
    for (int i =0;i<heap.size();i++){
        System.out.println(heap.get(i)+ " ");
    }
    System.out.println();
    System.out.println("lastnode: " + lastnode);
    System.out.println("size: " + size);
    System.out.println();

    }


   
   public boolean isEmpty()  // true if size == 0; else return false
   {  
    return size == 0;
   }

   public int size()
   {  
    return size;
   }

   public void insert(E item) //Use the algorithm to insert item into a minheap
   { 
      int parent;
      int child;
      E temp;
      heap.add(item);
      lastnode++;
      size++;
      child = lastnode;
      parent = (child - 1) / 2;
      while (child> 0 && heap.get(parent).compareTo(heap.get(child)) > 0) {
          temp = heap.get(parent);
          heap.set(parent, heap.get(child));
          heap.set(child, temp);
          child = parent;
          parent = (child - 1) / 2;
      }
    
    }
   
  /*Return but do not remove the highest priority item. Return null of heap is
     empty*/
 public E min()  {
   if(isEmpty()) {
      return null;
  }
  return heap.get(0);
  }   


   /* Use the minheap algorithm to delete the min value from the heap. Return null if
      heap is empty  */
   public E deleteMin() { 
      if(isEmpty()) {
         return null;
     }
     E temp = heap.get(0);
     heap.set(0, heap.get(lastnode));
     heap.remove(lastnode);
     lastnode--;
     size--;
     heapify();
     return temp;
    }
         
   

   /* -------------------- PRIVATE METHODS ----------------------*/
   /*Return the index of k’s smallest child in the minheap. 
     If k’s left child and right child have same priority return the  index of the
      left child . This method is very useful for the deleteMin algorithm
*/
   private int getIndexSmallestChild(int k)  { 
      int left = 2 * k + 1;
      int right = 2 * k + 2;
      if (left > lastnode) {
          return -1;
      } else if (left == lastnode) {
          return left;
      } else {
          if (heap.get(left).compareTo(heap.get(right)) < 0) {
              return left;
          } else {
              return right;
          }
      }
    }

   private void bubbleUp( int k)
   {
      int parent = (k - 1) / 2;
      E temp;
      while (parent >= 0 && heap.get(parent).compareTo(heap.get(k)) > 0) {
          temp = heap.get(parent);
          heap.set(parent, heap.get(k));
          heap.set(k, temp);
          k = parent;
          parent = (k - 1) / 2;
      }
     }
   private void bubbleDown( int k)
   { 
      int child = getIndexSmallestChild(k);
      E temp;
      while (child != -1 && heap.get(k).compareTo(heap.get(child)) > 0) {
          temp = heap.get(k);
          heap.set(k, heap.get(child));
          heap.set(child, temp);
          k = child;
          child = getIndexSmallestChild(k);
      }
    }
   
   private boolean heapify()   // heapifies the heap
   {  
      for (int i = lastnode / 2; i >= 0; i--) {
         bubbleDown(i);
     }
     return true;
   }


   /* -----------------------------MAIN PROGRAM---------------------------------*/
    
/* Use main to test all of your public and private methods on Integers and Strings.
   You do not need to read from a file. A test case for students will be posted a few days before the due date. The instructor will run another test case during the grading process.
*/
   public static void main( String[] args)
   { 
      
      
       
   //Instructor's Testcase posted on Canvas
   //---------------- TEST 1 ------------------------
   System.out.println("\nTest 1" );
   Integer[] t1 = {2,3,4,5,6,8,9};
   
   MinHeap<Integer> heap1 = new MinHeap<Integer>();
   
   for( int k = 0; k < t1.length; k++)
      heap1.insert(t1[k]);
   heap1.printMinHeap("minheap from Test 1");
   
   


// ---------------Test 2 ----------------------
   System.out.println("\nTest 2" );

   System.out.println("deleted mins:  " );
   Integer min2 = heap1.deleteMin();
   System.out.print(min2 + "  ");
   min2 = heap1.deleteMin();
   System.out.println(min2 + "  ");
   heap1.printMinHeap("minheap from Test2");

       /*           
//-------------Test 3-------------------
   System.out.println("\nTest3");
   Integer[] a1 = { 50, 45,30,42,51,70} ;
   System.out.println("Original array a1: \n" + Arrays.toString(a1) );
                   
   MinHeap<Integer> heap2 = new MinHeap<Integer>();  //empty heap
   for ( int k = 0; k < a1.length; k++)
      heap2.insert(a1[k]);  
   
   int i = 0;
   while (!heap2.isEmpty())
   {
      a1[i] = heap2.deleteMin();
      i++;
   }
    
   System.out.println("Sorted array a1: \n" + Arrays.toString(a1) );
   heap2.printMinHeap("heap2 should be an empty heap");
    
   
   //--------------Test 4----------------------------------------
   
   System.out.println("\nTest 4");
   String[] a = { "apple", "pear", "orange", "blueberry", 
                   "avocado", "lettuce", "onion", "apple", "greenBeans"} ;
                   

   System.out.println("Original array a: \n" + Arrays.toString(a) );
                   
   MinHeap<String> heap3 = new MinHeap<String>();  //empty heap
   for ( int k = 0; k < a.length; k++)
      heap3.insert(a[k]);  
   
   i = 0;
   while (!heap3.isEmpty())
   {
      a[i] = heap3.deleteMin();
      i++;
   }
    
   System.out.println("Sorted array a: \n" + Arrays.toString(a) );


//----------------- Test 5 ---------------------------      
   System.out.println("\nTest 5");
   String [] b = { "red", "blue", "yellow", "green", "grey" , 
                 "pink", "rose", "navy", "red", "purple", "orange"} ;
   System.out.println("Original array b: \n" + Arrays.toString(b) );
   MinHeap<String> heap4 = new MinHeap<String>(b);
   heap4.printMinHeap("MinHeap after heapify:");

*/

    } 
   
}//end MiinHeap

/*
         ---------------OUTPUT---------------------------

         Test 1
1 
1
3
2
3
4
5
6
6

lastnode: 8
size: 9


Test 2
deleted mins:
1  1
2
3
3
6
6
4
5

lastnode: 6
size: 7


Test3
Original array a1:
[50, 45, 30, 42, 51, 70]
Sorted array a1:
[30, 42, 45, 50, 51, 70]

lastnode: -1
size: 0


Test 4
Original array a:
[apple, pear, orange, blueberry, avocado, lettuce, onion, apple, greenBeans]
Sorted array a:
[apple, apple, avocado, blueberry, greenBeans, lettuce, onion, orange, pear]

Test 5
Original array b:
[red, blue, yellow, green, grey, pink, rose, navy, red, purple, orange]
blue
green
pink
navy
grey
yellow
rose
red
red
purple
orange

lastnode: 10
size: 11

 
 */
