import javax.swing.*;

public class ClientGui {


    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogInFrame logIn = new LogInFrame();
    }
}
