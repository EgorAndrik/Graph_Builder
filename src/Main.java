import javax.swing.*;

public class Main {
    public static void creatProgect() {
        JFrame wind = new JFrame("Progect");
        wind.setSize(700, 900);
        wind.setResizable(false);
        wind.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wind.setContentPane(new Panel());
        wind.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                creatProgect();
            }
        });
    }
}