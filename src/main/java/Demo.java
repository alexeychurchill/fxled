import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import led.Led;

public class Demo extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        primaryStage.setTitle("LED control demo");
        Scene scene = new Scene(root, 300, 250);
        scene.getStylesheets().add("demoStylesheet.css");
        primaryStage.setScene(scene);
        primaryStage.show();
        //...
        Led led = new Led();
        root.setCenter(led);
        //LED toggle
        Button ledButton = new Button("Toggle led!");
        ledButton.setOnAction(event -> led.setState(!led.getState()));
        root.setLeft(ledButton);
    }
}
