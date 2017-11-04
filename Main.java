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

public class Main extends Application
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

		Text t1 = new Text("Chain Reaction");
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

		Text title = new Text();
		title.setFont(Font.font("", FontWeight.BOLD, FontPosture.REGULAR, 24));
		title.setX(100);
		title.setY(95);
		title.setText("Chain Reaction");
		title.setFill(Color.WHITE);

		final ComboBox<String> player = new ComboBox<String>();
		player.getItems().addAll("2 Player Game", "3 Player Game", "4 Player Game", "5 Player Game", "6 Player Game", "7 Player Game", "8 Player Game");
		player.setLayoutX(60);
		player.setLayoutY(110);
		player.setValue("No. of Players");
		//player.setStyle("-fx-background-color:transparent; -fx-padding: 0.333333em 0.666667em 0.333333em 0.666667em;" + String.format("-fx-font-size: %dpx; -fx-text-fill: red;", (int)(0.45*40)));

		final ComboBox<String> game = new ComboBox<String>();
		game.getItems().addAll("Normal Grid", "HD Grid");
		game.setLayoutX(210);
		game.setLayoutY(110);
		game.setValue("Grid");

		Button play = new Button("Play");
		play.setStyle("-fx-background-color:transparent;" + String.format("-fx-font-size: %dpx; -fx-text-fill:white;", (int)(0.65*40)));
		play.setMinSize(105, 60);
		play.setPrefSize(105, 60);
		play.setMaxSize(105, 60);
		play.setLayoutX(70.0f);
		play.setLayoutY(140.0f);

		Line line1 = new Line();
		line1.setStartX(175.0f);
		line1.setStartY(150.0f);
		line1.setEndX(175.0f);
		line1.setEndY(190.0f);
		line1.setStroke(Color.GRAY);

		Button resume = new Button("Resume");
		resume.setStyle("-fx-background-color:transparent;" + String.format("-fx-font-size: %dpx; -fx-text-fill:white;", (int)(0.65*40)));
		resume.setMinSize(145, 60);
		resume.setPrefSize(145, 60);
		resume.setMaxSize(145, 60);
		resume.setLayoutX(175.0f);
		resume.setLayoutY(140.0f);

		Text content = new Text();
		content.setFont(Font.font("", FontWeight.BOLD, FontPosture.REGULAR, 16));
		content.setX(20);
		content.setY(220);
		content.setText("The objective of Chain Reaction is to take control of the board by eliminating your opponent orbs.");
		content.setFill(Color.WHITE);

		Text content1 = new Text();
		content1.setFont(Font.font("", FontWeight.BOLD, FontPosture.REGULAR, 16));
		content1.setX(20);
		content1.setY(320);
		content1.setText("Players takes it in turns to place their orbs in a cell." + 
							"Once a cell has reached critical mass the orbs explode" +
							" into the surrounding cells adding an extra orb and claiming " +
							"the cell for the player. A player may only place their orbs " +
							"in a blank cell or a cell that contains orbs of their own color. "+
							"As soon as a player looses all their orbs they are out of the game.");
		content1.setFill(Color.WHITE);

		Button b1 = new Button("Player Settings");
		b1.setLayoutX(282.0f);
		b1.setLayoutY(620.0f);

		Scene scene = new Scene(new Group(), 400, 650, Color.BLACK);
		Group root = (Group)scene.getRoot();
		root.getChildren().addAll(title, player, game, line, content, content1, b1, iv, t1, play, line1, resume);

		content.wrappingWidthProperty().bind(scene.widthProperty().subtract(25));
		content1.wrappingWidthProperty().bind(scene.widthProperty().subtract(25));

		stage.setTitle("Chain Reaction");
		stage.setScene(scene);
		stage.show();



	}

	public static void main(String[] args) {
		launch(args);
	}
}