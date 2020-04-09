
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MazeSolverGUI extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
		MazeSolver maze = new MazeSolver();
		
		HBox paneForUI = new HBox(20);
		paneForUI.setPadding(new Insets(15));
		paneForUI.setStyle("-fx-background-color: lightgrey");

		Button btFile = new Button("Open File");
		btFile.setOnAction(e -> {
			maze.readFile(fileChooser.showOpenDialog(primaryStage));
			maze.displayMaze();
		});
		
		Button btSolve = new Button("Show Solution");
		btSolve.setOnAction(e -> {
			maze.solveMaze(maze.getStartRow(), maze.getStartCol());
			maze.displayMaze();
		});
		
		paneForUI.getChildren().addAll(btFile, btSolve);

		BorderPane borderPane = new BorderPane();
		borderPane.setTop(paneForUI);
		borderPane.setCenter(maze);

		Scene scene = new Scene(borderPane, 1200, 850);
		primaryStage.setScene(scene);
		primaryStage.setResizable(true);
		primaryStage.setTitle("Maze Solver (implemented w/ Stack API)");
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
