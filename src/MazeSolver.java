import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class MazeSolver extends GridPane {

	private int numRows, numCols;
	private int startRow, startCol;
	private char[][] maze;
	private Stack<Point> stack = new Stack<>();
	
	public MazeSolver() {
		super();
	}
	
	public MazeSolver(File file) {
		readFile(file);
		solveMaze(this.startRow, this.startCol);
	}

	public void readFile(File file) {
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
		
	public void solveMaze(int x, int y) {
		x = this.startRow;
		y = this.startCol;
		
		// Starting coordinates
		Point currCoords = new Point(x,y);
		this.stack.push(currCoords);
		
		boolean isSolved = false;
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
				currCoords = new Point(stack.peek());
				x = currCoords.x;
				y = currCoords.y;
			}
			
			// This is checked regardless of moving
			if (this.maze[x][y] == 'E') {
				isSolved = true;
			}
			else if (this.maze[x][y] != 'X') {
				this.maze[x][y] = 'X';
			}
		}
		
		// Sets untouched paths to 0
		for (int i = 0; i < this.numRows; i++) {
			for (int j = 0; j < this.numCols; j++) {
				if (this.maze[i][j] == '1') {
					maze[i][j] = '0';
				}
			}
		}
	}
	
	public void displayMaze() {
		for (int i = 0; i < this.numRows; i++) {
			for (int j = 0; j < this.numCols; j++) {
				TextField tf = new TextField();
				tf.setAlignment(Pos.CENTER);
				
				if (maze[i][j] == 'S' || maze[i][j] == 'E') {
					tf.setStyle("-fx-background-color: cyan");
				}
				
				if (maze[i][j] == '0') {
					tf.setStyle("-fx-background-color: indianred");
				}
				
				if (maze[i][j] == 'X') {
					tf.setStyle("-fx-background-color: blanchedalmond");
				}
				tf.setText("" + maze[i][j]);
				this.add(tf, j, i);
				this.setGridLinesVisible(true);
			}
		}
	}
		
	public int getStartRow() {
		return this.startRow;
	}
	
	public int getStartCol() {
		return this.startCol;
	}
	
	
}