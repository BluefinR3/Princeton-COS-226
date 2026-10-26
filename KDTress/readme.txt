/* *****************************************************************************
 *  Name:     Roshan
 *  NetID:    
 *  Precept:  
 *
 *  Partner Name:       
 *  Partner NetID:      
 *  Partner Precept:    
 *
 *  Hours to complete assignment (optional):
 *
 **************************************************************************** */

Programming Assignment 5: Kd-Trees


/* *****************************************************************************
 *  Describe the Node data type you used to implement the
 *  2d-tree data structure.
 **************************************************************************** */

The Node data type represents a single node in the 2d-tree data structure which has the following attributes.
First one is Point2D object as a key. The second is Value object as a value associated with the key in the node. It also has 2 node objects to represent left and right reference of trees. Finally it has the RectHV object for axis-aligned rectangle in node region.


/* *****************************************************************************
 *  Describe your method for range search in a kd-tree.
 **************************************************************************** */

This method use the recursive approach by passing the current node and query rectangle in the helper method. It checks that if rectangle of node interest with the query rectangle, the subtree is trimmed, if the node exists inside of the rectangle, it will be added to the result. Finally it will iterate through all sides of the tree to ensure that no any possible match is missed here.


/* *****************************************************************************
 *  Describe your method for nearest neighbor search in a kd-tree.
 **************************************************************************** */

This method uses the backtracking approach using the recursion. It begins exploring from the root node, and keeps on searching in downward direction. It chooses the subtree that is on the same side as the query point. For each of the nodes, it checks if the current point is closer than the best point. If rectangle of the other subtree could contain a closer point, it will explore that tree also. In this way the method works efficiently and leaves out unnecessary branches.

/* *****************************************************************************
 *  How many nearest-neighbor calculations can your PointST implementation
 *  perform per second for input1M.txt (1 million points), where the query
 *  points are random points in the unit square?
 *
 *  Fill in the table below, using one digit after the decimal point
 *  for each entry. Use at least 1 second of CPU time.
 *  (Do not count the time to read the points or to build the 2d-tree.)
 *
 *  Repeat the same question but with your KdTreeST implementation.
 *
 **************************************************************************** */


                 # calls to         /   CPU time     =   # calls to nearest()
                 client nearest()       (seconds)        per second
                ------------------------------------------------------
PointST:          10,000                498          =   20.08 calls/sec

KdTreeST:         10,000,000            31.4         =   318248.4 calls/sec 

Note: more calls per second indicates better performance.

PointST is slow as usual due to brute force, but KdTreeST is fast due to algorithms.

/* *****************************************************************************
 *  Did you fill out the mid-semester feedback form?
 *  If not, please do so now: https://bit.ly/349Ql18
 **************************************************************************** */

Yes.

/* *****************************************************************************
 *  Known bugs / limitations.
 **************************************************************************** */

None.

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

Some issues in the KDTreeST class, but I spent a bit of time and it worked finally.


/* *****************************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 **************************************************************************** */

No.

/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on  how helpful the class meeting was and on how much you learned 
 * from doing the assignment, and whether you enjoyed doing it.
 **************************************************************************** */

Some minor challenges but it helped me learn about trees and their structure efficiently.