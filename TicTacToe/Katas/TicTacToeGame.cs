using System.Text;

namespace Katas
{
    public class TicTacToeGame
    {
        int[,] board;
        int boardLength;
        const int Player1 = 0;
        const int Player2 = 1;
        const int EmptyCell = -1;
        GameStauts gameStauts;
        int? nextPlayerMoves = null;

        public TicTacToeGame()
        {
            this.boardLength = 3;
            this.board = new int[this.boardLength, this.boardLength];
            this.gameStauts = GameStauts.ContinueGame;
            this.nextPlayerMoves = Player1;
            for (int i = 0; i < this.boardLength; i++)
                for (int j = 0; j < this.boardLength; j++)
                {
                    this.board[i, j] = EmptyCell;
                    this.board[j, i] = EmptyCell;
                }
        }

        public GameStauts GetGameStatus
        {
            get
            {
                return this.gameStauts;
            }
        }

        bool WeHaveErrors
        {
            get
            {
                return (this.gameStauts == GameStauts.Error_CellAlreadyFilledIn ||
                        this.gameStauts == GameStauts.Error_WrongPlayerMoves ||
                        this.gameStauts == GameStauts.InputError_WrongCoordinateNumber);
            }
        }

        void PlayerMakesAMove(int x, int y, int player, int nextPlayer)
        {
            if (this.WeHaveErrors)
                return;

            if (this.nextPlayerMoves != player)
                this.gameStauts = GameStauts.Error_WrongPlayerMoves;

            if (x > 2 || x < 0 || y > 2 || y < 0)
            {
                this.gameStauts = GameStauts.InputError_WrongCoordinateNumber;
                return;
            }

            if (this.gameStauts == GameStauts.ContinueGame)
            {
                if (this.board[x, y] == EmptyCell)
                {
                    this.board[x, y] = player;

                    this.gameStauts = this.StatusOfTheGame();

                    if (gameStauts == GameStauts.ContinueGame)
                        this.nextPlayerMoves = nextPlayer;
                    else
                        this.nextPlayerMoves = EmptyCell;
                }
                else
                    this.gameStauts = GameStauts.Error_CellAlreadyFilledIn;
            }
        }

        public void Player1MakesAMove(int x, int y)
        {
            PlayerMakesAMove(x, y, Player1, Player2);
        }

        public void Player2MakesAMove(int x, int y)
        {
            PlayerMakesAMove(x, y, Player2, Player1);
        }

        GameStauts GetWinner(int cellValue)
        {
            if (cellValue == Player1)
                return GameStauts.WinnerPlayer1;
            if (cellValue == Player2)
                return GameStauts.WinnerPlayer2;

            return GameStauts.ContinueGame;
        }

        GameStauts StatusOfTheGame()
        {
            if (this.WeHaveErrors)
                return this.gameStauts;

            if (board[0, 0] == board[1, 1] && board[2, 2] == board[1, 1] && board[1, 1] != EmptyCell)
                return GetWinner(board[1, 1]);

            if (board[0, 2] == board[1, 1] && board[2, 0] == board[1, 1] && board[1, 1] != EmptyCell)
                return GetWinner(board[1, 1]);

            var status = GameStauts.NoWinner;
            for (int i = 0; i < this.boardLength; i++)
            {
                if (board[i, 0] == board[i, 1] && board[i, 2] == board[i, 1] && board[i, 0] != EmptyCell)
                    return GetWinner(board[i, 0]);
                if (board[0, i] == board[1, i] && board[2, i] == board[1, i] && board[0, i] != EmptyCell)
                    return GetWinner(board[i, 0]);
                    
                for (int j = 0; j < this.boardLength; j++)
                {
                    if (board[i, j] == EmptyCell)
                    { 
                        status = GameStauts.ContinueGame; 
                        break;
                    }
                }
            }

            return status;
        }

        public StringBuilder DrawBoard()
        {
            var result = new StringBuilder("");

            for (int i = 0; i < this.boardLength; i++)
            {
                result.AppendLine();
                for (int j = 0; j < this.boardLength; j++)
                {
                    if (board[i, j] > 0)
                        result.Append($"  {board[i, j]} ");
                    else
                        result.Append($" {board[i, j]} ");
                }
            }
            return result;
        }
    }
}