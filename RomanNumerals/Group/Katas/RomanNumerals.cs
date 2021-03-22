using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Katas
{
    internal class RomanNumerals
    {
        const int MAX_RANGE_VALUE = 4000;
        IDictionary<int, string> BaseRomanNumeralsDict = new Dictionary<int, string>() {
            { 1, "I"}, { 5, "V"}, { 10, "X"},
            {50, "L" }, {100, "C" }, {500, "D" }, {1000, "M" }
        };

        private int numericNr;
        private int minBaseNrToApplyToOperation;
        private int maxBaseNr_ForNumericNr;
        private int minBaseNr_ForNumericNr;

        public RomanNumerals(int numericNrParam)
        {
            this.numericNr = numericNrParam;
        }

        internal string RomanNumber_ConversionAlgorithm()
        {
            ValidateNumberRange();

            string romanRepresentation = GetRomanRepresentationIfIsBaseNr(this.numericNr);

            if (romanRepresentation != string.Empty) return romanRepresentation;

            var devidedNumberList = this.numericNr.GetDevidedNumberList();

            foreach (var numberPartItem in devidedNumberList)
            {
                romanRepresentation += ResolveNumberParts(numberPartItem);
            }

            return romanRepresentation;
        }

        void ValidateNumberRange()
        {
            if (this.numericNr < 1 || this.numericNr >= MAX_RANGE_VALUE)
                throw new ArgumentOutOfRangeException($"Number out of range. The number must be between 1 and {MAX_RANGE_VALUE}");
        }

        string GetRomanRepresentationIfIsBaseNr(int numberParam)
        {
            if (this.BaseRomanNumeralsDict.ContainsKey(numberParam))
            {
                return this.BaseRomanNumeralsDict[numberParam];
            }

            return string.Empty;
        }

        private string ResolveNumberParts(int numberParam)
        {
            var romanStrPart = GetRomanRepresentationIfIsBaseNr(numberParam);

            if (romanStrPart != string.Empty) return romanStrPart;

            GetMinAndMax_ToApplyToOperations(numberParam);

            romanStrPart = ResolveNumeralNr_IfSubstractOperation(numberParam);

            return romanStrPart != string.Empty ? romanStrPart : ResolveNumeralNr_IfAddOperation(numberParam);
        }

        void GetMinAndMax_ToApplyToOperations(int numberParam)
        {

            int minIndex = 0;
            var baseRomanNumeralsKeysList = this.BaseRomanNumeralsDict.Select(n => n.Key).OrderBy(n => n).ToList();
            for (int i = 1; i < baseRomanNumeralsKeysList.Count; i++)
            {
                if (numberParam > baseRomanNumeralsKeysList[i - 1] && numberParam < baseRomanNumeralsKeysList[i])
                {
                    minIndex = i - 1;
                    this.maxBaseNr_ForNumericNr = baseRomanNumeralsKeysList[i];
                    this.minBaseNr_ForNumericNr = baseRomanNumeralsKeysList[minIndex];
                    break;
                }

                if (numberParam > baseRomanNumeralsKeysList[i])
                {
                    minIndex = i;
                    this.minBaseNr_ForNumericNr = baseRomanNumeralsKeysList[minIndex];
                    this.maxBaseNr_ForNumericNr = MAX_RANGE_VALUE;
                }
            }

            this.minBaseNrToApplyToOperation = this.minBaseNr_ForNumericNr.ToString().StartsWith("5") ?
                                                    baseRomanNumeralsKeysList[minIndex - 1] :
                                                    this.minBaseNr_ForNumericNr;
        }

        string ResolveNumeralNr_IfSubstractOperation(int numberParam)
        {
            int substractValue = this.maxBaseNr_ForNumericNr - this.minBaseNrToApplyToOperation;

            if (numberParam == substractValue && this.BaseRomanNumeralsDict.ContainsKey(maxBaseNr_ForNumericNr))
                return $"{this.BaseRomanNumeralsDict[this.minBaseNrToApplyToOperation]}{this.BaseRomanNumeralsDict[this.maxBaseNr_ForNumericNr]}";

            return string.Empty;
        }

        string ResolveNumeralNr_IfAddOperation(int numberParam)
        {
            var romanStr = this.BaseRomanNumeralsDict[this.minBaseNr_ForNumericNr];
            var buildNumberFromBase  = this.minBaseNr_ForNumericNr;
            
            while (numberParam > buildNumberFromBase)
            {
                romanStr += this.BaseRomanNumeralsDict[this.minBaseNrToApplyToOperation];
                
                buildNumberFromBase += this.minBaseNrToApplyToOperation;
            }

            return romanStr;
        }
    }
}