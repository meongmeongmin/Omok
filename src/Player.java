import java.awt.*;

class Player
{
    private Stone stoneColor;
    private Game game;

    public Player(Color stoneColor, Game game)
    {
        this.stoneColor = new Stone(stoneColor);
        this.game = game;
    }

    public Color getStoneColor()
    {
        return stoneColor.getColor();
    }

    // 플레이어가 돌을 놓는다.
    public boolean play(int y, int x, Board board)
    {
        return board.drawStone(y, x, getStoneColor());
    }
}