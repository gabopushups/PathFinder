import java.util.LinkedList;


public class PathFinder {

    public static void main(String args[]){
        Maze myMaze = new Maze(20,60);
        myMaze.fillMaze();
        int [] start =new int[2];
        int [] end = new int[2];
        int currentD=0;
        start[0] = 3;
        start[1]=2;
        end[0]= 15;
        end[1]=2;
        myMaze.printMaze(start[0],start[1]);
        myMaze.maze[start[0]][start[1]]=currentD;
        Queue neighbors= new Queue();
        Queue toVisit = new Queue();
        Node startN= new Node(start);
        toVisit.push(startN);

        boolean flag=false;

        int c=1;
        try {
            while (!toVisit.isEmpty()) {
                Node current = new Node();
                current = toVisit.pop();
                int[] currentPos = new int[2];
                currentPos = (int[]) current.getKey();

                if (myMaze.visitMaze[currentPos[0]][currentPos[1]] == 1) {
                    continue;
                } else {
                    //System.out.println(c);
                    myMaze.visitMaze[currentPos[0]][currentPos[1]] = 1;
                    currentD=myMaze.maze[currentPos[0]][currentPos[1]]+1;
                }


                if (currentPos[0]==end[0]&&currentPos[1]==end[1]) {
                    flag = true;
                    break;
                }

                neighbors = myMaze.findNeighbors(currentPos[0], currentPos[1], currentD, true);

                while (!neighbors.isEmpty()) {
                    Node<int[]> node = new Node(neighbors.pop().getKey(), null, null);
                    toVisit.push(node);
                }
                c++;
            }
        } catch (NullPointerException e){
            System.out.println("AAAAAAAA");
        }
        System.out.println("It took me " +  c + " steps and " + flag);

        if (flag){
            Queue<int[]> path = new Queue<>();
            path=myMaze.shortestPath(start,end);

            myMaze.printMaze(start[0],start[1],path);

            System.out.println("My shortest path: ");

            while(!path.isEmpty()){
                Node<int []> n= new Node<>();
                n=path.pop();
                int[] content=new int[2];
                content=n.getKey();
                System.out.println(content[0] + " " + content[1]);
            }
        }
    }

}
