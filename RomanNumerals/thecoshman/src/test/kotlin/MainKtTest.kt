import org.junit.jupiter.api.Assertions.*

internal class MainKtTest {
    @org.junit.jupiter.api.Test
    fun basicValues() {
        assertEquals("I", toRomanNumeral(1))
        assertEquals("V", toRomanNumeral(5))
        assertEquals("X", toRomanNumeral(10))
        assertEquals("L", toRomanNumeral(50))
        assertEquals("C", toRomanNumeral(100))
        assertEquals("D", toRomanNumeral(500))
        assertEquals("M", toRomanNumeral(1000))
    }

    @org.junit.jupiter.api.Test
    fun doubleAndTriples() {
        assertEquals("II", toRomanNumeral(2))
        assertEquals("XX", toRomanNumeral(20))
        assertEquals("CC", toRomanNumeral(200))
        assertEquals("MM", toRomanNumeral(2000))
        assertEquals("III", toRomanNumeral(3))
        assertEquals("XXX", toRomanNumeral(30))
        assertEquals("CCC", toRomanNumeral(300))
        assertEquals("MMM", toRomanNumeral(3000))
    }

    @org.junit.jupiter.api.Test
    fun borrowOne() {
        assertEquals("IV", toRomanNumeral(4))
        assertEquals("IX", toRomanNumeral(9))
        assertEquals("XL", toRomanNumeral(40))
        assertEquals("XC", toRomanNumeral(90))
        assertEquals("CD", toRomanNumeral(400))
        assertEquals("CM", toRomanNumeral(900))
    }

    @org.junit.jupiter.api.Test
    fun randomValues() {
        assertEquals("MMXXI", toRomanNumeral(2021))
        assertEquals("CMXXXVIII", toRomanNumeral(938))
        assertEquals("DCCXXXV", toRomanNumeral(735))
        assertEquals("DCLXXXVII", toRomanNumeral(687))
        assertEquals("DCCCXXV", toRomanNumeral(825))
        assertEquals("XXI", toRomanNumeral(21))
        assertEquals("CXC", toRomanNumeral(190))
        assertEquals("CCCLXXV", toRomanNumeral(375))
        assertEquals("DCX", toRomanNumeral(610))
        assertEquals("MMMMMMMMMM", toRomanNumeral(10000))
        assertEquals("CMXCIX", toRomanNumeral(999))
        assertEquals("MMMMMMMMMCMXCIX", toRomanNumeral(9999))
    }


    @org.junit.jupiter.api.Test
    fun zero() {
        assertEquals("", toRomanNumeral(0))
    }
}