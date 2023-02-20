package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TwoPlayersController implements Initializable {

    @FXML
    private PasswordField magicWord;

    @FXML
    private Button okButton;

    @FXML
    private char[] wordsLetters;

    @FXML
    ArrayList<String> word = new ArrayList<>();

    @FXML
    public int counter, tries = 7;

    @FXML
    private Label label, labelW, label1, label2, labelUsedLetters;

    @FXML
    private ComboBox<String> menu;

    @FXML
    String s = ".";

    @FXML
    private TextField textField;

    @FXML
    private ImageView img;

    @FXML
    private Button button, playAgainB, menuB;

    @FXML//Παίρνει την λέξη από τον χρήστη και ενεργοποιεί τις υπόλυπες επιλογές του παραθύρου
    private void getWord(ActionEvent event) throws IOException {

        //Παίρνει την λέξη από τον χρήστη
        String playersWord = magicWord.getText();

        //Ελέγχει αν ο χρήστης δεν έχει δώσει κάποιο γράμμα και βγάζει το αντίστοιχο μήνυμα και ξανάφορτωνει το πρόγραμμα από την αρχή
        if (magicWord.getText() == null || magicWord.getText().trim().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Δώσε μία λέξη που να αποτελείται τουλάχιστον από τέσσερα γράμματα");
            alert.showAndWait();

            Parent singlePlayerParent = FXMLLoader.load(getClass().getResource("TwoPlayers.fxml"));
            Scene singlePlayerScene = new Scene(singlePlayerParent,683, 373.6);

            //This line gets the stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(singlePlayerScene);
            window.setTitle("Two Players");
            window.show();

            //Ελέγχει αν η λέξη είναι μεγαλύτερη από τρία γράμματα. Αν όχι ξανά φορτώνει το πρόγραμμα από την αρχή βγάζοντας το ανάλογο μήνυμα
        }else if (playersWord.toCharArray().length <= 3){

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Η λέξη πρέπει να έχει περισσότερα από τρία γράμματα");
            alert.showAndWait();

            Parent singlePlayerParent = FXMLLoader.load(getClass().getResource("TwoPlayers.fxml"));
            Scene singlePlayerScene = new Scene(singlePlayerParent,683, 373.6);

            //This line gets the stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(singlePlayerScene);
            window.setTitle("Two Players");
            window.show();

        }else{//Αν πάνε όλα καλά ξεκινάει το πρόγραμμα
            okButton.setVisible(false);
            magicWord.setVisible(false);
            labelW.setVisible(false);
            label.setVisible(true);
            label1.setVisible(true);
            textField.setVisible(true);
            img.setVisible(true);
            button.setVisible(true);
            labelUsedLetters.setVisible(true);
        }
        // Copy character by character into array
        wordsLetters = playersWord.toCharArray();

        //Αρχικοποίηση της μεταβλητής για να ξέρουμε πότε ο χρήστης θα βρει την λέξη
        counter = wordsLetters.length;

        //Για να εμφανίζεται το πρώτο γράμμα της λέξης
        word.add(0, String.valueOf(wordsLetters[0]));
        wordsLetters[0] = '.';

        //Για να εμφανίζονται οι παύλες των γραμμάτων που δεν έχει βρει ο χρήστης
        for (int i = 1; i <= wordsLetters.length; i++) {
            word.add(" _");
            label.setText(label.getText() + word.get(i - 1));
        }
    }

    @FXML
    private void menu(ActionEvent event) throws IOException {

        //Παίρνει την επιλογή του χρήστη
        String choice = menu.getValue();

        //Επιστρέφει στο αρχικό μενού
        if(choice.equals("Επιστροφή στο κύριο μενού")){
            Parent singlePlayerParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Scene singlePlayerScene = new Scene(singlePlayerParent);

            //This line gets the stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(singlePlayerScene);
            window.setTitle("Menu");
            window.show();

        }else{
            Platform.exit();
        }
    }

    @FXML
    private void getLetters(ActionEvent event){

        boolean found = false;
        //Παίρνει από το textField το γράμμα που δίνει ο χρήστης
        String letter = textField.getText();
        textField.clear();

        //Κάνει έλεγχο αν υπάρχει το γράμμα στον τον πίνακα γραμμάτων της λέξης εμφανίζοντας και τα γράματα με τόνους γράφοντας απλά το φωνήεν
        switch (letter){
            case "α":
                for(int i = 0; i< wordsLetters.length; i++) {
                    if (letter.equals(String.valueOf(wordsLetters[i])) || "ά".equals(String.valueOf(wordsLetters[i]))){

                        //Προσθέτει το γράμμα σε ένα νέο ArrayList που περιέχει τα γράμματα που έχει βρει ο χρήστης
                        word.set(i,String.valueOf(wordsLetters[i]));

                        //‘’Αφαιρεί’’ το γράμμα από τον πίνακα για να μην το ξαναβρίσκει στην επόμενη αναζήτηση
                        wordsLetters[i] ='.';
                        counter--;
                        found = true;
                    }
                }
                break;
            case "ε":
                for(int i = 0; i< wordsLetters.length; i++) {
                    if (letter.equals(String.valueOf(wordsLetters[i])) || "έ".equals(String.valueOf(wordsLetters[i]))){

                        //Προσθέτει το γράμμα σε ένα νέο ArrayList που περιέχει τα γράμματα που έχει βρει ο χρήστης
                        word.set(i,String.valueOf(wordsLetters[i]));

                        //‘’Αφαιρεί’’ το γράμμα από τον πίνακα για να μην το ξαναβρίσκει στην επόμενη αναζήτηση
                        wordsLetters[i] ='.';
                        counter--;
                        found = true;
                    }
                }
                break;
            case "ι":
                for(int i = 0; i< wordsLetters.length; i++) {
                    if (letter.equals(String.valueOf(wordsLetters[i])) || "ί".equals(String.valueOf(wordsLetters[i]))){

                        //Προσθέτει το γράμμα σε ένα νέο ArrayList που περιέχει τα γράμματα που έχει βρει ο χρήστης
                        word.set(i,String.valueOf(wordsLetters[i]));

                        //‘’Αφαιρεί’’ το γράμμα από τον πίνακα για να μην το ξαναβρίσκει στην επόμενη αναζήτηση
                        wordsLetters[i] ='.';
                        counter--;
                        found = true;
                    }
                }
                break;
            case "η":
                for(int i = 0; i< wordsLetters.length; i++) {
                    if (letter.equals(String.valueOf(wordsLetters[i])) || "ή".equals(String.valueOf(wordsLetters[i]))){

                        //Προσθέτει το γράμμα σε ένα νέο ArrayList που περιέχει τα γράμματα που έχει βρει ο χρήστης
                        word.set(i,String.valueOf(wordsLetters[i]));

                        //‘’Αφαιρεί’’ το γράμμα από τον πίνακα για να μην το ξαναβρίσκει στην επόμενη αναζήτηση
                        wordsLetters[i] ='.';
                        counter--;
                        found = true;
                    }
                }
                break;
            case "υ":
                for(int i = 0; i< wordsLetters.length; i++) {
                    if (letter.equals(String.valueOf(wordsLetters[i])) || "ύ".equals(String.valueOf(wordsLetters[i]))){

                        //Προσθέτει το γράμμα σε ένα νέο ArrayList που περιέχει τα γράμματα που έχει βρει ο χρήστης
                        word.set(i,String.valueOf(wordsLetters[i]));

                        //‘’Αφαιρεί’’ το γράμμα από τον πίνακα για να μην το ξαναβρίσκει στην επόμενη αναζήτηση
                        wordsLetters[i] ='.';
                        counter--;
                        found = true;
                    }
                }
                break;
            case "ο":
                for(int i = 0; i< wordsLetters.length; i++) {
                    if (letter.equals(String.valueOf(wordsLetters[i])) || "ό".equals(String.valueOf(wordsLetters[i]))){

                        //Προσθέτει το γράμμα σε ένα νέο ArrayList που περιέχει τα γράμματα που έχει βρει ο χρήστης
                        word.set(i,String.valueOf(wordsLetters[i]));

                        //‘’Αφαιρεί’’ το γράμμα από τον πίνακα για να μην το ξαναβρίσκει στην επόμενη αναζήτηση
                        wordsLetters[i] ='.';
                        counter--;
                        found = true;
                    }
                }
                break;
            case "ω":
                for(int i = 0; i< wordsLetters.length; i++) {
                    if (letter.equals(String.valueOf(wordsLetters[i])) || "ώ".equals(String.valueOf(wordsLetters[i]))){

                        //Προσθέτει το γράμμα σε ένα νέο ArrayList που περιέχει τα γράμματα που έχει βρει ο χρήστης
                        word.set(i,String.valueOf(wordsLetters[i]));

                        //‘’Αφαιρεί’’ το γράμμα από τον πίνακα για να μην το ξαναβρίσκει στην επόμενη αναζήτηση
                        wordsLetters[i] ='.';
                        counter--;
                        found = true;
                    }
                }
                break;
            default:
                for(int i = 0; i< wordsLetters.length; i++) {
                    if (letter.equals(String.valueOf(wordsLetters[i]))) {

                        //Προσθέτει το γράμμα σε ένα νέο ArrayList που περιέχει τα γράμματα που έχει βρει ο χρήστης
                        word.set(i,letter);

                        //‘’Αφαιρεί’’ το γράμμα από τον πίνακα για να μην το ξαναβρίσκει στην επόμενη αναζήτηση
                        wordsLetters[i] ='.';
                        counter--;
                        found = true;
                    }
                }
        }

        //Άμα δεν έχει βρεθεί το γράμμα το εμφανίζει στα γράμματα που έχουν χρησιμοποιηθεί και αφαιρεί μία ζωή
        if (!found){
            label1.setText(label1.getText() + letter +" ,");
            tries--;
        }

        //Εμφανίζει τα γράμματα στο label από το ArrayList word
        label.setText("");
        for (int i = 0; i< wordsLetters.length; i++){
            label.setText(label.getText() + word.get(i));
        }

        //Εμφανίζει το ανθρωπάκι μέσω εικόνων
        switch (tries){
            case 6:
                img.setImage(new Image("resources/try2.png"));
                break;
            case 5:
                img.setImage(new Image("resources/try3.png"));
                break;
            case 4:
                img.setImage(new Image("resources/try4.png"));
                break;
            case 3:
                img.setImage(new Image("resources/try5.png"));
                break;
            case 2:
                img.setImage(new Image("resources/try6.png"));
                break;
            case 1:
                img.setImage(new Image("resources/try7.png"));
                break;
            case 0:
                //Αν τελειώσουν οι προσπάθειες το πρόγραμμα τερματίζει
                label.setText("");

                //Για να εμφανίζεται η λέξη όταν χάσει ο παίκτης
                for (int i=0; i<wordsLetters.length; i++){
                    if(s.equals(String.valueOf(wordsLetters[i]))){
                        label.setText(label.getText() + word.get(i));
                    }else {
                        label.setText(label.getText() + wordsLetters[i]);
                    }
                }

                //Εμφανίζονται κάποιες επιπλέον επιλογές αφού έχει τελειώσει το πρόγραμμα
                label2.setVisible(true);
                textField.setEditable(false);
                button.setVisible(false);
                playAgainB.setVisible(true);
                menuB.setVisible(true);
                break;
        }

        //Αφορά την στιγμή που ο χρήστης βρίσκει την λέξη
        if (counter==1){
            label2.setText("You Won");
            label2.setVisible(true);
            textField.setEditable(false);
            button.setVisible(false);
            playAgainB.setVisible(true);
            menuB.setVisible(true);
        }
    }

    @FXML //Ξεκινάει το πρόγραμμα από την αρχή
    private void playAgain(ActionEvent event) throws IOException {
        Parent singlePlayerParent = FXMLLoader.load(getClass().getResource("TwoPlayers.fxml"));
        Scene singlePlayerScene = new Scene(singlePlayerParent,683, 373.6);

        //This line gets the stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(singlePlayerScene);
        window.setTitle("Two Players");
        window.show();
    }

    @FXML //Επιστρέφει στο μενού
    private void returnMenu(ActionEvent event) throws IOException {
        Parent singlePlayerParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene singlePlayerScene = new Scene(singlePlayerParent);

        //This line gets the stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(singlePlayerScene);
        window.setTitle("Menu");
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        menu.getItems().addAll("Επιστροφή στο κύριο μενού", "Έξοδος");
        label2.setVisible(false);
        playAgainB.setVisible(false);
        menuB.setVisible(false);
        label.setVisible(false);
        label1.setVisible(false);
        textField.setVisible(false);
        img.setVisible(false);
        button.setVisible(false);
        labelUsedLetters.setVisible(false);

        //Για να δέχεται γράμματα Ελληνικά με τόνους και Αγγλικά
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sα-ωΑ-Ωά-ώ*")) {
                textField.setText(newValue.replaceAll("[^\\sA-Zά-ώ]", ""));
            }
        });

        //Για να δέχεται μόνο ένα γράμμα
        textField.setTextFormatter(new TextFormatter<String>((TextFormatter.Change change) -> {
            String newText = change.getControlNewText();
            if (newText.length() > 1) {
                return null;
            } else {
                return change;
            }
        }));
    }
}