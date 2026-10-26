/* *****************************************************************************
 *  Name:     Roshan
 *  NetID:    
 *  Precept:  
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 *
 *  Operating system:
 *  Compiler:
 *  Text editor / IDE:
 *
 *  Have you taken (part of) this course before:
 *  Have you taken (part of) the Coursera course Algorithms, Part I or II:
 *
 *  Hours to complete assignment (optional):
 *
 **************************************************************************** */

Programming Assignment 1: Percolation



/* *****************************************************************************
 *  Describe the instance variables that you used to implement the
 *  Percolation data type.
 **************************************************************************** */
These are following instance variables, I used in the Percolation data type.

1: WeightedQuickUnionUF unionFind: It will track site connectivity.
2: WeightedQuickUnionUF noBottomBackwash: It prevents backwash by removing bottom site.
3: boolean[] sites: To check if each site is open or closed.
4: int top: index for top site.
5: bottom`: index for bottom site.
6: int gridSize: the dimension size of grid.
7: int openSites: total number of open sites.


/* *****************************************************************************
 *  Explain how you used the union-find data type to check efficiently
 *  whether a system percolates.
 **************************************************************************** */
I used the 2 WeightedQuickUnionUF objects, one includes top and bottom nodes as unionFind to check for percolation. The other includes only the top node to avoid the backwash while checking if site is full. The percolation is detected if the top site is connected to the bottom in unionFind.


/* *****************************************************************************
 *  Perform computational experiments to estimate the running time of
 *  PercolationStats.java for various values of n and T when implementing
 *  Percolation.java with QuickFindUF.java (not QuickUnionUF.java). Use a
 *  "doubling" hypothesis, where you successively increase either n or T by
 *  a constant multiplicative factor (not necessarily 2).
 *
 *  To do so, fill in the two tables below. Each table must have 5-10
 *  data points, ranging in time from around 0.25 seconds for the smallest
 *  data point to around 30 seconds for the largest one. Do not include
 *  data points that take less than 0.25 seconds.
 **************************************************************************** */

(keep T constant)
 T = 100
 multiplicative factor (for n) = 1.25

 n          time (seconds)       ratio         log ratio
--------------------------------------------------------
100	    0.146                -             -
156         0.342                1.84          0.88      
195         0.532                1.56          0.64      
243         0.948                1.78          0.83      
303         1.760                1.86          0.89      
378         3.412                1.94          0.96      
472         6.572                1.93          0.95


(keep n constant)
 n = 100
 multiplicative factor (for T) = 1.25

 T          time (seconds)       ratio         log ratio
--------------------------------------------------------
100         0.117                -             -
303         0.276                1.25          0.33      
378         0.344                1.25          0.32      
472         0.436                1.27          0.34      
590         0.547                1.25          0.33      
737         0.671                1.23          0.29


/* *****************************************************************************
 *  Using the empirical data from the above two tables, give a formula 
 *  (using tilde notation) for the running time (in seconds) of
 *  PercolationStats.java as function of both n and T, such as
 *
 *       ~ 5.3*10^-8 * n^5.0 * T^1.5
 *
 *  Briefly explain how you determined the formula for the running time.
 *  Recall that with tilde notation, you include both the coefficient
 *  and exponents of the leading term (but not lower-order terms).
 *  Use two significant figures for each coefficient and exponent
 *  (e.g., 5.3*10^-8 or 5.0).
 **************************************************************************** */

QuickFindUF running time (in seconds) as a function of n and T:

    ~ 
       2.3 * 10^-5 * n^1.8 * T^0.4

I calculated 1.8 and 0.4 by averaging the log ratios. I computed the constant using highest data point (n = 472, T = 100, time = 6.572) by solving the equation.

/* *****************************************************************************
 *  Repeat the previous two questions, but using WeightedQuickUnionUF
 *  (instead of QuickFindUF).
 **************************************************************************** */

(keep T constant)
 T = 100
 multiplicative factor (for n) = 1.5

 n          time (seconds)       ratio         log ratio
--------------------------------------------------------
100	    0.114                -             -
195         0.269                1.29          0.37      
243         0.388                1.44          0.53      
303         0.583                1.50          0.59      
378         0.963                1.65          0.72      
472         1.559                1.62          0.70      


(keep n constant)
 n = 100
 multiplicative factor (for T) = 1.5

 T          time (seconds)       ratio         log ratio
--------------------------------------------------------
100         0.117                -             -
472         0.284                1.20          0.26      
590         0.344                1.21          0.28      
737         0.431                1.25          0.33      
921         0.530                1.23          0.30      
1151        0.659                1.24          0.31


WeightedQuickUnionUF running time (in seconds) as a function of n and T:

    ~ 
       3.3 * 10^-4 * n^0.99 * T^0.51

I calculated 0.99 and 0.51 by averaging the log ratios. I computed the constant using highest data point (n = 472, T = 100, time = 1.559) by solving the equation.
 
/* *****************************************************************************
 *  Known bugs / limitations.
 **************************************************************************** */
There are no any known bugs.



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
I learned a lot about union-find data structures in it.