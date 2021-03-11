using System;

namespace Katas
{
    public class RomanNumerals
    {
        static void Main() {   }

        public static string Convert(int x)
        {
            string result = "";

            string xString = x.ToString();

            int multiplier = (int)Math.Pow(10, xString.Length - 1);

            for (int i = 0; i < xString.Length; i++)
            {
                int unit = int.Parse(xString[i].ToString());
                AddUnitToResult(unit, multiplier, ref result);

                multiplier /= 10;
            }

            return result;
        }

        private static void AddUnitToResult(int unit, int multiplier, ref string result)
        {
            if (unit == 0)  // 0
            {
                return;
            }

            int valueOfUnit = unit * multiplier;

            var numeralSet = GetNumeralSet(valueOfUnit);
            string oneNumeral = numeralSet[0];
            string fiveNumeral = numeralSet[1];
            string tenNumeral = numeralSet[2];

            if (unit < 4)   // 1 - 3
            {
                for (int i = 1; i <= unit; i++)
                {
                    result += oneNumeral;
                }
            }
            else if (unit == 4) // 4
            {
                result += oneNumeral + fiveNumeral;
            }
            else if (unit < 9)  // 5 - 8
            {
                result += fiveNumeral;

                for (int i = 1; i <= (unit - 5); i++)
                {
                    result += oneNumeral;
                }
            }
            else // 9
            {
                result += oneNumeral + tenNumeral;
            }
        }

        private static string[] GetNumeralSet(int value)
        {
            if (value < 10)
            {
                return new string[] { "I", "V", "X" };
            }
            else if (value < 100)
            {
                return new string[] { "X", "L", "C" };
            }
            else if (value < 1000)
            {
                return new string[] { "C", "D", "M" };
            }
            else
            {
                return new string[] { "M", "", "" };
            }
        }
    }
}
