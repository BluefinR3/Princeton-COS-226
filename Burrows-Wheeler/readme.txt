/* *****************************************************************************
 *  Name:      Roshan
 *  NetID:    
 *  Precept:  
 *
 *  Partner Name:     N/A
 *  Partner NetID:    N/A
 *  Partner Precept:  N/A
 *
 *  Hours to complete assignment (optional):
 *
 **************************************************************************** */

Programming Assignment 8: Burrows-Wheeler

/* *****************************************************************************
 *  Which sorting algorithm did you use in CircularSuffixArray.java?
 *  Why did you choose it?
 **************************************************************************** */

I used the Java built in algorithm, which uses the Quicksort for primitive int
arrays and object arrays with comparators. I chose this because, its already
highly optimized and easily supports comparing circular suffixes using a
Comparator.

/* *****************************************************************************
 *  How long does your implementation of the Burrows-Wheeler data compression
 *  algorithm (BurrowsWheeler + MoveToFront + Huffman) take to compress and
 *  expand mobydick.txt? What is the compression ratio (number of bytes in
 *  compressed message divided by the number of bytes in the message)?
 *  Compare the results to that for running Huffman alone and GNU 
 *  command-line utility Gzip.
 **************************************************************************** */

Algorithm       Compression time    Expansion time           Compression ratio
------------------------------------------------------------------------------
My program      1.05 sec            0.92 sec                 0.38
Huffman alone   0.31 sec            0.27 sec                 0.560 (667651 / 1191463)
gzip            0.12 sec            0.06 sec                 0.30

See the Checklist for Bash commands to compress/expand.



/* *****************************************************************************
 *  Give the order of growth of the running time of each of the 7
 *  methods as a function of the input size n and the alphabet size R
 *  both in practice (on typical English text inputs) and in theory
 *  (in the worst case), e.g., n, n + R, n log n, n log^2n n, n^2,
 *  or R n.
 *
 *  Include the time for sorting circular suffixes in Burrows-Wheeler
 *  transform().
 **************************************************************************** */

                                      typical            worst
---------------------------------------------------------------------
CircularSuffixArray constructor       n log n            n^2
BurrowsWheeler transform()            n log n            n^2
BurrowsWheeler inverseTransform()     n + R              n + R
MoveToFront encode()                  n                  n + R
MoveToFront decode()                  n                  n + R
Huffman compress()                    n + R log R        n + R log R
Huffman expand()                      n                  n





/* *****************************************************************************
 *  Known bugs / limitations.
 **************************************************************************** */

No known bugs. A limitation is that, performance depends on built-in sorting
algorithm.

/* *****************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 **************************************************************************** */

I did not received any help.

/* *****************************************************************************
 *  Describe any serious problems you encountered.                    
 **************************************************************************** */

I didn't have any serious problems.

/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it. Additionally, you may include any suggestions
 *  for what to change or what to keep (assignments or otherwise) in future 
 *  semesters.
 **************************************************************************** */

It helped me a lot learning about the circular arrays and debugging string-processing
algorithms.