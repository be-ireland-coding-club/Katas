using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;

namespace ChristmasLightsTest
{
    [TestClass]
    public class ChristmasLightsTest
    {
        private ChristmasLights.ChristmasLights _christmasLights;

        [TestInitialize]
        public void Setup()
        {
            _christmasLights = new ChristmasLights.ChristmasLights();
        }

        private void SetUpDefault()
        {
            _christmasLights.DefaultLights();
        }

        [TestMethod]
        public void ValidateDefaultGridValues()
        {
            SetUpDefault();

            Assert.AreEqual(_christmasLights.TotalLightsOn(), 0);
            Assert.AreEqual(_christmasLights.TotalBrightness(), 0);
        }

        [TestMethod]
        public void ParseInstructions_ThrowsException()
        {
            Assert.ThrowsException<Exception>(() => _christmasLights.ParseInstructions("No lights should turn on because I am not in the correct format."));
        }

        [TestMethod]
        public void ParseInstructions_ShouldTurnOnAllLights()
        {
            SetUpDefault();
            _christmasLights.ParseInstructions("turn on 0,0 through 999,999");

            Assert.AreEqual(_christmasLights.TotalLightsOn(), 1000000);
            Assert.AreEqual(_christmasLights.TotalBrightness(), 1000000);
        }

        [TestMethod]
        public void ParseInstructions_ShouldToggleFirstRow()
        {
            SetUpDefault();
            _christmasLights.ParseInstructions("toggle 0,0 through 999,0");

            Assert.AreEqual(_christmasLights.TotalLightsOn(), 1000);
            Assert.AreEqual(_christmasLights.TotalBrightness(), 2000);
        }

        [TestMethod]
        public void ParseInstructions_ShouldTurnOnAndOffAllLights()
        {
            SetUpDefault();
            _christmasLights.ParseInstructions("turn on 0,0 through 999,999");

            Assert.AreEqual(_christmasLights.TotalLightsOn(), 1000000);
            Assert.AreEqual(_christmasLights.TotalBrightness(), 1000000);

            _christmasLights.ParseInstructions("turn off 0,0 through 999,999");

            Assert.AreEqual(_christmasLights.TotalLightsOn(), 0);
            Assert.AreEqual(_christmasLights.TotalBrightness(), 0);
        }
    }
}
