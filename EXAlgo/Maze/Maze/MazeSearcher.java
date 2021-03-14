
import java.io.*;
import java.util.*;


public class MazeSearcher 
{
  private static final int[][] STEPS = 
          { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };  // (dx, dy)
         //  DOWN    RIGHT    UP      LEFT


  public MazeSearcher(Maze maze) 
  {
    ArrayList<Coord> path = new ArrayList<>();
    Coord entry = maze.getStart();
    if (explore(maze, entry.getX(), entry.getY(), path)) {
       maze.printPath(path);
    }
    else
      System.out.println("No path found");
  }  // end of MazeSearcher()



  private boolean explore(Maze maze, int x, int y,ArrayList<Coord> path) {

    if(!maze.isValidLoc(x, y)||maze.isWall(x, y)||maze.wasVisited(x, y))
      return false;
    
    path.add(new Coord(x, y));
    maze.setVisited(x, y);
    if(maze.isExit(x, y))
      return true;
    
    for(int[] step:STEPS){
      Coord coord = getNextCoord(x,y,step);
      if(explore(maze,coord.getX(), coord.getY(), path))
        return true;
    }
    path.remove(path.size()-1);
    return false;
  }  // end of explore()


  private Coord getNextCoord(int x,int y,int[] step){
    return new Coord(x+step[0],y+step[1]);
  }


  public static void main(String[] args) throws Exception 
  {
    if (args.length != 1)
      System.out.println("Usage: java MazeSearcher <maze textfile>");
    else {
      Maze maze = new Maze( new File(args[0])); 
      MazeSearcher dfs = new MazeSearcher(maze);
    }
  }

}  // end of MazeSearcher class
