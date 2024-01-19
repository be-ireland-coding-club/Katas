fun toRomanNumeral(input: Int) = buildString {
    var remainder = input

    // A lambda function used to handle the logic of converting units, tens or hundreds
    val processPart = { scale: Int, tens: Char, fives: Char, ones: Char ->
        val digit = remainder / scale
        when (digit) {
            1,2,3 -> repeat(digit) { append(ones) }
            4 -> append(ones).append(fives) // Could get fancy and have option for doing "IIII"
            5 -> append(fives)
            6,7,8 -> {
                append(fives)
                repeat(digit - 5) { append(ones) }
            }
            9 -> append(ones).append(tens)
        }
        remainder -= digit * scale
    }

    // Handle the thousands first specifically
    repeat(remainder / 1000) {
        append('M')
        remainder -= 1000
    }
    // hundreds, tens and units are processed with the same logic, just difference in scales and symbols
    processPart(100, 'M', 'D', 'C')
    processPart( 10, 'C', 'L', 'X')
    processPart(  1, 'X', 'V', 'I')
}

fun main(args: Array<String>) {
}
