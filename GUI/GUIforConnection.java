import jssc.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.application.Application;
import javafx.scene.layout.*;
import javafx.event.*;
import javafx.scene.input.*;
public class GUIforConnection extends Application{
    private MakeConnection make;
    
    public GUIforConnection(){
        make = new MakeConnection();
    }
    
    public void start(Stage stage){
        make.connection();
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        
        Button btnUp = new Button("Up");
        Button btnDown = new Button("Down");
        Button btnRight = new Button("Right");
        Button btnLeft = new Button("Left");
        Button btnStop = new Button("Stop");
        Button btnOverride = new Button("Override");
        Button btnEight = new Button("Moeder");
        
        btnEight.setMaxWidth(100);
        btnEight.setMaxHeight(100);
        btnUp.setMaxWidth(100);
        btnUp.setMaxHeight(100);
        btnDown.setMaxWidth(100);
        btnDown.setMaxHeight(100);
        btnRight.setMaxWidth(100);
        btnRight.setMaxHeight(100);
        btnLeft.setMaxWidth(100);
        btnLeft.setMaxHeight(100);
        btnStop.setMaxHeight(100);
        btnStop.setMaxWidth(100);
        btnOverride.setMaxWidth(100);
        btnOverride.setMaxHeight(100);
        
        
        btnUp.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent upClicked){
                make.sendInt(16);
            }
        });
        btnDown.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent downClicked){
                make.sendInt(17);
            }
        });
        btnRight.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent rightClicked){
                make.sendInt(18);
            }
        });
        btnLeft.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent leftClicked){
                make.sendInt(19);
            }
        });
        btnStop.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent stopClicked){
                make.sendInt(20);
            }
        });
        btnOverride.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent overrideClicked){
                make.sendInt(21);
            }
        });        
        btnEight.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent overrideClicked){
                make.sendInt(7);
            }
        });
        grid.add(btnUp, 1, 0);
        grid.add(btnDown, 1, 2);
        grid.add(btnRight, 2, 1);
        grid.add(btnLeft, 0, 1);
        grid.add(btnStop, 1, 1);
        grid.add(btnEight, 3, 2);
        grid.add(btnOverride, 3, 3);
        
        Scene scene = new Scene(grid, 200, 200);
        stage.setTitle("Gui");
        stage.setScene(scene);
        stage.show();
    }
    
    public void main(String[] args){
        launch(args);
    }
}