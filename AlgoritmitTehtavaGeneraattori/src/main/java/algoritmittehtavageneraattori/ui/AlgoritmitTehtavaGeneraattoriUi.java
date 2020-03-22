package algoritmittehtavageneraattori.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AlgoritmitTehtavaGeneraattoriUi extends Application {
    
    private Scene newUserScene;
    private Scene loginScene;
    
    @Override
    public void start(Stage primaryStage) {
        // login scnece
        
        Label loginLabel = new Label("username:");
        TextField usernameInput = new TextField();
        Button loginButton = new Button("login");
        Button createButton = new Button("create new user");
        Label loginMessage = new Label();
        
        loginButton.setOnAction(event -> {
            String username = usernameInput.getText();
            if(username.length() < 3){
                loginMessage.setText("invalid username");
                loginMessage.setTextFill(Color.RED);
            } else {
                System.out.println(username);
            }
        });
        
        createButton.setOnAction(event -> {
            usernameInput.setText("");
            primaryStage.setScene(newUserScene);
        });
        
        BorderPane loginPane = new BorderPane();
        HBox inputPane = new HBox(10);
        HBox buttonsPane = new HBox(10);
        VBox inputLoginPane = new VBox(10);
        
        inputPane.getChildren().addAll(loginLabel, usernameInput);
        VBox.setMargin(inputPane, new Insets(0,65,0,0));
        inputPane.setAlignment(Pos.CENTER);
        
        buttonsPane.getChildren().addAll(loginButton, createButton);
        buttonsPane.setAlignment(Pos.CENTER);
        
        inputLoginPane.getChildren().addAll(loginMessage, inputPane, buttonsPane);
        inputLoginPane.setAlignment(Pos.CENTER);

        loginPane.setCenter(inputLoginPane);
        
        loginScene = new Scene(loginPane, 600, 400);
        
        //new createNewUserPane
        
        Label newUsernameLabel = new Label("username:");
        TextField newUsernameInput = new TextField();
        Button createNewUserButton = new Button("create");
        Button createNewUserLoginButton = new Button("back to login");
        Label createNewUserMessage = new Label();
        
        createNewUserButton.setOnAction(event -> {
            String newUsername = newUsernameInput.getText();
            if(newUsername.length() < 3){
                createNewUserMessage.setText("invalid username length");
                createNewUserMessage.setTextFill(Color.RED);
            } else {
                System.out.println("create new user "+newUsername);
            }
        });
        
        createNewUserLoginButton.setOnAction(event -> {
            newUsernameInput.setText("");
            createNewUserMessage.setText("");
            primaryStage.setScene(loginScene);
        });
        
        BorderPane newUserPane = new BorderPane();
        HBox newUsernameInputPane = new HBox(10);
        HBox newUsernameButtonsPane = new HBox(10);
        VBox newUsernamePane = new VBox(10);
        
        newUsernameInputPane.getChildren().addAll(newUsernameLabel, newUsernameInput);
        VBox.setMargin(newUsernameInputPane, new Insets(0,65,0,0));
        newUsernameInputPane.setAlignment(Pos.CENTER);
        
        newUsernameButtonsPane.getChildren().addAll(createNewUserButton, createNewUserLoginButton);
        newUsernameButtonsPane.setAlignment(Pos.CENTER);
        
        newUsernamePane.getChildren().addAll(createNewUserMessage, newUsernameInputPane, newUsernameButtonsPane);
        newUsernamePane.setAlignment(Pos.CENTER);
        
        newUserPane.setCenter(newUsernamePane);
        
        newUserScene = new Scene(newUserPane, 600, 400);
        
        primaryStage.setTitle("AlgoritmitTehtavaGeneraattori");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }
    
    @Override
    public void stop() {
        System.out.print("App closing");
    }
    
    public static void main(String[] args){
        launch(args);
    }
    
}
