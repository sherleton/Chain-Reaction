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

class Player{
	private Color c;
	private int i;

	Player(Color c, int i)
	{
		this.c = c;
		this.i = i;
	}
	public Color getColor(){
		return c;
	}
	public int getTurn()
	{
		return i;
	}
	public void setColor(Color c){
		this.c=c;
	}
}

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
			Main.cvalue[x][y] = 1;
			Main.cvalue[xi][yi] = 0;
			o.colors[xi][yi].setDisable(false);
			o.colors[x][y].setDisable(true);
			o.callmain(turn, c);
		}
		catch(FileNotFoundException f)
		{

		}
	}
}

public class Main extends Application
{
	static Stage pstage;
	static Scene mainscene, playerscene, gamescene, playascene, winnerscene;

	/*Game Attributes*/

	public static ArrayList<Player> p;
	private static ArrayList<Player> total;
	public static ArrayList<Color> c;
	public static int turn=0;
	public static int n=2;
	public static int sizex=6;
	public static int sizey=9;
	public static int [][]a=new int[6][9];
	public static int lol=0;
	public Button[][] colors = new Button[5][3];
	public static int[][] cvalue;
	public static int flagcheck = 0;
	public static int[] arr = new int[8];
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

	public static void write() {
		try{
            	BufferedWriter br=new BufferedWriter(new FileWriter(new File("resume.txt")) );
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
	           	br.write(Integer.toString((turn+1)%n));
	        	br.flush();
	        	br.close();
            }
        catch(IOException e){}
	}
	public static void burst(Group r[][],int x,int y,int i, Group root,Rectangle[][] grid2){
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
		
		write();
        
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
					if(checkWinner(r))
						callwinner(i);
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
					if(checkWinner(r))
						callwinner(i);
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
					if(checkWinner(r))
						callwinner(i);
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
					if(checkWinner(r))
						callwinner(i);
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
					if(checkWinner(r))
						callwinner(i);
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
					if(checkWinner(r))
						callwinner(i);
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
					if(checkWinner(r))
						callwinner(i);
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
					if(checkWinner(r))
						callwinner(i);
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
					if(checkWinner(r))
						callwinner(i);
					burst(r,x-1,y,i, root,grid2);
					burst(r,x+1,y,i, root,grid2);
					burst(r,x,y-1,i, root,grid2);
					burst(r,x,y+1,i, root,grid2);
		        });

			}
		}
	}

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
		String cgrid = "";
		flagcheck = 0;

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
		if(p.size() == 0)
		{
			p = new ArrayList<Player>();
			for(int i = 0; i < n; i++)
				p.add(i,total.get(i));
		}
		else if (p.size() > 0 && p.size() < n)
		{
			for(int i = p.size() - 1; i < n; i++)
				p.add(i,total.get(i));
		}
		else
		{
			for(int i = 0; i < n; i++)
				p.set(i,total.get(i));
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
				n = Integer.parseInt(player.getValue().substring(0,1));
				if(p.size() == 0)
				{
					p = new ArrayList<Player>(n);
					for(int i = 0; i < n; i++)
						p.add(i,total.get(i));
				}
				else if (p.size() > 0 && p.size() < n)
				{
					for(int i = p.size(); i < n; i++)
						p.add(total.get(i));
				}
				else if(p.size() > n)
				{
					p = new ArrayList<Player>(n);
					for(int i = 0; i < n; i++)
						p.add(i,total.get(i));

				}
				else if(p.size() == n)
				{
					for(int i = 0; i < n; i++)
						p.set(i,total.get(i));
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
					arr=new int[8];
					for(int i=0;i<8;i++){
						arr[i]=br.nextInt();
					}
					n=br.nextInt();
					p=new ArrayList<Player>();
					for(int i=0;i<n;i++){
						Color c=Color.web(br.next());
						Player p0=new Player(c, i+1);
						p.add(p0);
					}
					sizex=br.nextInt();
					sizey=br.nextInt();
					a=new int[sizex][sizey];
					for(int i=0;i<sizex;i++){
						for(int j=0;j<sizey;j++){
							a[i][j]=br.nextInt();
						}
					}
					turn=br.nextInt();
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
		un.setLayoutX(240.0f);
		un.setLayoutY(10.0f);


		Line line2 = new Line();
		line2.setStartX(315.0f);
		line2.setStartY(10.0f);
		line2.setEndX(315.0f);
		line2.setEndY(50.0f);
		line2.setStroke(Color.GRAY);

		/*Image gimage1 = new Image(new FileInputStream("2.png"));
		ImageView giv1 = new ImageView(gimage1);
		giv1.setX(335);
		giv1.setY(20);
		giv1.setFitHeight(30);
		giv1.setFitWidth(30);
		giv1.setPreserveRatio(true);*/

		ComboBox<String> option = new ComboBox<String>();
		option.getItems().addAll("New Game", "Exit");
		option.setLayoutX(335);
		option.setLayoutY(20);
		option.setStyle("-fx-background-image:url('2.png');" + String.format("-fx-font-size: %dpx;", (int)(0.35*40)));
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
							playGame("Normal Grid");
						}
						else
						{
							playGame("HD Grid");
						}
					}
					else
					{
						turn = 0;
						callmain(turn, p.get(turn).getColor());
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
		gamescene = new Scene(new Group(), 400, 650, Color.BLACK);
		Group root = (Group)gamescene.getRoot();
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
				recreate(r,root,grid2,material,rad);
				
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

	public void pcolor(int turn) throws FileNotFoundException
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
				colors[i][j].setBackground(new Background(new BackgroundFill(c.get(j + 3*i),null, null)));
				colors[i][j].setOnAction(new ColorChange(turn, c.get(j + 3*i), this, i, j, total.get(turn).getColor()));
			}
		if(flag == 0)
		{	
			for(int i = 0; i < 3; i++)
				for(int j = 0; j < 3; j++)
					colors[i][j].setDisable(true);
		}

		colors[2][2].setDisable(false);

		playascene = new Scene(new Group(), 400, 650, Color.BLACK);
		Group playaroot = (Group)playascene.getRoot();
		playaroot.getChildren().addAll(iv, t1, line);
		for(int i = 0; i < 5; i++)
			for(int j = 0; j < 3; j++)
				playaroot.getChildren().add(colors[i][j]);
	}

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
					playercolor(0);
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
					playercolor(1);
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
					playercolor(2);
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
					playercolor(3);
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
					playercolor(4);
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
					playercolor(5);
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
					playercolor(6);
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
					playercolor(7);
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

		playerscene = new Scene(new Group(), 400, 650, Color.BLACK);
		Group playerroot = (Group)playerscene.getRoot();
		playerroot.getChildren().addAll(bar, p1, bar1, p2, bar2, p3, bar3, p4, bar4, p5, bar5, p6, bar6, p7, bar7, p8, bar8, t2, iv1);

	}

	public static void recreate(Group r[][],Group root,Rectangle grid2[][],PhongMaterial[] m,double rad){
		int [][] beforeundo=new int [sizex][sizey];
		try{
			Scanner br=new Scanner(new File("abc.txt"));
			arr=new int[8];
			for(int i=0;i<8;i++){
				arr[i]=br.nextInt();
			}
					n=br.nextInt();
					p=new ArrayList<Player>();
					for(int i=0;i<n;i++){
						Color c=Color.web(br.next());
						Player p0=new Player(c, i+1);
						p.add(p0);
					}
					sizex=br.nextInt();
					sizey=br.nextInt();
					a=new int[sizex][sizey];
					for(int i=0;i<sizex;i++){
						for(int j=0;j<sizey;j++){
							beforeundo[i][j]=br.nextInt();
						}
					}
					turn=br.nextInt();
					br.close();
		}
		catch(IOException e){}
		check(r,root,grid2,m,rad,beforeundo);
		a=beforeundo;
	}
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
	public void playerSettings() throws FileNotFoundException
	{
		playerSettingscall();
		pstage.setScene(playerscene);
	}

	public void playGame(String s) throws FileNotFoundException
	{
		arr = new int[8];
		for(int i = 0; i < n; i++)
			arr[i] = 1;
		if(s.equals("Normal Grid")){
			sizex = 6;
			sizey = 9;
			a=new int[sizex][sizey];
			play(35, 100, 20, 80,55, 60, 15.0, 7.5,false);
		}
		else{
			sizex = 10;
			sizey = 15;
			a=new int[sizex][sizey];
			play(25, 90, 16, 80, 35.45f, 37, 10.0, 2.5,false);
		}
		pstage.setScene(gamescene);
	}

	public static void callwinner(int i)
	{
		/*Image image = new Image(new FileInputStream("3.png"));
		ImageView iv = new ImageView(image);
		iv.setX(120);
		iv.setY(200);
		iv.setFitHeight(150);
		iv.setFitWidth(150);
		iv.setPreserveRatio(true);*/
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
		winnerroot.getChildren().addAll(t2);
		pstage.setScene(winnerscene);
	}

	public void resumeGame() throws FileNotFoundException
	{
		if(sizex==6){
			play(35, 100, 20, 80,55, 60, 15.0, 7.5,true);
		}
		else{
			play(25, 90, 16, 80, 35.45f, 37, 10.0, 2.5,false);

		}
		pstage.setScene(gamescene);

			
	}

	public void playercolor(int turn) throws FileNotFoundException
	{
		pcolor(turn);
		pstage.setScene(playascene);
	}

	public void callmain(int turn, Color c) throws FileNotFoundException
	{
		total.set(turn, new Player(c, total.get(turn).getTurn()));
		start(pstage);
	}

	public static void main(String[] args) {
		p = new ArrayList<Player>();
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

		cvalue = new int[5][3];
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				cvalue[i][j] = 1;

		cvalue[2][2] = 0;
		launch(args);
	}
}