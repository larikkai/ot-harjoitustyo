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
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.Properties;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

public class AlgoritmitTehtavaGeneraattoriUi extends Application {
    
    private Scene newUserScene;
    private Scene loginScene;
    private Scene mainScene;
    private Label menuLabel;
    private Label menuUserLabel = new Label();
    private Label singleTaskSolveMessage;
    private Label singleTaskTitleLabel;
    private Label singleTaskDesciptionLabel;
    private Label singleTaskInputLabel;
    private TableView<Task> tableView;
    private Task selectedTask;
    private ObservableList<Task> selectedTaskList;
    private FilteredList<Task> filteredData;
    private AlgoritmitehtavageneraattoriService algoritmitehtavageneraattoriService;
    
    @Override
    public void init() throws Exception {
        Properties properties = new Properties();

        properties.load(new FileInputStream("config.properties"));
        
        String userFile = properties.getProperty("userFile");
        String taskFile = properties.getProperty("taskFile");

        FileUserDao userDao = new FileUserDao(userFile);
        FileTaskDao taskDao = new FileTaskDao(taskFile, userDao);
        algoritmitehtavageneraattoriService = new AlgoritmitehtavageneraattoriService(userDao, taskDao);
    }
    
    @Override
    public void start(Stage primaryStage) {
        loginScene = createLoginScene(primaryStage);
        newUserScene = createNewUserScene(primaryStage);
        mainScene = createMainScene(primaryStage);
        
        primaryStage.setTitle("AlgoritmitTehtavaGeneraattori");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }
    
    private Scene createLoginScene(Stage primaryStage) {
        Label loginMessage = new Label();
        Label loginLabel = new Label("username:");
        Label passwordLabel = new Label("password:");
        TextField usernameInput = new TextField();
        PasswordField passwordInput = new PasswordField();
        
        Button loginButton = createLoginButton(usernameInput, passwordInput, loginMessage, primaryStage);
        Button createButton = createNewUserButton(usernameInput, passwordInput, loginMessage, primaryStage);
        
        menuLabel = new Label();
        menuUserLabel = new Label();
        
        BorderPane loginPane = new BorderPane();
        
        HBox usernameInputPane = createUsernameInputPane(loginLabel, usernameInput);
        
        HBox passwordInputPane = createPasswordInputPane(passwordLabel, passwordInput);
        
        HBox buttonsPane = createButtonsPane(loginButton, createButton);
        
        VBox inputLoginPane = createInputLoginPane(usernameInputPane, passwordInputPane, loginMessage, buttonsPane);
        
        loginPane.setCenter(inputLoginPane);
        Scene scene = new Scene(loginPane, 400, 200);
        return scene;
    }
    
    private HBox createUsernameInputPane(Label loginLabel, TextField usernameInput) {
        HBox usernamePane = new HBox(10);
        usernamePane.getChildren().addAll(loginLabel, usernameInput);
        usernamePane.setAlignment(Pos.CENTER);
        return usernamePane;
    }
    
    private HBox createPasswordInputPane(Label passwordLabel, TextField passwordInput) {
        HBox passwordPane = new HBox(10);
        passwordPane.getChildren().addAll(passwordLabel, passwordInput);
        passwordPane.setAlignment(Pos.CENTER);
        return passwordPane;
    }
    
    private HBox createButtonsPane(Button loginButton, Button createButton) {
        HBox buttonsPane = new HBox(10);
        buttonsPane.getChildren().addAll(loginButton, createButton);
        buttonsPane.setAlignment(Pos.CENTER); 
        return buttonsPane;
    }
    
    private VBox createInputLoginPane(HBox usernameInputPane, HBox passwordInputPane, Label loginMessage, HBox buttonsPane) {
        VBox loginPane = new VBox(10);
        VBox.setMargin(usernameInputPane, new Insets(0, 65, 0, 0));  
        VBox.setMargin(passwordInputPane, new Insets(0, 65, 0, 0));      
        loginPane.getChildren().addAll(loginMessage, usernameInputPane, passwordInputPane, buttonsPane);
        loginPane.setAlignment(Pos.CENTER);
        return loginPane;
    }
    
    private Button createLoginButton(TextField usernameInput, TextField passwordInput, Label loginMessage, Stage primaryStage) {
        Button button = new Button("login");
        button.setOnAction(event -> {
            String username = usernameInput.getText();
            String password = passwordInput.getText();
            if (algoritmitehtavageneraattoriService.login(username, password)) {
                loginMessage.setText("");
                menuLabel.setText("Welcome " + username);
                menuLabel.setStyle("-fx-font-size: 20px");
                primaryStage.setScene(mainScene);
                usernameInput.setText("");
                passwordInput.setText("");
                update();
            } else {
                loginMessage.setText("invalid username or password");
                loginMessage.setTextFill(Color.RED);
            }
        });
        return button;
    }
    
    private Button createNewUserButton(TextField usernameInput, TextField passwordInput, Label loginMessage, Stage primaryStage) {
        Button button = new Button("create new user");
        button.setOnAction(event -> {
            usernameInput.setText("");
            passwordInput.setText("");
            loginMessage.setText("");
            primaryStage.setScene(newUserScene);
        });
        return button;
    }

    private Scene createNewUserScene(Stage primaryStage) {
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
            if (newUsername.length() < 3 || newUsername.length() > 10 || newPassword.length() < 8) {
                createNewUserMessage.setText("invalid username or password length");
                createNewUserMessage.setTextFill(Color.RED);
            } else if (algoritmitehtavageneraattoriService.createUser(newUsername, newPassword)) {
                createNewUserMessage.setText("");
                newUsernameInput.setText("");
                newPasswordInput.setText("");
                createNewUserMessage.setText("new user created");
                createNewUserMessage.setTextFill(Color.GREEN);
            } else {
                createNewUserMessage.setText("username has to be unique");
                createNewUserMessage.setTextFill(Color.RED);
            }
        });
        
        createNewUserLoginButton.setOnAction(event -> {
            newUsernameInput.setText("");
            createNewUserMessage.setText("");
            newPasswordInput.setText("");
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
        
        Scene scene = new Scene(newUserPane, 400, 200);
        return scene;
    }
    
    private Scene createMainScene(Stage primaryStage) {
        BorderPane mainPane = new BorderPane();
        HBox menuPane = new HBox(10);
        Button logoutButton = new Button("logout");
        Button newTaskButton = new Button("new task");
        Button mainButton = new Button("main");
        menuLabel.setAlignment(Pos.CENTER);
        
        BorderPane singleTaskPane = createSingleTaskPane();
        
        Menu file = new Menu("File");
        Menu fileSubmenu = new Menu("Load");
        MenuItem loadFileItem = new MenuItem("Load new task list");
        loadFileItem.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Load tasks file");
            configureFileChooser(fileChooser);
            File newTaskFile = fileChooser.showOpenDialog(primaryStage);
            File saveTaskFile = fileChooser.showSaveDialog(primaryStage);
            if (newTaskFile != null) {
                try {
                    Files.copy(newTaskFile.toPath(), saveTaskFile.toPath(), REPLACE_EXISTING);
                    algoritmitehtavageneraattoriService.loadTasks();
                    redrawTasklist();
                } catch (IOException e) {
                }
            }
        });
        MenuItem addFileItem = new MenuItem("Load new tasks to list");
        addFileItem.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Load tasks file");
            configureFileChooser(fileChooser);
            File newTaskFile = fileChooser.showOpenDialog(primaryStage);
            File saveTaskFile = fileChooser.showSaveDialog(primaryStage);
            if (newTaskFile != null) {
                try {
                    Files.copy(newTaskFile.toPath(), saveTaskFile.toPath(), REPLACE_EXISTING);
                    algoritmitehtavageneraattoriService.addTasks();
                    redrawTasklist();
                } catch (IOException e) {
                }
            }
        });
        
        fileSubmenu.getItems().addAll(loadFileItem, addFileItem);
        file.getItems().add(fileSubmenu);
        
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(file);
        
        tableView = makeTableView();
        
        TableViewSelectionModel selectionModel = tableView.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);
        selectedTaskList = selectionModel.getSelectedItems();
        selectedTaskList.addListener((ListChangeListener.Change<? extends Task> change) -> {
            if (!selectedTaskList.isEmpty()) {
                selectedTask = selectedTaskList.get(0);
                singleTaskTitleLabel.setText(selectedTask.getTitle());
                singleTaskDesciptionLabel.setText(selectedTask.getDescription());
                singleTaskInputLabel.setText("Input " + selectedTask.getInput());
                mainPane.setCenter(singleTaskPane);
            }
        });
        
        logoutButton.setOnAction(event -> {
            algoritmitehtavageneraattoriService.logout();
            mainPane.setCenter(menuLabel);
            primaryStage.setScene(loginScene);
        });
        
        newTaskButton.setOnAction(event -> {
            mainPane.setCenter(createNewTaskPane());
            singleTaskSolveMessage.setText("");
        });
        
        mainButton.setOnAction(event -> {
            selectionModel.clearSelection();
            mainPane.setCenter(menuLabel);
            singleTaskSolveMessage.setText("");
        });
        
        HBox searchBox = createSearchBox();
        
        menuPane.getChildren().addAll(menuBar, mainButton, newTaskButton, searchBox, logoutButton, menuUserLabel);
        menuPane.setAlignment(Pos.TOP_LEFT);
        
        mainPane.setTop(menuPane);
        mainPane.setCenter(menuLabel);
        mainPane.setLeft(tableView);
        
        Scene scene = new Scene(mainPane, 1200, 800);
        return scene;
    }
    
    private VBox createNewTaskPane() {
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
        Label newTaskCategoryIdLabel = new Label("Category:");
        TextField newTaskCategoryIdInput = new TextField();
        newTaskInputPane.add(newTaskCategoryIdLabel, 0, 5);
        newTaskInputPane.add(newTaskCategoryIdInput, 1, 5);
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
            String difficulty = newTaskDifficultyInput.getText();
            String categoryId = newTaskCategoryIdInput.getText();
            String input = newTaskInputInput.getText().trim();
            if (title.length() < 5 || description.length() < 10 || input.isBlank() || result.isBlank() || difficulty.isBlank() || categoryId.isBlank()) {
                newTaskMessage.setText("Invalid input.");
                newTaskMessage.setTextFill(Color.RED);
            } else {
                try {
                    algoritmitehtavageneraattoriService.createTask(title, description, result, Integer.valueOf(difficulty), Integer.valueOf(categoryId), input);
                    newTaskMessage.setText("new task created");
                    newTaskMessage.setTextFill(Color.GREEN);
                    newTaskTitleInput.setText("");
                    newTaskDescriptionInput.setText("");
                    newTaskInputInput.setText("");
                    newTaskResultInput.setText("");
                    newTaskDifficultyInput.setText("");
                    newTaskCategoryIdInput.setText("");
                    redrawTasklist();
                } catch (NumberFormatException e) {
                    newTaskMessage.setText("Invalid input.");
                    newTaskMessage.setTextFill(Color.RED);
                }
            }
        }); 
        return newTaskPane;
    }
    
    private BorderPane createSingleTaskPane() {
        BorderPane singleTaskPane = new BorderPane();
        VBox singleTaskMiddlePane = new VBox(10);
        HBox singleTaskResultPane = createSingleTaskResultPane();
        singleTaskSolveMessage = new Label();
        singleTaskTitleLabel = new Label();
        singleTaskTitleLabel.setStyle("-fx-font-size: 40px");
        singleTaskDesciptionLabel = createSingleTaskDescriptionLabel();
        singleTaskInputLabel = new Label();
        singleTaskMiddlePane.getChildren().addAll(singleTaskDesciptionLabel, singleTaskInputLabel, singleTaskSolveMessage, singleTaskResultPane);
        VBox.setMargin(singleTaskDesciptionLabel, new Insets(0, 20, 0, 20));
        VBox.setMargin(singleTaskInputLabel, new Insets(0, 20, 0, 20));
        VBox.setMargin(singleTaskSolveMessage, new Insets(0, 20, 0, 20));
        VBox.setMargin(singleTaskResultPane, new Insets(0, 20, 0, 20));
        singleTaskPane.setTop(singleTaskTitleLabel);
        singleTaskPane.setCenter(singleTaskMiddlePane);
        BorderPane.setAlignment(singleTaskTitleLabel, Pos.TOP_CENTER);
        return singleTaskPane;
    }
    
    private Label createSingleTaskDescriptionLabel() {
        Label label = new Label();
        label.setMaxWidth(700);
        label.setWrapText(true);
        label.setStyle("-fx-font-size: 20px");
        return label;
    }
    
    private HBox createSingleTaskResultPane() {
        HBox pane = new HBox(10);
        TextField singleTaskUserInput = new TextField();
        singleTaskUserInput.setMaxWidth(100);
        Button singleTaskSolveButton = new Button("Solve");
        singleTaskSolveButton.setOnAction(event -> {
            if (algoritmitehtavageneraattoriService.compareResults(singleTaskUserInput.getText(), selectedTask)) {
                singleTaskSolveMessage.setText("Correct answer");
                singleTaskSolveMessage.setTextFill(Color.GREEN);
                update();
            } else {
                singleTaskSolveMessage.setText("Wrong answer");
                singleTaskSolveMessage.setTextFill(Color.RED);
            }
            singleTaskUserInput.setText("");
        });
        pane.getChildren().addAll(singleTaskUserInput, singleTaskSolveButton);
        return pane;
    }
    
    private void update() {
        updateUserLabel();
        redrawTasklist();
    }

    private static void configureFileChooser(final FileChooser fileChooser) {      
        fileChooser.setTitle("Load task file");       
        fileChooser.setInitialFileName("tasks.txt");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT", "*.txt"));
    }
    
    private TableView makeTableView() {
        TableView<Task> table = new TableView<Task>();
        table.setEditable(true);
        TableColumn<Task, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Task, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        TableColumn<Task, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        TableColumn<Task, String> difficultyColumn = new TableColumn<>("Difficulty");
        difficultyColumn.setCellValueFactory(new PropertyValueFactory<>("difficulty"));
        TableColumn<Task, String> doneColumn = new TableColumn<>("Done");
        doneColumn.setCellValueFactory(new PropertyValueFactory<>("done"));
        table.getColumns().addAll(idColumn, titleColumn, categoryColumn, difficultyColumn, doneColumn);
        return table;
    }
    
    private HBox createSearchBox() {
        ChoiceBox<String> choiceSearchBox = new ChoiceBox();
        choiceSearchBox.getItems().addAll("Title", "Category", "Difficulty", "Done");
        choiceSearchBox.setValue("Title");
        TextField searchInput = createSearchInput(choiceSearchBox);
        choiceSearchBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                searchInput.setText("");
                filteredData.setPredicate(null);
            }
        });
        HBox searchBox = new HBox();
        searchBox.getChildren().addAll(choiceSearchBox, searchInput);
        return searchBox;
    }
    
    private TextField createSearchInput(ChoiceBox<String> choiceSearchBox) {
        TextField searchInput = new TextField();
        searchInput.setOnKeyReleased(keyEvent -> {
            switch (choiceSearchBox.getValue()) {
                case "Title":
                    filterWithTitle(searchInput);
                    break;
                case  "Category":
                    filterWithCategory(searchInput);
                    break;
                case "Difficulty":
                    filterWithDifficulty(searchInput);
                    break;
                case "Done":
                    filterWithDone(searchInput);
                    break;
            }
        });
        return searchInput;
    }
    
    private void filterWithTitle(TextField searchInput) {
        if (searchInput.getText().trim().isEmpty()) {
            filteredData.setPredicate(null);
        } else {
            filteredData.setPredicate(t -> t.getTitle().toLowerCase().contains(searchInput.getText().toLowerCase().trim()));
        }
    }
    
    private void filterWithCategory(TextField searchInput) {
        if (searchInput.getText().trim().isEmpty()) {
            filteredData.setPredicate(null);
        } else {
            filteredData.setPredicate(t -> t.getDifficulty() == Integer.valueOf(searchInput.getText().trim()));
        }
    }
    
    private void filterWithDifficulty(TextField searchInput) {
        if (searchInput.getText().trim().isEmpty()) {
            filteredData.setPredicate(null);
        } else {
            filteredData.setPredicate(t -> t.getCategoryId() == Integer.valueOf(searchInput.getText().trim()));
        }
    }
    
    private void filterWithDone(TextField searchInput) {
        if (searchInput.getText().trim().isEmpty()) {
            filteredData.setPredicate(null);
        } else {
            filteredData.setPredicate(t -> Boolean.toString(t.getDone()).contains(searchInput.getText().toLowerCase().trim()));
        }
    }
    
    private void redrawTasklist() {
        ObservableList<Task> data = FXCollections.observableList(algoritmitehtavageneraattoriService.getTasks());
        filteredData = new FilteredList(data, t -> true);
        tableView.setItems(filteredData);
        tableView.refresh();
    }
    
    private void updateUserLabel() {
        menuUserLabel.setText(algoritmitehtavageneraattoriService.getLoggedUser().getUsername() + " : "
                        + algoritmitehtavageneraattoriService.getLoggedUser().getPoints() + " points");
    }
    
    @Override
    public void stop() {
        System.out.print("App closing");
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
