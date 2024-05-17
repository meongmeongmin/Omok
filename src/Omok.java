import javax.swing.*;

public class Omok
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Omok Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Game game = new Game();
        Menu menu = new Menu(frame, game);

        // UI 설정
        frame.setContentPane(menu);
        frame.setSize(800, 800);
        frame.setVisible(true);
    }
}