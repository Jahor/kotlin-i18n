package info.leonenko.i18n.rbnf

import kotlin.test.Test
import kotlin.test.assertEquals

class RBNFUkTest {
    @Test
    fun spelloutCardinalFeminine() {
        val fmt = RBNFUk().spelloutCardinalFeminine
        assertEquals("мінус одна", fmt.format(-1), "-1")
        assertEquals("нуль", fmt.format(0), "0")
        assertEquals("нуль кома дві", fmt.format(0.2), "0.2")
        assertEquals("нуль кома дві три чотири пʼять", fmt.format(0.2345), "0.2345")
        assertEquals("нуль кома нуль дві", fmt.format(0.02), "0.02")
        assertEquals("нуль кома нуль дві одна три", fmt.format(0.0213), "0.0213")
        assertEquals("чотири кома сім пʼять одна", fmt.format(4.751), "4.751")
        assertEquals("дванадцять кома нуль пʼять одна", fmt.format(12.051), "12.051")
        assertEquals("тринадцять кома нуль дві пʼять одна", fmt.format(13.0251), "13.0251")
        assertEquals("чотирнадцять кома нуль дві дві пʼять одна", fmt.format(14.02251), "14.02251")
        assertEquals("одна", fmt.format(1), "1")
        assertEquals("дві", fmt.format(2), "2")
        assertEquals("три", fmt.format(3), "3")
        assertEquals("чотири", fmt.format(4), "4")
        assertEquals("пʼять", fmt.format(5), "5")
        assertEquals("шість", fmt.format(6), "6")
        assertEquals("сім", fmt.format(7), "7")
        assertEquals("вісім", fmt.format(8), "8")
        assertEquals("девʼять", fmt.format(9), "9")
        assertEquals("десять", fmt.format(10), "10")
        assertEquals("одинадцять", fmt.format(11), "11")
        assertEquals("дванадцять", fmt.format(12), "12")
        assertEquals("тринадцять", fmt.format(13), "13")
        assertEquals("чотирнадцять", fmt.format(14), "14")
        assertEquals("пʼятнадцять", fmt.format(15), "15")
        assertEquals("шістнадцять", fmt.format(16), "16")
        assertEquals("сімнадцять", fmt.format(17), "17")
        assertEquals("вісімнадцять", fmt.format(18), "18")
        assertEquals("девʼятнадцять", fmt.format(19), "19")
        assertEquals("двадцять", fmt.format(20), "20")
        assertEquals("двадцять одна", fmt.format(21), "21")
        assertEquals("двадцять дві", fmt.format(22), "22")
        assertEquals("двадцять три", fmt.format(23), "23")
        assertEquals("двадцять чотири", fmt.format(24), "24")
        assertEquals("двадцять пʼять", fmt.format(25), "25")
        assertEquals("двадцять шість", fmt.format(26), "26")
        assertEquals("двадцять сім", fmt.format(27), "27")
        assertEquals("двадцять вісім", fmt.format(28), "28")
        assertEquals("двадцять девʼять", fmt.format(29), "29")
        assertEquals("тридцять", fmt.format(30), "30")
        assertEquals("тридцять одна", fmt.format(31), "31")
        assertEquals("девʼяносто вісім", fmt.format(98), "98")
        assertEquals("девʼяносто девʼять", fmt.format(99), "99")
        assertEquals("сто", fmt.format(100), "100")
        assertEquals("сто одна", fmt.format(101), "101")
        assertEquals("сто дві", fmt.format(102), "102")
        assertEquals("девʼятсот девʼяносто вісім", fmt.format(998), "998")
        assertEquals("девʼятсот девʼяносто девʼять", fmt.format(999), "999")
        assertEquals("одна тисяча", fmt.format(1000), "1000")
        assertEquals("одна тисяча одна", fmt.format(1001), "1001")
        assertEquals("одна тисяча дві", fmt.format(1002), "1002")
        assertEquals("одна тисяча девʼятсот девʼяносто вісім", fmt.format(1998), "1998")
        assertEquals("одна тисяча девʼятсот девʼяносто девʼять", fmt.format(1999), "1999")
        assertEquals("дві тисячі", fmt.format(2000), "2000")
        assertEquals("дві тисячі одна", fmt.format(2001), "2001")
        assertEquals("дві тисячі дві", fmt.format(2002), "2002")
        assertEquals("девʼять тисяч девʼятсот девʼяносто вісім", fmt.format(9998), "9998")
        assertEquals("девʼять тисяч девʼятсот девʼяносто девʼять", fmt.format(9999), "9999")
        assertEquals("десять тисяч", fmt.format(10000), "10000")
        assertEquals("десять тисяч одна", fmt.format(10001), "10001")
        assertEquals("десять тисяч дві", fmt.format(10002), "10002")
        assertEquals("сто тисяч", fmt.format(100000), "100000")
        assertEquals("сто тисяч одна", fmt.format(100001), "100001")
        assertEquals("сто тисяч дві", fmt.format(100002), "100002")
        assertEquals("двісті тисяч", fmt.format(200000), "200000")
        assertEquals("двісті тисяч одна", fmt.format(200001), "200001")
        assertEquals("двісті тисяч дві", fmt.format(200002), "200002")
        assertEquals("один мільйон", fmt.format(1000000), "1000000")
        assertEquals("один мільйон одна", fmt.format(1000001), "1000001")
        assertEquals("один мільйон дві", fmt.format(1000002), "1000002")
        assertEquals("два мільйони", fmt.format(2000000), "2000000")
        assertEquals("два мільйони одна", fmt.format(2000001), "2000001")
        assertEquals("два мільйони дві", fmt.format(2000002), "2000002")
        assertEquals("десять мільйонів", fmt.format(10000000), "10000000")
        assertEquals("десять мільйонів одна", fmt.format(10000001), "10000001")
        assertEquals("десять мільйонів дві", fmt.format(10000002), "10000002")
        assertEquals("двадцять мільйонів", fmt.format(20000000), "20000000")
        assertEquals("двадцять мільйонів одна", fmt.format(20000001), "20000001")
        assertEquals("двадцять мільйонів дві", fmt.format(20000002), "20000002")
        assertEquals("сто мільйонів", fmt.format(100000000), "100000000")
        assertEquals("сто мільйонів одна", fmt.format(100000001), "100000001")
        assertEquals("сто мільйонів дві", fmt.format(100000002), "100000002")
        assertEquals("двісті мільйонів", fmt.format(200000000), "200000000")
        assertEquals("двісті мільйонів одна", fmt.format(200000001), "200000001")
        assertEquals("двісті мільйонів дві", fmt.format(200000002), "200000002")
        assertEquals("один мільярд", fmt.format(1000000000), "1000000000")
        assertEquals("один мільярд одна", fmt.format(1000000001), "1000000001")
        assertEquals("один мільярд дві", fmt.format(1000000002), "1000000002")
        assertEquals("два мільярди", fmt.format(2000000000), "2000000000")
        assertEquals("два мільярди одна", fmt.format(2000000001), "2000000001")
        assertEquals("два мільярди дві", fmt.format(2000000002), "2000000002")
    }

    @Test
    fun spelloutCardinalNeuter() {
        val fmt = RBNFUk().spelloutCardinalNeuter
        assertEquals("мінус одне", fmt.format(-1), "-1")
        assertEquals("нуль", fmt.format(0), "0")
        assertEquals("нуль кома два", fmt.format(0.2), "0.2")
        assertEquals("нуль кома два три чотири пʼять", fmt.format(0.2345), "0.2345")
        assertEquals("нуль кома нуль два", fmt.format(0.02), "0.02")
        assertEquals("нуль кома нуль два одне три", fmt.format(0.0213), "0.0213")
        assertEquals("чотири кома сім пʼять одне", fmt.format(4.751), "4.751")
        assertEquals("дванадцять кома нуль пʼять одне", fmt.format(12.051), "12.051")
        assertEquals("тринадцять кома нуль два пʼять одне", fmt.format(13.0251), "13.0251")
        assertEquals("чотирнадцять кома нуль два два пʼять одне", fmt.format(14.02251), "14.02251")
        assertEquals("одне", fmt.format(1), "1")
        assertEquals("два", fmt.format(2), "2")
        assertEquals("три", fmt.format(3), "3")
        assertEquals("чотири", fmt.format(4), "4")
        assertEquals("пʼять", fmt.format(5), "5")
        assertEquals("шість", fmt.format(6), "6")
        assertEquals("сім", fmt.format(7), "7")
        assertEquals("вісім", fmt.format(8), "8")
        assertEquals("девʼять", fmt.format(9), "9")
        assertEquals("десять", fmt.format(10), "10")
        assertEquals("одинадцять", fmt.format(11), "11")
        assertEquals("дванадцять", fmt.format(12), "12")
        assertEquals("тринадцять", fmt.format(13), "13")
        assertEquals("чотирнадцять", fmt.format(14), "14")
        assertEquals("пʼятнадцять", fmt.format(15), "15")
        assertEquals("шістнадцять", fmt.format(16), "16")
        assertEquals("сімнадцять", fmt.format(17), "17")
        assertEquals("вісімнадцять", fmt.format(18), "18")
        assertEquals("девʼятнадцять", fmt.format(19), "19")
        assertEquals("двадцять", fmt.format(20), "20")
        assertEquals("двадцять одне", fmt.format(21), "21")
        assertEquals("двадцять два", fmt.format(22), "22")
        assertEquals("двадцять три", fmt.format(23), "23")
        assertEquals("двадцять чотири", fmt.format(24), "24")
        assertEquals("двадцять пʼять", fmt.format(25), "25")
        assertEquals("двадцять шість", fmt.format(26), "26")
        assertEquals("двадцять сім", fmt.format(27), "27")
        assertEquals("двадцять вісім", fmt.format(28), "28")
        assertEquals("двадцять девʼять", fmt.format(29), "29")
        assertEquals("тридцять", fmt.format(30), "30")
        assertEquals("тридцять одне", fmt.format(31), "31")
        assertEquals("девʼяносто вісім", fmt.format(98), "98")
        assertEquals("девʼяносто девʼять", fmt.format(99), "99")
        assertEquals("сто", fmt.format(100), "100")
        assertEquals("сто одне", fmt.format(101), "101")
        assertEquals("сто два", fmt.format(102), "102")
        assertEquals("девʼятсот девʼяносто вісім", fmt.format(998), "998")
        assertEquals("девʼятсот девʼяносто девʼять", fmt.format(999), "999")
        assertEquals("одна тисяча", fmt.format(1000), "1000")
        assertEquals("одна тисяча одне", fmt.format(1001), "1001")
        assertEquals("одна тисяча два", fmt.format(1002), "1002")
        assertEquals("одна тисяча девʼятсот девʼяносто вісім", fmt.format(1998), "1998")
        assertEquals("одна тисяча девʼятсот девʼяносто девʼять", fmt.format(1999), "1999")
        assertEquals("дві тисячі", fmt.format(2000), "2000")
        assertEquals("дві тисячі одне", fmt.format(2001), "2001")
        assertEquals("дві тисячі два", fmt.format(2002), "2002")
        assertEquals("девʼять тисяч девʼятсот девʼяносто вісім", fmt.format(9998), "9998")
        assertEquals("девʼять тисяч девʼятсот девʼяносто девʼять", fmt.format(9999), "9999")
        assertEquals("десять тисяч", fmt.format(10000), "10000")
        assertEquals("десять тисяч одне", fmt.format(10001), "10001")
        assertEquals("десять тисяч два", fmt.format(10002), "10002")
        assertEquals("сто тисяч", fmt.format(100000), "100000")
        assertEquals("сто тисяч одне", fmt.format(100001), "100001")
        assertEquals("сто тисяч два", fmt.format(100002), "100002")
        assertEquals("двісті тисяч", fmt.format(200000), "200000")
        assertEquals("двісті тисяч одне", fmt.format(200001), "200001")
        assertEquals("двісті тисяч два", fmt.format(200002), "200002")
        assertEquals("один мільйон", fmt.format(1000000), "1000000")
        assertEquals("один мільйон одне", fmt.format(1000001), "1000001")
        assertEquals("один мільйон два", fmt.format(1000002), "1000002")
        assertEquals("два мільйони", fmt.format(2000000), "2000000")
        assertEquals("два мільйони одне", fmt.format(2000001), "2000001")
        assertEquals("два мільйони два", fmt.format(2000002), "2000002")
        assertEquals("десять мільйонів", fmt.format(10000000), "10000000")
        assertEquals("десять мільйонів одне", fmt.format(10000001), "10000001")
        assertEquals("десять мільйонів два", fmt.format(10000002), "10000002")
        assertEquals("двадцять мільйонів", fmt.format(20000000), "20000000")
        assertEquals("двадцять мільйонів одне", fmt.format(20000001), "20000001")
        assertEquals("двадцять мільйонів два", fmt.format(20000002), "20000002")
        assertEquals("сто мільйонів", fmt.format(100000000), "100000000")
        assertEquals("сто мільйонів одне", fmt.format(100000001), "100000001")
        assertEquals("сто мільйонів два", fmt.format(100000002), "100000002")
        assertEquals("двісті мільйонів", fmt.format(200000000), "200000000")
        assertEquals("двісті мільйонів одне", fmt.format(200000001), "200000001")
        assertEquals("двісті мільйонів два", fmt.format(200000002), "200000002")
        assertEquals("один мільярд", fmt.format(1000000000), "1000000000")
        assertEquals("один мільярд одне", fmt.format(1000000001), "1000000001")
        assertEquals("один мільярд два", fmt.format(1000000002), "1000000002")
        assertEquals("два мільярди", fmt.format(2000000000), "2000000000")
        assertEquals("два мільярди одне", fmt.format(2000000001), "2000000001")
        assertEquals("два мільярди два", fmt.format(2000000002), "2000000002")
    }

    @Test
    fun spelloutCardinalMasculine() {
        val fmt = RBNFUk().spelloutCardinalMasculine
        assertEquals("мінус один", fmt.format(-1), "-1")
        assertEquals("нуль", fmt.format(0), "0")
        assertEquals("нуль кома два", fmt.format(0.2), "0.2")
        assertEquals("нуль кома два три чотири пʼять", fmt.format(0.2345), "0.2345")
        assertEquals("нуль кома нуль два", fmt.format(0.02), "0.02")
        assertEquals("нуль кома нуль два один три", fmt.format(0.0213), "0.0213")
        assertEquals("чотири кома сім пʼять один", fmt.format(4.751), "4.751")
        assertEquals("дванадцять кома нуль пʼять один", fmt.format(12.051), "12.051")
        assertEquals("тринадцять кома нуль два пʼять один", fmt.format(13.0251), "13.0251")
        assertEquals("чотирнадцять кома нуль два два пʼять один", fmt.format(14.02251), "14.02251")
        assertEquals("один", fmt.format(1), "1")
        assertEquals("два", fmt.format(2), "2")
        assertEquals("три", fmt.format(3), "3")
        assertEquals("чотири", fmt.format(4), "4")
        assertEquals("пʼять", fmt.format(5), "5")
        assertEquals("шість", fmt.format(6), "6")
        assertEquals("сім", fmt.format(7), "7")
        assertEquals("вісім", fmt.format(8), "8")
        assertEquals("девʼять", fmt.format(9), "9")
        assertEquals("десять", fmt.format(10), "10")
        assertEquals("одинадцять", fmt.format(11), "11")
        assertEquals("дванадцять", fmt.format(12), "12")
        assertEquals("тринадцять", fmt.format(13), "13")
        assertEquals("чотирнадцять", fmt.format(14), "14")
        assertEquals("пʼятнадцять", fmt.format(15), "15")
        assertEquals("шістнадцять", fmt.format(16), "16")
        assertEquals("сімнадцять", fmt.format(17), "17")
        assertEquals("вісімнадцять", fmt.format(18), "18")
        assertEquals("девʼятнадцять", fmt.format(19), "19")
        assertEquals("двадцять", fmt.format(20), "20")
        assertEquals("двадцять один", fmt.format(21), "21")
        assertEquals("двадцять два", fmt.format(22), "22")
        assertEquals("двадцять три", fmt.format(23), "23")
        assertEquals("двадцять чотири", fmt.format(24), "24")
        assertEquals("двадцять пʼять", fmt.format(25), "25")
        assertEquals("двадцять шість", fmt.format(26), "26")
        assertEquals("двадцять сім", fmt.format(27), "27")
        assertEquals("двадцять вісім", fmt.format(28), "28")
        assertEquals("двадцять девʼять", fmt.format(29), "29")
        assertEquals("тридцять", fmt.format(30), "30")
        assertEquals("тридцять один", fmt.format(31), "31")
        assertEquals("девʼяносто вісім", fmt.format(98), "98")
        assertEquals("девʼяносто девʼять", fmt.format(99), "99")
        assertEquals("сто", fmt.format(100), "100")
        assertEquals("сто один", fmt.format(101), "101")
        assertEquals("сто два", fmt.format(102), "102")
        assertEquals("девʼятсот девʼяносто вісім", fmt.format(998), "998")
        assertEquals("девʼятсот девʼяносто девʼять", fmt.format(999), "999")
        assertEquals("одна тисяча", fmt.format(1000), "1000")
        assertEquals("одна тисяча один", fmt.format(1001), "1001")
        assertEquals("одна тисяча два", fmt.format(1002), "1002")
        assertEquals("одна тисяча девʼятсот девʼяносто вісім", fmt.format(1998), "1998")
        assertEquals("одна тисяча девʼятсот девʼяносто девʼять", fmt.format(1999), "1999")
        assertEquals("дві тисячі", fmt.format(2000), "2000")
        assertEquals("дві тисячі один", fmt.format(2001), "2001")
        assertEquals("дві тисячі два", fmt.format(2002), "2002")
        assertEquals("девʼять тисяч девʼятсот девʼяносто вісім", fmt.format(9998), "9998")
        assertEquals("девʼять тисяч девʼятсот девʼяносто девʼять", fmt.format(9999), "9999")
        assertEquals("десять тисяч", fmt.format(10000), "10000")
        assertEquals("десять тисяч один", fmt.format(10001), "10001")
        assertEquals("десять тисяч два", fmt.format(10002), "10002")
        assertEquals("сто тисяч", fmt.format(100000), "100000")
        assertEquals("сто тисяч один", fmt.format(100001), "100001")
        assertEquals("сто тисяч два", fmt.format(100002), "100002")
        assertEquals("двісті тисяч", fmt.format(200000), "200000")
        assertEquals("двісті тисяч один", fmt.format(200001), "200001")
        assertEquals("двісті тисяч два", fmt.format(200002), "200002")
        assertEquals("один мільйон", fmt.format(1000000), "1000000")
        assertEquals("один мільйон один", fmt.format(1000001), "1000001")
        assertEquals("один мільйон два", fmt.format(1000002), "1000002")
        assertEquals("два мільйони", fmt.format(2000000), "2000000")
        assertEquals("два мільйони один", fmt.format(2000001), "2000001")
        assertEquals("два мільйони два", fmt.format(2000002), "2000002")
        assertEquals("десять мільйонів", fmt.format(10000000), "10000000")
        assertEquals("десять мільйонів один", fmt.format(10000001), "10000001")
        assertEquals("десять мільйонів два", fmt.format(10000002), "10000002")
        assertEquals("двадцять мільйонів", fmt.format(20000000), "20000000")
        assertEquals("двадцять мільйонів один", fmt.format(20000001), "20000001")
        assertEquals("двадцять мільйонів два", fmt.format(20000002), "20000002")
        assertEquals("сто мільйонів", fmt.format(100000000), "100000000")
        assertEquals("сто мільйонів один", fmt.format(100000001), "100000001")
        assertEquals("сто мільйонів два", fmt.format(100000002), "100000002")
        assertEquals("двісті мільйонів", fmt.format(200000000), "200000000")
        assertEquals("двісті мільйонів один", fmt.format(200000001), "200000001")
        assertEquals("двісті мільйонів два", fmt.format(200000002), "200000002")
        assertEquals("один мільярд", fmt.format(1000000000), "1000000000")
        assertEquals("один мільярд один", fmt.format(1000000001), "1000000001")
        assertEquals("один мільярд два", fmt.format(1000000002), "1000000002")
        assertEquals("два мільярди", fmt.format(2000000000), "2000000000")
        assertEquals("два мільярди один", fmt.format(2000000001), "2000000001")
        assertEquals("два мільярди два", fmt.format(2000000002), "2000000002")
    }

    @Test
    fun spelloutNumbering() {
        val fmt = RBNFUk().spelloutNumbering
        assertEquals("мінус один", fmt.format(-1), "-1")
        assertEquals("нуль", fmt.format(0), "0")
        assertEquals("нуль кома два", fmt.format(0.2), "0.2")
        assertEquals("нуль кома два три чотири пʼять", fmt.format(0.2345), "0.2345")
        assertEquals("нуль кома нуль два", fmt.format(0.02), "0.02")
        assertEquals("нуль кома нуль два один три", fmt.format(0.0213), "0.0213")
        assertEquals("чотири кома сім пʼять один", fmt.format(4.751), "4.751")
        assertEquals("дванадцять кома нуль пʼять один", fmt.format(12.051), "12.051")
        assertEquals("тринадцять кома нуль два пʼять один", fmt.format(13.0251), "13.0251")
        assertEquals("чотирнадцять кома нуль два два пʼять один", fmt.format(14.02251), "14.02251")
        assertEquals("один", fmt.format(1), "1")
        assertEquals("два", fmt.format(2), "2")
        assertEquals("три", fmt.format(3), "3")
        assertEquals("чотири", fmt.format(4), "4")
        assertEquals("пʼять", fmt.format(5), "5")
        assertEquals("шість", fmt.format(6), "6")
        assertEquals("сім", fmt.format(7), "7")
        assertEquals("вісім", fmt.format(8), "8")
        assertEquals("девʼять", fmt.format(9), "9")
        assertEquals("десять", fmt.format(10), "10")
        assertEquals("одинадцять", fmt.format(11), "11")
        assertEquals("дванадцять", fmt.format(12), "12")
        assertEquals("тринадцять", fmt.format(13), "13")
        assertEquals("чотирнадцять", fmt.format(14), "14")
        assertEquals("пʼятнадцять", fmt.format(15), "15")
        assertEquals("шістнадцять", fmt.format(16), "16")
        assertEquals("сімнадцять", fmt.format(17), "17")
        assertEquals("вісімнадцять", fmt.format(18), "18")
        assertEquals("девʼятнадцять", fmt.format(19), "19")
        assertEquals("двадцять", fmt.format(20), "20")
        assertEquals("двадцять один", fmt.format(21), "21")
        assertEquals("двадцять два", fmt.format(22), "22")
        assertEquals("двадцять три", fmt.format(23), "23")
        assertEquals("двадцять чотири", fmt.format(24), "24")
        assertEquals("двадцять пʼять", fmt.format(25), "25")
        assertEquals("двадцять шість", fmt.format(26), "26")
        assertEquals("двадцять сім", fmt.format(27), "27")
        assertEquals("двадцять вісім", fmt.format(28), "28")
        assertEquals("двадцять девʼять", fmt.format(29), "29")
        assertEquals("тридцять", fmt.format(30), "30")
        assertEquals("тридцять один", fmt.format(31), "31")
        assertEquals("девʼяносто вісім", fmt.format(98), "98")
        assertEquals("девʼяносто девʼять", fmt.format(99), "99")
        assertEquals("сто", fmt.format(100), "100")
        assertEquals("сто один", fmt.format(101), "101")
        assertEquals("сто два", fmt.format(102), "102")
        assertEquals("девʼятсот девʼяносто вісім", fmt.format(998), "998")
        assertEquals("девʼятсот девʼяносто девʼять", fmt.format(999), "999")
        assertEquals("одна тисяча", fmt.format(1000), "1000")
        assertEquals("одна тисяча один", fmt.format(1001), "1001")
        assertEquals("одна тисяча два", fmt.format(1002), "1002")
        assertEquals("одна тисяча девʼятсот девʼяносто вісім", fmt.format(1998), "1998")
        assertEquals("одна тисяча девʼятсот девʼяносто девʼять", fmt.format(1999), "1999")
        assertEquals("дві тисячі", fmt.format(2000), "2000")
        assertEquals("дві тисячі один", fmt.format(2001), "2001")
        assertEquals("дві тисячі два", fmt.format(2002), "2002")
        assertEquals("девʼять тисяч девʼятсот девʼяносто вісім", fmt.format(9998), "9998")
        assertEquals("девʼять тисяч девʼятсот девʼяносто девʼять", fmt.format(9999), "9999")
        assertEquals("десять тисяч", fmt.format(10000), "10000")
        assertEquals("десять тисяч один", fmt.format(10001), "10001")
        assertEquals("десять тисяч два", fmt.format(10002), "10002")
        assertEquals("сто тисяч", fmt.format(100000), "100000")
        assertEquals("сто тисяч один", fmt.format(100001), "100001")
        assertEquals("сто тисяч два", fmt.format(100002), "100002")
        assertEquals("двісті тисяч", fmt.format(200000), "200000")
        assertEquals("двісті тисяч один", fmt.format(200001), "200001")
        assertEquals("двісті тисяч два", fmt.format(200002), "200002")
        assertEquals("один мільйон", fmt.format(1000000), "1000000")
        assertEquals("один мільйон один", fmt.format(1000001), "1000001")
        assertEquals("один мільйон два", fmt.format(1000002), "1000002")
        assertEquals("два мільйони", fmt.format(2000000), "2000000")
        assertEquals("два мільйони один", fmt.format(2000001), "2000001")
        assertEquals("два мільйони два", fmt.format(2000002), "2000002")
        assertEquals("десять мільйонів", fmt.format(10000000), "10000000")
        assertEquals("десять мільйонів один", fmt.format(10000001), "10000001")
        assertEquals("десять мільйонів два", fmt.format(10000002), "10000002")
        assertEquals("двадцять мільйонів", fmt.format(20000000), "20000000")
        assertEquals("двадцять мільйонів один", fmt.format(20000001), "20000001")
        assertEquals("двадцять мільйонів два", fmt.format(20000002), "20000002")
        assertEquals("сто мільйонів", fmt.format(100000000), "100000000")
        assertEquals("сто мільйонів один", fmt.format(100000001), "100000001")
        assertEquals("сто мільйонів два", fmt.format(100000002), "100000002")
        assertEquals("двісті мільйонів", fmt.format(200000000), "200000000")
        assertEquals("двісті мільйонів один", fmt.format(200000001), "200000001")
        assertEquals("двісті мільйонів два", fmt.format(200000002), "200000002")
        assertEquals("один мільярд", fmt.format(1000000000), "1000000000")
        assertEquals("один мільярд один", fmt.format(1000000001), "1000000001")
        assertEquals("один мільярд два", fmt.format(1000000002), "1000000002")
        assertEquals("два мільярди", fmt.format(2000000000), "2000000000")
        assertEquals("два мільярди один", fmt.format(2000000001), "2000000001")
        assertEquals("два мільярди два", fmt.format(2000000002), "2000000002")
    }

    @Test
    fun spelloutNumberingYear() {
        val fmt = RBNFUk().spelloutNumberingYear
// nonsense assertEquals("мінус один", fmt.format(-1), "-1")
// nonsense assertEquals("нуль", fmt.format(0), "0")
// nonsense assertEquals("0,2", fmt.format(0.2), "0.2")
// nonsense assertEquals("0,2", fmt.format(0.2345), "0.2345")
// nonsense assertEquals("0,0", fmt.format(0.02), "0.02")
// nonsense assertEquals("0,0", fmt.format(0.0213), "0.0213")
// nonsense assertEquals("4,8", fmt.format(4.751), "4.751")
// nonsense assertEquals("12,1", fmt.format(12.051), "12.051")
// nonsense assertEquals("13,0", fmt.format(13.0251), "13.0251")
// nonsense assertEquals("14,0", fmt.format(14.02251), "14.02251")
        assertEquals("один", fmt.format(1), "1")
        assertEquals("два", fmt.format(2), "2")
        assertEquals("три", fmt.format(3), "3")
        assertEquals("чотири", fmt.format(4), "4")
        assertEquals("пʼять", fmt.format(5), "5")
        assertEquals("шість", fmt.format(6), "6")
        assertEquals("сім", fmt.format(7), "7")
        assertEquals("вісім", fmt.format(8), "8")
        assertEquals("девʼять", fmt.format(9), "9")
        assertEquals("десять", fmt.format(10), "10")
        assertEquals("одинадцять", fmt.format(11), "11")
        assertEquals("дванадцять", fmt.format(12), "12")
        assertEquals("тринадцять", fmt.format(13), "13")
        assertEquals("чотирнадцять", fmt.format(14), "14")
        assertEquals("пʼятнадцять", fmt.format(15), "15")
        assertEquals("шістнадцять", fmt.format(16), "16")
        assertEquals("сімнадцять", fmt.format(17), "17")
        assertEquals("вісімнадцять", fmt.format(18), "18")
        assertEquals("девʼятнадцять", fmt.format(19), "19")
        assertEquals("двадцять", fmt.format(20), "20")
        assertEquals("двадцять один", fmt.format(21), "21")
        assertEquals("двадцять два", fmt.format(22), "22")
        assertEquals("двадцять три", fmt.format(23), "23")
        assertEquals("двадцять чотири", fmt.format(24), "24")
        assertEquals("двадцять пʼять", fmt.format(25), "25")
        assertEquals("двадцять шість", fmt.format(26), "26")
        assertEquals("двадцять сім", fmt.format(27), "27")
        assertEquals("двадцять вісім", fmt.format(28), "28")
        assertEquals("двадцять девʼять", fmt.format(29), "29")
        assertEquals("тридцять", fmt.format(30), "30")
        assertEquals("тридцять один", fmt.format(31), "31")
        assertEquals("девʼяносто вісім", fmt.format(98), "98")
        assertEquals("девʼяносто девʼять", fmt.format(99), "99")
        assertEquals("сто", fmt.format(100), "100")
        assertEquals("сто один", fmt.format(101), "101")
        assertEquals("сто два", fmt.format(102), "102")
        assertEquals("девʼятсот девʼяносто вісім", fmt.format(998), "998")
        assertEquals("девʼятсот девʼяносто девʼять", fmt.format(999), "999")
        assertEquals("одна тисяча", fmt.format(1000), "1000")
        assertEquals("одна тисяча один", fmt.format(1001), "1001")
        assertEquals("одна тисяча два", fmt.format(1002), "1002")
        assertEquals("одна тисяча девʼятсот девʼяносто вісім", fmt.format(1998), "1998")
        assertEquals("одна тисяча девʼятсот девʼяносто девʼять", fmt.format(1999), "1999")
        assertEquals("дві тисячі", fmt.format(2000), "2000")
        assertEquals("дві тисячі один", fmt.format(2001), "2001")
        assertEquals("дві тисячі два", fmt.format(2002), "2002")
        assertEquals("девʼять тисяч девʼятсот девʼяносто вісім", fmt.format(9998), "9998")
        assertEquals("девʼять тисяч девʼятсот девʼяносто девʼять", fmt.format(9999), "9999")
        assertEquals("десять тисяч", fmt.format(10000), "10000")
        assertEquals("десять тисяч один", fmt.format(10001), "10001")
        assertEquals("десять тисяч два", fmt.format(10002), "10002")
        assertEquals("сто тисяч", fmt.format(100000), "100000")
        assertEquals("сто тисяч один", fmt.format(100001), "100001")
        assertEquals("сто тисяч два", fmt.format(100002), "100002")
        assertEquals("двісті тисяч", fmt.format(200000), "200000")
        assertEquals("двісті тисяч один", fmt.format(200001), "200001")
        assertEquals("двісті тисяч два", fmt.format(200002), "200002")
        assertEquals("один мільйон", fmt.format(1000000), "1000000")
        assertEquals("один мільйон один", fmt.format(1000001), "1000001")
        assertEquals("один мільйон два", fmt.format(1000002), "1000002")
        assertEquals("два мільйони", fmt.format(2000000), "2000000")
        assertEquals("два мільйони один", fmt.format(2000001), "2000001")
        assertEquals("два мільйони два", fmt.format(2000002), "2000002")
        assertEquals("десять мільйонів", fmt.format(10000000), "10000000")
        assertEquals("десять мільйонів один", fmt.format(10000001), "10000001")
        assertEquals("десять мільйонів два", fmt.format(10000002), "10000002")
        assertEquals("двадцять мільйонів", fmt.format(20000000), "20000000")
        assertEquals("двадцять мільйонів один", fmt.format(20000001), "20000001")
        assertEquals("двадцять мільйонів два", fmt.format(20000002), "20000002")
        assertEquals("сто мільйонів", fmt.format(100000000), "100000000")
        assertEquals("сто мільйонів один", fmt.format(100000001), "100000001")
        assertEquals("сто мільйонів два", fmt.format(100000002), "100000002")
        assertEquals("двісті мільйонів", fmt.format(200000000), "200000000")
        assertEquals("двісті мільйонів один", fmt.format(200000001), "200000001")
        assertEquals("двісті мільйонів два", fmt.format(200000002), "200000002")
        assertEquals("один мільярд", fmt.format(1000000000), "1000000000")
        assertEquals("один мільярд один", fmt.format(1000000001), "1000000001")
        assertEquals("один мільярд два", fmt.format(1000000002), "1000000002")
        assertEquals("два мільярди", fmt.format(2000000000), "2000000000")
        assertEquals("два мільярди один", fmt.format(2000000001), "2000000001")
        assertEquals("два мільярди два", fmt.format(2000000002), "2000000002")
    }
}
