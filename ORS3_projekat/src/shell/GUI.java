package shell;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
public class GUI extends Application {
	
	private static TextArea part1=new TextArea();
	private static TextField part2=new TextField();
	
	
public static void main(String []args) {
	launch(args);
}
public void start(Stage primaryStage) throws FileNotFoundException {
	
	InputStream stream =new FileInputStream("src/picture.jpg");
	Image image=new Image(stream);
	ImageView iv=new ImageView();
	iv.setImage(image);
	iv.setX(0);
	iv.setY(0);
	iv.setFitWidth(1050);
	iv.setPreserveRatio(true);
	
	part1=new TextArea();
	part1.setMinWidth(880);
	part1.setMinHeight(450);
	part1.setLayoutX(80);
	part1.setLayoutY(50);
	part1.setEditable(false);
	part1.setText("  * * * WELCOME * * *  ");
	part1.setStyle("-fx-control-inner-background: #497d59; -fx-text-fill: #000000; ");
    part1.setFont(Font.font("Ariel", FontWeight.BOLD, 20));
    part1.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,null,null)));
    
	
	part2=new TextField();
	part2.setMinWidth(880);
	part2.setMinHeight(60);
	part2.setLayoutX(80);
	part2.setLayoutY(550);
	part2.setStyle("-fx-background-color: #497d59;  -fx-text-fill: #000000; ");
	part2.setFont(Font.font("Ariel", FontWeight.BOLD, 20));
	part2.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,null,null)));
	
	Group root=new Group();
	root.getChildren().addAll(iv,part1,part2);
	
	Scene scena=new Scene(root,1050,650);
	primaryStage.setScene(scena);
	primaryStage.setResizable(false);
	primaryStage.show();
	
}
}
