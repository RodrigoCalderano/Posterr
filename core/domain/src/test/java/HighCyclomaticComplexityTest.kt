import com.example.domain.exampleformutation.HighCyclomaticComplexity
import org.junit.Assert.assertEquals
import org.junit.Test

class HighCyclomaticComplexityTest {

    private val highCyclomaticComplexity = HighCyclomaticComplexity()

    @Test
    fun testComplexCode() {
        // Arrange
        val input = 12
        val expectedOutput = "Number divisible by 2 and 3"

        // Act
        val result = highCyclomaticComplexity.complexCode(input)

        // Assert
        assertEquals(expectedOutput, result)
    }
}