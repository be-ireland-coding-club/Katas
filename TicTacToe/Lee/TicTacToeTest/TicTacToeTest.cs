using NUnit.Framework;

namespace TicTacToeTest
{
    public class Tests
    {
        private TicTacToe.TicTacToe _game;
        [SetUp]
        public void Setup()
        {
            _game = new TicTacToe.TicTacToe();
        }

        #region Row Wins
        [Test]
        public void Row1_XWins()
        {
            _game.SetPosition(1, 'X');
            _game.SetPosition(4, 'O');
            _game.SetPosition(2, 'X');
            _game.SetPosition(5, 'O');
            _game.SetPosition(3, 'X');
            Assert.True(_game.FindVictor() == 'X');
        }

        [Test]
        public void Row2_XWins()
        {
            _game.SetPosition(4, 'X');
            _game.SetPosition(1, 'O');
            _game.SetPosition(5, 'X');
            _game.SetPosition(2, 'O');
            _game.SetPosition(6, 'X');
            Assert.True(_game.FindVictor() == 'X');
        }

        [Test]
        public void Row3_XWins()
        {
            _game.SetPosition(7, 'X');
            _game.SetPosition(1, 'O');
            _game.SetPosition(8, 'X');
            _game.SetPosition(2, 'O');
            _game.SetPosition(9, 'X');
            Assert.True(_game.FindVictor() == 'X');
        }

        [Test]
        public void Row1_OWins()
        {
            _game.SetPosition(1, 'O');
            _game.SetPosition(4, 'X');
            _game.SetPosition(2, 'O');
            _game.SetPosition(5, 'X');
            _game.SetPosition(3, 'O');
            Assert.True(_game.FindVictor() == 'O');
        }

        [Test]
        public void Row2_OWins()
        {
            _game.SetPosition(4, 'O');
            _game.SetPosition(1, 'X');
            _game.SetPosition(5, 'O');
            _game.SetPosition(2, 'X');
            _game.SetPosition(6, 'O');
            Assert.True(_game.FindVictor() == 'O');
        }

        [Test]
        public void Row3_OWins()
        {
            _game.SetPosition(7, 'O');
            _game.SetPosition(1, 'X');
            _game.SetPosition(8, 'O');
            _game.SetPosition(2, 'X');
            _game.SetPosition(9, 'O');
            Assert.True(_game.FindVictor() == 'O');
        }
        #endregion

        #region Column Wins
        [Test]
        public void Col1_XWins()
        {
            _game.SetPosition(1, 'X');
            _game.SetPosition(2, 'O');
            _game.SetPosition(4, 'X');
            _game.SetPosition(5, 'O');
            _game.SetPosition(7, 'X');
            Assert.True(_game.FindVictor() == 'X');
        }

        [Test]
        public void Col2_XWins()
        {
            _game.SetPosition(2, 'X');
            _game.SetPosition(1, 'O');
            _game.SetPosition(5, 'X');
            _game.SetPosition(2, 'O');
            _game.SetPosition(8, 'X');
            Assert.True(_game.FindVictor() == 'X');
        }

        [Test]
        public void Col3_XWins()
        {
            _game.SetPosition(3, 'X');
            _game.SetPosition(1, 'O');
            _game.SetPosition(6, 'X');
            _game.SetPosition(2, 'O');
            _game.SetPosition(9, 'X');
            Assert.True(_game.FindVictor() == 'X');
        }

        [Test]
        public void Col1_OWins()
        {
            _game.SetPosition(1, 'O');
            _game.SetPosition(2, 'X');
            _game.SetPosition(4, 'O');
            _game.SetPosition(5, 'X');
            _game.SetPosition(7, 'O');
            Assert.True(_game.FindVictor() == 'O');
        }

        [Test]
        public void Col2_OWins()
        {
            _game.SetPosition(2, 'O');
            _game.SetPosition(1, 'X');
            _game.SetPosition(5, 'O');
            _game.SetPosition(2, 'X');
            _game.SetPosition(8, 'O');
            Assert.True(_game.FindVictor() == 'O');
        }

        [Test]
        public void Col3_OWins()
        {
            _game.SetPosition(3, 'O');
            _game.SetPosition(1, 'X');
            _game.SetPosition(6, 'O');
            _game.SetPosition(2, 'X');
            _game.SetPosition(9, 'O');
            Assert.True(_game.FindVictor() == 'O');
        }

        #endregion

        #region Diagonal Wins
        [Test]
        public void DiagonalLeft_XWins()
        {
            _game.SetPosition(1, 'X');
            _game.SetPosition(2, 'O');
            _game.SetPosition(5, 'X');
            _game.SetPosition(8, 'O');
            _game.SetPosition(9, 'X');
            Assert.True(_game.FindVictor() == 'X');
        }

        public void DiagonalLeft_OWins()
        {
            _game.SetPosition(1, 'O');
            _game.SetPosition(2, 'X');
            _game.SetPosition(5, 'O');
            _game.SetPosition(8, 'X');
            _game.SetPosition(9, 'O');
            Assert.True(_game.FindVictor() == 'O');
        }

        public void DiagonalRight_XWins()
        {
            _game.SetPosition(3, 'X');
            _game.SetPosition(2, 'O');
            _game.SetPosition(5, 'X');
            _game.SetPosition(8, 'O');
            _game.SetPosition(7, 'X');
            Assert.True(_game.FindVictor() == 'X');
        }

        public void DiagonalRight_OWins()
        {
            _game.SetPosition(1, 'O');
            _game.SetPosition(2, 'X');
            _game.SetPosition(5, 'O');
            _game.SetPosition(8, 'X');
            _game.SetPosition(9, 'O');
            Assert.True(_game.FindVictor() == 'O');
        }
        #endregion
    }
}