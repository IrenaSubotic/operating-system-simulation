package shell;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

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
	
	private static String textToShow;
	
	private static TextArea part1=new TextArea();
	private static TextField part2=new TextField();
	
	private PipedInputStream input= new PipedInputStream();
	private PipedOutputStream output= new PipedOutputStream();
	
	private static StringBuilder outStringBuilder=new StringBuilder();
	
	private OutputStream outputStream;
	private int length1=0;
	
public static void main(String []args) {
	Shell.boot();
	launch(args);
	
}
public static void clearTerminal() {
	
	part1.setText("");
	part2.clear();
	
}

public static void addTextToPart1() {
	
	if(outStringBuilder.length()>0) {
		part1.appendText(outStringBuilder.toString());
		outStringBuilder=new StringBuilder();
	}
}


public void start(Stage primaryStage) throws IOException {
	
	input.connect(output);
	textToShow="";
	
	InputStream stream =new FileInputStream("src/picture.jpg");
	Image image=new Image(stream);
	 ImageView iv=new ImageView();
	 iv.setImage(image);
	 iv.setX(0);
	 iv.setY(0);
	 iv.setFitWidth(1090);
	 iv.setFitHeight(680);
	 iv.setPreserveRatio(true);
	
	
	part1=new TextArea();
	part1.setMinWidth(955);
	part1.setMinHeight(480);
	part1.setLayoutX(55);
	part1.setLayoutY(50);
	part1.setEditable(false);
	part1.setText("  * * * WELCOME * * *  \n");
	part1.setStyle("-fx-control-inner-background: #497d59; -fx-text-fill: #FFFFFF; ");
    part1.setFont(Font.font("Ariel", FontWeight.BOLD, 19));
    part1.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,null,null)));
    
	
	part2=new TextField();
	part2.setMinWidth(955);
	part2.setMinHeight(60);
	part2.setLayoutX(55);
	part2.setLayoutY(570);
	part2.setStyle("-fx-background-color: #497d59;  -fx-text-fill: #FFFFFF; ");
	part2.setFont(Font.font("Ariel", FontWeight.BOLD, 19));
	part2.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,null,null)));
	
	part2.setOnAction(e -> {
		
		try {
			
			byte [] array=part2.getText().getBytes();
			output.write(array);
			length1=array.length;
			
			Commands.readCommand(input, length1);
			
			textToShow=textToShow+">" + part2.getText() + "\n";
			part1.appendText(textToShow);
			Commands.getCommand();
            part2.clear();
            textToShow="";
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	});
	
	 outputStream=new OutputStream() {
		@Override
		public void write(int b) throws IOException{
			outStringBuilder.append((char)b);
			if(((char)b)=='\n')
				addTextToPart1();
		}
	};
	Commands.setOut(outputStream);
	
	
	Group root=new Group();
	root.getChildren().addAll(iv,part1,part2);
	
	Scene scena=new Scene(root,1060,675);
	primaryStage.setScene(scena);
	primaryStage.setResizable(false);
	primaryStage.show();
	
}

}
