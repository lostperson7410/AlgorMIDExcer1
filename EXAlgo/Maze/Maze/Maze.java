
// Maze.java
// Andrew Davison, ad@fivedots.coe.psu.ac.th,Jan. 2021

/* Example maze text:

S ########
#        #
# ### ## #
# #    # #
# #  # # #
# ## #####
# #      #
# # #  # #
##### ####
#   #    E
# #    # #
##########


  **** DO NOT CHANGE THIS CODE ****

*/


import java.io.*;
import java.util.*;


public class Maze 
{
  private static final int FLOOR = 0;
  private static final int WALL = 1;
  private static final int START = 2;
  private static final int EXIT = 3;
  private static final int PATH = 4;

  private int[][] maze;
  private boolean[][] visited;
  private Coord start, end;



  public Maze(File maze) throws FileNotFoundException 
  {
    String s = "";
    try (Scanner input = new Scanner(maze)) {
      while (input.hasNextLine())
        s += input.nextLine() + "\n";
    }
    initMaze(s);
  }  // end of Maze()


  private void initMaze(String s) 
  {
    if ((s == null || 
        (s = s.trim()).length() == 0))
      throw new IllegalArgumentException("empty lines data");

    String[] lines = s.split("[\r]?\n");

    maze = new int[lines.length][lines[0].length()];
    visited = new boolean[lines.length][lines[0].length()];
              // false by default

    for (int x = 0; x < getHeight(); x++) {
      if (lines[x].length() != getWidth()) {
        throw new IllegalArgumentException("line " + (x + 1) + 
                   " wrong length (was " + lines[x].length() +
                   " but should be " + getWidth() + ")");
      }

      for (int y = 0; y < getWidth(); y++) {
        if (lines[x].charAt(y) == '#')
          maze[x][y] = WALL;
        else if (lines[x].charAt(y) == 'S') {
          maze[x][y] = START;
          start = new Coord(x, y);
        } 
        else if (lines[x].charAt(y) == 'E') {
          maze[x][y] = EXIT;
          end = new Coord(x, y);
        } 
        else
          maze[x][y] = FLOOR;
      }
    }
  }  // end of initMaze()


  public int getHeight() 
  {  return maze.length; }

  public int getWidth() 
  {  return maze[0].length; }

  public Coord getStart()
  // get the coordinate of the start 
  {  return start;  }

  public Coord getExit() 
  // get the coordinate of the exit 
  {  return end; }

  public boolean isExit(int x, int y) 
  // is (x,y) the exit?
  {  return ((x == end.getX()) && (y == end.getY()));  }

  public boolean isStart(int x, int y) 
  // is (x,y) the start?
  {  return ((x == start.getX()) && (y == start.getY())); }

  public boolean wasVisited(int x, int y) 
  // has (x,y) already been visited during the search?
  {  return visited[x][y]; }

  public boolean isWall(int x, int y) 
  // is (x,y) the coordinate of a wall?
  {  return (maze[x][y] == WALL);  }

  public void setVisited(int x, int y) 
  // record that (x,y) has been visited during the search
  {  visited[x][y] = true;  }


  public boolean isValidLoc(int x, int y) 
  // is (x,y) inside the maze?
  { if (x < 0 || x >= getHeight() || 
        y < 0 || y >= getWidth())
      return false;
    return true;
  }


  public void printPath(ArrayList<Coord> path) 
  {
    int[][] tempMaze = Arrays.stream(maze)
                                .map(int[]::clone)
                                .toArray(int[][]::new);
       // copy the maze 2D map

    // mark the path through the maze
    for (Coord coord : path) {
      if (isStart(coord.getX(), coord.getY()) || 
          isExit(coord.getX(), coord.getY()))
        continue;
      tempMaze[coord.getX()][coord.getY()] = PATH;
    }

    System.out.println(toString(tempMaze));
  }


  public String toString(int[][] maze) 
  {
    StringBuilder result = 
             new StringBuilder(getWidth() * (getHeight() + 1));
    for (int x = 0; x < getHeight(); x++) {
      for (int y = 0; y < getWidth(); y++) {
        if (maze[x][y] == FLOOR)
          result.append(' ');
        else if (maze[x][y] == WALL)
          result.append('#');
        else if (maze[x][y] == START)
          result.append('S');
        else if (maze[x][y] == EXIT)
          result.append('E');
        else
          result.append('.');  // one step in the path
      }
      result.append('\n');
    }
    return result.toString();
  }  // end of toString()


  public void reset() 
  {  for (int i = 0; i < visited.length; i++)
       Arrays.fill(visited[i], false);
  }

}  // end of Maze class
