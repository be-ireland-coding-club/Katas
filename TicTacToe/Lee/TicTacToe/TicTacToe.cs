using System;
using System.Linq;
using System.Text;

namespace TicTacToe
{
    public class TicTacToe
    {
        private char?[,] _board;

        public TicTacToe()
        {
            _board = new char?[3, 3];
        }

        public void RenderGrid()
        {
            var sb = new StringBuilder();

            for(var row = 0; row < _board.GetLength(0); row++)
            {
                for(var col = 0; col < _board.GetLength(1); col++)
                {
                    //set value or numbered position on grid
                    var value = _board[row, col] ?? (col+1+(row*3)).ToString()[0];
                    sb.Append($"{value} ");
                }
                sb.Append(Environment.NewLine);
            }

            Console.WriteLine(sb.ToString());
        }

        public bool SetPosition(int position, char player)
        {
            if(position > _board.Length || position < 0)
            {
                Console.WriteLine("Selected position must be a numeric value from the board");
                return false;
            }
            position--;
            var col = position % _board.GetLength(1);
            var row = position / _board.GetLength(0);

            if (IsPositionTaken(row, col))
            {
                Console.WriteLine("That position is taken. Please select an unused position");
                return false;
            }

            _board[row, col] = player;
            return true;
        }

        private bool IsPositionTaken(int row, int col)
        {
            return _board[row, col] != null;
        }

        public char? FindVictor()
        {
            //check row victory
            for(var row = 0; row < _board.GetLength(0); row++)
            {
                if (GetRow(row).All(value => value == 'X'))
                {
                    return 'X';
                }

                if (GetRow(row).All(value => value == 'O'))
                {
                    return 'O';
                }
            }

            //check col victory
            for (var col = 0; col < _board.GetLength(0); col++)
            {
                if (GetColumn(col).All(value => value == 'X'))
                {
                    return 'X';
                }

                if (GetColumn(col).All(value => value == 'O'))
                {
                    return 'O';
                }
            }

            //check diagonal victory
            var topLeftDiagonal = GetTopLeftDiagonal();
            if(topLeftDiagonal.All(value => value == 'X'))
            {
                return 'X';
            }

            if (topLeftDiagonal.All(value => value == 'O'))
            {
                return 'O';
            }

            var topRightDiagonal = GetTopRightDiagonal();
            if (topRightDiagonal.All(value => value == 'X'))
            {
                return 'X';
            }

            if (topRightDiagonal.All(value => value == 'O'))
            {
                return 'O';
            }

            return null;
        }

        private char?[] GetRow(int row)
        {
            return Enumerable.Range(0, _board.GetLength(0))
                .Select(x => _board[row, x])
                .ToArray();
        }

        private char?[] GetColumn(int col)
        {
            return Enumerable.Range(0, _board.GetLength(0))
                .Select(x => _board[x, col])
                .ToArray();
        }

        private char?[] GetTopLeftDiagonal()
        {
            char?[] diagonal = new char?[_board.GetLength(0)];
            for(var i = 0; i < _board.GetLength(0); i++)
            {
                diagonal[i] = _board[i, i];
            }

            return diagonal;
        }

        private char?[] GetTopRightDiagonal()
        {
            char?[] diagonal = new char?[_board.GetLength(0)];
            for (var col = _board.GetLength(1)-1; col >= 0; col--)
            {
                diagonal[col] = _board[_board.GetLength(0)-1-col,col];
            }

            return diagonal;
        }

        public int GetTotalGridSpace()
        {
            return _board.Length;
        }
    }
}
