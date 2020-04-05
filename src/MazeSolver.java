import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class MazeSolver {

	private int numRows, numCols;
	private int startRow, startCol;
	private char[][] maze;
	private Stack<Coords> stack = new Stack<>();
	private boolean isSolved = false;

	public MazeSolver() {}

	public MazeSolver(File file) {
		readFile(file);
		solveMaze(this.startRow, this.startCol);
	}

	private void readFile(File file) {
		try {
			Scanner fileReader = new Scanner(file);

			// Getting maze dimensions
			String line = fileReader.nextLine();
			String[] values = line.split(" ");
			this.numRows = Integer.valueOf(values[0]);
			this.numCols = Integer.valueOf(values[1]);

			// Getting starting coordinates
			line = fileReader.nextLine();
			values = line.split(" ");
			this.startRow = Integer.valueOf(values[0]);
			this.startCol = Integer.valueOf(values[1]);

			this.maze = new char[this.numRows][this.numCols];
			String body = fileReader.nextLine();

			for (int i = 0; i < this.numRows; i++) {
				for (int j = 0; j < this.numCols; j++) {
					maze[i][j] = body.charAt(j);
//					System.out.print(maze[i][j] + " ");
				}
				if (i < this.numRows - 1) {
					body = fileReader.nextLine();
				}
//				System.out.println();
			}
	
			fileReader.close();
		} 
		catch (FileNotFoundException e) {
			System.out.println("File not Found");
		}
	}

	// "this." syntax not used in method
	private void solveMaze(int x, int y) {
		x = this.startRow;
		y = this.startCol;
		
		// Starting coordinates
		Coords currCoords = new Coords(x,y);
		stack.push(currCoords);

		// Previous argument: maze[currCoords.x][currCoords.y] != 'E'
		while (maze[x][y] != 'E') {
			
			// Checking top
			if (currCoords.getX() > 0 && maze[currCoords.getX() - 1][currCoords.getY()] == '1') {
				currCoords.setX(currCoords.getX() - 1);
				stack.push(currCoords);
			}
			
			// Checking right
			else if (currCoords.getY() < numCols - 1 && maze[currCoords.getX()][currCoords.getY() + 1] == '1') {
				currCoords.setY(currCoords.getY() + 1);
				stack.push(currCoords);
			}
			
			// Checking bottom
			else if (currCoords.getX() < numRows - 1 && maze[currCoords.getX() + 1][currCoords.getY()] == '1') {
				currCoords.setX(currCoords.getX() + 1);
				stack.push(currCoords);
			}
			
			// Checking left
			else if (currCoords.getY() > 0 && maze[currCoords.getX()][currCoords.getY() - 1] == '1') {
				currCoords.setY(currCoords.getY() - 1);
				stack.push(currCoords);
			}
			
			else {
				maze[currCoords.getX()][currCoords.getY()] = '0';
				stack.pop();
				currCoords = stack.peek();
			}
			
			// This is checked regardless of if-else
			if (maze[currCoords.getX()][currCoords.getY()] != 'X') {
				maze[currCoords.getX()][currCoords.getY()] = 'X';
			}
		}
		
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				System.out.print(maze[i][j] + " ");
			}
			System.out.println();
		}


	}
	
	
	
}