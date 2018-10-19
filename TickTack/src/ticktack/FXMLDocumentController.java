package ticktack;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import static javafx.scene.media.MediaPlayer.INDEFINITE;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class FXMLDocumentController implements Initializable {

    @FXML
    private ImageView imageview;
    @FXML
    public JFXButton b1, b2, b3, b4, b5, b6, b7, b8, b9; //nine buttons of the ticktack board
    @FXML
    private JFXToggleButton asX;  //used to switching the mode of player
    @FXML
    private Label result;

    public String s; //store the 'O' or 'X'
    public int checkmode, turnO, draw;

    //method to set remove the character from the button
    public void clearButton() {
        b1.setText("  ");
        b2.setText("  ");
        b3.setText("  ");
        b4.setText("  ");
        b5.setText("  ");
        b6.setText("  ");
        b7.setText("  ");
        b8.setText("  ");
        b9.setText("  ");
    }

    @FXML
    public void play() {
        //check togglebutton is selected or not
        clearButton();
        setAllEnable();
        setDefaultStyle();
        result.setText(" ");
        asX.setDisable(false);
        turnO = 1;
        s = "O";
        
        //checking the what would be the first character initially
        if (!asX.isSelected()) {
            playAsO();
            checkmode = 1;
        } else {
            checkmode = 0;
            playAsX();
        }
    }

    //Play as O
    public void playAsO() {
        if (!checkAllButton()) {
            boolean win;
            win = checkToWin();
        }
    }

    //Play as X
    public void playAsX() {
        if (!checkAllButton()) {
            boolean win;
            win = checkToWin();
        }
    }

    //handler for Button ---------------------------------------------------------
    public void setonaction(ActionEvent e) {
        JFXButton b = (JFXButton) e.getSource();
        b.setText(s);
        b.setDisable(true);
        boolean win = false;
        if (checkmode == 0) {
            win = checkToWin();  //checking necessary condition to win the game
            if (!win) {
                if (turnO == 0) {
                    turnO = 1;
                    s = "O";
                    playAsO();
                } else {
                    turnO = 0;
                    s = "X";
                    playAsX();
                }
            }
        } else {
            win = checkToWin();
            if (!win) {
                if (turnO == 1) {
                    turnO = 0;
                    s = "X";
                    playAsO();
                } else {
                    turnO = 1;
                    s = "O";
                    playAsX();
                }
            }
        }
    }
//---------------------------------------------------------------------------------------
    
    // Styling the buttons using CSS -----------------------------------------------------
    public void setStyleOnWin(JFXButton d1, JFXButton d2, JFXButton d3) {
        d1.setStyle("-fx-background-color : #00ff00");
        d2.setStyle("-fx-background-color : #00ff00");
        d3.setStyle("-fx-background-color : #00ff00");
    }

    public void setStyleOnLose(JFXButton d1, JFXButton d2, JFXButton d3) {
        d1.setStyle("-fx-background-color :  #F76541");
        d2.setStyle("-fx-background-color :  #F76541");
        d3.setStyle("-fx-background-color :  #F76541");
    }

    public void setDefaultStyle() {
        b1.setStyle("-fx-background-color :  #adff2f");
        b2.setStyle("-fx-background-color :  #adff2f");
        b3.setStyle("-fx-background-color :  #adff2f");
        b4.setStyle("-fx-background-color :  #adff2f");
        b5.setStyle("-fx-background-color :  #adff2f");
        b6.setStyle("-fx-background-color :  #adff2f");
        b7.setStyle("-fx-background-color :  #adff2f");
        b8.setStyle("-fx-background-color :  #adff2f");
        b9.setStyle("-fx-background-color :  #adff2f");
    }
//-----------------------------------------------------------------------------------------


    //cheking all buttons are empty or not
    public boolean checkAllButton() {
        int count = 0;
        String[] str;
        str = new String[9];
        //storing the text of buttons into string array
        str[0] = b1.getText();
        str[1] = b2.getText();
        str[2] = b3.getText();
        str[3] = b4.getText();
        str[4] = b5.getText();
        str[5] = b6.getText();
        str[6] = b7.getText();
        str[7] = b8.getText();
        str[8] = b9.getText();
        //checking whether all buttons are empty or not
        for (String s : str) {
            if (!s.equals("  ")) {
                count++;
            }
        }
        boolean win = checkToWin();
        if (count == 9) {
            if (!win) {
                result.setText("DRAW");
                setAllDisable();
            }
        }
        //if not empty it return true , otherwise false
        return count == 9;
    }

    //setting all the buttons disable
    public void setAllDisable() {
        b1.setDisable(true);
        b2.setDisable(true);
        b3.setDisable(true);
        b4.setDisable(true);
        b5.setDisable(true);
        b6.setDisable(true);
        b7.setDisable(true);
        b8.setDisable(true);
        b9.setDisable(true);
        asX.setDisable(false);
    }

    //setting all the buttons enable
    public void setAllEnable() {
        b1.setDisable(false);
        b2.setDisable(false);
        b3.setDisable(false);
        b4.setDisable(false);
        b5.setDisable(false);
        b6.setDisable(false);
        b7.setDisable(false);
        b8.setDisable(false);
        b9.setDisable(false);
    }

    //checking condition for buttons in single row , column and diagonal
    public boolean checkButtons(JFXButton d1, JFXButton d2, JFXButton d3) {
        if (!d1.getText().equals("  ") && d1.getText().equals(d2.getText()) && d1.getText().equals(d3.getText())) {
            if (checkmode == 1) {
                if (d1.getText().equals("X")) {
                    result.setText("You LOSE !");
                    setStyleOnLose(d1, d2, d3);
                    setAllDisable();
                    return true;
                } else {
                    Image win = new Image("/win.png");
                    Notifications n = Notifications.create()
                            .position(Pos.TOP_RIGHT)
                            .hideAfter(Duration.seconds(2))
                            .text("")
                            .graphic(new ImageView(win))
                            .title("YOU WON !!!!")
                            .hideCloseButton();
                    n.show();
                    setStyleOnWin(d1, d2, d3);
                    setAllDisable();
                    return true;
                }
            } else {
                if (d1.getText().equals("O")) {
                    result.setText("You LOSE !");
                    setStyleOnLose(d1, d2, d3);
                    setAllDisable();
                    return true;
                } else {
                    Image win = new Image("/win.png");
                    Notifications n = Notifications.create()
                            .position(Pos.TOP_RIGHT)
                            .hideAfter(Duration.seconds(2))
                            .text("")
                            .graphic(new ImageView(win))
                            .title("YOU WIN !!!")
                            .hideCloseButton();
                    n.show();
                    setStyleOnWin(d1, d2, d3);
                    setAllDisable();
                    return true;
                }
            }
        }
        return false;
    }

    //checking the wining condition
    public boolean checkToWin() {
        boolean win;
        // (0,0) row
        win = checkButtons(b1, b2, b3);
        if (win) {
            return win;
        }
        // (0,0) column
        win = checkButtons(b1, b4, b7);
        if (win) {
            return win;
        }
        // (0,0) diagonal
        win = checkButtons(b1, b5, b9);
        if (win) {
            return win;
        }
        // (1,1) row
        win = checkButtons(b4, b5, b6);
        if (win) {
            return win;
        }
        // (2,1) row
        win = checkButtons(b7, b8, b9);
        if (win) {
            return win;
        }
        // (0,2) diagonal
        win = checkButtons(b3, b5, b7);
        if (win) {
            return win;
        }
        //(0,1) column
        win = checkButtons(b2, b5, b8);
        if (win) {
            return win;
        }
        //(0,2) column
        win = checkButtons(b3, b6, b9);
        if (win) {
            return win;
        }
        return false;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Playing Background music while playing
        AudioClip audio = new AudioClip(getClass().getResource("/background.wav").toExternalForm());
        audio.setCycleCount(INDEFINITE);
        audio.play();

        //setting image on paly button
        Image img = new Image("/play.png");
        imageview.setImage(img);
        //intialy make all the buttons disabled
        b1.setDisable(true);
        b2.setDisable(true);
        b3.setDisable(true);
        b4.setDisable(true);
        b5.setDisable(true);
        b6.setDisable(true);
        b7.setDisable(true);
        b8.setDisable(true);
        b9.setDisable(true);
    }
}
