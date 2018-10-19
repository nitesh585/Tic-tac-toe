package ticktack;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class SplashScreenController implements Initializable {

    @FXML
    private StackPane stackpane;
    @FXML
    private MediaView mv;
    private Media m;
    private MediaPlayer mp;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       new Splash().start();
    }
    
    class Splash extends Thread{
        @Override
        public void run(){
            try {
                String path = new File("vedio/Countdown.mp4").getAbsolutePath();
                m = new Media(new File(path).toURI().toString());
                mp = new MediaPlayer(m);
                mp.setAutoPlay(true);
                mv.setMediaPlayer(mp);
                Thread.sleep(2800);
                Platform.runLater(() -> {
                    Parent root;
                    try {
                        root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.setResizable(false);
                        stage.show();
                        stackpane.getScene().getWindow().hide();
                    } catch (IOException ex) {
                        Logger.getLogger(SplashScreenController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            } catch (InterruptedException ex) {
                Logger.getLogger(SplashScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }

        private String File(String countdownmp4) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
}
