import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class MazeSolver {

	private int numRows, numCols;
	private int startRow, startCol;
	private char[][] maze;
	private Stack<Character> stack = new Stack<>();

	public MazeSolver() {}

	public MazeSolver(File file) {
		readFile(file);
		solveMaze();
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

	private void solveMaze() {
		
		stack.push(maze[startRow][startCol]);
		
		while (stack.peek() != 'E') {
			
			// Checking top direction
			if (maze[startRow - 1][startCol] == '1') {
				maze[startRow - 1][startCol] = 'X';
				stack.push(maze[startRow - 1][startCol]);
			}
		}
		
	}
}