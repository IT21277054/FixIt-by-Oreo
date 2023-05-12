import org.junit.Assert.*
import org.junit.Test


class UnitTest {

    @Test
    fun validNumber() {
        val validNumber = "0763573777"
        assertTrue(isValidPhoneNumber(validNumber))
    }

    @Test
    fun invalidNumber() {
        val invalidNumber = "12345"
        val invalidNumberWithAlpha = "1234abc"
        val invalidNumberWithSpace = "12 345"
        val invalidNumberWithExtraDigits = "1234567890123456"

        assertFalse(isValidPhoneNumber(invalidNumber))
        assertFalse(isValidPhoneNumber(invalidNumberWithAlpha))
        assertFalse(isValidPhoneNumber(invalidNumberWithSpace))
        assertFalse(isValidPhoneNumber(invalidNumberWithExtraDigits))
    }

    @Test
    fun validatePassword() {
        val validPassword = "Thiwa25109"
        //val p = "T"

        assertTrue(validatePassword(validPassword))
        //assertTrue(validatePassword(p))
    }

    @Test
    fun invalidPassword() {
        val invalidLessThanMin = "T"
        val invalidMoreThanMax = "Thiwa1111122222333334444455555"
        val invalidPasswordWithoutUppercase = "thiwa25109"
        val invalidPasswordWithoutLowercase = "THIWA25109"
        val invalidPasswordWithoutDigits = "Thiwanka"

        assertFalse(isValidPhoneNumber(invalidLessThanMin))
        assertFalse(isValidPhoneNumber(invalidMoreThanMax))
        assertFalse(isValidPhoneNumber(invalidPasswordWithoutUppercase))
        assertFalse(isValidPhoneNumber(invalidPasswordWithoutLowercase))
        assertFalse(isValidPhoneNumber(invalidPasswordWithoutDigits))
    }

    @Test
    fun validAge() {
        val validAge = "20"
        //val p = "T"

        assertTrue(validateAge(validAge))
    }

    @Test
    fun invalidAge() {
        val invalidType = "T"
        val invalidMoreThanMax = "100"
        val invalidLessThanMin = "5"

        assertFalse(validateAge(invalidType))
        assertFalse(validateAge(invalidMoreThanMax))
        assertFalse(validateAge(invalidLessThanMin))
    }


    private fun isValidPhoneNumber(number: String): Boolean {
        val regexPattern = """^\d{10}$""".toRegex()
        return regexPattern.matches(number)
    }

    fun validatePassword(password: String): Boolean {
        val minLength = 7
        val maxLength = 20

        if (password.length !in minLength..maxLength) {
            // Password length is not within the required range
            //Toast.makeText(this, "Password length is not within the required range(min - 7, max - 20)", Toast.LENGTH_LONG).show()
            return false
        }

        // Add more validation rules as per your requirements
        // For example, you can check for the presence of specific characters, uppercase letters, etc.

        // At least one uppercase letter
        if (!password.any { it.isUpperCase() }) {
            //Toast.makeText(this, "Don't have any uppercase letter", Toast.LENGTH_SHORT).show()
            return false
        }

        // At least one lowercase letter
        if (!password.any { it.isLowerCase() }) {
            //Toast.makeText(this, "Don't have any lowercase letter", Toast.LENGTH_SHORT).show()
            return false
        }

        // At least one digit
        if (!password.any { it.isDigit() }) {
            //Toast.makeText(this, "Don't have any digits", Toast.LENGTH_SHORT).show()
            return false
        }

        // Password meets all criteria
        return true
    }

    fun validateAge(age: String): Boolean {
        // Check if the age string contains only digits
        if (!age.matches(Regex("\\d+"))) {
            //Toast.makeText(this, "Invalid Age", Toast.LENGTH_SHORT).show()
            return false
        }

        // Convert the age string to an Int value and validate the age
        val age = age.toInt()
        if (age < 18 || age > 65) {
            //Toast.makeText(this, "Your are under age (18) or over age (65)", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

}