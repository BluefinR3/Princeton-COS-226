/* *****************************************************************************
 *  Name:     Roshan
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

Programming Assignment 7: Seam Carving


/* *****************************************************************************
 *  Describe concisely your algorithm to find a horizontal (or vertical)
 *  seam.
 **************************************************************************** */

Here's a description of the vertical seam: 
Each pixel in the matrix is a vertex with weight equal to energy(x, y). 
All edges do in downward position from row y to y + 1, and each pixel do transitions to 3 neighbors of (x-1, y+1), (x, y+1) and (x+1, y+1).
I run top to bottom row order with keeping a parent[x][y] and 
dist[x][y] = energy(x, y) + min(dist[x-1][y-1], dist[x][y-1], dist[x+1][y-1])


/* *****************************************************************************
 *  Describe what makes an image suitable to the seam-carving approach
 *  (in terms of preserving the content and structure of the original
 *  image, without introducing visual artifacts). Describe an image that
 *  would not work well.
 **************************************************************************** */

If an image is divided into high energy contents like strong edges and textures, and low energy like background, sky etc, it works well. If the image is uniformly detailed with dense textures, crowds and text everywhere in image, it did not work correctly here and struggles with repeating patterns.

/* *****************************************************************************
 *  Perform computational experiments to estimate the running time to reduce
 *  a W-by-H image by one column and one row (i.e., one call each to
 *  findVerticalSeam(), removeVerticalSeam(), findHorizontalSeam(), and
 *  removeHorizontalSeam()). Use a "doubling" hypothesis, where you
 *  successively increase either W or H by a constant multiplicative
 *  factor (not necessarily 2).
 *
 *  To do so, fill in the two tables below. Each table must have 5-10
 *  data points, ranging in time from around 0.25 seconds for the smallest
 *  data point to around 30 seconds for the largest one.
 **************************************************************************** */

(keep W constant)
 W = 2000
 multiplicative factor (for H) =

 H           time (seconds)      ratio       log ratio
------------------------------------------------------
250          0.204000            N/A         N/A
500          0.612000            3.000       1.585
1000         0.988000            1.614       0.691
2000         2.198000            2.225       1.154
4000         4.660000            2.120       1.084
8000         9.664000            2.074       1.052


(keep H constant)
 H = 2000
 multiplicative factor (for W) =

 W           time (seconds)      ratio       log ratio
------------------------------------------------------
250          0.264000            N/A         N/A
500          0.443000            1.678       0.747
1000         0.991000            2.237       1.162
2000         2.214000            2.234       1.160
4000         4.331000            1.956       0.968
8000         9.354000            2.160       1.111



/* *****************************************************************************
 *  Using the empirical data from the above two tables, give a formula 
 *  (using tilde notation) for the running time (in seconds) as a function
 *  of both W and H, such as
 *
 *       ~ 5.3*10^-8 * W^5.1 * H^1.5
 *
 *  Briefly explain how you determined the formula for the running time.
 *  Recall that with tilde notation, you include both the coefficient
 *  and exponents of the leading term (but not lower-order terms).
 *  Round each coefficient and exponent to two significant digits.
 **************************************************************************** */


Running time (in seconds) to find and remove one horizontal seam and one
vertical seam, as a function of both W and H:
    ~ 2.5x10^-7 * W^1.0 * H^1.1



/* *****************************************************************************
 *  Known bugs / limitations.
 **************************************************************************** */

No known bugs.

/* *****************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 **************************************************************************** */

No.

/* *****************************************************************************
 *  Describe any serious problems you encountered.                    
 **************************************************************************** */

No.

/* *****************************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 **************************************************************************** */

No.

/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **************************************************************************** */

I enjoyed working with seam carving.