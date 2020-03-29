package algoritmittehtavageneraattori.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import algoritmittehtavageneraattori.dao.FileUserDao;
import algoritmittehtavageneraattori.domain.AlgoritmitehtavageneraattoriService;
import java.io.FileInputStream;
import java.util.Properties;
import javafx.scene.control.PasswordField;

public class AlgoritmitTehtavaGeneraattoriUi extends Application {
    
    private Scene newUserScene;
    private Scene loginScene;
    private Scene mainScene;
    private AlgoritmitehtavageneraattoriService algoritmitehtavageneraattoriService;
    
    @Override
    public void init() throws Exception {
        Properties properties = new Properties();

        properties.load(new FileInputStream("config.properties"));
        
        String userFile = properties.getProperty("userFile");

        FileUserDao userDao = new FileUserDao(userFile);
        algoritmitehtavageneraattoriService = new AlgoritmitehtavageneraattoriService(userDao);
    }
    
    @Override
    public void start(Stage primaryStage) {
        // login scnece
        
        Label loginLabel = new Label("username:");
        Label passwordLabel = new Label("password:");
        TextField usernameInput = new TextField();
        PasswordField passwordInput = new PasswordField();
        Button loginButton = new Button("login");
        Button createButton = new Button("create new user");
        Label loginMessage = new Label();
        
        loginButton.setOnAction(event -> {
            String username = usernameInput.getText();
            String password = passwordInput.getText();
            if(algoritmitehtavageneraattoriService.login(username, password)){
                loginMessage.setText("");
                primaryStage.setScene(mainScene);
                usernameInput.setText("");
                passwordInput.setText("");
            } else {
                loginMessage.setText("invalid username");
                loginMessage.setTextFill(Color.RED);
            }
        });
        
        createButton.setOnAction(event -> {
            usernameInput.setText("");
            passwordInput.setText("");
            primaryStage.setScene(newUserScene);
        });
        
        BorderPane loginPane = new BorderPane();
        HBox usernameInputPane = new HBox(10);
        HBox passwordInputPane = new HBox(10);
        HBox buttonsPane = new HBox(10);
        VBox inputLoginPane = new VBox(10);
        
        usernameInputPane.getChildren().addAll(loginLabel, usernameInput);
        passwordInputPane.getChildren().addAll(passwordLabel, passwordInput);
        
        VBox.setMargin(usernameInputPane, new Insets(0,65,0,0));
        VBox.setMargin(passwordInputPane, new Insets(0,65,0,0));
        usernameInputPane.setAlignment(Pos.CENTER);
        passwordInputPane.setAlignment(Pos.CENTER);
        
        buttonsPane.getChildren().addAll(loginButton, createButton);
        buttonsPane.setAlignment(Pos.CENTER);
        
        inputLoginPane.getChildren().addAll(loginMessage, usernameInputPane, passwordInputPane, buttonsPane);
        inputLoginPane.setAlignment(Pos.CENTER);

        loginPane.setCenter(inputLoginPane);
        
        loginScene = new Scene(loginPane, 600, 400);
        
        //new createNewUserScene
        
        Label newUsernameLabel = new Label("username:");
        Label newPasswordLabel = new Label("password:");
        TextField newUsernameInput = new TextField();
        PasswordField newPasswordInput = new PasswordField();
        Button createNewUserButton = new Button("create");
        Button createNewUserLoginButton = new Button("back to login");
        Label createNewUserMessage = new Label();
        
        createNewUserButton.setOnAction(event -> {
            String newUsername = newUsernameInput.getText();
            String newPassword = newPasswordInput.getText();
            if(newUsername.length() < 3 || newPassword.length() < 8){
                createNewUserMessage.setText("invalid username or password length");
                createNewUserMessage.setTextFill(Color.RED);
            }else if(algoritmitehtavageneraattoriService.createUser(newUsername, newPassword)) {
                createNewUserMessage.setText("");
                newUsernameInput.setText("");
                newPasswordInput.setText("");
                loginMessage.setText("new user created");
                loginMessage.setTextFill(Color.GREEN);
                primaryStage.setScene(loginScene);
            } else {
                createNewUserMessage.setText("username has to be unique");
                createNewUserMessage.setTextFill(Color.RED);
            }
        });
        
        createNewUserLoginButton.setOnAction(event -> {
            newUsernameInput.setText("");
            createNewUserMessage.setText("");
            primaryStage.setScene(loginScene);
        });
        
        BorderPane newUserPane = new BorderPane();
        HBox newUsernameInputPane = new HBox(10);
        HBox newPasswordInputPane = new HBox(10);
        HBox newUsernameButtonsPane = new HBox(10);
        VBox newUsernamePane = new VBox(10);
        
        newUsernameInputPane.getChildren().addAll(newUsernameLabel, newUsernameInput);
        VBox.setMargin(newUsernameInputPane, new Insets(0,65,0,0));
        newUsernameInputPane.setAlignment(Pos.CENTER);
        
        newPasswordInputPane.getChildren().addAll(newPasswordLabel, newPasswordInput);
        VBox.setMargin(newPasswordInputPane, new Insets(0,65,0,0));
        newPasswordInputPane.setAlignment(Pos.CENTER);
        
        newUsernameButtonsPane.getChildren().addAll(createNewUserButton, createNewUserLoginButton);
        newUsernameButtonsPane.setAlignment(Pos.CENTER);
        
        newUsernamePane.getChildren().addAll(createNewUserMessage, newUsernameInputPane, newPasswordInputPane, newUsernameButtonsPane);
        newUsernamePane.setAlignment(Pos.CENTER);
        
        newUserPane.setCenter(newUsernamePane);
        
        newUserScene = new Scene(newUserPane, 600, 400);
        
        // main scene
        
        BorderPane mainPane = new BorderPane();
        HBox menuPane = new HBox(10);
        Label menuLabel = new Label("Welcome");
        Button logoutButton = new Button("logout");
        
        logoutButton.setOnAction(event -> {
            algoritmitehtavageneraattoriService.logout();
            primaryStage.setScene(loginScene);
        });
        
        menuPane.getChildren().addAll(logoutButton);
        
        mainPane.setTop(menuPane);
        mainPane.setCenter(menuLabel);
        
        mainScene = new Scene(mainPane, 600, 400);
        
        // setup primary stage
        
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
