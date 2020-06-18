# PathFinder
A short algorithm to find a path in a Maze, implementing linear data structures such as Queues (Linked List).
The algorithm consists of the following;

# Setup
1. Initialize maze dimensions and walls. Set start and end positions
2. Create a queue of the positions that will be visited, called toVisit.
3. Set the current position to start. Add it to toVisit.

# Iterate until toVisit is empty
4. Pop the current position from toVisit
5. Verify if the current position is equal to the end position. If yes, we are done.
6. For the current position, check its 4 neighbors and add them to a queue named neighbors
7. Empty the neighbors queue and add them to toVisit.
8. Go back to 4.

