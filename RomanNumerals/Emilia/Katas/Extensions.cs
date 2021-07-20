using System;
using System.Collections.Generic;

namespace Katas
{
    public static class Extensions
    {
        public static string ConvertToRomanNumber(this int numericNumber){
            RomanNumerals rn = new RomanNumerals(numericNumber);
            
            return rn.RomanNumber_ConversionAlgorithm();
        }


        public static List<int> GetDevidedNumberList(this int number)
        {
            var devidedNumberList = new List<int>();
            int aux = number;
            var strNumber = number.ToString();

            for (int i = 0; i < strNumber.Length; i++)
            {
                int pow = strNumber.Length - i - 1;
                int firstCharNr = aux / Convert.ToInt32(Math.Pow(10, pow)); // get the first part of our nr: 49/10^1 = 4
                int devidedNumber = firstCharNr * Convert.ToInt32(Math.Pow(10, pow)); // get the first part of the nr: 4 * 10^1 = 40


                aux = aux - devidedNumber; // get the remaining part to process: example 49 - 40 = 9
                if (devidedNumber > 0)
                    devidedNumberList.Add(devidedNumber);
            }

            return devidedNumberList;
        }

    }
}