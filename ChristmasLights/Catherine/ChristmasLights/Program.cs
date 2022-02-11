using System;
using System.IO;
using System.Linq;
using System.Reflection;

namespace ChristmasLights
{
    class Program
    {
        static void Main(string[] args)
        {
            ChristmasLights christmasLights = new ChristmasLights();
            christmasLights.DefaultLights();

            Console.WriteLine("Default lights set...\n");

            string instructionsFile = Path.Combine(Path.GetDirectoryName(Assembly.GetExecutingAssembly().Location), @"Instructions.txt");
            string[] instructions = File.ReadAllLines(instructionsFile);

            foreach (var instruction in instructions.Select((value, index) => new { index, value }))
            {
                christmasLights.ParseInstructions(instruction.value);
            }

            Console.WriteLine("\nCheck your result?");
            Console.ReadKey();
            Console.WriteLine("There are " + christmasLights.TotalLightsOn() + " turned on.");
            Console.WriteLine("Total brightness of all lights: " + christmasLights.TotalBrightness());

            Console.Read();
        }
    }
}