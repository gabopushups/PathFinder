import java.util.LinkedList;


public class Maze {

    private int dim;
    public int[][] maze;
    private int walls;
    public int[][] visitMaze;
    public int[][] roadMaze;
    public int getWalls() {
        return this.walls;
    }

    public Maze(int dim, int walls){
        this.dim=dim;
        this.maze= new int[dim][dim];
        this.walls=walls;
        this.visitMaze=new int[dim][dim];
        this.roadMaze=new int[dim][dim];
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

        for (int i=0;i<dim;i++){
            for (int j=0;j<dim;j++){
                this.roadMaze[i][j]=this.maze[i][j];
            }
        }
        while(!path.isEmpty()){
            Node<int[]> n = new Node();
            n=path.pop();
            int [] coords=new int[2];
            coords=n.getKey();
            this.roadMaze[coords[0]][coords[1]]=-2;
        }

        for (int i=0;i<dim;i++) {
            for (int j = 0; j < dim; j++) {
                if((i==currentX)&&(j==currentY)){
                    System.out.print("O");
                }
                else if (this.roadMaze[i][j] > 0) {
                    System.out.print("-");
                }
                else if(this.roadMaze[i][j]==-2){
                    System.out.print("x");
                }
                else {
                    System.out.print("#");
                }
            }
            System.out.println("");
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

