import java.io.File;

public class MazeSolverTester {

	public static void main(String[] args) {
		File file = new File("sampleMaze1.txt");
		MazeSolver maze = new MazeSolver(file);
	}

}