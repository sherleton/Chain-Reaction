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
public class Game extends Application
{	
	public static ArrayList<Color> c0;
	public static int turn=0;
	public static int n=3;
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
	public static void burst(Group r[][],int x,int y,int i){
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
				a[x][y]=0;
				r[x+1][y].getChildren().add(new Sphere());
				burst(r,x+1,y,i);
				burst(r,x,y+1,i);			
			}
	
		}
		else if(corner2(x,y)){
			if(a[x][y]%4==2){
				while(!r[x][y].getChildren().isEmpty()){
					s1.add((Sphere)r[x][y].getChildren().get(0));
					r[x][y].getChildren().remove(0);
				}
				a[x][y]=0;
				burst(r,x+1,y,i);
				burst(r,x,y-1,i);				
			}
		}
		else if(corner3(x,y)){
			if(a[x][y]%4==2){
				while(!r[x][y].getChildren().isEmpty()){
					s1.add((Sphere)r[x][y].getChildren().get(0));
					r[x][y].getChildren().remove(0);
				}
				a[x][y]=0;
				burst(r,x-1,y,i);
				burst(r,x,y+1,i);			
			}
	
		}
		else if(corner4(x,y)){
			if(a[x][y]%4==2){
				while(!r[x][y].getChildren().isEmpty()){
					s1.add((Sphere)r[x][y].getChildren().get(0));
					r[x][y].getChildren().remove(0);
				}
				a[x][y]=0;
				burst(r,x-1,y,i);
				burst(r,x,y-1,i);
			}		
		}
		else if(edge1(x,y)){
			if(a[x][y]%4==3){
				while(!r[x][y].getChildren().isEmpty()){
					s1.add((Sphere)r[x][y].getChildren().get(0));
					r[x][y].getChildren().remove(0);
				}
				a[x][y]=0;
				burst(r,x,y-1,i);
				burst(r,x,y+1,i);
				burst(r,x+1,y,i);
			}
		}
		else if(edge2(x,y)){
			if(a[x][y]%4==3){
				while(!r[x][y].getChildren().isEmpty()){
					s1.add((Sphere)r[x][y].getChildren().get(0));
					r[x][y].getChildren().remove(0);
				}
				a[x][y]=0;
				burst(r,x-1,y,i);
				burst(r,x+1,y,i);
				burst(r,x+1,y-1,i);
			}
		}
		else if(edge3(x,y)){
			if(a[x][y]%4==3){
				while(!r[x][y].getChildren().isEmpty()){
					s1.add((Sphere)r[x][y].getChildren().get(0));
					r[x][y].getChildren().remove(0);
				}
				a[x][y]=0;
				burst(r,x,y-1,i);
				burst(r,x,y+1,i);
				burst(r,x-1,y,i);
			}
		}
		else if(edge4(x,y)){
			if(a[x][y]%4==3){
				while(!r[x][y].getChildren().isEmpty()){
					s1.add((Sphere)r[x][y].getChildren().get(0));
					r[x][y].getChildren().remove(0);
				}
				a[x][y]=0;
				burst(r,x-1,y,i);
				burst(r,x+1,y,i);
				burst(r,x,y+1,i);
			}
		}
		else{
			if(a[x][y]%4==0){
				while(!r[x][y].getChildren().isEmpty()){
					s1.add((Sphere)r[x][y].getChildren().get(0));
					r[x][y].getChildren().remove(0);
				}
				a[x][y]=0;
				burst(r,x-1,y,i);
				burst(r,x+1,y,i);
				burst(r,x,y-1,i);
				burst(r,x,y+1,i);
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

		Color ccc=c0.get(turn);
		Group[][] r=new Group[6][9];
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

		Line line1 = new Line();
		line1.setStartX(10.0f);
		line1.setStartY(65.0f);
		line1.setEndX(390.0f);
		line1.setEndY(65.0f);
		line1.setStroke(Color.CYAN);

		Button b1 = new Button("Undo");
		b1.setStyle("-fx-background-color:transparent;" + String.format("-fx-font-size: %dpx; -fx-text-fill:white;", (int)(0.45*40)));
		b1.setMinSize(75, 40);
		b1.setPrefSize(75, 40);
		b1.setMaxSize(75, 40);
		b1.setLayoutX(240.0f);
		b1.setLayoutY(10.0f);

		Line line2 = new Line();
		line2.setStartX(315.0f);
		line2.setStartY(10.0f);
		line2.setEndX(315.0f);
		line2.setEndY(50.0f);
		line2.setStroke(Color.GRAY);

		Image image1 = new Image(new FileInputStream("2.png"));
		ImageView iv1 = new ImageView(image1);
		iv1.setX(335);
		iv1.setY(20);
		iv1.setFitHeight(30);
		iv1.setFitWidth(30);
		iv1.setPreserveRatio(true);

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
		Group root=new Group();
		Group gridroot=new Group();
		PhongMaterial material[] = new PhongMaterial[n];
		for(int i=0;i<n;i++){
			material[i] = new PhongMaterial(); 
        	material[i].setDiffuseColor(c0.get(i)); 
        	material[i].setSpecularColor(c0.get(i)); 
        	
		}
		 
        
		for(int i=0;i<6;i++){
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
              			Sphere s=new Sphere();
              			s.setRadius(15.0);
              			s.setTranslateX(x);
              			s.setTranslateY(y);
              			s.setMaterial(redMaterial);
              			r[xxx][yyy].getChildren().add(s);
              			burst(r,xxx,yyy,turn);
              			root.getChildren().remove(r[xxx][yyy]);
						root.getChildren().add(r[xxx][yyy]);
						r[xxx][yyy].toBack();
						turn=(turn+1)%n;
              		}
              		else if(a[xxx][yyy]%4==1){
              			Sphere v=find(new Sphere(),r[xxx][yyy]);
              			if(v.getMaterial().equals(redMaterial)){
	              			Sphere s=new Sphere();
	              			s.setRadius(15.0);
	              			s.setTranslateX(x+7.5);
	              			s.setTranslateY(y);
	              			s.setMaterial(redMaterial);
	              			r[xxx][yyy].getChildren().add(s);
	              			burst(r,xxx,yyy,turn);
	              			root.getChildren().remove(r[xxx][yyy]);
							root.getChildren().add(r[xxx][yyy]);
							r[xxx][yyy].toBack();
							turn=(turn+1)%n;
						}
              		}
              		else if(a[xxx][yyy]%4==2){
              			Sphere v=find(new Sphere(),r[xxx][yyy]);
              			if(v.getMaterial().equals(redMaterial)){
	              			Sphere s=new Sphere();
	              			s.setRadius(15.0);
	              			s.setTranslateX(x);
	              			s.setTranslateY(y+7.5);
	              			s.setMaterial(redMaterial);
	              			r[xxx][yyy].getChildren().add(s);
	              			burst(r,xxx,yyy,turn);
	              			root.getChildren().remove(r[xxx][yyy]);
							root.getChildren().add(r[xxx][yyy]);
							r[xxx][yyy].toBack();
							turn=(turn+1)%n;
						}
              		}
              		else if(a[xxx][yyy]%4==3){
              			Sphere v=find(new Sphere(),r[xxx][yyy]);
              			if(v.getMaterial().equals(redMaterial)){
	              			Sphere s=new Sphere();
	              			s.setRadius(15.0);
	              			s.setTranslateX(x);
	              			s.setTranslateY(y+7.5);
	              			s.setMaterial(redMaterial);
	              			r[xxx][yyy].getChildren().add(s);
	              			burst(r,xxx,yyy,turn);
	              			root.getChildren().remove(r[xxx][yyy]);
							root.getChildren().add(r[xxx][yyy]);
							r[xxx][yyy].toBack();
							turn=(turn+1)%n;
						}
              		}
              		RotateTransition rt=new RotateTransition(Duration.millis(1000),r[xxx][yyy]);
        			rt.setByAngle(360);
     				rt.setCycleCount(Timeline.INDEFINITE);
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

		Scene scene = new Scene(root, 400, 650, Color.BLACK);
		gridroot.getChildren().addAll(line1, extra, b1, line2, iv, t1, iv1);
		for(int i = 0; i < 6; i++)
			for(int j = 0; j < 9; j++)
				gridroot.getChildren().addAll(grid1[i][j], grid2[i][j], line[i][j]);
		for(int i = 0 ; i < 7; i++)
			gridroot.getChildren().add(line[i][9]);
		for(int i = 0; i < 9; i++)
			gridroot.getChildren().add(line[6][i]);
		root.getChildren().add(gridroot);
		stage.setTitle("Chain Reaction");
		stage.setScene(scene);
		stage.show();

	}

	public static void main(String[] args) {
		c0=new ArrayList<Color>();
		c0.add(Color.RED);
		c0.add(Color.GREEN);
		c0.add(Color.YELLOW);
		launch(args);
	}
} 
