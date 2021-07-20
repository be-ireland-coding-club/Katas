using System;
using System.Collections.Generic;
using System.Text;

namespace Katas
{
    public class RomanNumerals
    {

        public static Dictionary<int, string> romanNumeral = new Dictionary<int, string>() {
            { 1, "I"},
            { 5, "V"},
            { 10, "X"},
            {50, "L" },
            {100, "C" },
            {500, "D" },
            {1000, "M" }
        };

        public static string Convert(int x)
        {
            string romanNumeralString = "";

            //switch (x)
            //{
            //    case 1:
            //        return "I";
            //    case 2:
            //        return "II";
            //    default:
            //        throw new Exception();
            //}

            if (romanNumeral.ContainsKey(x))
            {
                return romanNumeral[x];
            }

            //49 - XLIX
           // for( int i=0; i < romanNumeral.Count; i++)
           // {   
           //
           //     int modolus = x % romanNumeral.Keys; //Return all keys in dictonary, and get remainder
           //     
           //     x = x - modolus;
           //
           //
           // }

            if (x / 1000 > 0)
            {
                romanNumeralString += "M";
                int remainder = x % 1000;

            }


           // Centea, Emilia
           // start from number x and get the string length(49-> 2) have a list to put the 2 for int i = 1 to 2y = x % 10if (y == 0) x = x / 10else l.add(y)
           //
           // L = {​​40, 9 }​​ // 49
           //
           // [13:27] Centea, Emilia
           // l = {​​400 }​​ //400
           //
           // [13:27] Centea, Emilia
           // L = {​​400, 80, 7 }​​//487
           //
           // [13:28] Centea, Emilia
           // N = {​​XL, IX }​​



            throw new Exception("");
        }
    }
}