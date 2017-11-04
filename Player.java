import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.stage.Stage; 
import javafx.scene.text.Font; 
import javafx.scene.text.FontPosture; 
import javafx.scene.text.FontWeight; 
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.control.*;
import javafx.scene.shape.Line;
import java.io.*;
import javafx.scene.image.*;

public class Player extends Application
{
	@Override
	public void start(Stage stage) throws FileNotFoundException
	{
		Image image = new Image(new FileInputStream("1.png"));
		ImageView iv = new ImageView(image);
		iv.setX(15);
		iv.setY(10);
		iv.setFitHeight(40);
		iv.setFitWidth(40);
		iv.setPreserveRatio(true);

		Text t1 = new Text("Chain Reaction Preferences");
		t1.setX(65);
		t1.setY(40);
		t1.setFill(Color.WHITE);
		t1.setFont(Font.font("", FontWeight.NORMAL, FontPosture.REGULAR, 18));

		Line line = new Line();
		line.setStartX(10.0f);
		line.setStartY(65.0f);
		line.setEndX(390.0f);
		line.setEndY(65.0f);
		line.setStroke(Color.CYAN);

		Button p1 = new Button("Red Component of Color");
		p1.setStyle("-fx-background-color:transparent;-fx-padding:0;-fx-background-size:0;" + String.format("-fx-font-size: %dpx; -fx-alignment: LEFT; -fx-text-fill: white;", (int)(0.35 * 70)));
		p1.setMinSize(380, 60);
		p1.setPrefSize(380, 60);
		p1.setMaxSize(380, 60);
		p1.setLayoutX(10.0f);
		p1.setLayoutY(65.0f);
		p1.setWrapText(true);

		Line line1 = new Line();
		line1.setStartX(10.0f);
		line1.setStartY(120.0f);
		line1.setEndX(390.0f);
		line1.setEndY(120.0f);
		line1.setStroke(Color.GRAY);

		Button p2 = new Button("Green Component of Color");
		p2.setStyle("-fx-background-color:transparent;-fx-padding:0;-fx-background-size:0;" + String.format("-fx-font-size: %dpx; -fx-alignment: LEFT; -fx-text-fill: white;", (int)(0.35 * 70)));
		p2.setMinSize(380, 60);
		p2.setPrefSize(380, 60);
		p2.setMaxSize(380, 60);
		p2.setLayoutX(10.0f);
		p2.setLayoutY(122.0f);
		p2.setWrapText(true);

		Line line2 = new Line();
		line2.setStartX(10.0f);
		line2.setStartY(187.0f);
		line2.setEndX(390.0f);
		line2.setEndY(187.0f);
		line2.setStroke(Color.GRAY);

		Button p3 = new Button("Blue Component of Color");
		p3.setStyle("-fx-background-color:transparent;-fx-padding:0;-fx-background-size:0;" + String.format("-fx-font-size: %dpx; -fx-alignment: LEFT; -fx-text-fill: white;", (int)(0.35 * 70)));
		p3.setMinSize(380, 60);
		p3.setPrefSize(380, 60);
		p3.setMaxSize(380, 60);
		p3.setLayoutX(10.0f);
		p3.setLayoutY(187.0f);
		p3.setWrapText(true);

		Line line3 = new Line();
		line3.setStartX(10.0f);
		line3.setStartY(252.0f);
		line3.setEndX(390.0f);
		line3.setEndY(252.0f);
		line3.setStroke(Color.GRAY);

		Scene scene = new Scene(new Group(), 400, 650, Color.BLACK);
		Group root = (Group)scene.getRoot();
		root.getChildren().addAll(line, p1, line1, p2, line2, p3, line3, iv, t1);

		stage.setTitle("Chain Reaction");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
} 
