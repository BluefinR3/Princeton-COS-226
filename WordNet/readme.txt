/* *****************************************************************************
 *  Name:     	Roshan
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

Programming Assignment 6: WordNet

/* *****************************************************************************
 *  Describe concisely the data structure(s) you used to store the 
 *  information in synsets.txt. Why did you make this choice?
 **************************************************************************** */

I used the 2 different data structures. The first one is the ArrayList of idTosynset in which
index is synset id, and value is synset strings with all nouns. And second data structure
is HashMap of nounToIds in which we store hashset of all synset ids containing that noun.

I used the ArrayList, because finding by id in the array list is O(1). And nouns can be
present in many synsets, so map of set gives O(1) for insertion and finding.

/* *****************************************************************************
 *  Describe concisely the data structure(s) you used to store the 
 *  information in hypernyms.txt. Why did you make this choice?
 **************************************************************************** */

I used the Digraph with the variable g having v as the number of synsets. I made this choice,
because Digraph matches the model of hypernyms, when they form a DAG over synset ids.
The graph things of BFS, cycle check etc all run in linear time O(V + E), and it also
keep the memory simple.

/* *****************************************************************************
 *  Describe concisely the algorithm you use in the constructor of
 *  ShortestCommonAncestor to check if the digraph is a rooted DAG.
 *  What is the order of growth of the worst-case running times of
 *  your algorithm? Express your answer as a function of the
 *  number of vertices V and the number of edges E in the digraph.
 *  (Do not use other parameters.) Use Big Theta notation to simplify
 *  your answer.
 **************************************************************************** */

Description:
I used the Digraph; It run a DFS on G, if a cycle exists, it will put out an exception.
Then it checks for exactly one root by counting the vertices with out degree 0. If
the count is not equal to 1, it will again put out an exception.

Order of growth of running time:

O (v + E)
The cycle detection checks each and every edge, while counting roots checks
all vertices by once.

/* *****************************************************************************
 *  Describe concisely your algorithm to compute the shortest common ancestor
 *  in ShortestCommonAncestor. For each method, give the order of growth of
 *  the best- and worst-case running times. Express your answers as functions
 *  of the number of vertices V and the number of edges E in the digraph.
 *  (Do not use other parameters.) Use Big Theta notation to simplify your
 *  answers.
 *
 *  If you use hashing, assume the uniform hashing assumption so that put()
 *  and get() take constant time per operation.
 *
 *  Be careful! If you use a BreadthFirstDirectedPaths object, don't forget
 *  to count the time needed to initialize the marked[], edgeTo[], and
 *  distTo[] arrays.
 **************************************************************************** */

Description:
First of all, I ran 2 multi-source BFS traversals for 2 vertices or set of vertices.
I ran the first BFS to compute the distToA[], and second BFS to compute distToB[].
Then I checked all vertices x, and for those vertices, which are reachable from both of
them, I calculated sum of distance by (distToA[x] + distToB[x]). Then the vertex
with the smallest sum is the shortest common ancestor in it.

                                 running time
method                  best case            worst case
--------------------------------------------------------
length()                O(V + E)            O(V + E)

ancestor()              O(V + E)            O(V + E)

lengthSubset()          O(V + E)            O(V + E)

ancestorSubset()        O(V + E)            O(V + E)



/* *****************************************************************************
 *  Known bugs / limitations.
 **************************************************************************** */
No.

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
I ran into some issues while working with Digraph, but I was able to work them out.

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
It was a satisfying experience working with graphs.