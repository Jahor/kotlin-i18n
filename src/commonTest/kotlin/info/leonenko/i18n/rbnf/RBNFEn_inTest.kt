package info.leonenko.i18n.rbnf
    
    import kotlin.test.Test
    import kotlin.test.assertEquals
    
class RBNFEn_inTest {
  @Test
  fun spelloutOrdinalVerbose() {
 val fmt = RBNFEn_in().spelloutOrdinalVerbose
// nonsense assertEquals("minus first", fmt.format(-1), "-1")
// nonsense assertEquals("zeroth", fmt.format(0), "0")
// nonsense assertEquals("0.2", fmt.format(0.2), "0.2")
// nonsense assertEquals("0.2", fmt.format(0.2345), "0.2345")
// nonsense assertEquals("0", fmt.format(0.02), "0.02")
// nonsense assertEquals("0", fmt.format(0.0213), "0.0213")
// nonsense assertEquals("4.8", fmt.format(4.751), "4.751")
// nonsense assertEquals("12.1", fmt.format(12.051), "12.051")
// nonsense assertEquals("13", fmt.format(13.0251), "13.0251")
// nonsense assertEquals("14", fmt.format(14.02251), "14.02251")
assertEquals("first", fmt.format(1), "1")
assertEquals("second", fmt.format(2), "2")
assertEquals("third", fmt.format(3), "3")
assertEquals("fourth", fmt.format(4), "4")
assertEquals("fifth", fmt.format(5), "5")
assertEquals("sixth", fmt.format(6), "6")
assertEquals("seventh", fmt.format(7), "7")
assertEquals("eighth", fmt.format(8), "8")
assertEquals("ninth", fmt.format(9), "9")
assertEquals("tenth", fmt.format(10), "10")
assertEquals("eleventh", fmt.format(11), "11")
assertEquals("twelfth", fmt.format(12), "12")
assertEquals("thirteenth", fmt.format(13), "13")
assertEquals("fourteenth", fmt.format(14), "14")
assertEquals("fifteenth", fmt.format(15), "15")
assertEquals("sixteenth", fmt.format(16), "16")
assertEquals("seventeenth", fmt.format(17), "17")
assertEquals("eighteenth", fmt.format(18), "18")
assertEquals("nineteenth", fmt.format(19), "19")
assertEquals("twentieth", fmt.format(20), "20")
assertEquals("twenty-first", fmt.format(21), "21")
assertEquals("twenty-second", fmt.format(22), "22")
assertEquals("twenty-third", fmt.format(23), "23")
assertEquals("twenty-fourth", fmt.format(24), "24")
assertEquals("twenty-fifth", fmt.format(25), "25")
assertEquals("twenty-sixth", fmt.format(26), "26")
assertEquals("twenty-seventh", fmt.format(27), "27")
assertEquals("twenty-eighth", fmt.format(28), "28")
assertEquals("twenty-ninth", fmt.format(29), "29")
assertEquals("thirtieth", fmt.format(30), "30")
assertEquals("thirty-first", fmt.format(31), "31")
assertEquals("ninety-eighth", fmt.format(98), "98")
assertEquals("ninety-ninth", fmt.format(99), "99")
assertEquals("one hundredth", fmt.format(100), "100")
assertEquals("one hundred and first", fmt.format(101), "101")
assertEquals("one hundred and second", fmt.format(102), "102")
assertEquals("nine hundred and ninety-eighth", fmt.format(998), "998")
assertEquals("nine hundred and ninety-ninth", fmt.format(999), "999")
assertEquals("one thousandth", fmt.format(1000), "1000")
assertEquals("one thousand and first", fmt.format(1001), "1001")
assertEquals("one thousand and second", fmt.format(1002), "1002")
assertEquals("one thousand nine hundred and ninety-eighth", fmt.format(1998), "1998")
assertEquals("one thousand nine hundred and ninety-ninth", fmt.format(1999), "1999")
assertEquals("two thousandth", fmt.format(2000), "2000")
assertEquals("two thousand and first", fmt.format(2001), "2001")
assertEquals("two thousand and second", fmt.format(2002), "2002")
assertEquals("nine thousand nine hundred and ninety-eighth", fmt.format(9998), "9998")
assertEquals("nine thousand nine hundred and ninety-ninth", fmt.format(9999), "9999")
assertEquals("ten thousandth", fmt.format(10000), "10000")
assertEquals("ten thousand and first", fmt.format(10001), "10001")
assertEquals("ten thousand and second", fmt.format(10002), "10002")
assertEquals("one hundred thousandth", fmt.format(100000), "100000")
assertEquals("one hundred thousand and first", fmt.format(100001), "100001")
assertEquals("one hundred thousand and second", fmt.format(100002), "100002")
assertEquals("two hundred thousandth", fmt.format(200000), "200000")
assertEquals("two hundred thousand and first", fmt.format(200001), "200001")
assertEquals("two hundred thousand and second", fmt.format(200002), "200002")
assertEquals("one millionth", fmt.format(1000000), "1000000")
assertEquals("one million and first", fmt.format(1000001), "1000001")
assertEquals("one million and second", fmt.format(1000002), "1000002")
assertEquals("two millionth", fmt.format(2000000), "2000000")
assertEquals("two million and first", fmt.format(2000001), "2000001")
assertEquals("two million and second", fmt.format(2000002), "2000002")
assertEquals("ten millionth", fmt.format(10000000), "10000000")
assertEquals("ten million and first", fmt.format(10000001), "10000001")
assertEquals("ten million and second", fmt.format(10000002), "10000002")
assertEquals("twenty millionth", fmt.format(20000000), "20000000")
assertEquals("twenty million and first", fmt.format(20000001), "20000001")
assertEquals("twenty million and second", fmt.format(20000002), "20000002")
assertEquals("one hundred millionth", fmt.format(100000000), "100000000")
assertEquals("one hundred million and first", fmt.format(100000001), "100000001")
assertEquals("one hundred million and second", fmt.format(100000002), "100000002")
assertEquals("two hundred millionth", fmt.format(200000000), "200000000")
assertEquals("two hundred million and first", fmt.format(200000001), "200000001")
assertEquals("two hundred million and second", fmt.format(200000002), "200000002")
assertEquals("one billionth", fmt.format(1000000000), "1000000000")
assertEquals("one billion and first", fmt.format(1000000001), "1000000001")
assertEquals("one billion and second", fmt.format(1000000002), "1000000002")
assertEquals("two billionth", fmt.format(2000000000), "2000000000")
assertEquals("two billion and first", fmt.format(2000000001), "2000000001")
assertEquals("two billion and second", fmt.format(2000000002), "2000000002")
  }
  @Test
  fun spelloutOrdinal() {
 val fmt = RBNFEn_in().spelloutOrdinal
// nonsense assertEquals("minus first", fmt.format(-1), "-1")
// nonsense assertEquals("zeroth", fmt.format(0), "0")
// nonsense assertEquals("0.2", fmt.format(0.2), "0.2")
// nonsense assertEquals("0.2", fmt.format(0.2345), "0.2345")
// nonsense assertEquals("0", fmt.format(0.02), "0.02")
// nonsense assertEquals("0", fmt.format(0.0213), "0.0213")
// nonsense assertEquals("4.8", fmt.format(4.751), "4.751")
// nonsense assertEquals("12.1", fmt.format(12.051), "12.051")
// nonsense assertEquals("13", fmt.format(13.0251), "13.0251")
// nonsense assertEquals("14", fmt.format(14.02251), "14.02251")
assertEquals("first", fmt.format(1), "1")
assertEquals("second", fmt.format(2), "2")
assertEquals("third", fmt.format(3), "3")
assertEquals("fourth", fmt.format(4), "4")
assertEquals("fifth", fmt.format(5), "5")
assertEquals("sixth", fmt.format(6), "6")
assertEquals("seventh", fmt.format(7), "7")
assertEquals("eighth", fmt.format(8), "8")
assertEquals("ninth", fmt.format(9), "9")
assertEquals("tenth", fmt.format(10), "10")
assertEquals("eleventh", fmt.format(11), "11")
assertEquals("twelfth", fmt.format(12), "12")
assertEquals("thirteenth", fmt.format(13), "13")
assertEquals("fourteenth", fmt.format(14), "14")
assertEquals("fifteenth", fmt.format(15), "15")
assertEquals("sixteenth", fmt.format(16), "16")
assertEquals("seventeenth", fmt.format(17), "17")
assertEquals("eighteenth", fmt.format(18), "18")
assertEquals("nineteenth", fmt.format(19), "19")
assertEquals("twentieth", fmt.format(20), "20")
assertEquals("twenty-first", fmt.format(21), "21")
assertEquals("twenty-second", fmt.format(22), "22")
assertEquals("twenty-third", fmt.format(23), "23")
assertEquals("twenty-fourth", fmt.format(24), "24")
assertEquals("twenty-fifth", fmt.format(25), "25")
assertEquals("twenty-sixth", fmt.format(26), "26")
assertEquals("twenty-seventh", fmt.format(27), "27")
assertEquals("twenty-eighth", fmt.format(28), "28")
assertEquals("twenty-ninth", fmt.format(29), "29")
assertEquals("thirtieth", fmt.format(30), "30")
assertEquals("thirty-first", fmt.format(31), "31")
assertEquals("ninety-eighth", fmt.format(98), "98")
assertEquals("ninety-ninth", fmt.format(99), "99")
assertEquals("one hundredth", fmt.format(100), "100")
assertEquals("one hundred first", fmt.format(101), "101")
assertEquals("one hundred second", fmt.format(102), "102")
assertEquals("nine hundred ninety-eighth", fmt.format(998), "998")
assertEquals("nine hundred ninety-ninth", fmt.format(999), "999")
assertEquals("one thousandth", fmt.format(1000), "1000")
assertEquals("one thousand first", fmt.format(1001), "1001")
assertEquals("one thousand second", fmt.format(1002), "1002")
assertEquals("one thousand nine hundred ninety-eighth", fmt.format(1998), "1998")
assertEquals("one thousand nine hundred ninety-ninth", fmt.format(1999), "1999")
assertEquals("two thousandth", fmt.format(2000), "2000")
assertEquals("two thousand first", fmt.format(2001), "2001")
assertEquals("two thousand second", fmt.format(2002), "2002")
assertEquals("nine thousand nine hundred ninety-eighth", fmt.format(9998), "9998")
assertEquals("nine thousand nine hundred ninety-ninth", fmt.format(9999), "9999")
assertEquals("ten thousandth", fmt.format(10000), "10000")
assertEquals("ten thousand first", fmt.format(10001), "10001")
assertEquals("ten thousand second", fmt.format(10002), "10002")
assertEquals("one hundred thousandth", fmt.format(100000), "100000")
assertEquals("one hundred thousand first", fmt.format(100001), "100001")
assertEquals("one hundred thousand second", fmt.format(100002), "100002")
assertEquals("two hundred thousandth", fmt.format(200000), "200000")
assertEquals("two hundred thousand first", fmt.format(200001), "200001")
assertEquals("two hundred thousand second", fmt.format(200002), "200002")
assertEquals("one millionth", fmt.format(1000000), "1000000")
assertEquals("one million first", fmt.format(1000001), "1000001")
assertEquals("one million second", fmt.format(1000002), "1000002")
assertEquals("two millionth", fmt.format(2000000), "2000000")
assertEquals("two million first", fmt.format(2000001), "2000001")
assertEquals("two million second", fmt.format(2000002), "2000002")
assertEquals("ten millionth", fmt.format(10000000), "10000000")
assertEquals("ten million first", fmt.format(10000001), "10000001")
assertEquals("ten million second", fmt.format(10000002), "10000002")
assertEquals("twenty millionth", fmt.format(20000000), "20000000")
assertEquals("twenty million first", fmt.format(20000001), "20000001")
assertEquals("twenty million second", fmt.format(20000002), "20000002")
assertEquals("one hundred millionth", fmt.format(100000000), "100000000")
assertEquals("one hundred million first", fmt.format(100000001), "100000001")
assertEquals("one hundred million second", fmt.format(100000002), "100000002")
assertEquals("two hundred millionth", fmt.format(200000000), "200000000")
assertEquals("two hundred million first", fmt.format(200000001), "200000001")
assertEquals("two hundred million second", fmt.format(200000002), "200000002")
assertEquals("one billionth", fmt.format(1000000000), "1000000000")
assertEquals("one billion first", fmt.format(1000000001), "1000000001")
assertEquals("one billion second", fmt.format(1000000002), "1000000002")
assertEquals("two billionth", fmt.format(2000000000), "2000000000")
assertEquals("two billion first", fmt.format(2000000001), "2000000001")
assertEquals("two billion second", fmt.format(2000000002), "2000000002")
  }
  @Test
  fun spelloutCardinalVerbose() {
 val fmt = RBNFEn_in().spelloutCardinalVerbose
assertEquals("minus one", fmt.format(-1), "-1")
assertEquals("zero", fmt.format(0), "0")
assertEquals("zero point two", fmt.format(0.2), "0.2")
assertEquals("zero point two three four five", fmt.format(0.2345), "0.2345")
assertEquals("zero point zero two", fmt.format(0.02), "0.02")
assertEquals("zero point zero two one three", fmt.format(0.0213), "0.0213")
assertEquals("four point seven five one", fmt.format(4.751), "4.751")
assertEquals("twelve point zero five one", fmt.format(12.051), "12.051")
assertEquals("thirteen point zero two five one", fmt.format(13.0251), "13.0251")
assertEquals("fourteen point zero two two five one", fmt.format(14.02251), "14.02251")
assertEquals("one", fmt.format(1), "1")
assertEquals("two", fmt.format(2), "2")
assertEquals("three", fmt.format(3), "3")
assertEquals("four", fmt.format(4), "4")
assertEquals("five", fmt.format(5), "5")
assertEquals("six", fmt.format(6), "6")
assertEquals("seven", fmt.format(7), "7")
assertEquals("eight", fmt.format(8), "8")
assertEquals("nine", fmt.format(9), "9")
assertEquals("ten", fmt.format(10), "10")
assertEquals("eleven", fmt.format(11), "11")
assertEquals("twelve", fmt.format(12), "12")
assertEquals("thirteen", fmt.format(13), "13")
assertEquals("fourteen", fmt.format(14), "14")
assertEquals("fifteen", fmt.format(15), "15")
assertEquals("sixteen", fmt.format(16), "16")
assertEquals("seventeen", fmt.format(17), "17")
assertEquals("eighteen", fmt.format(18), "18")
assertEquals("nineteen", fmt.format(19), "19")
assertEquals("twenty", fmt.format(20), "20")
assertEquals("twenty-one", fmt.format(21), "21")
assertEquals("twenty-two", fmt.format(22), "22")
assertEquals("twenty-three", fmt.format(23), "23")
assertEquals("twenty-four", fmt.format(24), "24")
assertEquals("twenty-five", fmt.format(25), "25")
assertEquals("twenty-six", fmt.format(26), "26")
assertEquals("twenty-seven", fmt.format(27), "27")
assertEquals("twenty-eight", fmt.format(28), "28")
assertEquals("twenty-nine", fmt.format(29), "29")
assertEquals("thirty", fmt.format(30), "30")
assertEquals("thirty-one", fmt.format(31), "31")
assertEquals("ninety-eight", fmt.format(98), "98")
assertEquals("ninety-nine", fmt.format(99), "99")
assertEquals("one hundred", fmt.format(100), "100")
assertEquals("one hundred and one", fmt.format(101), "101")
assertEquals("one hundred and two", fmt.format(102), "102")
assertEquals("nine hundred and ninety-eight", fmt.format(998), "998")
assertEquals("nine hundred and ninety-nine", fmt.format(999), "999")
assertEquals("one thousand", fmt.format(1000), "1000")
assertEquals("one thousand and one", fmt.format(1001), "1001")
assertEquals("one thousand and two", fmt.format(1002), "1002")
assertEquals("one thousand nine hundred and ninety-eight", fmt.format(1998), "1998")
assertEquals("one thousand nine hundred and ninety-nine", fmt.format(1999), "1999")
assertEquals("two thousand", fmt.format(2000), "2000")
assertEquals("two thousand and one", fmt.format(2001), "2001")
assertEquals("two thousand and two", fmt.format(2002), "2002")
assertEquals("nine thousand nine hundred and ninety-eight", fmt.format(9998), "9998")
assertEquals("nine thousand nine hundred and ninety-nine", fmt.format(9999), "9999")
assertEquals("ten thousand", fmt.format(10000), "10000")
assertEquals("ten thousand and one", fmt.format(10001), "10001")
assertEquals("ten thousand and two", fmt.format(10002), "10002")
assertEquals("one lakh", fmt.format(100000), "100000")
assertEquals("one lakh and one", fmt.format(100001), "100001")
assertEquals("one lakh and two", fmt.format(100002), "100002")
assertEquals("two lakh", fmt.format(200000), "200000")
assertEquals("two lakh and one", fmt.format(200001), "200001")
assertEquals("two lakh and two", fmt.format(200002), "200002")
assertEquals("ten lakh", fmt.format(1000000), "1000000")
assertEquals("ten lakh and one", fmt.format(1000001), "1000001")
assertEquals("ten lakh and two", fmt.format(1000002), "1000002")
assertEquals("twenty lakh", fmt.format(2000000), "2000000")
assertEquals("twenty lakh and one", fmt.format(2000001), "2000001")
assertEquals("twenty lakh and two", fmt.format(2000002), "2000002")
assertEquals("one crore", fmt.format(10000000), "10000000")
assertEquals("one crore and one", fmt.format(10000001), "10000001")
assertEquals("one crore and two", fmt.format(10000002), "10000002")
assertEquals("two crore", fmt.format(20000000), "20000000")
assertEquals("two crore and one", fmt.format(20000001), "20000001")
assertEquals("two crore and two", fmt.format(20000002), "20000002")
assertEquals("ten crore", fmt.format(100000000), "100000000")
assertEquals("ten crore and one", fmt.format(100000001), "100000001")
assertEquals("ten crore and two", fmt.format(100000002), "100000002")
assertEquals("twenty crore", fmt.format(200000000), "200000000")
assertEquals("twenty crore and one", fmt.format(200000001), "200000001")
assertEquals("twenty crore and two", fmt.format(200000002), "200000002")
assertEquals("one hundred crore", fmt.format(1000000000), "1000000000")
assertEquals("one hundred crore and one", fmt.format(1000000001), "1000000001")
assertEquals("one hundred crore and two", fmt.format(1000000002), "1000000002")
assertEquals("two hundred crore", fmt.format(2000000000), "2000000000")
assertEquals("two hundred crore and one", fmt.format(2000000001), "2000000001")
assertEquals("two hundred crore and two", fmt.format(2000000002), "2000000002")
  }
  @Test
  fun spelloutCardinal() {
 val fmt = RBNFEn_in().spelloutCardinal
assertEquals("minus one", fmt.format(-1), "-1")
assertEquals("zero", fmt.format(0), "0")
assertEquals("zero point two", fmt.format(0.2), "0.2")
assertEquals("zero point two three four five", fmt.format(0.2345), "0.2345")
assertEquals("zero point zero two", fmt.format(0.02), "0.02")
assertEquals("zero point zero two one three", fmt.format(0.0213), "0.0213")
assertEquals("four point seven five one", fmt.format(4.751), "4.751")
assertEquals("twelve point zero five one", fmt.format(12.051), "12.051")
assertEquals("thirteen point zero two five one", fmt.format(13.0251), "13.0251")
assertEquals("fourteen point zero two two five one", fmt.format(14.02251), "14.02251")
assertEquals("one", fmt.format(1), "1")
assertEquals("two", fmt.format(2), "2")
assertEquals("three", fmt.format(3), "3")
assertEquals("four", fmt.format(4), "4")
assertEquals("five", fmt.format(5), "5")
assertEquals("six", fmt.format(6), "6")
assertEquals("seven", fmt.format(7), "7")
assertEquals("eight", fmt.format(8), "8")
assertEquals("nine", fmt.format(9), "9")
assertEquals("ten", fmt.format(10), "10")
assertEquals("eleven", fmt.format(11), "11")
assertEquals("twelve", fmt.format(12), "12")
assertEquals("thirteen", fmt.format(13), "13")
assertEquals("fourteen", fmt.format(14), "14")
assertEquals("fifteen", fmt.format(15), "15")
assertEquals("sixteen", fmt.format(16), "16")
assertEquals("seventeen", fmt.format(17), "17")
assertEquals("eighteen", fmt.format(18), "18")
assertEquals("nineteen", fmt.format(19), "19")
assertEquals("twenty", fmt.format(20), "20")
assertEquals("twenty-one", fmt.format(21), "21")
assertEquals("twenty-two", fmt.format(22), "22")
assertEquals("twenty-three", fmt.format(23), "23")
assertEquals("twenty-four", fmt.format(24), "24")
assertEquals("twenty-five", fmt.format(25), "25")
assertEquals("twenty-six", fmt.format(26), "26")
assertEquals("twenty-seven", fmt.format(27), "27")
assertEquals("twenty-eight", fmt.format(28), "28")
assertEquals("twenty-nine", fmt.format(29), "29")
assertEquals("thirty", fmt.format(30), "30")
assertEquals("thirty-one", fmt.format(31), "31")
assertEquals("ninety-eight", fmt.format(98), "98")
assertEquals("ninety-nine", fmt.format(99), "99")
assertEquals("one hundred", fmt.format(100), "100")
assertEquals("one hundred one", fmt.format(101), "101")
assertEquals("one hundred two", fmt.format(102), "102")
assertEquals("nine hundred ninety-eight", fmt.format(998), "998")
assertEquals("nine hundred ninety-nine", fmt.format(999), "999")
assertEquals("one thousand", fmt.format(1000), "1000")
assertEquals("one thousand one", fmt.format(1001), "1001")
assertEquals("one thousand two", fmt.format(1002), "1002")
assertEquals("one thousand nine hundred ninety-eight", fmt.format(1998), "1998")
assertEquals("one thousand nine hundred ninety-nine", fmt.format(1999), "1999")
assertEquals("two thousand", fmt.format(2000), "2000")
assertEquals("two thousand one", fmt.format(2001), "2001")
assertEquals("two thousand two", fmt.format(2002), "2002")
assertEquals("nine thousand nine hundred ninety-eight", fmt.format(9998), "9998")
assertEquals("nine thousand nine hundred ninety-nine", fmt.format(9999), "9999")
assertEquals("ten thousand", fmt.format(10000), "10000")
assertEquals("ten thousand one", fmt.format(10001), "10001")
assertEquals("ten thousand two", fmt.format(10002), "10002")
assertEquals("one lakh", fmt.format(100000), "100000")
assertEquals("one lakh one", fmt.format(100001), "100001")
assertEquals("one lakh two", fmt.format(100002), "100002")
assertEquals("two lakh", fmt.format(200000), "200000")
assertEquals("two lakh one", fmt.format(200001), "200001")
assertEquals("two lakh two", fmt.format(200002), "200002")
assertEquals("ten lakh", fmt.format(1000000), "1000000")
assertEquals("ten lakh one", fmt.format(1000001), "1000001")
assertEquals("ten lakh two", fmt.format(1000002), "1000002")
assertEquals("twenty lakh", fmt.format(2000000), "2000000")
assertEquals("twenty lakh one", fmt.format(2000001), "2000001")
assertEquals("twenty lakh two", fmt.format(2000002), "2000002")
assertEquals("one crore", fmt.format(10000000), "10000000")
assertEquals("one crore one", fmt.format(10000001), "10000001")
assertEquals("one crore two", fmt.format(10000002), "10000002")
assertEquals("two crore", fmt.format(20000000), "20000000")
assertEquals("two crore one", fmt.format(20000001), "20000001")
assertEquals("two crore two", fmt.format(20000002), "20000002")
assertEquals("ten crore", fmt.format(100000000), "100000000")
assertEquals("ten crore one", fmt.format(100000001), "100000001")
assertEquals("ten crore two", fmt.format(100000002), "100000002")
assertEquals("twenty crore", fmt.format(200000000), "200000000")
assertEquals("twenty crore one", fmt.format(200000001), "200000001")
assertEquals("twenty crore two", fmt.format(200000002), "200000002")
assertEquals("one hundred crore", fmt.format(1000000000), "1000000000")
assertEquals("one hundred crore one", fmt.format(1000000001), "1000000001")
assertEquals("one hundred crore two", fmt.format(1000000002), "1000000002")
assertEquals("two hundred crore", fmt.format(2000000000), "2000000000")
assertEquals("two hundred crore one", fmt.format(2000000001), "2000000001")
assertEquals("two hundred crore two", fmt.format(2000000002), "2000000002")
  }
  @Test
  fun spelloutNumberingVerbose() {
 val fmt = RBNFEn_in().spelloutNumberingVerbose
assertEquals("minus one", fmt.format(-1), "-1")
assertEquals("zero", fmt.format(0), "0")
assertEquals("zero point two", fmt.format(0.2), "0.2")
assertEquals("zero point two three four five", fmt.format(0.2345), "0.2345")
assertEquals("zero point zero two", fmt.format(0.02), "0.02")
assertEquals("zero point zero two one three", fmt.format(0.0213), "0.0213")
assertEquals("four point seven five one", fmt.format(4.751), "4.751")
assertEquals("twelve point zero five one", fmt.format(12.051), "12.051")
assertEquals("thirteen point zero two five one", fmt.format(13.0251), "13.0251")
assertEquals("fourteen point zero two two five one", fmt.format(14.02251), "14.02251")
assertEquals("one", fmt.format(1), "1")
assertEquals("two", fmt.format(2), "2")
assertEquals("three", fmt.format(3), "3")
assertEquals("four", fmt.format(4), "4")
assertEquals("five", fmt.format(5), "5")
assertEquals("six", fmt.format(6), "6")
assertEquals("seven", fmt.format(7), "7")
assertEquals("eight", fmt.format(8), "8")
assertEquals("nine", fmt.format(9), "9")
assertEquals("ten", fmt.format(10), "10")
assertEquals("eleven", fmt.format(11), "11")
assertEquals("twelve", fmt.format(12), "12")
assertEquals("thirteen", fmt.format(13), "13")
assertEquals("fourteen", fmt.format(14), "14")
assertEquals("fifteen", fmt.format(15), "15")
assertEquals("sixteen", fmt.format(16), "16")
assertEquals("seventeen", fmt.format(17), "17")
assertEquals("eighteen", fmt.format(18), "18")
assertEquals("nineteen", fmt.format(19), "19")
assertEquals("twenty", fmt.format(20), "20")
assertEquals("twenty-one", fmt.format(21), "21")
assertEquals("twenty-two", fmt.format(22), "22")
assertEquals("twenty-three", fmt.format(23), "23")
assertEquals("twenty-four", fmt.format(24), "24")
assertEquals("twenty-five", fmt.format(25), "25")
assertEquals("twenty-six", fmt.format(26), "26")
assertEquals("twenty-seven", fmt.format(27), "27")
assertEquals("twenty-eight", fmt.format(28), "28")
assertEquals("twenty-nine", fmt.format(29), "29")
assertEquals("thirty", fmt.format(30), "30")
assertEquals("thirty-one", fmt.format(31), "31")
assertEquals("ninety-eight", fmt.format(98), "98")
assertEquals("ninety-nine", fmt.format(99), "99")
assertEquals("one hundred", fmt.format(100), "100")
assertEquals("one hundred and one", fmt.format(101), "101")
assertEquals("one hundred and two", fmt.format(102), "102")
assertEquals("nine hundred and ninety-eight", fmt.format(998), "998")
assertEquals("nine hundred and ninety-nine", fmt.format(999), "999")
assertEquals("one thousand", fmt.format(1000), "1000")
assertEquals("one thousand and one", fmt.format(1001), "1001")
assertEquals("one thousand and two", fmt.format(1002), "1002")
assertEquals("one thousand nine hundred and ninety-eight", fmt.format(1998), "1998")
assertEquals("one thousand nine hundred and ninety-nine", fmt.format(1999), "1999")
assertEquals("two thousand", fmt.format(2000), "2000")
assertEquals("two thousand and one", fmt.format(2001), "2001")
assertEquals("two thousand and two", fmt.format(2002), "2002")
assertEquals("nine thousand nine hundred and ninety-eight", fmt.format(9998), "9998")
assertEquals("nine thousand nine hundred and ninety-nine", fmt.format(9999), "9999")
assertEquals("ten thousand", fmt.format(10000), "10000")
assertEquals("ten thousand and one", fmt.format(10001), "10001")
assertEquals("ten thousand and two", fmt.format(10002), "10002")
assertEquals("one lakh", fmt.format(100000), "100000")
assertEquals("one lakh and one", fmt.format(100001), "100001")
assertEquals("one lakh and two", fmt.format(100002), "100002")
assertEquals("two lakh", fmt.format(200000), "200000")
assertEquals("two lakh and one", fmt.format(200001), "200001")
assertEquals("two lakh and two", fmt.format(200002), "200002")
assertEquals("ten lakh", fmt.format(1000000), "1000000")
assertEquals("ten lakh and one", fmt.format(1000001), "1000001")
assertEquals("ten lakh and two", fmt.format(1000002), "1000002")
assertEquals("twenty lakh", fmt.format(2000000), "2000000")
assertEquals("twenty lakh and one", fmt.format(2000001), "2000001")
assertEquals("twenty lakh and two", fmt.format(2000002), "2000002")
assertEquals("one crore", fmt.format(10000000), "10000000")
assertEquals("one crore and one", fmt.format(10000001), "10000001")
assertEquals("one crore and two", fmt.format(10000002), "10000002")
assertEquals("two crore", fmt.format(20000000), "20000000")
assertEquals("two crore and one", fmt.format(20000001), "20000001")
assertEquals("two crore and two", fmt.format(20000002), "20000002")
assertEquals("ten crore", fmt.format(100000000), "100000000")
assertEquals("ten crore and one", fmt.format(100000001), "100000001")
assertEquals("ten crore and two", fmt.format(100000002), "100000002")
assertEquals("twenty crore", fmt.format(200000000), "200000000")
assertEquals("twenty crore and one", fmt.format(200000001), "200000001")
assertEquals("twenty crore and two", fmt.format(200000002), "200000002")
assertEquals("one hundred crore", fmt.format(1000000000), "1000000000")
assertEquals("one hundred crore and one", fmt.format(1000000001), "1000000001")
assertEquals("one hundred crore and two", fmt.format(1000000002), "1000000002")
assertEquals("two hundred crore", fmt.format(2000000000), "2000000000")
assertEquals("two hundred crore and one", fmt.format(2000000001), "2000000001")
assertEquals("two hundred crore and two", fmt.format(2000000002), "2000000002")
  }
  @Test
  fun spelloutNumbering() {
 val fmt = RBNFEn_in().spelloutNumbering
assertEquals("minus one", fmt.format(-1), "-1")
assertEquals("zero", fmt.format(0), "0")
assertEquals("zero point two", fmt.format(0.2), "0.2")
assertEquals("zero point two three four five", fmt.format(0.2345), "0.2345")
assertEquals("zero point zero two", fmt.format(0.02), "0.02")
assertEquals("zero point zero two one three", fmt.format(0.0213), "0.0213")
assertEquals("four point seven five one", fmt.format(4.751), "4.751")
assertEquals("twelve point zero five one", fmt.format(12.051), "12.051")
assertEquals("thirteen point zero two five one", fmt.format(13.0251), "13.0251")
assertEquals("fourteen point zero two two five one", fmt.format(14.02251), "14.02251")
assertEquals("one", fmt.format(1), "1")
assertEquals("two", fmt.format(2), "2")
assertEquals("three", fmt.format(3), "3")
assertEquals("four", fmt.format(4), "4")
assertEquals("five", fmt.format(5), "5")
assertEquals("six", fmt.format(6), "6")
assertEquals("seven", fmt.format(7), "7")
assertEquals("eight", fmt.format(8), "8")
assertEquals("nine", fmt.format(9), "9")
assertEquals("ten", fmt.format(10), "10")
assertEquals("eleven", fmt.format(11), "11")
assertEquals("twelve", fmt.format(12), "12")
assertEquals("thirteen", fmt.format(13), "13")
assertEquals("fourteen", fmt.format(14), "14")
assertEquals("fifteen", fmt.format(15), "15")
assertEquals("sixteen", fmt.format(16), "16")
assertEquals("seventeen", fmt.format(17), "17")
assertEquals("eighteen", fmt.format(18), "18")
assertEquals("nineteen", fmt.format(19), "19")
assertEquals("twenty", fmt.format(20), "20")
assertEquals("twenty-one", fmt.format(21), "21")
assertEquals("twenty-two", fmt.format(22), "22")
assertEquals("twenty-three", fmt.format(23), "23")
assertEquals("twenty-four", fmt.format(24), "24")
assertEquals("twenty-five", fmt.format(25), "25")
assertEquals("twenty-six", fmt.format(26), "26")
assertEquals("twenty-seven", fmt.format(27), "27")
assertEquals("twenty-eight", fmt.format(28), "28")
assertEquals("twenty-nine", fmt.format(29), "29")
assertEquals("thirty", fmt.format(30), "30")
assertEquals("thirty-one", fmt.format(31), "31")
assertEquals("ninety-eight", fmt.format(98), "98")
assertEquals("ninety-nine", fmt.format(99), "99")
assertEquals("one hundred", fmt.format(100), "100")
assertEquals("one hundred one", fmt.format(101), "101")
assertEquals("one hundred two", fmt.format(102), "102")
assertEquals("nine hundred ninety-eight", fmt.format(998), "998")
assertEquals("nine hundred ninety-nine", fmt.format(999), "999")
assertEquals("one thousand", fmt.format(1000), "1000")
assertEquals("one thousand one", fmt.format(1001), "1001")
assertEquals("one thousand two", fmt.format(1002), "1002")
assertEquals("one thousand nine hundred ninety-eight", fmt.format(1998), "1998")
assertEquals("one thousand nine hundred ninety-nine", fmt.format(1999), "1999")
assertEquals("two thousand", fmt.format(2000), "2000")
assertEquals("two thousand one", fmt.format(2001), "2001")
assertEquals("two thousand two", fmt.format(2002), "2002")
assertEquals("nine thousand nine hundred ninety-eight", fmt.format(9998), "9998")
assertEquals("nine thousand nine hundred ninety-nine", fmt.format(9999), "9999")
assertEquals("ten thousand", fmt.format(10000), "10000")
assertEquals("ten thousand one", fmt.format(10001), "10001")
assertEquals("ten thousand two", fmt.format(10002), "10002")
assertEquals("one lakh", fmt.format(100000), "100000")
assertEquals("one lakh one", fmt.format(100001), "100001")
assertEquals("one lakh two", fmt.format(100002), "100002")
assertEquals("two lakh", fmt.format(200000), "200000")
assertEquals("two lakh one", fmt.format(200001), "200001")
assertEquals("two lakh two", fmt.format(200002), "200002")
assertEquals("ten lakh", fmt.format(1000000), "1000000")
assertEquals("ten lakh one", fmt.format(1000001), "1000001")
assertEquals("ten lakh two", fmt.format(1000002), "1000002")
assertEquals("twenty lakh", fmt.format(2000000), "2000000")
assertEquals("twenty lakh one", fmt.format(2000001), "2000001")
assertEquals("twenty lakh two", fmt.format(2000002), "2000002")
assertEquals("one crore", fmt.format(10000000), "10000000")
assertEquals("one crore one", fmt.format(10000001), "10000001")
assertEquals("one crore two", fmt.format(10000002), "10000002")
assertEquals("two crore", fmt.format(20000000), "20000000")
assertEquals("two crore one", fmt.format(20000001), "20000001")
assertEquals("two crore two", fmt.format(20000002), "20000002")
assertEquals("ten crore", fmt.format(100000000), "100000000")
assertEquals("ten crore one", fmt.format(100000001), "100000001")
assertEquals("ten crore two", fmt.format(100000002), "100000002")
assertEquals("twenty crore", fmt.format(200000000), "200000000")
assertEquals("twenty crore one", fmt.format(200000001), "200000001")
assertEquals("twenty crore two", fmt.format(200000002), "200000002")
assertEquals("one hundred crore", fmt.format(1000000000), "1000000000")
assertEquals("one hundred crore one", fmt.format(1000000001), "1000000001")
assertEquals("one hundred crore two", fmt.format(1000000002), "1000000002")
assertEquals("two hundred crore", fmt.format(2000000000), "2000000000")
assertEquals("two hundred crore one", fmt.format(2000000001), "2000000001")
assertEquals("two hundred crore two", fmt.format(2000000002), "2000000002")
  }
  @Test
  fun spelloutNumberingYear() {
 val fmt = RBNFEn_in().spelloutNumberingYear
// nonsense assertEquals("minus one", fmt.format(-1), "-1")
// nonsense assertEquals("zero", fmt.format(0), "0")
// nonsense assertEquals("0.2", fmt.format(0.2), "0.2")
// nonsense assertEquals("0.2", fmt.format(0.2345), "0.2345")
// nonsense assertEquals("0", fmt.format(0.02), "0.02")
// nonsense assertEquals("0", fmt.format(0.0213), "0.0213")
// nonsense assertEquals("4.8", fmt.format(4.751), "4.751")
// nonsense assertEquals("12.1", fmt.format(12.051), "12.051")
// nonsense assertEquals("13", fmt.format(13.0251), "13.0251")
// nonsense assertEquals("14", fmt.format(14.02251), "14.02251")
assertEquals("one", fmt.format(1), "1")
assertEquals("two", fmt.format(2), "2")
assertEquals("three", fmt.format(3), "3")
assertEquals("four", fmt.format(4), "4")
assertEquals("five", fmt.format(5), "5")
assertEquals("six", fmt.format(6), "6")
assertEquals("seven", fmt.format(7), "7")
assertEquals("eight", fmt.format(8), "8")
assertEquals("nine", fmt.format(9), "9")
assertEquals("ten", fmt.format(10), "10")
assertEquals("eleven", fmt.format(11), "11")
assertEquals("twelve", fmt.format(12), "12")
assertEquals("thirteen", fmt.format(13), "13")
assertEquals("fourteen", fmt.format(14), "14")
assertEquals("fifteen", fmt.format(15), "15")
assertEquals("sixteen", fmt.format(16), "16")
assertEquals("seventeen", fmt.format(17), "17")
assertEquals("eighteen", fmt.format(18), "18")
assertEquals("nineteen", fmt.format(19), "19")
assertEquals("twenty", fmt.format(20), "20")
assertEquals("twenty-one", fmt.format(21), "21")
assertEquals("twenty-two", fmt.format(22), "22")
assertEquals("twenty-three", fmt.format(23), "23")
assertEquals("twenty-four", fmt.format(24), "24")
assertEquals("twenty-five", fmt.format(25), "25")
assertEquals("twenty-six", fmt.format(26), "26")
assertEquals("twenty-seven", fmt.format(27), "27")
assertEquals("twenty-eight", fmt.format(28), "28")
assertEquals("twenty-nine", fmt.format(29), "29")
assertEquals("thirty", fmt.format(30), "30")
assertEquals("thirty-one", fmt.format(31), "31")
assertEquals("ninety-eight", fmt.format(98), "98")
assertEquals("ninety-nine", fmt.format(99), "99")
assertEquals("one hundred", fmt.format(100), "100")
assertEquals("one hundred one", fmt.format(101), "101")
assertEquals("one hundred two", fmt.format(102), "102")
assertEquals("nine hundred ninety-eight", fmt.format(998), "998")
assertEquals("nine hundred ninety-nine", fmt.format(999), "999")
assertEquals("one thousand", fmt.format(1000), "1000")
assertEquals("one thousand one", fmt.format(1001), "1001")
assertEquals("one thousand two", fmt.format(1002), "1002")
assertEquals("nineteen ninety-eight", fmt.format(1998), "1998")
assertEquals("nineteen ninety-nine", fmt.format(1999), "1999")
assertEquals("two thousand", fmt.format(2000), "2000")
assertEquals("two thousand one", fmt.format(2001), "2001")
assertEquals("two thousand two", fmt.format(2002), "2002")
assertEquals("ninety-nine ninety-eight", fmt.format(9998), "9998")
assertEquals("ninety-nine ninety-nine", fmt.format(9999), "9999")
assertEquals("ten thousand", fmt.format(10000), "10000")
assertEquals("ten thousand one", fmt.format(10001), "10001")
assertEquals("ten thousand two", fmt.format(10002), "10002")
assertEquals("one lakh", fmt.format(100000), "100000")
assertEquals("one lakh one", fmt.format(100001), "100001")
assertEquals("one lakh two", fmt.format(100002), "100002")
assertEquals("two lakh", fmt.format(200000), "200000")
assertEquals("two lakh one", fmt.format(200001), "200001")
assertEquals("two lakh two", fmt.format(200002), "200002")
assertEquals("ten lakh", fmt.format(1000000), "1000000")
assertEquals("ten lakh one", fmt.format(1000001), "1000001")
assertEquals("ten lakh two", fmt.format(1000002), "1000002")
assertEquals("twenty lakh", fmt.format(2000000), "2000000")
assertEquals("twenty lakh one", fmt.format(2000001), "2000001")
assertEquals("twenty lakh two", fmt.format(2000002), "2000002")
assertEquals("one crore", fmt.format(10000000), "10000000")
assertEquals("one crore one", fmt.format(10000001), "10000001")
assertEquals("one crore two", fmt.format(10000002), "10000002")
assertEquals("two crore", fmt.format(20000000), "20000000")
assertEquals("two crore one", fmt.format(20000001), "20000001")
assertEquals("two crore two", fmt.format(20000002), "20000002")
assertEquals("ten crore", fmt.format(100000000), "100000000")
assertEquals("ten crore one", fmt.format(100000001), "100000001")
assertEquals("ten crore two", fmt.format(100000002), "100000002")
assertEquals("twenty crore", fmt.format(200000000), "200000000")
assertEquals("twenty crore one", fmt.format(200000001), "200000001")
assertEquals("twenty crore two", fmt.format(200000002), "200000002")
assertEquals("one hundred crore", fmt.format(1000000000), "1000000000")
assertEquals("one hundred crore one", fmt.format(1000000001), "1000000001")
assertEquals("one hundred crore two", fmt.format(1000000002), "1000000002")
assertEquals("two hundred crore", fmt.format(2000000000), "2000000000")
assertEquals("two hundred crore one", fmt.format(2000000001), "2000000001")
assertEquals("two hundred crore two", fmt.format(2000000002), "2000000002")
  }
}
