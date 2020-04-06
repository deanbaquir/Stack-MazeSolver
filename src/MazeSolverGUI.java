import javafx.application.Application;
import javafx.stage.Stage;

public class MazeSolverGUI extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("Maze Solver (implemented w/ Stack API)");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
