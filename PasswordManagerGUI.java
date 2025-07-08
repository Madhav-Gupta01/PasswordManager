import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.crypto.SecretKey;
import java.util.Scanner;

public class PasswordManagerGUI extends Application {
    private PasswordManagerCore manager;
    private char[] masterPassword;
    private TextArea outputArea;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(15));

        Label lblMaster = new Label("Enter Master Password:");
        PasswordField txtMaster = new PasswordField();

        Button btnLogin = new Button("Login");
        outputArea = new TextArea();
        outputArea.setEditable(false);

        HBox form = new HBox(10);
        VBox formFields = new VBox(10);
        TextField txtAccount = new TextField();
        TextField txtUsername = new TextField();
        PasswordField txtPassword = new PasswordField();

        txtAccount.setPromptText("Account Name");
        txtUsername.setPromptText("Username");
        txtPassword.setPromptText("Password");
        Button btnAdd = new Button("Add Password");
        Button btnGet = new Button("Get Password");

        formFields.getChildren().addAll(txtAccount, txtUsername, txtPassword, btnAdd, btnGet);
        form.getChildren().addAll(formFields);

        root.getChildren().addAll(lblMaster, txtMaster, btnLogin, outputArea, form);

        btnLogin.setOnAction(e -> {
            try {
                masterPassword = txtMaster.getText().toCharArray();
                manager = new PasswordManagerCore(masterPassword);
                outputArea.appendText("Logged in successfully.\n");
            } catch (Exception ex) {
                outputArea.appendText("Error: " + ex.getMessage() + "\n");
            }
        });

        btnAdd.setOnAction(e -> {
            try {
                if (manager == null) {
                    outputArea.appendText("Please login first.\n");
                    return;
                }
                manager.add(txtAccount.getText(), txtUsername.getText(), txtPassword.getText());
                outputArea.appendText("Password saved.\n");
            } catch (Exception ex) {
                outputArea.appendText("Error: " + ex.getMessage() + "\n");
            }
        });

        btnGet.setOnAction(e -> {
            try {
                if (manager == null) {
                    outputArea.appendText("Please login first.\n");
                    return;
                }
                String result = manager.get(txtAccount.getText());
                outputArea.appendText((result != null ? result : "Account not found.") + "\n");
            } catch (Exception ex) {
                outputArea.appendText("Error: " + ex.getMessage() + "\n");
            }
        });

        Scene scene = new Scene(root, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Password Manager");
        primaryStage.show();
    }
} 
