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
import javafx.scene.shape.*;
import javafx.scene.Node;
import java.io.*;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.image.*;
import javafx.geometry.Bounds;
import javafx.animation.*;
import javafx.util.Duration;
import javafx.scene.paint.Color; 
import javafx.scene.paint.PhongMaterial; 
import java.util.*;

class Player{
	private Color c;
	public Color getColor(){
		return c;
	}
	public void setColor(Color c){
		this.c=c;
	}
}

public class Main extends Application
{
	Stage pstage;
	Scene mainscene, playerscene, gamescene;

	/*Game Attributes*/

	public static ArrayList<Color> c0;
	public static int turn=0;
	public static int n=6;
	public static int sizex=6;
	public static int sizey=9;
	public static int [][]a=new int[6][9];

	public static boolean corner1(int i,int j){
		return (i==0&&j==0);
	}
	public static boolean corner2(int i,int j){
		return (i==0&&j==sizey-1);
	}
	public static boolean corner3(int i,int j){
		return (i==sizex-1&&j==0);
	}
	public static boolean corner4(int i,int j){
		return (i==sizex-1&&j==sizey-1);
	}
	public static boolean edge1(int i,int j){
		return (i==0&&j>0&&j<sizey-1);
	}
	public static boolean edge2(int i,int j){
		return (j==sizey-1&&i>0&&i<sizex-1);
	}
	public static boolean edge3(int i,int j){
		return (i==sizex-1&&j>0&&j<sizey-1);
	}
	public static boolean edge4(int i,int j){
		return (j==0&&i>0&&i<sizex-1);
	}

	public static void conquer(Group[][] r, int x, int y, Sphere s, Group root)
	{
		root.getChildren().remove(r[x][y]);
		Sphere s1;
		if(r[x][y].getChildren().isEmpty())
		{
			r[x][y].getChildren().add(s);
		}
		else
		{
			for(int i = 0; i < r[x][y].getChildren().size(); i++)
			{
				s1 = (Sphere)r[x][y].getChildren().remove(i);
				s1.setMaterial(s.getMaterial());
        		r[x][y].getChildren().add(s1);
			}
			r[x][y].getChildren().add(s);
		}
		root.getChildren().add(r[x][y]);
		r[x][y].toBack();
	}

	public static void burst(Group r[][],int x,int y,int i, double xc, double yc, Group root){
		ArrayList<Sphere> s1=new ArrayList<Sphere>();	
		if(a[x][y]/4!=i){
			int r1=a[x][y]/4;
			a[x][y]=a[x][y]+4*(i-r1);
		}
		a[x][y]++;
		/*
		if(Main.winA(A.a)){
			return ;
		}
		if(Main.winB(A.a)){
			return;
		}
		*/
		if(corner1(x,y)){
			if(a[x][y]%4==2){
				
				while(!r[x][y].getChildren().isEmpty()){
					s1.add((Sphere)r[x][y].getChildren().get(0));
					r[x][y].getChildren().remove(0);
				}

		        TranslateTransition t2 =new TranslateTransition();
		        t2.setDuration(Duration.seconds(0.5));
		        t2.setToX(xc+67.5);
		        t2.setToY(yc);
		        t2.setNode(s1.get(0));
		        t2.play();
		      	
		        TranslateTransition t4 =new TranslateTransition();
		        t4.setDuration(Duration.seconds(0.5));
		        t4.setToX(xc);
		        t4.setToY(yc+67.5);
		        t4.setNode(s1.get(1));
		        t4.play();

				a[x][y]=0;
				conquer(r, x+1, y, s1.get(0), root);
				conquer(r, x, y+1, s1.get(1), root);
				burst(r,x+1,y,i,xc+67.5,yc,root);
				burst(r,x,y+1,i,xc,yc+60,root);			
			}
	
		}
		else if(corner2(x,y)){
			if(a[x][y]%4==2){
				while(!r[x][y].getChildren().isEmpty()){
					s1.add((Sphere)r[x][y].getChildren().get(0));
					r[x][y].getChildren().remove(0);
				}

		        TranslateTransition t2 =new TranslateTransition();
		        t2.setDuration(Duration.seconds(0.5));
		        t2.setToX(xc+67.5);
		        t2.setToY(yc);
		        t2.setNode(s1.get(0));
		        t2.play();
		        
		        TranslateTransition t3 =new TranslateTransition();
		        t3.setDuration(Duration.seconds(0.5));
		        t3.setToX(xc);
		        t3.setToY(yc-67.5);
		        t3.setNode(s1.get(1));
		        t3.play();

				a[x][y]=0;
				conquer(r, x+1, y, s1.get(0), root);
				conquer(r, x, y-1, s1.get(1), root);
				burst(r,x+1,y,i,xc+60,yc,root);
				burst(r,x,y-1,i,xc,yc-60,root);				
			}
		}
		else if(corner3(x,y)){
			if(a[x][y]%4==2){
				while(!r[x][y].getChildren().isEmpty()){
					s1.add((Sphere)r[x][y].getChildren().get(0));
					r[x][y].getChildren().remove(0);
				}

				TranslateTransition t1 =new TranslateTransition();
		        t1.setDuration(Duration.seconds(0.5));
		        t1.setToX(xc-67.5);
		        t1.setToY(yc);
		        t1.setNode(s1.get(0));
		        t1.play();

		        TranslateTransition t4 =new TranslateTransition();
		        t4.setDuration(Duration.seconds(0.5));
		        t4.setToX(xc);
		        t4.setToY(yc+67.5);
		        t4.setNode(s1.get(1));
		        t4.play();

				a[x][y]=0;
				conquer(r, x-1, y, s1.get(0), root);
				conquer(r, x, y+1, s1.get(1), root);
				burst(r,x-1,y,i,xc-60,yc,root);
				burst(r,x,y+1,i,xc,yc+60,root);			
			}
	
		}
		else if(corner4(x,y)){
			if(a[x][y]%4==2){
				while(!r[x][y].getChildren().isEmpty()){
					s1.add((Sphere)r[x][y].getChildren().get(0));
					r[x][y].getChildren().remove(0);
				}

				TranslateTransition t1 =new TranslateTransition();
		        t1.setDuration(Duration.seconds(0.5));
		        t1.setToX(xc-67.5);
		        t1.setToY(yc);
		        t1.setNode(s1.get(0));
		        t1.play();

		        TranslateTransition t3 =new TranslateTransition();
		        t3.setDuration(Duration.seconds(0.5));
		        t3.setToX(xc);
		        t3.setToY(yc-67.5);
		        t3.setNode(s1.get(1));
		        t3.play();

				a[x][y]=0;
				conquer(r, x-1, y, s1.get(0), root);
				conquer(r, x, y-1, s1.get(1), root);
				burst(r,x-1,y,i,xc-60,yc,root);
				burst(r,x,y-1,i,xc,yc-60,root);
			}		
		}
		else if(edge1(x,y)){
			if(a[x][y]%4==3){
				while(!r[x][y].getChildren().isEmpty()){
					s1.add((Sphere)r[x][y].getChildren().get(0));
					r[x][y].getChildren().remove(0);
				}

		        TranslateTransition t2 =new TranslateTransition();
		        t2.setDuration(Duration.seconds(0.5));
		        t2.setToX(xc+67.5);
		        t2.setToY(yc);
		        t2.setNode(s1.get(0));
		        t2.play();
		        
		        TranslateTransition t3 =new TranslateTransition();
		        t3.setDuration(Duration.seconds(0.5));
		        t3.setToX(xc);
		        t3.setToY(yc-67.5);
		        t3.setNode(s1.get(1));
		        t3.play();

		        TranslateTransition t4 =new TranslateTransition();
		        t4.setDuration(Duration.seconds(0.5));
		        t4.setToX(xc);
		        t4.setToY(yc+67.5);
		        t4.setNode(s1.get(2));
		        t4.play();

				a[x][y]=0;
				conquer(r, x+1, y, s1.get(0), root);
				conquer(r, x, y-1, s1.get(1), root);
				conquer(r, x, y+1, s1.get(2), root);
				burst(r,x,y-1,i,xc,yc-60,root);
				burst(r,x,y+1,i,xc,yc+60,root);
				burst(r,x+1,y,i,xc+60,yc,root);
			}
		}
		else if(edge2(x,y)){
			if(a[x][y]%4==3){
				while(!r[x][y].getChildren().isEmpty()){
					s1.add((Sphere)r[x][y].getChildren().get(0));
					r[x][y].getChildren().remove(0);
				}

				TranslateTransition t1 =new TranslateTransition();
		        t1.setDuration(Duration.seconds(0.5));
		        t1.setToX(xc-67.5);
		        t1.setToY(yc);
		        t1.setNode(s1.get(0));
		        t1.play();

		        TranslateTransition t2 =new TranslateTransition();
		        t2.setDuration(Duration.seconds(0.5));
		        t2.setToX(xc+67.5);
		        t2.setToY(yc);
		        t2.setNode(s1.get(1));
		        t2.play();
		        
		        TranslateTransition t3 =new TranslateTransition();
		        t3.setDuration(Duration.seconds(0.5));
		        t3.setToX(xc+67.5);
		        t3.setToY(yc-67.5);
		        t3.setNode(s1.get(2));
		        t3.play();

				a[x][y]=0;
				conquer(r, x-1, y, s1.get(0), root);
				conquer(r, x+1, y, s1.get(1), root);
				conquer(r, x+1, y-1, s1.get(2), root);
				burst(r,x-1,y,i,xc-60,yc,root);
				burst(r,x+1,y,i,xc+60,yc,root);
				burst(r,x+1,y-1,i,xc+60,yc-60,root);
			}
		}
		else if(edge3(x,y)){
			if(a[x][y]%4==3){
				while(!r[x][y].getChildren().isEmpty()){
					s1.add((Sphere)r[x][y].getChildren().get(0));
					r[x][y].getChildren().remove(0);
				}

				TranslateTransition t1 =new TranslateTransition();
		        t1.setDuration(Duration.seconds(0.5));
		        t1.setToX(xc-67.5);
		        t1.setToY(yc);
		        t1.setNode(s1.get(0));
		        t1.play();
		        
		        TranslateTransition t3 =new TranslateTransition();
		        t3.setDuration(Duration.seconds(0.5));
		        t3.setToX(xc);
		        t3.setToY(yc-67.5);
		        t3.setNode(s1.get(1));
		        t3.play();

		        TranslateTransition t4 =new TranslateTransition();
		        t4.setDuration(Duration.seconds(0.5));
		        t4.setToX(xc);
		        t4.setToY(yc+67.5);
		        t4.setNode(s1.get(2));
		        t4.play();

				a[x][y]=0;
				conquer(r, x-1, y, s1.get(0), root);
				conquer(r, x, y-1, s1.get(1), root);
				conquer(r, x, y+1, s1.get(2), root);
				burst(r,x-1,y,i,xc-60,yc,root);
				burst(r,x,y-1,i,xc,yc-60,root);
				burst(r,x,y+1,i,xc,yc+60,root);
			}
		}
		else if(edge4(x,y)){
			if(a[x][y]%4==3){
				while(!r[x][y].getChildren().isEmpty()){
					s1.add((Sphere)r[x][y].getChildren().get(0));
					r[x][y].getChildren().remove(0);
				}

				TranslateTransition t1 =new TranslateTransition();
		        t1.setDuration(Duration.seconds(0.5));
		        t1.setToX(xc-67.5);
		        t1.setToY(yc);
		        t1.setNode(s1.get(0));
		        t1.play();

		        TranslateTransition t2 =new TranslateTransition();
		        t2.setDuration(Duration.seconds(0.5));
		        t2.setToX(xc+67.5);
		        t2.setToY(yc);
		        t2.setNode(s1.get(1));
		        t2.play();

		        TranslateTransition t4 =new TranslateTransition();
		        t4.setDuration(Duration.seconds(0.5));
		        t4.setToX(xc);
		        t4.setToY(yc+67.5);
		        t4.setNode(s1.get(2));
		        t4.play();

				a[x][y]=0;
				conquer(r, x-1, y, s1.get(0), root);
				conquer(r, x+1, y, s1.get(1), root);
				conquer(r, x, y+1, s1.get(2), root);
				burst(r,x-1,y,i,xc-60,yc,root);
				burst(r,x+1,y,i,xc+60,yc,root);
				burst(r,x,y+1,i,xc,yc+60,root);
			}
		}
		else{
			if(a[x][y]%4==0){
				while(!r[x][y].getChildren().isEmpty()){
					s1.add((Sphere)r[x][y].getChildren().get(0));
					r[x][y].getChildren().remove(0);
				}

				TranslateTransition t1 =new TranslateTransition();
		        t1.setDuration(Duration.seconds(0.5));
		        t1.setToX(xc-67.5);
		        t1.setToY(yc);
		        t1.setNode(s1.get(0));
		        t1.play();

		        TranslateTransition t2 =new TranslateTransition();
		        t2.setDuration(Duration.seconds(0.5));
		        t2.setToX(xc+67.5);
		        t2.setToY(yc);
		        t2.setNode(s1.get(1));
		        t2.play();

		        TranslateTransition t3 =new TranslateTransition();
		        t3.setDuration(Duration.seconds(0.5));
		        t3.setToX(xc);
		        t3.setToY(yc-67.5);
		        t3.setNode(s1.get(2));
		        t3.play();

		        TranslateTransition t4 =new TranslateTransition();
		        t4.setDuration(Duration.seconds(0.5));
		        t4.setToX(xc);
		        t4.setToY(yc+67.5);
		        t4.setNode(s1.get(3));
		        t4.play();

				a[x][y]=0;
				conquer(r, x-1, y, s1.get(0), root);
				conquer(r, x+1, y, s1.get(1), root);
				conquer(r, x, y-1, s1.get(2), root);
				conquer(r, x, y+1, s1.get(3), root);
				burst(r,x-1,y,i, xc-50, yc,root);
				burst(r,x+1,y,i, xc+50, yc,root);
				burst(r,x,y-1,i, xc, yc-60,root);
				burst(r,x,y+1,i, xc, yc+60,root);
			}
		}
	}
	/*
	public static void lit(int x, int y, Group aw){
		Sphere s=(Sphere)aw.getChildren().get(0);
		aw.getChildren().remove(0);

		TranslateTransition t1 =new TranslateTransition();
        t1.setDuration(Duration.seconds(1));
        t1.setToX();
        t1.setToY();
        t1.setNode(s);
        t1.play();
	}*/
	public static Sphere find(Sphere s,Group r){
		for(Node n:r.getChildren()){
			if(n.getClass().getName().equals(s.getClass().getName())){
				return (Sphere)n;
			}
		}
		return null;
	}

	@Override
	public void start(Stage stage) throws FileNotFoundException
	{
		pstage = stage;

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

		Line barr = new Line();
		barr.setStartX(10.0f);
		barr.setStartY(65.0f);
		barr.setEndX(390.0f);
		barr.setEndY(65.0f);
		barr.setStroke(Color.CYAN);

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
		play.setOnAction(e -> playGame());

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
		b1.setOnAction(e -> playerSettings());
		b1.setLayoutX(282.0f);
		b1.setLayoutY(620.0f);

		mainscene = new Scene(new Group(), 400, 650, Color.BLACK);
		Group mainroot = (Group)mainscene.getRoot();
		mainroot.getChildren().addAll(title, player, game, barr, content, content1, b1, iv, t1, play, line1, resume);

		content.wrappingWidthProperty().bind(mainscene.widthProperty().subtract(25));
		content1.wrappingWidthProperty().bind(mainscene.widthProperty().subtract(25));

		/*Player Settings*/

		Image image1 = new Image(new FileInputStream("1.png"));
		ImageView iv1 = new ImageView(image1);
		iv1.setX(15);
		iv1.setY(10);
		iv1.setFitHeight(40);
		iv1.setFitWidth(40);
		iv1.setPreserveRatio(true);

		Text t2 = new Text("Chain Reaction Preferences");
		t2.setX(65);
		t2.setY(40);
		t2.setFill(Color.WHITE);
		t2.setFont(Font.font("", FontWeight.NORMAL, FontPosture.REGULAR, 18));

		Line bar = new Line();
		bar.setStartX(10.0f);
		bar.setStartY(65.0f);
		bar.setEndX(390.0f);
		bar.setEndY(65.0f);
		bar.setStroke(Color.CYAN);

		Button p1 = new Button("Player 1 Settings                      Customisation for Player 1");
		p1.setStyle("-fx-background-color:transparent;-fx-padding:0;-fx-background-size:0;" + String.format("-fx-font-size: %dpx; -fx-alignment: LEFT; -fx-text-fill: white;", (int)(0.35 * 70)));
		p1.setMinSize(380, 60);
		p1.setPrefSize(380, 60);
		p1.setMaxSize(380, 60);
		p1.setLayoutX(10.0f);
		p1.setLayoutY(70.0f);
		p1.setWrapText(true);

		Line bar1 = new Line();
		bar1.setStartX(10.0f);
		bar1.setStartY(135.0f);
		bar1.setEndX(390.0f);
		bar1.setEndY(135.0f);
		bar1.setStroke(Color.GRAY);

		Button p2 = new Button("Player 2 Settings                      Customisation for Player 2");
		p2.setStyle("-fx-background-color:transparent;-fx-padding:0;-fx-background-size:0;" + String.format("-fx-font-size: %dpx; -fx-alignment: LEFT; -fx-text-fill: white;", (int)(0.35 * 70)));
		p2.setMinSize(380, 60);
		p2.setPrefSize(380, 60);
		p2.setMaxSize(380, 60);
		p2.setLayoutX(10.0f);
		p2.setLayoutY(137.0f);
		p2.setWrapText(true);

		Line bar2 = new Line();
		bar2.setStartX(10.0f);
		bar2.setStartY(202.0f);
		bar2.setEndX(390.0f);
		bar2.setEndY(202.0f);
		bar2.setStroke(Color.GRAY);

		Button p3 = new Button("Player 3 Settings                      Customisation for Player 3");
		p3.setStyle("-fx-background-color:transparent;-fx-padding:0;-fx-background-size:0;" + String.format("-fx-font-size: %dpx; -fx-alignment: LEFT; -fx-text-fill: white;", (int)(0.35 * 70)));
		p3.setMinSize(380, 60);
		p3.setPrefSize(380, 60);
		p3.setMaxSize(380, 60);
		p3.setLayoutX(10.0f);
		p3.setLayoutY(204.0f);
		p3.setWrapText(true);

		Line bar3 = new Line();
		bar3.setStartX(10.0f);
		bar3.setStartY(269.0f);
		bar3.setEndX(390.0f);
		bar3.setEndY(269.0f);
		bar3.setStroke(Color.GRAY);

		Button p4 = new Button("Player 4 Settings                      Customisation for Player 4");
		p4.setStyle("-fx-background-color:transparent;-fx-padding:0;-fx-background-size:0;" + String.format("-fx-font-size: %dpx; -fx-alignment: LEFT; -fx-text-fill: white;", (int)(0.35 * 70)));
		p4.setMinSize(380, 60);
		p4.setPrefSize(380, 60);
		p4.setMaxSize(380, 60);
		p4.setLayoutX(10.0f);
		p4.setLayoutY(271.0f);
		p4.setWrapText(true);

		Line bar4 = new Line();
		bar4.setStartX(10.0f);
		bar4.setStartY(336.0f);
		bar4.setEndX(390.0f);
		bar4.setEndY(336.0f);
		bar4.setStroke(Color.GRAY);

		Button p5 = new Button("Player 5 Settings                      Customisation for Player 5");
		p5.setStyle("-fx-background-color:transparent;-fx-padding:0;-fx-background-size:0;" + String.format("-fx-font-size: %dpx; -fx-alignment: LEFT; -fx-text-fill: white;", (int)(0.35 * 70)));
		p5.setMinSize(380, 60);
		p5.setPrefSize(380, 60);
		p5.setMaxSize(380, 60);
		p5.setLayoutX(10.0f);
		p5.setLayoutY(338.0f);
		p5.setWrapText(true);

		Line bar5 = new Line();
		bar5.setStartX(10.0f);
		bar5.setStartY(403.0f);
		bar5.setEndX(390.0f);
		bar5.setEndY(403.0f);
		bar5.setStroke(Color.GRAY);

		Button p6 = new Button("Player 6 Settings                      Customisation for Player 6");
		p6.setStyle("-fx-background-color:transparent;-fx-padding:0;-fx-background-size:0;" + String.format("-fx-font-size: %dpx; -fx-alignment: LEFT; -fx-text-fill: white;", (int)(0.35 * 70)));
		p6.setMinSize(380, 60);
		p6.setPrefSize(380, 60);
		p6.setMaxSize(380, 60);
		p6.setLayoutX(10.0f);
		p6.setLayoutY(405.0f);
		p6.setWrapText(true);

		Line bar6 = new Line();
		bar6.setStartX(10.0f);
		bar6.setStartY(470.0f);
		bar6.setEndX(390.0f);
		bar6.setEndY(470.0f);
		bar6.setStroke(Color.GRAY);

		Button p7 = new Button("Player 7 Settings                      Customisation for Player 7");
		p7.setStyle("-fx-background-color:transparent;-fx-padding:0;-fx-background-size:0;" + String.format("-fx-font-size: %dpx; -fx-alignment: LEFT; -fx-text-fill: white;", (int)(0.35 * 70)));
		p7.setMinSize(380, 60);
		p7.setPrefSize(380, 60);
		p7.setMaxSize(380, 60);
		p7.setLayoutX(10.0f);
		p7.setLayoutY(472.0f);
		p7.setWrapText(true);

		Line bar7 = new Line();
		bar7.setStartX(10.0f);
		bar7.setStartY(537.0f);
		bar7.setEndX(390.0f);
		bar7.setEndY(537.0f);
		bar7.setStroke(Color.GRAY);

		Button p8 = new Button("Player 8 Settings                      Customisation for Player 8");
		p8.setStyle("-fx-background-color:transparent;-fx-padding:0;-fx-background-size:0;" + String.format("-fx-font-size: %dpx; -fx-alignment: LEFT; -fx-text-fill: white;", (int)(0.35 * 70)));
		p8.setMinSize(380, 60);
		p8.setPrefSize(380, 60);
		p8.setMaxSize(380, 60);
		p8.setLayoutX(10.0f);
		p8.setLayoutY(539.0f);
		p8.setWrapText(true);

		Line bar8 = new Line();
		bar8.setStartX(10.0f);
		bar8.setStartY(604.0f);
		bar8.setEndX(390.0f);
		bar8.setEndY(604.0f);
		bar8.setStroke(Color.GRAY);

		playerscene = new Scene(new Group(), 400, 650, Color.BLACK);
		Group playerroot = (Group)playerscene.getRoot();
		playerroot.getChildren().addAll(bar, p1, bar1, p2, bar2, p3, bar3, p4, bar4, p5, bar5, p6, bar6, p7, bar7, p8, bar8, t2, iv1);

		/*Game */

		Color ccc=c0.get(turn);
		Group[][] r=new Group[6][9];
		for(int i=0;i<6;i++){
			for(int j=0;j<9;j++){
				r[i][j]=new Group();
			}
		}
		Image gimage = new Image(new FileInputStream("1.png"));
		ImageView giv = new ImageView(gimage);
		giv.setX(15);
		giv.setY(10);
		giv.setFitHeight(40);
		giv.setFitWidth(40);
		giv.setPreserveRatio(true);

		Text g1 = new Text("Chain Reaction");
		g1.setX(65);
		g1.setY(40);
		g1.setFill(Color.WHITE);
		g1.setFont(Font.font("", FontWeight.NORMAL, FontPosture.REGULAR, 18));

		Line bar12 = new Line();
		bar12.setStartX(10.0f);
		bar12.setStartY(65.0f);
		bar12.setEndX(390.0f);
		bar12.setEndY(65.0f);
		bar12.setStroke(Color.CYAN);

		Button un = new Button("Undo");
		un.setStyle("-fx-background-color:transparent;" + String.format("-fx-font-size: %dpx; -fx-text-fill:white;", (int)(0.45*40)));
		un.setMinSize(75, 40);
		un.setPrefSize(75, 40);
		un.setMaxSize(75, 40);
		un.setLayoutX(240.0f);
		un.setLayoutY(10.0f);

		Line line2 = new Line();
		line2.setStartX(315.0f);
		line2.setStartY(10.0f);
		line2.setEndX(315.0f);
		line2.setEndY(50.0f);
		line2.setStroke(Color.GRAY);

		Image gimage1 = new Image(new FileInputStream("2.png"));
		ImageView giv1 = new ImageView(gimage1);
		giv1.setX(335);
		giv1.setY(20);
		giv1.setFitHeight(30);
		giv1.setFitWidth(30);
		giv1.setPreserveRatio(true);

		Rectangle[][] grid1 = new Rectangle[6][9];
		Rectangle[][] grid2 = new Rectangle[6][9];
		Line[][] line = new Line[7][10];

		for(int i = 0; i < 6; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				grid1[i][j] = new Rectangle(35 + i*55, 100 + j*55, 55, 55);
				grid1[i][j].setFill(Color.TRANSPARENT);
				grid1[i][j].setStroke(ccc);

				grid2[i][j] = new Rectangle(20 + i*60, 80 + j*60, 60, 60);
				grid2[i][j].setFill(Color.TRANSPARENT);
				grid2[i][j].setStroke(ccc);

				line[i][j] = new Line();
				line[i][j].setStartX(grid1[i][j].getX());
				line[i][j].setEndX(grid2[i][j].getX());
				line[i][j].setStartY(grid1[i][j].getY());
				line[i][j].setEndY(grid2[i][j].getY());
				line[i][j].setStroke(ccc);
			}
		}

		for(int j = 0; j < 9; j++)
		{
			line[6][j] = new Line();
			line[6][j].setStartX(grid1[5][j].getX() + 55);
			line[6][j].setEndX(grid2[5][j].getX() + 60);
			line[6][j].setStartY(grid1[5][j].getY() + 55);
			line[6][j].setEndY(grid2[5][j].getY() + 60);
			line[6][j].setStroke(ccc);
		}

		for(int i = 0; i < 6; i++)
		{
			line[i][9] = new Line();
			line[i][9].setStartX(grid1[i][8].getX() + 55);
			line[i][9].setEndX(grid2[i][8].getX() + 60);
			line[i][9].setStartY(grid1[i][8].getY() + 55);
			line[i][9].setEndY(grid2[i][8].getY() + 60);
			line[i][9].setStroke(ccc);
		}

		line[6][9] = new Line();
		line[6][9].setStartX(grid1[5][0].getX() + 55);
		line[6][9].setEndX(grid2[5][0].getX() + 60);
		line[6][9].setStartY(grid1[5][0].getY());
		line[6][9].setEndY(grid2[5][0].getY());
		line[6][9].setStroke(ccc);

		Line extra = new Line();
		extra.setStartX(grid1[0][8].getX());
		extra.setEndX(grid2[0][8].getX());
		extra.setStartY(grid1[0][8].getY() + 55);
		extra.setEndY(grid2[0][8].getY() + 60);
		extra.setStroke(ccc);

		Group gridroot=new Group();
		gamescene = new Scene(new Group(), 400, 650, Color.BLACK);
		Group root = (Group)gamescene.getRoot();
		PhongMaterial material[] = new PhongMaterial[n];
		for(int i=0;i<n;i++){
			material[i] = new PhongMaterial(); 
        	material[i].setDiffuseColor(c0.get(i)); 
        	material[i].setSpecularColor(c0.get(i)); 
        	
		}
		 
        
		for(int i=0;i<6;i++)
		{
			for(int j=0;j<9;j++){
				int xxx=i;
				int yyy=j;
				Bounds b=grid2[i][j].getBoundsInLocal();
				grid2[i][j].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
					@Override public void handle(MouseEvent event) {
					double x=b.getMinX()+b.getWidth()/2;
    				double y=b.getMinY()+b.getHeight()/2;
              		PhongMaterial redMaterial=material[turn];
              		//changeColor();
              		if(a[xxx][yyy]%4==0){
              			r[xxx][yyy]=new Group();
              		}
              		if(a[xxx][yyy]%4==0){

              			for(int k = 0; k < 6; k++)
              				for(int l = 0; l < 9; l++)
              				{
              					grid1[k][l].setStroke(c0.get((turn + 1)%n));
              					grid2[k][l].setStroke(c0.get((turn + 1)%n));
              					line[k][l].setStroke(c0.get((turn + 1)%n));
              				}

              			for(int k = 0; k < 9; k++)
							line[6][k].setStroke(c0.get((turn + 1)%n));
						for(int k = 0; k < 6; k++)
							line[k][9].setStroke(c0.get((turn + 1)%n));
						line[6][9].setStroke(c0.get((turn + 1)%n));
						extra.setStroke(c0.get((turn + 1) % n));

              			Sphere s=new Sphere();
              			s.setRadius(15.0);
              			s.setTranslateX(x);
              			s.setTranslateY(y);
              			s.setMaterial(redMaterial);
              			r[xxx][yyy].getChildren().add(s);
              			burst(r,xxx,yyy,turn, x, y, root);
              			root.getChildren().remove(r[xxx][yyy]);
						root.getChildren().add(r[xxx][yyy]);
						r[xxx][yyy].toBack();
						turn=(turn+1)%n;
              		}
              		else if(a[xxx][yyy]%4==1){
              			Sphere v=find(new Sphere(),r[xxx][yyy]);

              			if(v.getMaterial().equals(redMaterial)){
              				for(int k = 0; k < 6; k++)
	              				for(int l = 0; l < 9; l++)
	              				{
	              					grid1[k][l].setStroke(c0.get((turn + 1)%n));
	              					grid2[k][l].setStroke(c0.get((turn + 1)%n));
	              					line[k][l].setStroke(c0.get((turn + 1)%n));
	              				}

	              			for(int k = 0; k < 9; k++)
								line[6][k].setStroke(c0.get((turn + 1)%n));
							for(int k = 0; k < 6; k++)
								line[k][9].setStroke(c0.get((turn + 1)%n));
							line[6][9].setStroke(c0.get((turn + 1)%n));
							extra.setStroke(c0.get((turn + 1) % n));

	              			Sphere s=new Sphere();
	              			s.setRadius(15.0);
	              			s.setTranslateX(x+7.5);
	              			s.setTranslateY(y);
	              			s.setMaterial(redMaterial);
	              			r[xxx][yyy].getChildren().add(s);
	              			burst(r,xxx,yyy,turn, x, y, root);
	              			root.getChildren().remove(r[xxx][yyy]);
							root.getChildren().add(r[xxx][yyy]);
							r[xxx][yyy].toBack();
							turn=(turn+1)%n;
						}
              		}
              		else if(a[xxx][yyy]%4==2){

              			Sphere v=find(new Sphere(),r[xxx][yyy]);
              			if(v.getMaterial().equals(redMaterial)){
              				for(int k = 0; k < 6; k++)
	              				for(int l = 0; l < 9; l++)
	              				{
	              					grid1[k][l].setStroke(c0.get((turn + 1)%n));
	              					grid2[k][l].setStroke(c0.get((turn + 1)%n));
	              					line[k][l].setStroke(c0.get((turn + 1)%n));
	              				}

	              			for(int k = 0; k < 9; k++)
								line[6][k].setStroke(c0.get((turn + 1)%n));
							for(int k = 0; k < 6; k++)
								line[k][9].setStroke(c0.get((turn + 1)%n));
							line[6][9].setStroke(c0.get((turn + 1)%n));
							extra.setStroke(c0.get((turn + 1) % n));

	              			Sphere s=new Sphere();
	              			s.setRadius(15.0);
	              			s.setTranslateX(x);
	              			s.setTranslateY(y+7.5);
	              			s.setMaterial(redMaterial);
	              			r[xxx][yyy].getChildren().add(s);
	              			burst(r,xxx,yyy,turn, x, y, root);
	              			root.getChildren().remove(r[xxx][yyy]);
							root.getChildren().add(r[xxx][yyy]);
							r[xxx][yyy].toBack();
							turn=(turn+1)%n;
						}
              		}
              		else if(a[xxx][yyy]%4==3){
              			Sphere v=find(new Sphere(),r[xxx][yyy]);

              			if(v.getMaterial().equals(redMaterial)){
              				for(int k = 0; k < 6; k++)
	              				for(int l = 0; l < 9; l++)
	              				{
	              					grid1[k][l].setStroke(c0.get((turn + 1)%n));
	              					grid2[k][l].setStroke(c0.get((turn + 1)%n));
	              					line[k][l].setStroke(c0.get((turn + 1)%n));
	              				}

	              			for(int k = 0; k < 9; k++)
								line[6][k].setStroke(c0.get((turn + 1)%n));
							for(int k = 0; k < 6; k++)
								line[k][9].setStroke(c0.get((turn + 1)%n));
							line[6][9].setStroke(c0.get((turn + 1)%n));
							extra.setStroke(c0.get((turn + 1) % n));

	              			Sphere s=new Sphere();
	              			s.setRadius(15.0);
	              			s.setTranslateX(x);
	              			s.setTranslateY(y+7.5);
	              			s.setMaterial(redMaterial);
	              			r[xxx][yyy].getChildren().add(s);
	              			burst(r,xxx,yyy,turn, x, y, root);
	              			root.getChildren().remove(r[xxx][yyy]);
							root.getChildren().add(r[xxx][yyy]);
							r[xxx][yyy].toBack();
							turn=(turn+1)%n;
						}
              		}
              		RotateTransition rt=new RotateTransition(Duration.millis(1000),r[xxx][yyy]);
        			rt.setByAngle(360);
     				rt.setCycleCount(Timeline.INDEFINITE);
     				rt.setInterpolator(Interpolator.LINEAR);
     				rt.play();
              		for(int i=0;i<6;i++){
              			for(int j=0;j<9;j++){
              				System.out.print(a[i][j]+"  ");
              			}
              			System.out.println("");
              		}
              		System.out.println("");	
				}
			});
			}
		}

		gridroot.getChildren().addAll(bar12, extra, un, line2, giv, g1, giv1);
		for(int i = 0; i < 6; i++)
			for(int j = 0; j < 9; j++)
				gridroot.getChildren().addAll(grid1[i][j], grid2[i][j], line[i][j]);
		for(int i = 0 ; i < 7; i++)
			gridroot.getChildren().add(line[i][9]);
		for(int i = 0; i < 9; i++)
			gridroot.getChildren().add(line[6][i]);
		root.getChildren().add(gridroot);

		stage.setTitle("Chain Reaction");
		stage.setScene(mainscene);
		stage.show();
	}

	public static void main(String[] args) {
		c0=new ArrayList<Color>();
		c0.add(Color.RED);
		c0.add(Color.GREEN);
		c0.add(Color.YELLOW);
		c0.add(Color.BLUE);
		c0.add(Color.WHITE);
		c0.add(Color.CYAN);
		launch(args);
	}

	public void playerSettings()
	{
		pstage.setScene(playerscene);
	}

	public void playGame()
	{
		pstage.setScene(gamescene);
	}
}