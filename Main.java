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
import javafx.event.*; 
import java.util.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

/**
 * This is an application created using JavaFX which is similar to the 
 * game Chain Reaction available on PlayStore and AppStore. This was done 
 * as a project for the course Advance Programming (CSE 201) at IIIT Delhi.  
 * For back end we have an integer grid in which the value of Player 1 are 1-4
 * , for Player 2 are 5-8 for example in the given grid
 * 0 1 2 5 7 
 * 9 11 0 0
 * The 1 tells us that this is being occupied by Player 1 with 1 Sphere,
 * the 2 tells us that this is being occupied by Player 1 with 2 Spheres,
 * the 5 tells us that this is being occupied by Player 2 with 1 (5%4=1) Sphere, 
 * the 7 tells us that this is being occupied by Player 2 with 3 (7%4=3) Spheres,
 * the 9 tells us that this is being occupied by Player 3 with 1 (9%4=1) Sphere,  
 * the 11 tells us that this is being occupied by Player 3 with 3 (11%4=3) Spheres.
 * This prevents creation of Cell class to determine who is occupying the cell.
 * @author Apoorv Khattar 2016016
 * @author Nikhil Sachdeva 2016061
 * @version 1.0
 */


class ColorChange implements EventHandler<ActionEvent>
{
	private Main o;
	private int turn;
	private Color c;
	private int x;
	private int y;
	private Color ci;

	ColorChange(int turn, Color c, Main o1, int x, int y, Color ci)
	{
		this.turn = turn;
		this.c = c;
		this.o = o1;
		this.x = x;
		this.y = y;
		this.ci = ci;
	}

	@Override
	public void handle(ActionEvent e)
	{
		try
		{
			int xi = 0, yi = 0;
			for(int i = 0; i < 5; i++)
				for(int j = 0; j < 3; j++)
				{

					if(Main.c.get(i*3 + j).equals(ci))
					{
						xi = i;
						yi = j;
					}
				}
			Game.cvalue[x][y] = 1;
			Game.cvalue[xi][yi] = 0;
			Settings.colors[xi][yi].setDisable(false);
			Settings.colors[x][y].setDisable(true);
			o.callmain(Game.turn, c);
		}
		catch(FileNotFoundException f)
		{

		}
	}
}

/**
 * This is the Player class in which Color assigned to that player and their no. dare data members.
 *
 */
class Player{
	private Color c;
	private int i;

	Player(Color c, int i)
	{
		this.c = c;
		this.i = i;
	}
	
	/**
	 * @param Nothing
	 * @return Color This returns the color assigned to that Player 
	 */
	public Color getColor(){
		return c;
	}
	
	/**
	 * @param Nothing
	 * @return int This returns the no. assigned to that Player 
	 */
	public int getTurn()
	{
		return i;
	}
	
	/**
	 * @param c This is the the new color that needs to be assigned to the Player
	 * @return Nothing
	 */
	public void setColor(Color c){
		this.c=c;
	}
}

/**
 * Settings class is used to create the settings menu when the player clicks on Player Settings Button
 * and has functions for changing the color of the color of a particular player.
 */
class Settings
{
	/**
	 * o is a static object of the Main class
	 */	
	public static Main o;
	
	/**
	 * colors is a 2D array of button that provide a color panel when the user wants to change the color of a player
	 */	
	public static Button[][] colors;

	Settings(Main o)
	{
		this.o = o;
		colors = new Button[5][3];
	}
	
	/**
	 * This function creates the Player Settings Menu
	 * @param Nothing
	 * @return Nothing
	 */
	public void playerSettingscall() throws FileNotFoundException
	{
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

		Button un = new Button("Menu");
		un.setStyle("-fx-background-color:transparent; -fx-border-color:white;" + String.format("-fx-font-size: %dpx; -fx-text-fill:white;", (int)(0.40*40)));
		un.setMinSize(75, 30);
		un.setPrefSize(75, 30);
		un.setMaxSize(75, 30);
		un.setLayoutX(320.0f);
		un.setLayoutY(15.0f);
		un.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e)
			{
				try
				{
					Game.turn = 0;
					o.callmain(Game.turn, Game.p.get(Game.turn).getColor());
				}
				catch(FileNotFoundException f)
				{

				}
			}
		});

		Button p1 = new Button("Player 1 Settings                      Customisation for Player 1");
		p1.setStyle("-fx-background-color:transparent;-fx-padding:0;-fx-background-size:0;" + String.format("-fx-font-size: %dpx; -fx-alignment: LEFT; -fx-text-fill: white;", (int)(0.35 * 70)));
		p1.setMinSize(380, 60);
		p1.setPrefSize(380, 60);
		p1.setMaxSize(380, 60);
		p1.setLayoutX(10.0f);
		p1.setLayoutY(70.0f);
		p1.setWrapText(true);
		p1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e)
			{
				try
				{
					o.playercolor(0);
				}
				catch(FileNotFoundException f)
				{

				}
			}
		});

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
		p2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e)
			{
				try
				{
					o.playercolor(1);
				}
				catch(FileNotFoundException f)
				{

				}
			}
		});

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
		p3.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e)
			{
				try
				{
					o.playercolor(2);
				}
				catch(FileNotFoundException f)
				{

				}
			}
		});

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
		p4.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e)
			{
				try
				{
					o.playercolor(3);
				}
				catch(FileNotFoundException f)
				{

				}
			}
		});

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
		p5.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e)
			{
				try
				{
					o.playercolor(4);
				}
				catch(FileNotFoundException f)
				{

				}
			}
		});

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
		p6.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e)
			{
				try
				{
					o.playercolor(5);
				}
				catch(FileNotFoundException f)
				{

				}
			}
		});

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
		p7.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e)
			{
				try
				{
					o.playercolor(6);
				}
				catch(FileNotFoundException f)
				{

				}
			}
		});

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
		p8.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e)
			{
				try
				{
					o.playercolor(7);
				}
				catch(FileNotFoundException f)
				{

				}
			}
		});

		Line bar8 = new Line();
		bar8.setStartX(10.0f);
		bar8.setStartY(604.0f);
		bar8.setEndX(390.0f);
		bar8.setEndY(604.0f);
		bar8.setStroke(Color.GRAY);

		Main.playerscene = new Scene(new Group(), 400, 650, Color.BLACK);
		Group playerroot = (Group)Main.playerscene.getRoot();
		playerroot.getChildren().addAll(bar, p1, bar1, p2, bar2, p3, bar3, p4, bar4, p5, bar5, p6, bar6, p7, bar7, p8, bar8, t2, iv1, un);

	}
	
	/**
	 * After selecting the Player for whom the color is to be changed, 15 different colors are presented 
	 * from which the Player can select any color that has not been assigned to any user.
	 * @param turn This tells us the Player for whom the color is to be changed
	 * @return Nothing
	 */
	public static void pcolor(int turn) throws FileNotFoundException
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

		Button un = new Button("Menu");
		un.setStyle("-fx-background-color:transparent; -fx-border-color:white;" + String.format("-fx-font-size: %dpx; -fx-text-fill:white;", (int)(0.40*40)));
		un.setMinSize(75, 30);
		un.setPrefSize(75, 30);
		un.setMaxSize(75, 30);
		un.setLayoutX(320.0f);
		un.setLayoutY(15.0f);
		un.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e)
			{
				try
				{
					Game.turn = 0;
					o.callmain(Game.turn, Game.p.get(Game.turn).getColor());
				}
				catch(FileNotFoundException f)
				{

				}
			}
		});

		int flag = 0;
		if(colors[0][0] != null)
			flag = 1;

		for(int i = 0; i < 5; i++)
			for(int j = 0; j < 3; j++)
			{
				if(colors[i][j] == null)
					colors[i][j] = new Button();
				colors[i][j].setMinSize(90, 90);
				colors[i][j].setPrefSize(90, 90);
				colors[i][j].setMaxSize(90, 90);
				colors[i][j].setLayoutX(j*110 + 45);
				colors[i][j].setLayoutY(i*110 + 90);
				colors[i][j].setBackground(new Background(new BackgroundFill(Main.c.get(j + 3*i),null, null)));
				colors[i][j].setOnAction(new ColorChange(turn, Main.c.get(j + 3*i), o, i, j, Main.total.get(turn).getColor()));
			}
		if(flag == 0)
		{	
			for(int i = 0; i < 3; i++)
				for(int j = 0; j < 3; j++)
					colors[i][j].setDisable(true);
		}

		colors[2][2].setDisable(false);

		Main.playascene = new Scene(new Group(), 400, 650, Color.BLACK);
		Group playaroot = (Group)Main.playascene.getRoot();
		playaroot.getChildren().addAll(iv, t1, line, un);
		for(int i = 0; i < 5; i++)
			for(int j = 0; j < 3; j++)
				playaroot.getChildren().add(colors[i][j]);
	}
}

/**
 * The functions related to the Game like burst(), conquer() that change the value of the grid after each move
 * present here. The animation for translation of spheres is also carried out in the class
 */
class Game
{
	/**
	 * This is s static object of the Main class
	 */
	static Main o;

	/**
	 * This represents the list of players that are currently playing
	 */
	public static ArrayList<Player> p;
	
	/**
	 * This represents the player who has to make a move
	 */
	public static int turn;
	
	/**
	 * This represents the no. of players playing the game
	 */
	public static int n;
	
	/**
	 * This represents no. of rows in the grid 
	 */
	public static int sizex;
	
	/**
	 * This represents no. of columns in the grid 
	 */
	public static int sizey;
	
	/**
	 * Back end grid
	 */
	public static int [][]a;
	
	/**
	 * This value is used by the undo function to always write the grid of previous turn onto the file abc.txt
	 */
	public static int lol;
	

	public static int[][] cvalue;
	public static int flagcheck;
	
	/**
	 * arr tells which player are still playing. Of the 8 elements in it if arr[i] is zero then that player
	 * is not allowed to make a move.
	 */
	public static int[] arr;

	Game(Main o)
	{
		turn = 0;
		n = 2;
		sizex = 6;
		sizey = 9;
		a = new int[6][9];
		lol = 0;
		flagcheck = 0;
		arr = new int[8];
		this.o = o;
	}

	/**
	 * Checks if the sphere is added in the top left hand corner of the grid.
	 * @param i This tells us the row of the Integer matrix (in the back end) to be accessed
	 * @param j This tells us the column of the Integer matrix (in the back end) to be accessed
	 * @return boolean Returns true if the sphere is in that corner
	 */
	public static boolean corner1(int i,int j){
		return (i==0&&j==0);
	}
	
	/**
	 * Checks if the sphere is added in the top right hand corner of the grid.
	 * @param i This tells us the row of the Integer matrix (in the back end) to be accessed
	 * @param j This tells us the column of the Integer matrix (in the back end) to be accessed
	 * @return boolean Returns true if the sphere is in that corner
	 */
	public static boolean corner2(int i,int j){
		return (i==0&&j==sizey-1);
	}
	
	/**
	 * Checks if the sphere is added in the bottom left hand corner of the grid.
	 * @param i This tells us the row of the Integer matrix (in the back end) to be accessed
	 * @param j This tells us the column of the Integer matrix (in the back end) to be accessed
	 * @return boolean Returns true if the sphere in that corner
	 */
	public static boolean corner3(int i,int j){
		return (i==sizex-1&&j==0);
	}
	
	/**
	 * Checks if the sphere is added in the bottom right hand corner of the grid.
	 * @param i This tells us the row of the Integer matrix (in the back end) to be accessed
	 * @param j This tells us the column of the Integer matrix (in the back end) to be accessed
	 * @return boolean Returns true if the sphere in that corner
	 */
	public static boolean corner4(int i,int j){
		return (i==sizex-1&&j==sizey-1);
	}
	
	/**
	 * Checks if the sphere is added in the top most row of the grid.
	 * @param i This tells us the row of the Integer matrix (in the back end) to be accessed
	 * @param j This tells us the column of the Integer matrix (in the back end) to be accessed
	 * @return boolean Returns true if the sphere in that row
	 */
	public static boolean edge1(int i,int j){
		return (i==0&&j>0&&j<sizey-1);
	}
	
	/**
	 * Checks if the sphere is added in the first column of the grid.
	 * @param i This tells us the row of the Integer matrix (in the back end) to be accessed
	 * @param j This tells us the column of the Integer matrix (in the back end) to be accessed
	 * @return boolean Returns true if the sphere in that column
	 */
	public static boolean edge2(int i,int j){
		return (j==sizey-1&&i>0&&i<sizex-1);
	}
	
	/**
	 * Checks if the sphere is added in the bottom most row of the grid.
	 * @param i This tells us the row of the Integer matrix (in the back end) to be accessed
	 * @param j This tells us the column of the Integer matrix (in the back end) to be accessed
	 * @return boolean Returns true if the sphere in that row
	 */
	public static boolean edge3(int i,int j){
		return (i==sizex-1&&j>0&&j<sizey-1);
	}
	
	/**
	 * Checks if the sphere is added in the last column of the grid.
	 * @param i This tells us the row of the Integer matrix (in the back end) to be accessed
	 * @param j This tells us the column of the Integer matrix (in the back end) to be accessed
	 * @return boolean Returns true if the sphere in that column
	 */
	public static boolean edge4(int i,int j){
		return (j==0&&i>0&&i<sizex-1);
	}
	
	/** 
	 * This function is used for an extra grid that is maintained in the back end. This was done because the
	 * updation of the grid was slowed down due to animations which was needed for serializing it and for checking
	 * if a Player has been eliminated or not.
	 * @param b This is an integer matrix in which the changes are made
	 * @param x This is an integer representing the row no. of the grid
	 * @param y This is an integer representing the column no. of the grid
	 * @param i This is an integer representing which Player it is
	 * @return Nothing
	 */
	public static void backburst(int[][] b, int x,int y,int i){
		if(b[x][y]/4!=i){
			int r=b[x][y]/4;
			b[x][y]=b[x][y]+4*(i-r);
		}
		b[x][y]++;
		
		if(corner1(x,y)){
			if(b[x][y]%4==2){
				b[x][y]=0;
				backburst(b,x+1,y,i);
				backburst(b,x,y+1,i);			
			}
	
		}
		else if(corner2(x,y)){
			if(b[x][y]%4==2){
				b[x][y]=0;
				backburst(b,x+1,y,i);
				backburst(b,x,y-1,i);				
			}
		}
		else if(corner3(x,y)){
			if(b[x][y]%4==2){
				b[x][y]=0;
				backburst(b,x-1,y,i);
				backburst(b,x,y+1,i);			
			}
	
		}
		else if(corner4(x,y)){
			if(b[x][y]%4==2){
				b[x][y]=0;
				backburst(b,x-1,y,i);
				backburst(b,x,y-1,i);
			}		
		}
		else if(edge1(x,y)){
			if(b[x][y]%4==3){
				b[x][y]=0;
				backburst(b,x,y-1,i);
				backburst(b,x,y+1,i);
				backburst(b,x+1,y,i);
			}
		}
		else if(edge2(x,y)){
			if(b[x][y]%4==3){
				b[x][y]=0;
				backburst(b,x-1,y,i);
				backburst(b,x+1,y,i);
				backburst(b,x,y-1,i);
			}
		}
		else if(edge3(x,y)){
			if(b[x][y]%4==3){
				b[x][y]=0;
				backburst(b,x,y-1,i);
				backburst(b,x,y+1,i);
				backburst(b,x-1,y,i);
			}
		}
		else if(edge4(x,y)){
			if(b[x][y]%4==3){
				b[x][y]=0;
				backburst(b,x-1,y,i);
				backburst(b,x+1,y,i);
				backburst(b,x,y+1,i);
			}
		}
		else{
			if(b[x][y]%4==0){
				b[x][y]=0;
				backburst(b,x-1,y,i);
				backburst(b,x+1,y,i);
				backburst(b,x,y-1,i);
				backburst(b,x,y+1,i);
			}
		}
	}
	
	/**
	 * This function is called inside burst() to update the grid after there has a change in it if 
	 * one of the cells had more than spheres than the permissible limit.
	 * @param r This a 2D array of Group representing each cell in the front end Grid
	 * @param x This is an integer representing the row no. of the grid
	 * @param y This is an integer representing the column no. of the grid
	 * @param s This is the new sphere that has to be added at r[x][y]
	 * @param root This is the main Group in which r and other components of the grid are present
	 * @param grid2 This is a 2D array of rectangles present in the grid that are needed to find the centre
	 * 		  of the cell in which the Sphere is to be added 
	 * @return Nothing
	 */
	public static void conquer(Group[][] r, int x, int y, Sphere s, Group root,Rectangle[][] grid2)
	{
		ArrayList<Sphere> s1=new ArrayList<Sphere>();
		Bounds b=grid2[x][y].getBoundsInLocal();
		double a0=b.getMinX()+b.getWidth()/2;
		double coree=s.getRadius()/2;
		double b0=b.getMinY()+b.getWidth()/2;
		root.getChildren().remove(r[x][y]);
		if(r[x][y].getChildren().isEmpty())
		{
			r[x][y].getChildren().add(s);
		}
		else
		{
			for(int i = 0; i < r[x][y].getChildren().size(); i++)
			{
				s1.add((Sphere)r[x][y].getChildren().get(i));
			}
			r[x][y].getChildren().clear();
			r[x][y].getChildren().add(s);
			for(int i=0;i<s1.size();i++){
				s1.get(i).setMaterial(s.getMaterial());
				if(i==0){
					s1.get(i).setTranslateX(a0+coree);
					s1.get(i).setTranslateY(b0);
				}				
				else if(i==1){
					s1.get(i).setTranslateX(a0);
					s1.get(i).setTranslateY(b0+coree);
				}
				r[x][y].getChildren().add(s1.get(i));

			}
		}
		root.getChildren().add(r[x][y]);
		r[x][y].toBack();
	}
	
	/**
	 * This function is called to check if we have a winner. It does so by checking if there are two or more different
	 * colored Spheres present in the Grid
	 * @param r This a 2D array of Group representing each cell in the front end Grid 
	 * @return boolean returns true if the winner has been found
	 */
	public static boolean checkWinner(Group[][] r)
	{
		ArrayList<Color> ch = new ArrayList<Color>();
		for(int i = 0; i < sizex; i++)
			for(int j = 0; j < sizey; j++)
			{
				if(r[i][j].getChildren().size() != 0)
					ch.add(((PhongMaterial)((Sphere)(r[i][j].getChildren().get(0))).getMaterial()).getDiffuseColor());
			}
		Color c = ch.get(0);
		int flag = 0;
		for(int i = 1; i < ch.size(); i++)
		{
			if(!c.equals(ch.get(i)))
			{
				flag = 1;
				break;
			}
		}

		if(flag == 0)
			return true;
		return false;
	}
	
	/**
	 * This function is used to check if the any Player no longer has even a single Sphere in the Grid or not
	 * @param t This is a 2D Integer matrix which is the back end grid which is being maintained. 
	 * @return int[] This is an array in which if the value is zero then it implies that Player has been eliminated
	 */
	public static int[] updateplayers(int[][] t)
	{
		int[] x = new int[8];
		for(int i = 0; i < sizey; i++)
			for(int j = 0; j < sizex; j++)
			{
				if(t[j][i] != 0)
				{	
					int te = t[j][i]/4;
					x[te] = 1;
				}
			}
		
		return x;
	}
	
	/** 
	 * This function is used for the front end Grid that is maintained. the translation animation if there are more than
	 * permissible no. of Spheres is being carried out here. Also the write(), checkinner() are called in it. 
	 * @param r This a 2D array of Group representing each cell in the front end Grid
	 * @param x This is an integer representing the row no. of the grid
	 * @param y This is an integer representing the column no. of the grid
	 * @param s This is the new sphere that has to be added at r[x][y]
	 * @param root This is the main Group in which r and other components of the grid are present
	 * @param grid2 This is a 2D array of rectangles present in the grid that are needed to find the centre
	 * 		  of the cell in which the Sphere is to be added 
	 * @return Nothing
	 */
	public static void burst(Group r[][],int x,int y,int i, Group root,Rectangle[][] grid2)
	{
		Bounds b=grid2[x][y].getBoundsInLocal();
		Double xc=b.getMinX()+b.getWidth()/2;
		Double yc=b.getMinY()+b.getHeight()/2;
		ArrayList<Sphere> s1=new ArrayList<Sphere>();	
		if(a[x][y]/4!=i){
			int r1=a[x][y]/4;
			a[x][y]=a[x][y]+4*(i-r1);
		}
		a[x][y]++;
		/*
		for(int k=0;k<sizey;k++){
              			for(int j=0;j<sizex;j++){
              				System.out.print(a[j][k]+"  ");
              			}
              			System.out.println("");
              		}*/
		
		Main.write();
        
		if(corner1(x,y)){
			if(a[x][y]%4==2){
				
				while(!r[x][y].getChildren().isEmpty()){
					s1.add((Sphere)r[x][y].getChildren().get(0));
					r[x][y].getChildren().remove(0);
				}

		        TranslateTransition t2 =new TranslateTransition();
		        t2.setDuration(Duration.seconds(0.5));
		        t2.setToX(xc+b.getWidth()-5);
		        t2.setToY(yc);
		        t2.setNode(s1.get(0));
		      	
		        TranslateTransition t4 =new TranslateTransition();
		        t4.setDuration(Duration.seconds(0.5));
		        t4.setToX(xc);
		        t4.setToY(yc+b.getHeight()-5);
		        t4.setNode(s1.get(1));

		        ParallelTransition p1 = new ParallelTransition(t2, t4);
		        p1.play();

		        root.getChildren().addAll(s1.get(0), s1.get(1));
		        a[x][y]=0;
		        p1.setOnFinished(e -> {
					conquer(r, x+1, y, s1.get(0), root,grid2);
					conquer(r, x, y+1, s1.get(1), root,grid2);
					
					try
					{
						if(checkWinner(r))
							o.callwinner(i);
					}
					catch(FileNotFoundException f)
					{

					}

					burst(r,x+1,y,i,root,grid2);
					burst(r,x,y+1,i,root,grid2);
		        });
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
		        t2.setToX(xc+b.getWidth());
		        t2.setToY(yc);
		        t2.setNode(s1.get(0));
		        
		        TranslateTransition t3 =new TranslateTransition();
		        t3.setDuration(Duration.seconds(0.5));
		        t3.setToX(xc);
		        t3.setToY(yc-b.getHeight());
		        t3.setNode(s1.get(1));

		        ParallelTransition p1 = new ParallelTransition(t2, t3);
		        p1.play();

		        root.getChildren().addAll(s1.get(0), s1.get(1));
		        a[x][y]=0;
		        p1.setOnFinished(e -> {
					conquer(r, x+1, y, s1.get(0), root,grid2);
					conquer(r, x, y-1, s1.get(1), root,grid2);
					try
					{
						if(checkWinner(r))
							o.callwinner(i);
					}
					catch(FileNotFoundException f)
					{

					}
					burst(r,x+1,y,i,root,grid2);
					burst(r,x,y-1,i,root,grid2);
		        });

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
		        t1.setToX(xc-b.getWidth());
		        t1.setToY(yc);
		        t1.setNode(s1.get(0));

		        TranslateTransition t4 =new TranslateTransition();
		        t4.setDuration(Duration.seconds(0.5));
		        t4.setToX(xc);
		        t4.setToY(yc+b.getHeight());
		        t4.setNode(s1.get(1));

		        ParallelTransition p1 = new ParallelTransition(t1, t4);
		        p1.play();

		        root.getChildren().addAll(s1.get(0), s1.get(1));
		        a[x][y]=0;
		        p1.setOnFinished(e -> {
					conquer(r, x-1, y, s1.get(0), root,grid2);
					conquer(r, x, y+1, s1.get(1), root,grid2);
					try
					{
						if(checkWinner(r))
							o.callwinner(i);
					}
					catch(FileNotFoundException f)
					{

					}
					burst(r,x-1,y,i,root,grid2);
					burst(r,x,y+1,i,root,grid2);
		        });
				

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
		        t1.setToX(xc-b.getWidth());
		        t1.setToY(yc);
		        t1.setNode(s1.get(0));

		        TranslateTransition t3 =new TranslateTransition();
		        t3.setDuration(Duration.seconds(0.5));
		        t3.setToX(xc);
		        t3.setToY(yc-b.getHeight());
		        t3.setNode(s1.get(1));

		        ParallelTransition p1 = new ParallelTransition(t1, t3);
		        p1.play();

		        root.getChildren().addAll(s1.get(0), s1.get(1));
		        a[x][y]=0;
		        p1.setOnFinished(e -> {
					conquer(r, x-1, y, s1.get(0), root,grid2);
					conquer(r, x, y-1, s1.get(1), root,grid2);
					try
					{
						if(checkWinner(r))
							o.callwinner(i);
					}
					catch(FileNotFoundException f)
					{

					}
					burst(r,x-1,y,i,root,grid2);
					burst(r,x,y-1,i,root,grid2);
		        });
				
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
		        t2.setToX(xc+b.getWidth());
		        t2.setToY(yc);
		        t2.setNode(s1.get(0));
		        
		        TranslateTransition t3 =new TranslateTransition();
		        t3.setDuration(Duration.seconds(0.5));
		        t3.setToX(xc);
		        t3.setToY(yc-b.getHeight());
		        t3.setNode(s1.get(1));

		        TranslateTransition t4 =new TranslateTransition();
		        t4.setDuration(Duration.seconds(0.5));
		        t4.setToX(xc);
		        t4.setToY(yc+b.getHeight());
		        t4.setNode(s1.get(2));

		        ParallelTransition p1 = new ParallelTransition(t2, t3, t4);
		        p1.play();

		        root.getChildren().addAll(s1.get(0), s1.get(1), s1.get(2));
		        a[x][y]=0;
		        p1.setOnFinished(e -> {
					conquer(r, x+1, y, s1.get(0), root,grid2);
					conquer(r, x, y-1, s1.get(1), root,grid2);
					conquer(r, x, y+1, s1.get(2), root,grid2);
					try
					{
						if(checkWinner(r))
							o.callwinner(i);
					}
					catch(FileNotFoundException f)
					{

					}
					burst(r,x,y-1,i,root,grid2);
					burst(r,x,y+1,i,root,grid2);
					burst(r,x+1,y,i,root,grid2);
		        });				
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
		        t1.setToX(xc-b.getWidth());
		        t1.setToY(yc);
		        t1.setNode(s1.get(0));

		        TranslateTransition t2 =new TranslateTransition();
		        t2.setDuration(Duration.seconds(0.5));
		        t2.setToX(xc+b.getWidth());
		        t2.setToY(yc);
		        t2.setNode(s1.get(1));
		        
		        TranslateTransition t3 =new TranslateTransition();
		        t3.setDuration(Duration.seconds(0.5));
		        t3.setToX(xc);
		        t3.setToY(yc-b.getHeight());
		        t3.setNode(s1.get(2));

		        ParallelTransition p1 = new ParallelTransition(t2, t3, t1);
		        p1.play();

		        root.getChildren().addAll(s1.get(0), s1.get(1), s1.get(2));
		        a[x][y]=0;
		        p1.setOnFinished(e -> {
					conquer(r, x-1, y, s1.get(0), root,grid2);
					conquer(r, x+1, y, s1.get(1), root,grid2);
					conquer(r, x, y-1, s1.get(2), root,grid2);
					try
					{
						if(checkWinner(r))
							o.callwinner(i);
					}
					catch(FileNotFoundException f)
					{

					}
					burst(r,x-1,y,i,root,grid2);
					burst(r,x+1,y,i,root,grid2);
					burst(r,x,y-1,i,root,grid2);
		        });
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
		        t1.setToX(xc-b.getWidth());
		        t1.setToY(yc);
		        t1.setNode(s1.get(0));
		        
		        TranslateTransition t3 =new TranslateTransition();
		        t3.setDuration(Duration.seconds(0.5));
		        t3.setToX(xc);
		        t3.setToY(yc-b.getHeight());
		        t3.setNode(s1.get(1));

		        TranslateTransition t4 =new TranslateTransition();
		        t4.setDuration(Duration.seconds(0.5));
		        t4.setToX(xc);
		        t4.setToY(yc+b.getHeight());
		        t4.setNode(s1.get(2));

		        ParallelTransition p1 = new ParallelTransition(t1, t3, t4);
		        p1.play();

		        root.getChildren().addAll(s1.get(0), s1.get(1), s1.get(2));
		        a[x][y]=0;
		        p1.setOnFinished(e -> {

					conquer(r, x-1, y, s1.get(0), root,grid2);
					conquer(r, x, y-1, s1.get(1), root,grid2);
					conquer(r, x, y+1, s1.get(2), root,grid2);
					try
					{
						if(checkWinner(r))
							o.callwinner(i);
					}
					catch(FileNotFoundException f)
					{

					}
					burst(r,x-1,y,i,root,grid2);
					burst(r,x,y-1,i,root,grid2);
					burst(r,x,y+1,i,root,grid2);
		        });
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
		        t1.setToX(xc-b.getWidth());
		        t1.setToY(yc);
		        t1.setNode(s1.get(0));

		        TranslateTransition t2 =new TranslateTransition();
		        t2.setDuration(Duration.seconds(0.5));
		        t2.setToX(xc+b.getWidth());
		        t2.setToY(yc);
		        t2.setNode(s1.get(1));

		        TranslateTransition t4 =new TranslateTransition();
		        t4.setDuration(Duration.seconds(0.5));
		        t4.setToX(xc);
		        t4.setToY(yc+b.getHeight());
		        t4.setNode(s1.get(2));

		        ParallelTransition p1 = new ParallelTransition(t1, t2, t4);
		        p1.play();

		        root.getChildren().addAll(s1.get(0), s1.get(1), s1.get(2));
		        a[x][y]=0;
		        p1.setOnFinished(e -> {
					conquer(r, x-1, y, s1.get(0), root,grid2);
					conquer(r, x+1, y, s1.get(1), root,grid2);
					conquer(r, x, y+1, s1.get(2), root,grid2);
					try
					{
						if(checkWinner(r))
							o.callwinner(i);
					}
					catch(FileNotFoundException f)
					{

					}
					burst(r,x-1,y,i,root,grid2);
					burst(r,x+1,y,i,root,grid2);
					burst(r,x,y+1,i,root,grid2);
		        });
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
		        t1.setToX(xc-b.getWidth());
		        t1.setToY(yc);
		        t1.setNode(s1.get(0));

		        TranslateTransition t2 =new TranslateTransition();
		        t2.setDuration(Duration.seconds(0.5));
		        t2.setToX(xc+b.getWidth());
		        t2.setToY(yc);
		        t2.setNode(s1.get(1));

		        TranslateTransition t3 =new TranslateTransition();
		        t3.setDuration(Duration.seconds(0.5));
		        t3.setToX(xc);
		        t3.setToY(yc-b.getHeight());
		        t3.setNode(s1.get(2));

		        TranslateTransition t4 =new TranslateTransition();
		        t4.setDuration(Duration.seconds(0.5));
		        t4.setToX(xc);
		        t4.setToY(yc+b.getHeight());
		        t4.setNode(s1.get(3));

		        ParallelTransition p1 = new ParallelTransition(t1, t3, t2, t4);
		        p1.play();

		        root.getChildren().addAll(s1.get(0), s1.get(1), s1.get(2), s1.get(3));
		        a[x][y]=0;
		        p1.setOnFinished(e -> {		        	
					conquer(r, x-1, y, s1.get(0), root,grid2);
					conquer(r, x+1, y, s1.get(1), root,grid2);
					conquer(r, x, y-1, s1.get(2), root,grid2);
					conquer(r, x, y+1, s1.get(3), root,grid2);
					try
					{
						if(checkWinner(r))
							o.callwinner(i);
					}
					catch(FileNotFoundException f)
					{

					}
					burst(r,x-1,y,i, root,grid2);
					burst(r,x+1,y,i, root,grid2);
					burst(r,x,y-1,i, root,grid2);
					burst(r,x,y+1,i, root,grid2);
		        });

			}
		}
	}
	/**
	 * This function is used by play() to get a sphere present in the requested Group and then use that sphere
	 * to ensure it has the same color as the color assigned to the Player making the move.
	 * @param s This is the Sphere to be searched for
	 * @param r This is the group in which we wish to know if there is Sphere or not.
	 * @return Sphere returns a sphere if there is any in that group
	 */
	public static Sphere find(Sphere s,Group r){
		for(Node n:r.getChildren()){
			if(n.getClass().getName().equals(s.getClass().getName())){
				return (Sphere)n;
			}
		}
		return null;
	}
	
	/**
	 * This is the most important function in which the grid of the requested size is created and the input made by 
	 * the Player are handled here i.e. adding a sphere, ensuring it has same color as the one that might be already 
	 * present in that cell of the grid, calling  burst() function, functionality of the undo button.
	 * @param
	 * @return Nothing
	 */
	public void play(int gx1, int gy1, int gx2, int gy2,  float sbs, float bbs, double rad, double rt,boolean axy) throws FileNotFoundException
	{
		Color ccc=p.get(turn).getColor();
		Group[][] r=new Group[sizex][sizey];
		for(int i=0;i<sizex;i++){
			for(int j=0;j<sizey;j++){
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
		un.setLayoutX(200.0f);
		un.setLayoutY(10.0f);


		Line line2 = new Line();
		line2.setStartX(270.0f);
		line2.setStartY(10.0f);
		line2.setEndX(270.0f);
		line2.setEndY(50.0f);
		line2.setStroke(Color.GRAY);

		ComboBox<String> option = new ComboBox<String>();
		option.getItems().addAll("New Game", "Exit");
		option.setLayoutX(280);
		option.setLayoutY(15);
		option.setStyle("-fx-background-image:url('2.png'); -fx-pref-width:50;" + String.format("-fx-font-size: %dpx;", (int)(0.35*40)));
		option.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue ov, String t1, String t2)
			{
				try
				{
					if(t2.equals("New Game"))
					{
						if(sizex == 6)
						{
							turn = 0;
							o.playGame("Normal Grid");
						}
						else
						{
							o.playGame("HD Grid");
						}
					}
					else
					{
						turn = 0;
						o.callmain(turn, p.get(turn).getColor());
					}
				}
				catch(FileNotFoundException f)
				{

				}
			}
		});

		Rectangle[][] grid1 = new Rectangle[sizex][sizey];
		Rectangle[][] grid2 = new Rectangle[sizex][sizey];
		Line[][] line = new Line[sizex+1][sizey+1];

		for(int i = 0; i < sizex; i++)
		{
			for(int j = 0; j < sizey; j++)
			{
				grid1[i][j] = new Rectangle(gx1 + i*sbs, gy1 + j*sbs, sbs, sbs);
				grid1[i][j].setFill(Color.TRANSPARENT);
				grid1[i][j].setStroke(ccc);

				grid2[i][j] = new Rectangle(gx2 + i*bbs, gy2 + j*bbs, bbs, bbs);
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

		for(int j = 0; j < sizey; j++)
		{
			line[sizex][j] = new Line();
			line[sizex][j].setStartX(grid1[sizex-1][j].getX() + sbs);
			line[sizex][j].setEndX(grid2[sizex-1][j].getX() + bbs);
			line[sizex][j].setStartY(grid1[sizex-1][j].getY() + sbs);
			line[sizex][j].setEndY(grid2[sizex-1][j].getY() + bbs);
			line[sizex][j].setStroke(ccc);
		}

		for(int i = 0; i < sizex; i++)
		{
			line[i][sizey] = new Line();
			line[i][sizey].setStartX(grid1[i][sizey-1].getX() + sbs);
			line[i][sizey].setEndX(grid2[i][sizey-1].getX() + bbs);
			line[i][sizey].setStartY(grid1[i][sizey-1].getY() + sbs);
			line[i][sizey].setEndY(grid2[i][sizey-1].getY() + bbs);
			line[i][sizey].setStroke(ccc);
		}

		line[sizex][sizey] = new Line();
		line[sizex][sizey].setStartX(grid1[sizex-1][0].getX() + sbs);
		line[sizex][sizey].setEndX(grid2[sizex-1][0].getX() + bbs);
		line[sizex][sizey].setStartY(grid1[sizex-1][0].getY());
		line[sizex][sizey].setEndY(grid2[sizex-1][0].getY());
		line[sizex][sizey].setStroke(ccc);

		Line extra = new Line();
		extra.setStartX(grid1[0][sizey-1].getX());
		extra.setEndX(grid2[0][sizey-1].getX());
		extra.setStartY(grid1[0][sizey-1].getY() + sbs);
		extra.setEndY(grid2[0][sizey-1].getY() + bbs);
		extra.setStroke(ccc);

		Group gridroot=new Group();
		Main.gamescene = new Scene(new Group(), 400, 650, Color.BLACK);
		Group root = (Group)Main.gamescene.getRoot();
			PhongMaterial material[] = new PhongMaterial[n];
		for(int i=0;i<n;i++){
			material[i] = new PhongMaterial(); 
        	material[i].setDiffuseColor(p.get(i).getColor()); 
        	material[i].setSpecularColor(p.get(i).getColor()); 
        	
		}
		if(axy){
			check(r,root,grid2,material,rad,a);
			axy=false;
		}

		
        
		for(int i=0;i<sizex;i++)
		{
			for(int j=0;j<sizey;j++){
				int xxx=i;
				int yyy=j;
				Bounds b=grid2[i][j].getBoundsInLocal();
				grid2[i][j].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
					@Override public void handle(MouseEvent event) {
					if(arr[turn] == 1)
					{
						double x=b.getMinX()+b.getWidth()/2;
	    				double y=b.getMinY()+b.getHeight()/2;
	    				int flag = 0;

	    				if(lol==0){
							try{
	              				BufferedWriter br=new BufferedWriter(new FileWriter(new File("abc.txt")) );
	              				for(int i=0;i<8;i++){
				            		br.write(Integer.toString(arr[i]));
				            		br.newLine();
				            	}
				            	br.write(Integer.toString(n));
				            	br.newLine();
				            	for(int i=0;i<n;i++){
				            		String s=p.get(i).getColor().toString();
				            		br.write(s);
				              		br.newLine();
				              	}
				              	br.write(Integer.toString(sizex));
				              	br.newLine();
				              	br.write(Integer.toString(sizey));
				              	br.newLine();
					            for(int i=0;i<sizex;i++){
					            	for(int j=0;j<sizey;j++){
					            		br.write(Integer.toString(0));
					            		br.newLine();
					            	}
					            }
					           	br.write(Integer.toString(turn));
					        	br.flush();
					        	br.close();
		              			lol++;
	              			}
	              			catch(IOException e){}
	              			lol++;
	              		}
	              		else if(lol!=0){
	              			try{
	              				BufferedWriter br=new BufferedWriter(new FileWriter(new File("abc.txt")) );
	              				for(int i=0;i<8;i++){
				            		br.write(Integer.toString(arr[i]));
				            		br.newLine();
				            	}
				            	br.write(Integer.toString(n));
				            	br.newLine();
				            	for(int i=0;i<n;i++){
				            		String s=p.get(i).getColor().toString();
				            		br.write(s);
				              		br.newLine();
				              	}
				              	br.write(Integer.toString(sizex));
				              	br.newLine();
				              	br.write(Integer.toString(sizey));
				              	br.newLine();
					            for(int i=0;i<sizex;i++){
					            	for(int j=0;j<sizey;j++){
					            		br.write(Integer.toString(a[i][j]));
					            		br.newLine();
					            	}
					            }
					           	br.write(Integer.toString(turn));
					        	br.flush();
					        	br.close();
		              			lol++;
	              			}
	              			catch(IOException e){}
		              			
	              		}
	              		PhongMaterial redMaterial=material[turn];
	              		//changeColor();

	              		int[][] temp = new int[sizex][sizey];
	              		for(int l = 0; l < sizey; l++)
	              			for(int m = 0; m < sizex; m++)
	              				temp[m][l] = a[m][l];
	              		backburst(temp, xxx, yyy, turn);

	              		if(a[xxx][yyy]%4==0){
	              			r[xxx][yyy]=new Group();
	              		}
	              		if(a[xxx][yyy]%4==0){
	              			Sphere s=new Sphere();
	              			s.setRadius(rad);
	              			s.setTranslateX(x);
	              			s.setTranslateY(y);
	              			s.setMaterial(redMaterial);
	              			r[xxx][yyy].getChildren().add(s);
	              			burst(r,xxx,yyy,turn,root,grid2);
	              			root.getChildren().remove(r[xxx][yyy]);
							root.getChildren().add(r[xxx][yyy]);
							r[xxx][yyy].toBack();
							turn=(turn+1)%n;
							un.setDisable(false);
	              		}
	              		else if(a[xxx][yyy]%4==1){
	              			Sphere v=find(new Sphere(),r[xxx][yyy]);

	              			if(v.getMaterial().equals(redMaterial)){
		              			Sphere s=new Sphere();
		              			s.setRadius(rad);
		              			s.setTranslateX(x+rt);
		              			s.setTranslateY(y);
		              			s.setMaterial(redMaterial);
		              			r[xxx][yyy].getChildren().add(s);
		              			flagcheck = 1;
		              			burst(r,xxx,yyy,turn,root,grid2);
		              			root.getChildren().remove(r[xxx][yyy]);
								root.getChildren().add(r[xxx][yyy]);
								r[xxx][yyy].toBack();
								turn=(turn+1)%n;
								un.setDisable(false);
							}
	              		}
	              		else if(a[xxx][yyy]%4==2){

	              			Sphere v=find(new Sphere(),r[xxx][yyy]);
	              			if(v.getMaterial().equals(redMaterial)){

	              				flag = 1;
		              			Sphere s=new Sphere();
		              			s.setRadius(rad);
		              			s.setTranslateX(x);
		              			s.setTranslateY(y+rt);
		              			s.setMaterial(redMaterial);
		              			r[xxx][yyy].getChildren().add(s);
		              			burst(r,xxx,yyy,turn,root,grid2);
		              			root.getChildren().remove(r[xxx][yyy]);
								root.getChildren().add(r[xxx][yyy]);
								r[xxx][yyy].toBack();
								turn=(turn+1)%n;
								un.setDisable(false);
							}
	              		}
	              		else if(a[xxx][yyy]%4==3){
	              			Sphere v=find(new Sphere(),r[xxx][yyy]);

	              			if(v.getMaterial().equals(redMaterial)){
	              				
		              			Sphere s=new Sphere();
		              			s.setRadius(rad);
		              			s.setTranslateX(x);
		              			s.setTranslateY(y+rt);
		              			s.setMaterial(redMaterial);
		              			r[xxx][yyy].getChildren().add(s);
		              			burst(r,xxx,yyy,turn,root,grid2);
		              			root.getChildren().remove(r[xxx][yyy]);
								root.getChildren().add(r[xxx][yyy]);
								r[xxx][yyy].toBack();
								turn=(turn+1)%n;
								un.setDisable(false);
							}
	              		}

	              		if(flagcheck == 1)
	              			arr = updateplayers(temp);
	              		while(arr[turn] != 1)
	              			turn = (turn + 1)%n;
	              		for(int k = 0; k < sizex; k++)
          				for(int l = 0; l < sizey; l++)
          				{
          					grid1[k][l].setStroke(p.get(turn).getColor());
          					grid2[k][l].setStroke(p.get(turn).getColor());
          					line[k][l].setStroke(p.get(turn).getColor());
          				}

	          			for(int k = 0; k < sizey; k++)
							line[sizex][k].setStroke(p.get(turn).getColor());
						for(int k = 0; k < sizex; k++)
							line[k][sizey].setStroke(p.get(turn).getColor());
						line[sizex][sizey].setStroke(p.get(turn).getColor());
						extra.setStroke(p.get(turn).getColor());
	              	}
	              	else
	              	{
	              		turn = (turn + 1)%n;
	              	}
	              	turn = turn % n;

              		RotateTransition rt=new RotateTransition(Duration.millis(1000),r[xxx][yyy]);
        			rt.setByAngle(360);
     				rt.setCycleCount(Timeline.INDEFINITE);
     				rt.setInterpolator(Interpolator.LINEAR);
     				rt.play();
				}
			});
			}
		}

		un.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){
				Main.recreate(r,root,grid2,material,rad);
				
				for(int k = 0; k < sizex; k++)
	              	for(int l = 0; l < sizey; l++){
	              		grid1[k][l].setStroke(p.get((turn)%n).getColor());
	            		grid2[k][l].setStroke(p.get((turn )%n).getColor());
	             		line[k][l].setStroke(p.get((turn)%n).getColor());
	             	}

	            for(int k = 0; k < sizey; k++)
					line[sizex][k].setStroke(p.get((turn)%n).getColor());
				for(int k = 0; k < sizex; k++)
					line[k][sizey].setStroke(p.get((turn )%n).getColor());
				line[sizex][sizey].setStroke(p.get((turn )%n).getColor());
				extra.setStroke(p.get((turn) % n).getColor());
				
				un.setDisable(true);
			}
		});

		gridroot.getChildren().addAll(bar12, extra, un, line2, giv, g1, option);
		for(int i = 0; i < sizex; i++)
			for(int j = 0; j < sizey; j++)
				gridroot.getChildren().addAll(grid1[i][j], grid2[i][j], line[i][j]);
		for(int i = 0 ; i < sizex+1; i++)
			gridroot.getChildren().add(line[i][sizey]);
		for(int i = 0; i < sizey; i++)
			gridroot.getChildren().add(line[sizex][i]);
		root.getChildren().add(gridroot);
	}
	
	/**
	 * This function is called when undo button is pressed from the recreate() function. This based on the data 
	 * provided by the beforeundo matrix creates the front end Grid
	 * @param r This a 2D array of Group representing each cell in the front end Grid
	 * @param root This is the main Group in which r and other components of the grid are present
	 * @param grid2 This is a 2D array of rectangles present in the grid that are needed to find the centre
	 * 		  of the cell in which the Sphere is to be added 
	 * @param m This is a 1D array of PhongMaterial the different materials that a sphere can have
	 * @param rad This is the radius of the sphere to be added in a cell of the grid.
	 * @param beforeundo This is a 2D Integer matrix of the back end grid
	 * @return Nothing
	 */
	public static void check(Group r[][],Group root,Rectangle grid2[][],PhongMaterial[] m,double rad,int beforeundo[][]){
		for(int i=0;i<sizex;i++){
			for(int j=0;j<sizey;j++){
				Bounds b=grid2[i][j].getBoundsInLocal();
				root.getChildren().remove(r[i][j]);
				if(beforeundo[i][j]==0){
					r[i][j].getChildren().clear();
				}
				else{
					double x=b.getMinX()+b.getWidth()/2;
    				double y=b.getMinY()+b.getHeight()/2;
					int a=beforeundo[i][j]/4;
					int number=beforeundo[i][j]%4;
					r[i][j].getChildren().clear();
					for(int k=0;k<number;k++){
						Sphere s=new Sphere();
	              		s.setRadius(rad);
	              		s.setMaterial(m[a]);
						if(k==0){
	              			s.setTranslateX(x);
	              			s.setTranslateY(y);
						}
						else if(k==1){
	              			s.setTranslateX(x+7.5);
	              			s.setTranslateY(y);
						}
						else if(k==2){
	              			s.setTranslateX(x);
	              			s.setTranslateY(y+7.5);
						}
						RotateTransition rt=new RotateTransition(Duration.millis(1000),r[i][j]);
        				rt.setByAngle(360);
	     				rt.setCycleCount(Timeline.INDEFINITE);
	     				rt.setInterpolator(Interpolator.LINEAR);
	     				rt.play();	
						r[i][j].getChildren().add(s);
						
					}
				}
				root.getChildren().add(r[i][j]);
				r[i][j].toBack();
			}
		}
	}
}

/** 
 * Main class has all the different pages as its members and here the grid is created based on the user's choices
 * and the input made by the user is placed in the grid by the play(). the rotation transistion of spheres is also 
 * done here.
 */
public class Main extends Application
{	
	/**
	 * The main stage 
	 */
	static Stage pstage;
	
	/**
	 * Scenes for main menu, play game, payer settings, winner page
	 */
	static Scene mainscene, playerscene, gamescene, playascene, winnerscene;

	/**
	 * static object of Settings class
	 */
	static Settings s;
	
	/**
	 * static object of Game class
	 */
	static Game g;

	/**
	 * This is just a list of all the 8 players and the color assigned to them
	 */
	public static ArrayList<Player> total;
	public static ArrayList<Color> c;

	/**
	 * This function is called to write the contents of the game's current status into the file resume.txt
	 * @param Nothing
	 * @return Nothing
	 */
	public static void write() {
		try{
            	BufferedWriter br=new BufferedWriter(new FileWriter(new File("resume.txt")) );
            	for(int i=0;i<8;i++){
            		br.write(Integer.toString(Game.arr[i]));
            		br.newLine();
            	}
            	br.write(Integer.toString(Game.n));
            	br.newLine();
            	for(int i=0;i<Game.n;i++){
            		String s = Game.p.get(i).getColor().toString();
            		br.write(s);
              		br.newLine();
              	}
              	br.write(Integer.toString(Game.sizex));
              	br.newLine();
              	br.write(Integer.toString(Game.sizey));
              	br.newLine();
	            for(int i=0;i<Game.sizex;i++){
	            	for(int j=0;j<Game.sizey;j++){
	            		br.write(Integer.toString(Game.a[i][j]));
	            		br.newLine();
	            	}
	            }
	           	br.write(Integer.toString((Game.turn+1)%Game.n));
	        	br.flush();
	        	br.close();
            }
        catch(IOException e){}
	}
	
	@Override
	public void start(Stage stage) throws FileNotFoundException
	{
		pstage = stage;
		String cgrid = "";
		Game.flagcheck = 0;

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
		player.setValue("2 Player Game");
		if(Game.p.size() == 0)
		{
			Game.p = new ArrayList<Player>();
			for(int i = 0; i < Game.n; i++)
				Game.p.add(i,total.get(i));
		}
		else if (Game.p.size() > 0 && Game.p.size() < Game.n)
		{
			for(int i = Game.p.size() - 1; i < Game.n; i++)
				Game.p.add(i,total.get(i));
		}
		else
		{
			for(int i = 0; i < Game.n; i++)
				Game.p.set(i,total.get(i));
		}
		player.valueProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue ov, String t1, String t2)
			{
				for(int i = 0; i < player.getItems().size(); i++)
				{
					if(ov.equals(player.getItems().get(i)))
					{
						player.setValue(player.getItems().get(i));
						break;
					}
				}
				if(ov.equals(t1))
					player.setValue(t1);
				else
					player.setValue(t2);
				Game.n = Integer.parseInt(player.getValue().substring(0,1));
				if(Game.p.size() == 0)
				{
					Game.p = new ArrayList<Player>(Game.n);
					for(int i = 0; i < Game.n; i++)
						Game.p.add(i,total.get(i));
				}
				else if (Game.p.size() > 0 && Game.p.size() < Game.n)
				{
					for(int i = Game.p.size(); i < Game.n; i++)
						Game.p.add(total.get(i));
				}
				else if(Game.p.size() > Game.n)
				{
					Game.p = new ArrayList<Player>(Game.n);
					for(int i = 0; i < Game.n; i++)
						Game.p.add(i,total.get(i));

				}
				else if(Game.p.size() == Game.n)
				{
					for(int i = 0; i < Game.n; i++)
						Game.p.set(i,total.get(i));
				}
			}
		});

		final ComboBox<String> game = new ComboBox<String>();
		game.getItems().addAll("Normal Grid", "HD Grid");
		game.setLayoutX(210);
		game.setLayoutY(110);
		game.setValue("Normal Grid");
		game.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue ov, String t1, String t2)
			{
				if(ov.equals(t1))
					game.setValue(t1);
				else
					game.setValue(t2);
			}
		});

		Button play = new Button("Play");
		play.setStyle("-fx-background-color:transparent;" + String.format("-fx-font-size: %dpx; -fx-text-fill:white;", (int)(0.65*40)));
		play.setMinSize(105, 60);
		play.setPrefSize(105, 60);
		play.setMaxSize(105, 60);
		play.setLayoutX(70.0f);
		play.setLayoutY(140.0f);
		play.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e)
			{
				try
				{
					File f1=new File("resume.txt");
					f1.createNewFile();
					if(game.getValue().equals("Normal Grid"))
						playGame("Normal Grid");
					else
						playGame("HD Grid");
				}
				catch(Exception f)
				{

				}
			}
		});

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
		resume.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e)
			{
				try{
					Scanner br=new Scanner(new File("resume.txt"));
					Game.arr=new int[8];
					for(int i=0;i<8;i++){
						Game.arr[i]=br.nextInt();
					}
					Game.n=br.nextInt();
					Game.p=new ArrayList<Player>();
					for(int i=0;i < Game.n;i++){
						Color c=Color.web(br.next());
						Player p0=new Player(c, i+1);
						Game.p.add(p0);
					}
					Game.sizex=br.nextInt();
					Game.sizey=br.nextInt();
					Game.a=new int[Game.sizex][Game.sizey];
					for(int i=0;i<Game.sizex;i++){
						for(int j=0;j<Game.sizey;j++){
							Game.a[i][j]=br.nextInt();
						}
					}
					Game.turn=br.nextInt();
					br.close();
					resumeGame();
					//System.out.println(p.size());
				}
		catch(IOException e1){}
			}
		});

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
		b1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e)
			{
				try
				{
					playerSettings();
				}
				catch(FileNotFoundException f)
				{

				}
			}
		});
		b1.setLayoutX(282.0f);
		b1.setLayoutY(620.0f);

		mainscene = new Scene(new Group(), 400, 650, Color.BLACK);
		Group mainroot = (Group)mainscene.getRoot();
		mainroot.getChildren().addAll(title, player, game, barr, content, content1, b1, iv, t1, play, line1, resume);

		content.wrappingWidthProperty().bind(mainscene.widthProperty().subtract(25));
		content1.wrappingWidthProperty().bind(mainscene.widthProperty().subtract(25));

		stage.setTitle("Chain Reaction");
		stage.setScene(mainscene);
		stage.show();
	}
	/**
	 * This function is called when the undo button is pressed, it reads the content from the file abc.txt where 
	 * the grid from the previous move have been maintained to update the back end grid and it then calls the check()
	 * function to make the same changes in the front end grid.
	 * @param r This a 2D array of Group representing each cell in the front end Grid
	 * @param root This is the main Group in which r and other components of the grid are present
	 * @param grid2 This is a 2D array of rectangles present in the grid that are needed to find the centre
	 * 		  of the cell in which the Sphere is to be added 
	 * @param m This is a 1D array of PhongMaterial the different materials that a sphere can have
	 * @param rad This is the radius of the sphere to be added in a cell of the grid.
	 * @return Nothing
	 */
	public static void recreate(Group r[][],Group root,Rectangle grid2[][],PhongMaterial[] m,double rad){
		int [][] beforeundo=new int [Game.sizex][Game.sizey];
		try{
			Scanner br=new Scanner(new File("abc.txt"));
			Game.arr=new int[8];
			for(int i=0;i<8;i++){
				Game.arr[i]=br.nextInt();
			}
					Game.n=br.nextInt();
					Game.p=new ArrayList<Player>();
					for(int i=0;i<Game.n;i++){
						Color c=Color.web(br.next());
						Player p0=new Player(c, i+1);
						Game.p.add(p0);
					}
					Game.sizex=br.nextInt();
					Game.sizey=br.nextInt();
					Game.a=new int[Game.sizex][Game.sizey];
					for(int i=0;i<Game.sizex;i++){
						for(int j=0;j<Game.sizey;j++){
							beforeundo[i][j]=br.nextInt();
						}
					}
					Game.turn=br.nextInt();
					br.close();
		}
		catch(IOException e){}
		Game.check(r,root,grid2,m,rad,beforeundo);
		Game.a=beforeundo;
	}
	
	/**
	 * This function is called when the player clicks on the PlayerSettings button to change the menu 
	 * by first calling playerSettingscall() then changing the scene.
	 * @param Nothing
	 * @return Nothing
	 */
	public void playerSettings() throws FileNotFoundException
	{
		s.playerSettingscall();
		pstage.setScene(playerscene);
	}
	
	/**
	 *  This function is called when the play button is pressed. This functions depending on the size requested,
	 *  calls play() function to create the front end grid
	 * @param s determines whether the grid is 9x6 or 15X10
	 * @return Nothing
	 */
	public void playGame(String s) throws FileNotFoundException
	{
		Game.arr = new int[8];
		for(int i = 0; i < Game.n; i++)
			Game.arr[i] = 1;
		if(s.equals("Normal Grid")){
			Game.sizex = 6;
			Game.sizey = 9;
			Game.a=new int[Game.sizex][Game.sizey];
			g.play(35, 100, 20, 80,55, 60, 15.0, 7.5,false);
		}
		else{
			Game.sizex = 10;
			Game.sizey = 15;
			Game.a=new int[Game.sizex][Game.sizey];
			g.play(25, 90, 16, 80, 35.45f, 37, 10.0, 2.5,false);
		}
		pstage.setScene(gamescene);
	}
	
	/**
	 * This method is called when in the player settings menu the Player selects whose color they want to 
	 * change. It further calls pcolor() where the color panel is displayed to the user.
	 * @param turn This tells us for which Player the color has to be changed
	 * @return Nothing
	 */
	public void playercolor(int turn) throws FileNotFoundException
	{
		Settings.pcolor(turn);
		Main.pstage.setScene(Main.playascene);
	}
	
	/** This function is called if checkwinner() returns true. It displays the player who has won the game
	 * and provides to either start a new game or return to the main menu.
	 * @param i This is the Player no. who has won
	 * @return Nothing
	 */
	public void callwinner(int i) throws FileNotFoundException
	{
		
		
		Image image = new Image(new FileInputStream("3.png"));
		ImageView iv = new ImageView(image);
		iv.setX(115);
		iv.setY(150);
		iv.setFitHeight(200);
		iv.setFitWidth(200);
		iv.setPreserveRatio(true);

		Image image1 = new Image(new FileInputStream("4.png"));
		ImageView iv1 = new ImageView(image1);
		iv1.setX(5);
		iv1.setY(5);
		iv1.setFitHeight(100);
		iv1.setFitWidth(390);

		Button play = new Button("New Game");
		play.setStyle("-fx-background-color:transparent; -fx-border-color:white;" + String.format("-fx-font-size: %dpx; -fx-text-fill:white;", (int)(0.45*40)));
		play.setMinSize(150, 60);
		play.setPrefSize(150, 60);
		play.setMaxSize(150, 60);
		play.setLayoutX(50.0f);
		play.setLayoutY(450.0f);
		play.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e)
			{
				try
				{
					if(Game.sizex == 6)
					{
						Game.turn = 0;
						playGame("Normal Grid");
					}
					else
					{
						playGame("HD Grid");
					}
				}
				catch(FileNotFoundException f)
				{

				}
			}
		});

		Button resume = new Button("Menu");
		resume.setStyle("-fx-background-color:transparent; -fx-border-color:white;" + String.format("-fx-font-size: %dpx; -fx-text-fill:white;", (int)(0.65*40)));
		resume.setMinSize(145, 60);
		resume.setPrefSize(145, 60);
		resume.setMaxSize(145, 60);
		resume.setLayoutX(210.0f);
		resume.setLayoutY(450.0f);
		resume.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e)
			{
				try
				{
					Game.turn = 0;
					callmain(Game.turn, Game.p.get(Game.turn).getColor());
				}
				catch(FileNotFoundException f)
				{

				}
			}
		});

		i++;
			File f=new File("resume.txt");
			f.delete();
		Text t2 = new Text("Player " + i + " Won!!!");
		t2.setX(80);
		t2.setY(400);
		t2.setFill(Color.WHITE);
		t2.setFont(Font.font("", FontWeight.NORMAL, FontPosture.REGULAR, 30));

		winnerscene = new Scene(new Group(), 400, 650, Color.BLACK);
		Group winnerroot = (Group)winnerscene.getRoot();
		winnerroot.getChildren().addAll(t2, iv, iv1, play,resume);
		pstage.setScene(winnerscene);
	}
	
	/**
	 * This function is called when the resume button is pressed to create a grid of the size that was
	 * saved from the last game that was played.
	 * @param Nothing
	 * @return Nothing
	 */
	public void resumeGame() throws FileNotFoundException
	{
		if(Game.sizex==6){
			g.play(35, 100, 20, 80,55, 60, 15.0, 7.5,true);
		}
		else{
			g.play(25, 90, 16, 80, 35.45f, 37, 10.0, 2.5,true);

		}
		pstage.setScene(gamescene);		
	}
	
	/**
	 * This functions is called when the Player clicks on Back to Menu button. 
	 * @param turn This is the player that is being added to the ArrayList total
	 * @param c Color for the Player that is being added to the list
	 * @return Nothing 
	 */
	public void callmain(int turn, Color c) throws FileNotFoundException
	{
		total.set(turn, new Player(c, total.get(turn).getTurn()));
		start(pstage);
	}
	
	/** 
	 * This is the main function where object of Main class is created and a few other components are initialized.
	 * @param args Unused
	 * @return Nothing 
	 */
	public static void main(String[] args) {
		Main o = new Main();
		s = new Settings(o);
		g = new Game(o);

		Game.p = new ArrayList<Player>();
		total = new ArrayList<Player>();
		total.add(new Player(Color.RED, 1));
		total.add(new Player(Color.GREEN, 2));
		total.add(new Player(Color.BLUE, 3));
		total.add(new Player(Color.CYAN, 4));
		total.add(new Player(Color.ORANGE, 5));
		total.add(new Player(Color.YELLOW, 6));
		total.add(new Player(Color.WHITE, 7));
		total.add(new Player(Color.DEEPPINK, 8));

		c = new ArrayList<Color>();
		c.add(Color.RED);
		c.add(Color.GREEN);
		c.add(Color.BLUE);
		c.add(Color.CYAN);
		c.add(Color.ORANGE);
		c.add(Color.YELLOW);
		c.add(Color.WHITE);
		c.add(Color.DEEPPINK);
		c.add(Color.CORAL);
		c.add(Color.PURPLE);
		c.add(Color.MOCCASIN);
		c.add(Color.DIMGRAY);
		c.add(Color.BROWN);
		c.add(Color.DARKBLUE);
		c.add(Color.LIME);

		Game.cvalue = new int[5][3];
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				Game.cvalue[i][j] = 1;

		Game.cvalue[2][2] = 0;
		launch(args);
	}
}