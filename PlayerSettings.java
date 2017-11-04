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

public class PlayerSettings extends Application
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

		Button p1 = new Button("Player 1 Settings                      Customisation for Player 1");
		p1.setStyle("-fx-background-color:transparent;-fx-padding:0;-fx-background-size:0;" + String.format("-fx-font-size: %dpx; -fx-alignment: LEFT; -fx-text-fill: white;", (int)(0.35 * 70)));
		p1.setMinSize(380, 60);
		p1.setPrefSize(380, 60);
		p1.setMaxSize(380, 60);
		p1.setLayoutX(10.0f);
		p1.setLayoutY(70.0f);
		p1.setWrapText(true);

		Line line1 = new Line();
		line1.setStartX(10.0f);
		line1.setStartY(135.0f);
		line1.setEndX(390.0f);
		line1.setEndY(135.0f);
		line1.setStroke(Color.GRAY);

		Button p2 = new Button("Player 2 Settings                      Customisation for Player 2");
		p2.setStyle("-fx-background-color:transparent;-fx-padding:0;-fx-background-size:0;" + String.format("-fx-font-size: %dpx; -fx-alignment: LEFT; -fx-text-fill: white;", (int)(0.35 * 70)));
		p2.setMinSize(380, 60);
		p2.setPrefSize(380, 60);
		p2.setMaxSize(380, 60);
		p2.setLayoutX(10.0f);
		p2.setLayoutY(137.0f);
		p2.setWrapText(true);

		Line line2 = new Line();
		line2.setStartX(10.0f);
		line2.setStartY(202.0f);
		line2.setEndX(390.0f);
		line2.setEndY(202.0f);
		line2.setStroke(Color.GRAY);

		Button p3 = new Button("Player 3 Settings                      Customisation for Player 3");
		p3.setStyle("-fx-background-color:transparent;-fx-padding:0;-fx-background-size:0;" + String.format("-fx-font-size: %dpx; -fx-alignment: LEFT; -fx-text-fill: white;", (int)(0.35 * 70)));
		p3.setMinSize(380, 60);
		p3.setPrefSize(380, 60);
		p3.setMaxSize(380, 60);
		p3.setLayoutX(10.0f);
		p3.setLayoutY(204.0f);
		p3.setWrapText(true);

		Line line3 = new Line();
		line3.setStartX(10.0f);
		line3.setStartY(269.0f);
		line3.setEndX(390.0f);
		line3.setEndY(269.0f);
		line3.setStroke(Color.GRAY);

		Button p4 = new Button("Player 4 Settings                      Customisation for Player 4");
		p4.setStyle("-fx-background-color:transparent;-fx-padding:0;-fx-background-size:0;" + String.format("-fx-font-size: %dpx; -fx-alignment: LEFT; -fx-text-fill: white;", (int)(0.35 * 70)));
		p4.setMinSize(380, 60);
		p4.setPrefSize(380, 60);
		p4.setMaxSize(380, 60);
		p4.setLayoutX(10.0f);
		p4.setLayoutY(271.0f);
		p4.setWrapText(true);

		Line line4 = new Line();
		line4.setStartX(10.0f);
		line4.setStartY(336.0f);
		line4.setEndX(390.0f);
		line4.setEndY(336.0f);
		line4.setStroke(Color.GRAY);

		Button p5 = new Button("Player 5 Settings                      Customisation for Player 5");
		p5.setStyle("-fx-background-color:transparent;-fx-padding:0;-fx-background-size:0;" + String.format("-fx-font-size: %dpx; -fx-alignment: LEFT; -fx-text-fill: white;", (int)(0.35 * 70)));
		p5.setMinSize(380, 60);
		p5.setPrefSize(380, 60);
		p5.setMaxSize(380, 60);
		p5.setLayoutX(10.0f);
		p5.setLayoutY(338.0f);
		p5.setWrapText(true);

		Line line5 = new Line();
		line5.setStartX(10.0f);
		line5.setStartY(403.0f);
		line5.setEndX(390.0f);
		line5.setEndY(403.0f);
		line5.setStroke(Color.GRAY);

		Button p6 = new Button("Player 6 Settings                      Customisation for Player 6");
		p6.setStyle("-fx-background-color:transparent;-fx-padding:0;-fx-background-size:0;" + String.format("-fx-font-size: %dpx; -fx-alignment: LEFT; -fx-text-fill: white;", (int)(0.35 * 70)));
		p6.setMinSize(380, 60);
		p6.setPrefSize(380, 60);
		p6.setMaxSize(380, 60);
		p6.setLayoutX(10.0f);
		p6.setLayoutY(405.0f);
		p6.setWrapText(true);

		Line line6 = new Line();
		line6.setStartX(10.0f);
		line6.setStartY(470.0f);
		line6.setEndX(390.0f);
		line6.setEndY(470.0f);
		line6.setStroke(Color.GRAY);

		Button p7 = new Button("Player 7 Settings                      Customisation for Player 7");
		p7.setStyle("-fx-background-color:transparent;-fx-padding:0;-fx-background-size:0;" + String.format("-fx-font-size: %dpx; -fx-alignment: LEFT; -fx-text-fill: white;", (int)(0.35 * 70)));
		p7.setMinSize(380, 60);
		p7.setPrefSize(380, 60);
		p7.setMaxSize(380, 60);
		p7.setLayoutX(10.0f);
		p7.setLayoutY(472.0f);
		p7.setWrapText(true);

		Line line7 = new Line();
		line7.setStartX(10.0f);
		line7.setStartY(537.0f);
		line7.setEndX(390.0f);
		line7.setEndY(537.0f);
		line7.setStroke(Color.GRAY);

		Button p8 = new Button("Player 8 Settings                      Customisation for Player 8");
		p8.setStyle("-fx-background-color:transparent;-fx-padding:0;-fx-background-size:0;" + String.format("-fx-font-size: %dpx; -fx-alignment: LEFT; -fx-text-fill: white;", (int)(0.35 * 70)));
		p8.setMinSize(380, 60);
		p8.setPrefSize(380, 60);
		p8.setMaxSize(380, 60);
		p8.setLayoutX(10.0f);
		p8.setLayoutY(539.0f);
		p8.setWrapText(true);

		Line line8 = new Line();
		line8.setStartX(10.0f);
		line8.setStartY(604.0f);
		line8.setEndX(390.0f);
		line8.setEndY(604.0f);
		line8.setStroke(Color.GRAY);

		Scene scene = new Scene(new Group(), 400, 650, Color.BLACK);
		Group root = (Group)scene.getRoot();
		root.getChildren().addAll(line, p1, line1, p2, line2, p3, line3, p4, line4, p5, line5, p6, line6, p7, line7, p8, line8, t1, iv);

		stage.setTitle("Chain Reaction");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
} 
 
