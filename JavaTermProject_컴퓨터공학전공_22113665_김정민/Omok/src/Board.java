import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class Board extends JPanel implements MouseListener
{
    public static final int SIZE = 19;   // 오목판 행과 열의 크기, 1번째 부터 19번째까지
    private Color[][] omokBoard;    // 오목판
    private Game game;
    private JFrame frame;

    public Board(Game game, JFrame frame)
    {
        this.game = game;
        omokBoard = new Color[SIZE][SIZE];  // 오목판 크기 19*19, null로 초기화
        setBackground(new Color(172, 124, 102));    // 오목판 색 지정
        this.addMouseListener(this);    // 마우스 리스너 등록
        this.frame = frame;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;    // 오목판 무늬를 그리기 위해 Graphics2D로 변환
        g2d.setStroke(new BasicStroke(2));  // 선 굵기를 2픽셀

        // 오목판 좌표 모두 순회(행과 열 0~18)
        for (int y = 0; y < SIZE; y++)
        {
            for (int x = 0; x < SIZE; x++)
            {
                // 1번째부터 19번째 까지의 행과 열을 가진 오목판의 그리기
                if (y < SIZE - 1 && x < SIZE - 1)
                    g2d.drawRect(x * 40 + 20, y * 40 + 20, 40, 40);

                // 해당 오목판 좌표에 돌이 있는지 확인
                if (omokBoard[y][x] != null)
                {
                    g.setColor(omokBoard[y][x]);    // 돌의 색상
                    g.fillOval(x * 40, y * 40, 36, 36); // 돌의 지름이 36
                    g.setColor(Color.BLACK);    // 오목판 무늬 색깔
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        int y = e.getY() / 40;
        int x = e.getX() / 40;
        if (y < SIZE && x < SIZE)
        {
            Player currentPlayer = game.getCurrentPlayer();
            if (currentPlayer.play(y, x,this))
                game.switchPlayer();
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

    // 오목판에 돌을 그린다.
    public boolean drawStone(int y, int x, Color color)
    {
        // 이미 돌이 그려져있는지 확인
        if (omokBoard[y][x] == null)
        {
            omokBoard[y][x] = color;
            repaint();  // 다시 paintComponent()
            // 승리하면 다시 시작할지 종료할지 확인
            if (checkWin(y, x, color))
                game.displayWinMessage(color, frame);
            return true;
        }
        else
            return false;
    }
    // 승리 조건을 확인
    public boolean checkWin(int y, int x, Color color)
    {
        return checkHorizontalWin(y, x, color) || checkVerticalWin(y, x, color) || checkDiagonalLeftToRightWin(y, x, color) || checkDiagonalRightToLeftWin(y, x, color);
    }

    // 가로줄 검사
    public boolean checkHorizontalWin(int y, int x, Color color)
    {
        int count = 1;

        // 오른쪽 검사→
        for (int nextX = x + 1; nextX < SIZE; nextX++)
        {
            if (color == omokBoard[y][nextX])
            {
                if (++count >= 5)
                    return true;
            }
            else
                break;
        }

        // 왼쪽 검사←
        for (int nextX = x - 1; nextX >= 0; nextX--)
        {
            if (color == omokBoard[y][nextX])
            {
                if (++count >= 5)
                    return true;
            }
            else
                break;
        }

        return false;
    }

    // 세로줄 검사
    public boolean checkVerticalWin(int y, int x, Color color)
    {
        int count = 1;

        // 위쪽 검사↑
        for (int nextY = y + 1; nextY < SIZE; nextY++)
        {
            if (color == omokBoard[nextY][x])
            {
                if (++count >= 5)
                    return true;
            }
            else
                break;
        }

        // 아래쪽 검사↓
        for (int nextY = y - 1; nextY >= 0; nextY--)
        {
            if (color == omokBoard[nextY][x])
            {
                if (++count >= 5)
                    return true;
            }
            else
                break;
        }

        return false;
    }

    // 대각선 검사1
    public boolean checkDiagonalLeftToRightWin(int y, int x, Color color)
    {
        int count = 1;

        int nextY = y - 1;
        int nextX = x + 1;
        // ↗
        while (count < 5 && nextY >= 0 && nextX < SIZE)
        {
            if (color == omokBoard[nextY--][nextX++])
            {
                if (++count >= 5)
                    return true;
            }
            else
                break;
        }

        nextY = y + 1;
        nextX = x - 1;
        // ↙
        while (count < 5 && nextY < SIZE && nextX >= 0)
        {
            if (color == omokBoard[nextY++][nextX--])
            {
                if (++count >= 5)
                    return true;
            }
            else
                break;
        }

        return false;
    }

    // 대각선 검사2
    public boolean checkDiagonalRightToLeftWin(int y, int x, Color color)
    {
        int count = 1;

        int nextY = y + 1;
        int nextX = x + 1;
        // ↖
        while (count < 5 && nextY < SIZE && nextX < SIZE)
        {
            if (color == omokBoard[nextY++][nextX++])
            {
                if (++count >= 5)
                    return true;
            }
            else
                break;
        }

        nextY = y - 1;
        nextX = x - 1;
        // ↘
        while (count < 5 && nextY >= 0 && nextX >= 0)
        {
            if (color == omokBoard[nextY--][nextX--])
            {
                if (++count >= 5)
                    return true;
            }
            else break;
        }

        return false;
    }
}