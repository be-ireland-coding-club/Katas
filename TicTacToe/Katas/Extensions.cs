using System;
using System.Collections.Generic;

namespace Katas
{
    public static class Extensions
    {
        public static string GetStatusStr(this GameStauts gameStatus)
        {
            switch (gameStatus)
            {
                case GameStauts.ContinueGame: return "ContinueGame";
                case GameStauts.NoWinner: return "NoWinner";
                case GameStauts.WinnerPlayer1: return "WinnerPlayer1";
                case GameStauts.WinnerPlayer2: return "WinnerPlayer2";
                case GameStauts.Error_CellAlreadyFilledIn: return "Error_CellAlreadyFilledIn";
                case GameStauts.Error_WrongPlayerMoves: return "Error_WrongPlayerMoves";
                case GameStauts.InputError_WrongCoordinateNumber: return "InputError_WrongCoordinateNumber";
                default: return "Unknown Status";
            }
        }
    }
}