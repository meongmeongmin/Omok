import javax.swing.*;
import java.awt.*;

class Game
{
    private Player blackStone, whiteStone, currentPlayer;

    public Game()
    {
        blackStone = new Player(Color.BLACK, this);
        whiteStone = new Player(Color.WHITE, this);
        currentPlayer = blackStone; // 처음에는 흑돌이 먼저 시작
    }

    // 현재 차례의 플레이어를 바꾼다.
    public void switchPlayer()
    {
        if (currentPlayer == blackStone)
            currentPlayer = whiteStone;
        else
            currentPlayer = blackStone;
    }

    public Player getCurrentPlayer()
    {
        return currentPlayer;
    }

    public void displayWinMessage(Color color, JFrame frame)
    {
        String winner = color == Color.BLACK ? "Black Stone" : "White Stone";
        int dialogResult = JOptionPane.showConfirmDialog(null, winner + " win!\nDo you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION)
            resetGame(frame);
        else
            System.exit(0); // 게임 종료
    }

    public void resetGame(JFrame frame)
    {
        Game game = new Game();
        Board board = new Board(game, frame);
        frame.setContentPane(board);
        frame.validate();
    }
}