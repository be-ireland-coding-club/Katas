using System;
using System.Collections.Generic;

namespace Katas
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Welcome to Tic Tac Toe Game");
            Console.WriteLine("The game allows a table of 3x3 and the coordinates allow the following values: 0,1 and 2");

            var game = new TicTacToeGame();
            int xP1 = -1;
            int yP1= -1;
            int xP2 = -1;
            int yP2 = -1;
            var gameStatus = game.GetGameStatus;

            while (gameStatus == GameStauts.ContinueGame)
            {

                Console.Write("Player 1 coordinate x: ");
                Int32.TryParse(Console.ReadLine(), out xP1);

                Console.Write("Player 1 coordinate y: ");
                Int32.TryParse(Console.ReadLine(), out yP1);

                game.Player1MakesAMove(xP1, yP1);
                gameStatus = game.GetGameStatus;
                if(game.GetGameStatus != GameStauts.ContinueGame)  
                    break;

                Console.Write("Player 2 coordinate x: ");
                Int32.TryParse(Console.ReadLine(), out xP2);
                Console.Write("Player 2 coordinate y: ");
                Int32.TryParse(Console.ReadLine(), out yP2);

                game.Player2MakesAMove(xP2, yP2);
                gameStatus = game.GetGameStatus;
                if (game.GetGameStatus != GameStauts.ContinueGame)
                    break;
            }

            Console.WriteLine($"Game status: {gameStatus.GetStatusStr()}");
            Console.WriteLine($"{game.DrawBoard() }");
        }

    }
}
