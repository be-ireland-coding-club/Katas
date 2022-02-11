using System;
using System.Text.RegularExpressions;

namespace ChristmasLights
{
    public class ChristmasLights
    {
        private LightBulb[,] _lightGrid;

        public ChristmasLights()
        {
            _lightGrid = new LightBulb[1000, 1000];
        }

        public class LightBulb
        {
            public bool LightSwitch;
            public int Brightness;

            public LightBulb()
            {
                LightSwitch = false;
                Brightness = 0;
            }
        }

        public void DefaultLights()
        {
           for (int row = 0; row < _lightGrid.GetLength(0); row++)
           {
               for (int column = 0; column < _lightGrid.GetLength(1); column++)
               {
                   _lightGrid[row, column] = new LightBulb();
                   
                   var bulbValue = _lightGrid[row, column].LightSwitch;
               }
           }
        }

        public int TotalLightsOn()
        {
            int lightCounter = 0;
            foreach (LightBulb bulb in _lightGrid)
            {
                if (bulb.LightSwitch)
                {
                    lightCounter++;
                }
            }

            return lightCounter;
        }

        public int TotalBrightness()
        {
            int brightnessCounter = 0;
            foreach (LightBulb bulb in _lightGrid)
            {
                if (bulb.Brightness > 0)
                {
                    brightnessCounter += bulb.Brightness;
                }
            }

            return brightnessCounter;
        }

        private void TurnOn(int fromXGroup, int fromYGroup, int toXGroup, int toYGroup)
        {
            for (int row = fromXGroup; row < toXGroup + 1; row++)
            {
                for (int column = fromYGroup; column < toYGroup + 1; column++)
                {
                    _lightGrid[row, column].LightSwitch = true;
                    _lightGrid[row, column].Brightness++;
                }
            }
        }

        private void TurnOff(int fromXGroup, int fromYGroup, int toXGroup, int toYGroup)
        {
            for (int row = fromXGroup; row < toXGroup + 1; row++)
            {
                for (int column = fromYGroup; column < toYGroup + 1; column++)
                {
                    _lightGrid[row, column].LightSwitch = false;

                    _lightGrid[row, column].Brightness = _lightGrid[row, column].Brightness > 0
                        ? --_lightGrid[row, column].Brightness
                        : _lightGrid[row, column].Brightness;
                }
            }
        }

        private void Toggle(int fromXGroup, int fromYGroup, int toXGroup, int toYGroup)
        {
            for (int row = fromXGroup; row < toXGroup + 1; row++)
            {
                for (int column = fromYGroup; column < toYGroup + 1; column++)
                {
                    var bulbValue = _lightGrid[row, column].LightSwitch ? _lightGrid[row, column].LightSwitch = false : _lightGrid[row, column].LightSwitch = true;
                    
                    _lightGrid[row, column].Brightness += 2;
                }
            }
        }

        public void ParseInstructions(string instruction)
        {
            Console.WriteLine("\nInstruction Passed: " + instruction);
            Regex regexToMatch = new Regex(Constants.InstructionsRegex, RegexOptions.Compiled);
            Match match = regexToMatch.Match(instruction);

            var groups = match.Groups;
            
            // Group 1 is the light switch, 0 is the instruction passed
            if (groups[1].Value == "turn on")
            {
                TurnOn(int.Parse(groups[2].Value), int.Parse(groups[3].Value), int.Parse(groups[4].Value), int.Parse(groups[5].Value));
            }
            else if (groups[1].Value == "turn off")
            {
                TurnOff(int.Parse(groups[2].Value), int.Parse(groups[3].Value), int.Parse(groups[4].Value), int.Parse(groups[5].Value));

            }
            else if(groups[1].Value == "toggle")
            {
                Toggle(int.Parse(groups[2].Value), int.Parse(groups[3].Value), int.Parse(groups[4].Value), int.Parse(groups[5].Value));
            }
            else
            {
                Console.WriteLine($"You have misunderstood Santas instructions: {groups[0].Value}");
                Console.WriteLine($"Value passed: {instruction}");
                throw new Exception("An error has occurred parsing the instructions...");
            }
        }
    }
}