import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Stage stage = new Stage();
        Group root = new Group();
        Scene scene = new Scene(root,Color.CADETBLUE);
        primaryStage.setScene(scene);
        
        StackPane root2 = new StackPane();
        primaryStage.setTitle("Hello World");
        primaryStage.show();
    }
}
