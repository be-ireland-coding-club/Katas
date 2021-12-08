using System;

namespace TicTacToe
{
    class Program
    {
        static void Main(string[] args)
        {
            var game = new TicTacToe();
            game.RenderGrid();
            var boardSize = game.GetTotalGridSpace();
            var counter = 0;

            char? winner = null;
            do
            {
                char currentPlayer;
                //set players turn
                if(counter % 2 == 0)
                {
                    Console.WriteLine("X's turn");
                    currentPlayer = 'X';
                }
                else
                {
                    Console.WriteLine("O's turn");
                    currentPlayer = 'O';
                }

                //take position input
                var positionSet = false;
                while (!positionSet)
                {
                    Console.WriteLine("Select grid position");
                    var input = Console.ReadLine();
                    if(int.TryParse(input.ToString(), out int position))
                    {
                        positionSet = game.SetPosition(position, currentPlayer);
                    }
                    else
                    {
                        Console.WriteLine("Invalid number");
                    }                    
                }
                game.RenderGrid();

                winner = game.FindVictor();
                counter++;
            }
            while (winner == null && counter != boardSize);

            if (winner.HasValue)
            {
                Console.WriteLine($"{winner} wins!");
            }
            else
            {
                Console.WriteLine("It's a draw!");
            }
            
        }
    }
}
