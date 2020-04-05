import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class MazeSolver {

	private int numRows, numCols;
	private int startRow, startCol;
	private char[][] maze;
	private Stack<Point> stack = new Stack<>();
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
				}
				if (i < this.numRows - 1) {
					body = fileReader.nextLine();
				}
			}
			fileReader.close();
		} 
		catch (FileNotFoundException e) {
			System.out.println("File not Found");
		}
	}

	private void solveMaze(int x, int y) {
		x = this.startRow;
		y = this.startCol;
		
		// Starting coordinates
		Point currCoords = new Point(x,y);
		this.stack.push(currCoords);

		while (isSolved == false) {
			
			// Checking top
			if (x > 0 && (this.maze[x - 1][y] == '1' || this.maze[x - 1][y] == 'E')) {
				x = x - 1;
				this.stack.push(new Point(x,y));
			}
			
			// Checking right
			else if (y < this.numCols - 1 && (this.maze[x][y + 1] == '1' || this.maze[x][y + 1] == 'E')) {
				y = y + 1;
				this.stack.push(new Point(x,y));
			}
			
			// Checking bottom
			else if (x < this.numRows - 1 && (this.maze[x + 1][y] == '1' || this.maze[x + 1][y] == 'E')) {
				x = x + 1;
				this.stack.push(new Point(x,y));
			}
			
			// Checking left
			else if (y > 0 && (this.maze[x][y - 1] == '1' || this.maze[x][y - 1] == 'E')) {
				y = y - 1;
				this.stack.push(new Point(x,y));
			}
			
			else {
				this.maze[x][y] = '0';
				this.stack.pop();
				//TODO: Check this while debugging
				currCoords = new Point(stack.peek());
				x = currCoords.x;
				y = currCoords.y;
			}
			
			// This is checked regardless of moving
			if (this.maze[x][y] == 'E') {
				this.isSolved = true;
			}
			else if (this.maze[x][y] != 'X') {
				this.maze[x][y] = 'X';
			}
		}
		
		// Printing out solution path
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				if (this.maze[i][j] == '1') {
					maze[i][j] = '0';
				}
				System.out.print(maze[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	
	
}