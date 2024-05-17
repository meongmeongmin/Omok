import javax.swing.*;
import java.awt.*;

class Menu extends JPanel
{
    public Menu(JFrame frame, Game game)
    {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBackground(Color.BLACK);

        // 게임 타이틀
        JLabel title = new JLabel("OMOK GAME");
        title.setFont(new Font("Arial", Font.BOLD, 60));    // 텍스트
        title.setForeground(Color.WHITE);   // 텍스트 색상
        title.setAlignmentX(Component.CENTER_ALIGNMENT);    // 정렬
        // 게임시작
        JButton startBtn = new JButton("Game Start!");
        startBtn.setPreferredSize(new Dimension(300, 100)); // 버튼 크기
        startBtn.setFont(new Font("Arial", Font.PLAIN, 30));    // 텍스트
        startBtn.setAlignmentX(Component.CENTER_ALIGNMENT); // 정렬
        startBtn.addActionListener(e -> // `Game Start!`버튼을 클릭하면 보드판 화면으로 전환
        {
            Board board = new Board(game, frame);
            frame.setContentPane(board);
            frame.validate();
        });
        // 게임종료
        JButton exitBtn = new JButton("Game Exit");
        exitBtn.setPreferredSize(new Dimension(300, 100));  // 버튼 크기
        exitBtn.setFont(new Font("Arial", Font.PLAIN, 30)); // 텍스트
        exitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);  // 정렬
        exitBtn.addActionListener(e -> { System.exit(0); });

        add(Box.createVerticalGlue());
        add(title);
        add(Box.createVerticalGlue());
        add(startBtn);
        add(Box.createRigidArea(new Dimension(0, 40)));
        add(exitBtn);
        add(Box.createVerticalGlue());
    }
}