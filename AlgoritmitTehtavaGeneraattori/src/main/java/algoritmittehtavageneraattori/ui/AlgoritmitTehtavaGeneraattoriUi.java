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
import algoritmittehtavageneraattori.dao.FileTaskDao;
import algoritmittehtavageneraattori.domain.AlgoritmitehtavageneraattoriService;
import algoritmittehtavageneraattori.domain.Task;
import java.io.FileInputStream;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

public class AlgoritmitTehtavaGeneraattoriUi extends Application {
    
    private Scene newUserScene;
    private Scene loginScene;
    private Scene mainScene;
    private Scene taskScene;
    private Scene newTaskScene;
    private Label menuLabel = new Label();
    private GridPane tasksGridPane;
    private int taskToSolveId;
    private TableView tableView;
    private Task selectedTask;
    private ObservableList<Task> selectedTaskList;
    private Label singleTaskSolveMessage;
    private AlgoritmitehtavageneraattoriService algoritmitehtavageneraattoriService;
    
    @Override
    public void init() throws Exception {
        Properties properties = new Properties();

        properties.load(new FileInputStream("config.properties"));
        
        String userFile = properties.getProperty("userFile");
        String taskFile = properties.getProperty("taskFile");

        FileUserDao userDao = new FileUserDao(userFile);
        FileTaskDao taskDao = new FileTaskDao(taskFile);
        algoritmitehtavageneraattoriService = new AlgoritmitehtavageneraattoriService(userDao, taskDao);
    }
    
    public void redrawTasklist() {
        tableView.getItems().clear();
        List<Task> tasks = algoritmitehtavageneraattoriService.getTasks();
        Collections.sort(tasks);
        tasks.forEach((task) -> {
            tableView.getItems().add(task);
        });
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
            if (algoritmitehtavageneraattoriService.login(username, password)) {
                loginMessage.setText("");
                menuLabel.setText("Welcome " + username);
                primaryStage.setScene(mainScene);
                usernameInput.setText("");
                passwordInput.setText("");
                redrawTasklist();
            } else {
                loginMessage.setText("invalid username");
                loginMessage.setTextFill(Color.RED);
            }
        });
        
        createButton.setOnAction(event -> {
            usernameInput.setText("");
            passwordInput.setText("");
            loginMessage.setText("");
            primaryStage.setScene(newUserScene);
        });
        
        BorderPane loginPane = new BorderPane();
        HBox usernameInputPane = new HBox(10);
        HBox passwordInputPane = new HBox(10);
        HBox buttonsPane = new HBox(10);
        VBox inputLoginPane = new VBox(10);
        
        usernameInputPane.getChildren().addAll(loginLabel, usernameInput);
        passwordInputPane.getChildren().addAll(passwordLabel, passwordInput);
        
        VBox.setMargin(usernameInputPane, new Insets(0, 65, 0, 0));
        VBox.setMargin(passwordInputPane, new Insets(0, 65, 0, 0));
        usernameInputPane.setAlignment(Pos.CENTER);
        passwordInputPane.setAlignment(Pos.CENTER);
        
        buttonsPane.getChildren().addAll(loginButton, createButton);
        buttonsPane.setAlignment(Pos.CENTER);
        
        inputLoginPane.getChildren().addAll(loginMessage, usernameInputPane, passwordInputPane, buttonsPane);
        inputLoginPane.setAlignment(Pos.CENTER);

        loginPane.setCenter(inputLoginPane);
        
        loginScene = new Scene(loginPane, 400, 200);
        
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
            if (newUsername.length() < 3 || newPassword.length() < 8) {
                createNewUserMessage.setText("invalid username or password length");
                createNewUserMessage.setTextFill(Color.RED);
            } else if (algoritmitehtavageneraattoriService.createUser(newUsername, newPassword)) {
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
        VBox.setMargin(newUsernameInputPane, new Insets(0, 65, 0, 0));
        newUsernameInputPane.setAlignment(Pos.CENTER);
        
        newPasswordInputPane.getChildren().addAll(newPasswordLabel, newPasswordInput);
        VBox.setMargin(newPasswordInputPane, new Insets(0, 65, 0, 0));
        newPasswordInputPane.setAlignment(Pos.CENTER);
        
        newUsernameButtonsPane.getChildren().addAll(createNewUserButton, createNewUserLoginButton);
        newUsernameButtonsPane.setAlignment(Pos.CENTER);
        
        newUsernamePane.getChildren().addAll(createNewUserMessage, newUsernameInputPane, newPasswordInputPane, newUsernameButtonsPane);
        newUsernamePane.setAlignment(Pos.CENTER);
        
        newUserPane.setCenter(newUsernamePane);
        
        newUserScene = new Scene(newUserPane, 400, 200);
        
        // new task scene
        
        GridPane newTaskInputPane = new GridPane();
        newTaskInputPane.setAlignment(Pos.CENTER);
        Label newTaskMessage = new Label();
        
        Label newTaskTitleLabel = new Label("Title:");
        TextField newTaskTitleInput = new TextField();
        newTaskTitleInput.setStyle("-fx-font-size: 40px");
        newTaskInputPane.add(newTaskTitleLabel, 0, 0);
        newTaskInputPane.add(newTaskTitleInput, 1, 0);
        
        Label newTaskDescriptionLabel = new Label("Description:");
        TextArea newTaskDescriptionInput = new TextArea();
        newTaskDescriptionInput.setWrapText(true);
        newTaskDescriptionInput.setStyle("-fx-font-size: 20px");
        newTaskDescriptionInput.setPrefWidth(700);
        newTaskDescriptionInput.setPrefHeight(300);
        newTaskInputPane.add(newTaskDescriptionLabel, 0, 1);
        newTaskInputPane.add(newTaskDescriptionInput, 1, 1);
        
        Label newTaskInputLabel = new Label("Input:");
        TextField newTaskInputInput = new TextField();
        newTaskInputPane.add(newTaskInputLabel, 0, 2);
        newTaskInputPane.add(newTaskInputInput, 1, 2);
        
        Label newTaskResultLabel = new Label("Result:");
        TextField newTaskResultInput = new TextField();
        newTaskInputPane.add(newTaskResultLabel, 0, 3);
        newTaskInputPane.add(newTaskResultInput, 1, 3);
        
        Label newTaskDifficultyLabel = new Label("Difficulty:");
        TextField newTaskDifficultyInput = new TextField();
        newTaskInputPane.add(newTaskDifficultyLabel, 0, 4);
        newTaskInputPane.add(newTaskDifficultyInput, 1, 4);
        
        Label newTaskGategoryIdLabel = new Label("Gategory:");
        TextField newTaskGategoryIdInput = new TextField();
        newTaskInputPane.add(newTaskGategoryIdLabel, 0, 5);
        newTaskInputPane.add(newTaskGategoryIdInput, 1, 5);
        
        Button createNewTask = new Button("create");
        newTaskInputPane.add(createNewTask, 1, 6);
        
        VBox newTaskPane = new VBox(10);
        newTaskPane.setAlignment(Pos.CENTER);
        
        newTaskPane.getChildren().addAll(newTaskMessage, newTaskInputPane);
        
        createNewTask.setOnAction(event -> {
            String title = newTaskTitleInput.getText();
            String description = newTaskDescriptionInput.getText();
            description = description.replaceAll("\\t", " ");
            description = description.replaceAll("\\n", " ");
            String result = newTaskResultInput.getText();
            int difficulty = Integer.valueOf(newTaskDifficultyInput.getText());
            int gategoryId = Integer.valueOf(newTaskGategoryIdInput.getText());
            String input = newTaskInputInput.getText().trim();
            if (title.length() < 5 || description.length() < 10) {
                newTaskMessage.setText("invalid title or description");
                newTaskMessage.setTextFill(Color.RED);
            } else {
                algoritmitehtavageneraattoriService.createTask(title, description, result, difficulty, gategoryId, input);
                newTaskMessage.setText("new task created");
                newTaskMessage.setTextFill(Color.GREEN);
                newTaskTitleInput.setText("");
                newTaskDescriptionInput.setText("");
                newTaskInputInput.setText("");
                newTaskResultInput.setText("");
                newTaskDifficultyInput.setText("");
                newTaskGategoryIdInput.setText("");
                redrawTasklist();
            }
        });
        
        newTaskScene = new Scene(newTaskPane, 1200, 800);
        
        // single task scene
        
        BorderPane singleTaskPane = new BorderPane();
        VBox singleTaskMiddlePane = new VBox(10);
        HBox singleTaskResultPane = new HBox(10);
        singleTaskSolveMessage = new Label();

        Label singleTaskTitleLabel = new Label();
        singleTaskTitleLabel.setStyle("-fx-font-size: 40px");
        Label singleTaskDesciptionLabel = new Label();
        singleTaskDesciptionLabel.setMaxWidth(700);
        singleTaskDesciptionLabel.setWrapText(true);
        singleTaskDesciptionLabel.setStyle("-fx-font-size: 20px");
        
        TextField singleTaskUserInput = new TextField();
        singleTaskUserInput.setMaxWidth(100);
        
        Button singleTaskSolveButton = new Button("Solve");
        
        singleTaskSolveButton.setOnAction(event -> {
            if (singleTaskUserInput.getText().equals(selectedTask.getResult())) {
                selectedTask.setDone();
                singleTaskSolveMessage.setText("Correct answer");
                singleTaskSolveMessage.setTextFill(Color.GREEN);
                redrawTasklist();
            } else {
                singleTaskSolveMessage.setText("Wrong answer");
                singleTaskSolveMessage.setTextFill(Color.RED);
                singleTaskUserInput.setText("");
            }
        });
        
        singleTaskResultPane.getChildren().addAll(singleTaskUserInput, singleTaskSolveButton);
   
        Label singleTaskTestLabel = new Label("TESTIT \nTÄNNE");
        
        singleTaskMiddlePane.getChildren().addAll(singleTaskDesciptionLabel, singleTaskSolveMessage, singleTaskResultPane);
        VBox.setMargin(singleTaskDesciptionLabel, new Insets(0, 20, 0, 20));
        VBox.setMargin(singleTaskSolveMessage, new Insets(0, 20, 0, 20));
        VBox.setMargin(singleTaskResultPane, new Insets(0, 20, 0, 20));
        
        singleTaskPane.setTop(singleTaskTitleLabel);
        singleTaskPane.setCenter(singleTaskMiddlePane);
        BorderPane.setAlignment(singleTaskTitleLabel, Pos.TOP_CENTER);
        singleTaskPane.setBottom(singleTaskTestLabel);
        
        // main scene
        
        BorderPane mainPane = new BorderPane();
        HBox menuPane = new HBox(10);
        Button logoutButton = new Button("logout");
        Button newTaskButton = new Button("new task");
        Button mainButton = new Button("main");
        Button tasksButton = new Button("tasks");
        tasksGridPane = new GridPane();
        tasksGridPane.setAlignment(Pos.CENTER);
        menuLabel.setAlignment(Pos.CENTER);
        
        tableView = new TableView();
        TableColumn<String, Task> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<String, Task> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        TableColumn<String, Task> gategoryColumn = new TableColumn<>("Gategory");
        gategoryColumn.setCellValueFactory(new PropertyValueFactory<>("gategoryId"));
        TableColumn<String, Task> difficultyColumn = new TableColumn<>("Difficulty");
        difficultyColumn.setCellValueFactory(new PropertyValueFactory<>("difficulty"));
        TableColumn<String, Task> doneColumn = new TableColumn<>("Done");
        doneColumn.setCellValueFactory(new PropertyValueFactory<>("done"));
        tableView.getColumns().add(idColumn);
        tableView.getColumns().add(titleColumn);
        tableView.getColumns().add(gategoryColumn);
        tableView.getColumns().add(difficultyColumn);
        tableView.getColumns().add(doneColumn);
        
        TableViewSelectionModel selectionModel = tableView.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);
        selectedTaskList = selectionModel.getSelectedItems();
        selectedTaskList.addListener(new ListChangeListener<Task>() {
            @Override
            public void onChanged(Change<? extends Task> change) {
                if (!selectedTaskList.isEmpty()) { 
                    selectedTask = selectedTaskList.get(0);
                    singleTaskTitleLabel.setText(selectedTask.getTitle());
                    singleTaskDesciptionLabel.setText(selectedTask.getDescription());
                    mainPane.setCenter(singleTaskPane);
                }
            }
        });
        
        logoutButton.setOnAction(event -> {
            algoritmitehtavageneraattoriService.logout();
            primaryStage.setScene(loginScene);
        });
        
        newTaskButton.setOnAction(event -> {
            mainPane.setCenter(newTaskPane);
            singleTaskSolveMessage.setText("");
        });
        
        mainButton.setOnAction(event -> {
            selectionModel.clearSelection();
            mainPane.setCenter(menuLabel);
            newTaskMessage.setText("");
            singleTaskSolveMessage.setText("");
        });
        
        tasksButton.setOnAction(event -> {
            redrawTasklist();
            //mainPane.setCenter(tasksGridPane);
            //mainPane.setLeft(tableView);
        });
        
        menuPane.getChildren().addAll(mainButton, tasksButton, newTaskButton, logoutButton);
        menuPane.setAlignment(Pos.TOP_RIGHT);
        
        //mainPane.setLeft(taskScrollPane);
        mainPane.setTop(menuPane);
        mainPane.setCenter(menuLabel);
        mainPane.setLeft(tableView);
        
        mainScene = new Scene(mainPane, 1200, 800);
        
        // setup primary stage
        
        primaryStage.setTitle("AlgoritmitTehtavaGeneraattori");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }
    
    @Override
    public void stop() {
        System.out.print("App closing");
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
