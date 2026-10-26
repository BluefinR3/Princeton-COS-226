/* *****************************************************************************
 *  Name:    Roshan
 *  NetID:   
 *  Precept: 
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 *
 *  Hours to complete assignment (optional):
 *
 **************************************************************************** */

Programming Assignment 2: Deques and Randomized Queues


/* *****************************************************************************
 *  Explain briefly how you implemented the randomized queue and deque.
 *  Which data structure did you choose (array, linked list, etc.)
 *  and why?
 **************************************************************************** */
I used a doubly linked list for Deque, as it allow constant time for addition and removal of elements from both front and back side. Each node contains reference of both previous and next nodes, which make the Deque operations easy.
I used resizing array for RandomizedQueue, as it gives almost constant time for enqueue and dequeue operations and give good random access to any element without iterations and help in space efficiency.

/* *****************************************************************************
 *  How much memory (in bytes) do your data types use to store n items
 *  in the worst case? Use the 64-bit memory cost model from Section
 *  1.4 of the textbook and use tilde notation to simplify your answer.
 *  Briefly justify your answers and show your work.
 *
 *  Do not include the memory for the items themselves (as this
 *  memory is allocated by the client and depends on the item type)
 *  or for any iterators, but do include the memory for the references
 *  to the items (in the underlying array or linked list).
 **************************************************************************** */

In Randomized Queue, As each object has 16 bytes, and resizing array of 'Item' reference has 8n bytes, and if array is full in worst case, it will become 2n references, which makes it total of 16n bytes. We also need to include 8 bytes for capacity and size variables.

Randomized Queue:   ~  32n  bytes

In Deque, each node has 1 reference to item, next, prev. Each reference has 8 bytes, which makes it total of 24 bytes, and there is object overhead of 16 bytes. So for each object, there are almost 40 bytes, and additional reference of head and tail makes it 16 bytes, and in worst case, total memory is 48n.

Deque:              ~  48n  bytes


/* *****************************************************************************
 *  Known bugs / limitations.
 **************************************************************************** */
No any bugs.

/* *****************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 **************************************************************************** */
None.



/* *****************************************************************************
 *  Describe any serious problems you encountered.                    
 **************************************************************************** */
None.


/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **************************************************************************** */
I learned a lot about abstract data types and iterators.