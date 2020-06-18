import java.util.LinkedList;


public class PathFinder {

    private int dim;
    public int[][] maze;
    private int walls;
    public int[][] visitMaze;
    public int[][] roadMaze;
    public int getWalls() {
        return this.walls;
    }

    public PathFinder(int dim, int walls){
        this.dim=dim;
        this.maze= new int[dim][dim];
        this.walls=walls;
        this.visitMaze=new int[dim][dim];
    }


    public void fillMaze(){
        double a;
        int maxWalls=this.getWalls();
        int drawnWalls=0;
        int rowWalls=0;
        int maxRowWalls=5;
        for (int i=0;i<this.dim;i++){
            rowWalls=0;
            for(int j=0;j<this.dim;j++){
                a=Math.random();
                if((a>0.65)&&drawnWalls<=maxWalls && rowWalls<maxRowWalls){
                    this.maze[i][j]=-1;
                    drawnWalls++;
                    rowWalls++;
                }
                else {
                    this.maze[i][j]=this.dim*this.dim+1;
                }
                this.visitMaze[i][j]=0;
            }
        }
    }

    public void printMaze(int currentX,int currentY){
        for (int i=0;i<dim;i++) {
            for (int j = 0; j < dim; j++) {

                if((i==currentX)&&(j==currentY)){
                    System.out.print("O");
                }
                else if (maze[i][j] > 0) {
                    System.out.print("-");
                }
                else {
                    System.out.print("#");
                }
            }
            System.out.println("");
        }
    }

    public void printMaze(int currentX,int currentY, Queue path){
        int[][] roadMaze= new int[this.dim][this.dim];
        for (int i=0;i<dim;i++){
            for (int j=0;j<dim;j++){
                roadMaze[i][j]=this.maze[i][j];
            }
        }
        while(!path.isEmpty()){
            Node<int[]> n = new Node();
            n=path.pop();
            int [] coords=new int[2];
            coords=n.getKey();
            roadMaze[coords[0]][coords[1]]=-2;
        }

        for (int i=0;i<dim;i++) {
            for (int j = 0; j < dim; j++) {
                if((i==currentX)&&(j==currentY)){
                    System.out.print("O");
                }
                else if (roadMaze[i][j] > 0) {
                    System.out.print("-");
                }
                else if(roadMaze[i][j]==-2){
                    System.out.print("x");
                }
                else {
                    System.out.print("#");
                }
            }
            System.out.println("");
        }
    }

    public static void main(String args[]){
        PathFinder myMaze = new PathFinder(20,60);
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

    public Queue shortestPath(int[] start, int[] end){
        Queue<int[]> path = new Queue<>();
        int[] current= new int[]{end[0],end[1]};
        int endVal= this.maze[end[0]][end[1]];
        int currentVal=endVal;
        while(currentVal!=0){
            Node<int[]> neighbor= new Node<>();
            int[] coord = new int[2];
            coord=this.closestNeighbor(current[0],current[1]);
            neighbor.setKey(coord);
            path.push(neighbor);
            current=coord;
            currentVal=this.maze[current[0]][current[1]];
        }
        return path;
    }

    public int[] closestNeighbor(int currentX,int currentY){
        int currentD=this.maze[currentX][currentY];
        int [] finalCoords= new int[]{currentX, currentY};
        for(int i=-1;i<2;i++){
            for(int j=-1;j<2;j++){
                if((Math.abs(i)!=Math.abs(j))&&((currentX+i)>-1)&&((currentY+j)>-1)&&((currentX+i)<this.dim)&&((currentY+j)<this.dim)) {
                    if (this.maze[currentX+i][currentY+j] != -1) {
                        int[] coords= new int[2];
                        coords[0] = currentX+i;
                        coords[1] = currentY+j;
                        if(this.maze[coords[0]][coords[1]]<currentD){
                            currentD=this.maze[coords[0]][coords[1]];
                            finalCoords=coords;
                        }
                    }
                }
            }
        }
        return finalCoords;
    }

    public Queue findNeighbors(int currentX,int currentY, int currentD, boolean flag){
        Queue neighbors= new Queue();

        for(int i=-1;i<2;i++){
            for(int j=-1;j<2;j++){
                if((Math.abs(i)!=Math.abs(j))&&((currentX+i)>-1)&&((currentY+j)>-1)&&((currentX+i)<this.dim)&&((currentY+j)<this.dim)) {
                    if (this.maze[currentX+i][currentY+j] != -1) {
                        int[] coords= new int[2];
                        Node Coords= new Node(coords);
                        coords[0] = currentX+i;
                        coords[1] = currentY+j;
                        neighbors.push(Coords);
                        if (flag){
                            if (this.maze[currentX+i][currentY+j] > currentD){
                                this.maze[currentX+i][currentY+j]=currentD;
                            }
                        }
                    }
                }
            }
        }
        return neighbors;
    }

}
