using Xunit;
using Katas;
using System;
using System.Collections.Generic;

namespace KatasTests
{
    public class TicTacToeUnitTests
    {

        [Fact]
        public void TestGame_WhenPlayer1Wins()
        {
            //Arrange
            var game = new TicTacToeGame();

            //Act
            game.Player1MakesAMove(1, 1);
            game.Player2MakesAMove(2, 1);

            game.Player1MakesAMove(0, 0);
            game.Player2MakesAMove(2, 2);

            game.Player1MakesAMove(2, 0);
            game.Player2MakesAMove(1, 2);

            game.Player1MakesAMove(0, 2);

            var result = game.GetGameStatus;

            // Assert
            Assert.True(result == Katas.GameStauts.WinnerPlayer1);

            Console.WriteLine("TestGame_WhenPlayer1Wins");
            Console.WriteLine($"{game.DrawBoard() }");
        }

        [Fact]
        public void TestGame_WhenPlayer2Wins()
        {
            //Arrange
            var game = new TicTacToeGame();

            //Act
            game.Player1MakesAMove(1, 1);
            game.Player2MakesAMove(2, 1);

            game.Player1MakesAMove(0, 0);
            game.Player2MakesAMove(2, 2);

            game.Player1MakesAMove(1, 2);
            game.Player2MakesAMove(2, 0);

            var result = game.GetGameStatus;

            // Assert
            Assert.True(result== Katas.GameStauts.WinnerPlayer2);

            Console.WriteLine("TestGame_WhenPlayer2Wins");
            Console.WriteLine($"{game.DrawBoard() }");
        }

        [Fact]
        public void TestGame_WhenThereIsNoWinner()
        {
            //Arrange
            var game = new TicTacToeGame();

            //Act
            game.Player1MakesAMove(1, 1);
            game.Player2MakesAMove(2, 1);

            game.Player1MakesAMove(2, 0);
            game.Player2MakesAMove(0, 2);

            game.Player1MakesAMove(1, 0);
            game.Player2MakesAMove(0, 0);

            game.Player1MakesAMove(0, 1);
            game.Player2MakesAMove(1, 2);

            game.Player1MakesAMove(2, 2);

            var result = game.GetGameStatus;

            // Assert
            Assert.True(result == Katas.GameStauts.NoWinner);

            Console.WriteLine("TestGame_WhenThereIsNoWinner");
            Console.WriteLine($"{game.DrawBoard() }");
        }

        [Fact]
        public void TestGame_WhenWrongPlayerMoves()
        {
            //Arrange
            var game = new TicTacToeGame();

            //Act
            game.Player1MakesAMove(1, 1);
            game.Player2MakesAMove(2, 1);

            game.Player1MakesAMove(0, 0);
            game.Player1MakesAMove(2, 2);

            var result = game.GetGameStatus;

            // Assert
            Assert.True(result == Katas.GameStauts.Error_WrongPlayerMoves);

            Console.WriteLine("TestGame_WhenWrongPlayerMoves");
            Console.WriteLine($"{game.DrawBoard() }");
        }

        [Fact]
        public void TestGame_WhenCellAlreadyFilledIn()
        {
            //Arrange
            var game = new TicTacToeGame();

            //Act
            game.Player1MakesAMove(1, 1);
            game.Player2MakesAMove(2, 1);

            game.Player1MakesAMove(0, 0);
            game.Player2MakesAMove(1, 1);

            var result = game.GetGameStatus;

            // Assert
            Assert.True(result == Katas.GameStauts.Error_CellAlreadyFilledIn);

            Console.WriteLine($"TestGame_WhenCellAlreadyFilledIn");
            Console.WriteLine($"{game.DrawBoard() }");
        }
    }
}