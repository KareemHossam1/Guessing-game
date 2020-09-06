package guessing.game;
// Importing packages we will use in the project
import java.util.Random;
import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
public class GuessingGame extends Application {
    // Making objects we will use in this project
    Button submit = new Button("Submit");                        // Create submit button
    Button giveUp = new Button("Give Up");                       // Create give up button
    TextField input = new TextField();                           // Create a text field to accept input
    Label lbl1 = new Label("\n\t  Guess the secret number\n");   // Create a label
    Label lbl2 = new Label("\tYour guess?");                     // Create a label
    Label lbl3 = new Label("      I'm thinking of a number between 1 and 100");
    HBox inputH = new HBox(10);                                  // HBox to add second label and field box
    HBox buttons = new HBox(30);                                 // HBox to add buttons
    VBox root = new VBox(10);                                    // VBox to arrange elements
    Font font = new  Font(20);                                   // To change labels font
    Random rand = new Random();                                  // To generate random nubers
    Alert inputError = new Alert (Alert.AlertType.ERROR);        // Error alert to be shown when the user enters an invalid input
    Alert result = new Alert (Alert.AlertType.INFORMATION);      // Information alert to show the result
    Scene scene;                                                 // To create scene
    // Declaring variables
    int number;             // To store the random number
    Double test = null;     // To test the input if it's a string input
    @Override
    public void start(Stage primaryStage) {
        lbl1.setStyle("-fx-font-weight: bold");                // Make the label text bold
        lbl1.setFont(font);                                    // Change label font size
        buttons.getChildren().addAll(submit,giveUp);           // Arrange buttons
        buttons.setPadding(new Insets(0,0,0,85));              // To change buttons position
        inputH.getChildren().addAll(lbl2,input);               // Arrange input part
        root.getChildren().addAll(lbl1,inputH,buttons,lbl3);   // Arrange elements
        number = rand.nextInt(100) + 1;                        // Generate a random number between 0 & 100
        // Handling submit button
        submit.setOnAction(e->{
            // Check if the user entered a string value
            try{
                test = Double.valueOf(input.getText());
            }
            catch (final NumberFormatException Event)
            {
                // Show error message if it's string
                inputError.setTitle("Wrong Input");                         
                inputError.setHeaderText("");
                inputError.setContentText("Numbers only allowed!");
                inputError.show();
            }
            if(test != null)  // if it's a number do this
            {
                if (Double.valueOf(input.getText())>=1&&Double.valueOf(input.getText())<=100) // If the user entered a number between 1 & 100
                {
                    if (Double.valueOf(input.getText())>number){ // If the user entered a large number
                        // Inform the user that the number is large
                        result.setTitle("Wrong Number");                         
                        result.setHeaderText("");
                        result.setContentText("\tYou entered a large number.\n\tTry again!");
                        result.show();
                    }
                    else if (Double.valueOf(input.getText())<number){  // If the user entered a small number
                        // Inform the user that the number is small
                        result.setTitle("Wrong Number");                         
                        result.setHeaderText("");
                        result.setContentText("\tYou entered a small number.\n\tTry again!");
                        result.show();
                    }
                    else{  // If the user entered the right number
                        // Inform the user that the number is right and think of another number
                        result.setTitle("Right Number");                         
                        result.setHeaderText("");
                        result.setContentText("\tRight answer.\n\tLet's try another number.");
                        result.show();
                        number = rand.nextInt(100) + 1;  // To change the random number
                    }}
                else{   // If the user entered a number bigger than 100 or smaller than 1
                    // Show error message
                    inputError.setTitle("Wrong Input");                         
                    inputError.setHeaderText("");
                    inputError.setContentText("Your number should be between 1 and 100");
                    inputError.show();
                }}});
        // Handling give up button
        giveUp.setOnAction(e->{
            // Inform the user with the right number and think of another number
            result.setTitle("Right Number");                         
            result.setHeaderText("");
            result.setContentText("\tThe right answer is : " + number + "\n\tLet's try another number.");
            result.show();
            number = rand.nextInt(100) + 1;       // To change the random number
        });
        scene = new Scene(root, 330, 180);       // Creating scene and set its size
        primaryStage.setResizable(false);        // Make the stage unresizable
        primaryStage.setTitle("Guessing Game");  // Stage title
        primaryStage.setScene(scene);            // Adding scene to stage
        primaryStage.show();                     // Show stage
    }
    public static void main(String[] args) {
        launch(args);
    }}