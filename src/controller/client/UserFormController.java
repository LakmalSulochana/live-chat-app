package controller.client;

import controller.Data;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import util.ConnectionUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class UserFormController {
    public TextField txtMsgInput;
    public TextArea txtMsgDisplay;

    public String userName;
    public Pane emojiPaneBar;
    PrintWriter printWriter;
    Socket socket = null;

    public void btnClose(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void btnMinimize(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        // is stage minimizable into task bar. (true | false)
        stage.setIconified(true);
    }

    public void btnSend(ActionEvent actionEvent) throws IOException {
        printWriter = new PrintWriter(socket.getOutputStream());
        printWriter.println(userName + " : " + txtMsgInput.getText());
        printWriter.flush();
        txtMsgInput.clear();
    }

    public void initialize() throws IOException {
        emojiPaneBar.setVisible(false);
        userName = Data.userName;
        System.out.println("userName is : " +userName);
        socket = new Socket(ConnectionUtil.host, ConnectionUtil.port);
        txtMsgDisplay.appendText("Connect. \n");
        printWriter = new PrintWriter(socket.getOutputStream());
        TaskReadThread task = new TaskReadThread(socket, this);
        Thread thread = new Thread(task);
        thread.start();

    }


    public void onMouseClickEmojiBar(MouseEvent mouseEvent) {
        if (!emojiPaneBar.isVisible()) {
            emojiPaneBar.setVisible(true);
        } else {
            emojiPaneBar.setVisible(false);
        }
    }

    public void imojiWorry(MouseEvent mouseEvent) {
        txtMsgInput.appendText("\uD83D\uDE14");
    }

    public void imojiGrinningFace(MouseEvent mouseEvent) {
        txtMsgInput.appendText("\uD83D\uDE00");
    }

    public void imojiRedHeart(MouseEvent mouseEvent) {
        txtMsgInput.appendText("❤");

    }

    public void imojiWow(MouseEvent mouseEvent) {
        txtMsgInput.appendText("\uD83D\uDE2E");
    }

    public void imojiSad(MouseEvent mouseEvent) {
        txtMsgInput.appendText("\uD83D\uDE25");
    }

    public void imojiSmilingFacewithHeartEyes(MouseEvent mouseEvent) {
        txtMsgInput.appendText("\uD83D\uDE0D");
    }
}
