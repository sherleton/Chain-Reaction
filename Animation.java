import javafx.util.Duration;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.shape.Sphere; 
public class Animation extends Application{

    @Override
    public void start(Stage stage) {
    	Pane canvas = new Pane();
    	Scene scene = new Scene(canvas, 300, 300);
        Sphere s1=new Sphere();
        s1.setRadius(20.0);
        s1.setTranslateX(200); 
        s1.setTranslateY(150);
        Sphere s2=new Sphere();
        s2.setRadius(20.0);
        s2.setTranslateX(220); 
        s2.setTranslateY(150);
        Sphere s3=new Sphere();
        s3.setRadius(20.0);
        s3.setTranslateX(210); 
        s3.setTranslateY(170); 
        Sphere s4=new Sphere();
        s4.setRadius(20.0);
        s4.setTranslateX(210); 
        s4.setTranslateY(130); 
        canvas.getChildren().add(s1);
        canvas.getChildren().add(s2);
        canvas.getChildren().add(s3);
        canvas.getChildren().add(s4);


        stage.setTitle("Moving Ball");
        stage.setScene(scene);
        stage.show();

        TranslateTransition t1 =new TranslateTransition();
        t1.setDuration(Duration.seconds(1));
        t1.setToX(170);
        t1.setToY(150);
        t1.setNode(s1);
        t1.setAutoReverse(true);
        t1.setCycleCount(2);
        t1.play();
        TranslateTransition t2 =new TranslateTransition();
        t2.setDuration(Duration.seconds(1));
        t2.setToX(250);
        t2.setToY(150);
        t2.setNode(s2);
        t2.setAutoReverse(true);
        t2.setCycleCount(2);
        t2.play();
        TranslateTransition t3 =new TranslateTransition();
        t3.setDuration(Duration.seconds(1));
        t3.setToX(210);
        t3.setToY(200);
        t3.setNode(s3);
        t3.setAutoReverse(true);
        t3.setCycleCount(2);
        t3.play();
        TranslateTransition t4 =new TranslateTransition();
        t4.setDuration(Duration.seconds(1));
        t4.setToX(210);
        t4.setToY(100);
        t4.setNode(s4);
        t4.setAutoReverse(true);
        t4.setCycleCount(2);
        t4.play();
    }

    public static void main(String[] args) {
        launch();
    }
}