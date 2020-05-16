#COMP261 Assignment 3 Answers

##Articulation points
###Depth and reachback
* A
    * Depth: 0
    * Reach-back: 0
    
* B
    * Depth: 1
    * Reach-back: 0
    
* C
    * Depth: 2
    * Reach-back: 2

* D
    * Depth: 3
    * Reach-back: 0
    
* E
    * Depth: 2
    * Reach-back: 0
    
* F
    * Depth: 5
    * Reach-back: 4
    
* G
    * Depth: 4
    * Reach-back: 4
    
* H
    * Depth: 4
    * Reach-back: 4
    
* I
    * Depth: 6
    * Reach-back: 4    

* J
    * Depth: 6
    * Reach-back: 6
    
###List of points
* B
    > C is a child of B that has a reach-back of 2 while B has a depth of 1, 
    this indicates that there are no alternative paths for C making B an AP
* D
    > D has two children which both have reach-back values higher than the depth of D
    indicating that D is an AP
* H
    > H has two children which both have reach-back values that are higher or equal to
    the depth of H which indicates that H is an AP
* F
    > F has the child J which has a reach back higher than the depth of F which indicates
    that F is an AP
