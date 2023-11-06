import com.example.domain.exampleformutation.MyMutationExampleClass
import com.example.domain.exampleformutation.MyMutationExampleClass.Companion.A_EQUALS_B
import com.example.domain.exampleformutation.MyMutationExampleClass.Companion.A_GRATER_THAN_B
import com.example.domain.exampleformutation.MyMutationExampleClass.Companion.BOTH_NEGATIVE
import com.example.domain.exampleformutation.MyMutationExampleClass.Companion.BOTH_ZERO
import com.example.domain.exampleformutation.MyMutationExampleClass.Companion.B_GRATER_THAN_A
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class MyMutationExampleClassImplTest {

    private val myMutationExampleClass = MyMutationExampleClass()

    @Test
    fun `Should return BOTH_NEGATIVE given (-2,-1)`() = runBlocking {
        val result = myMutationExampleClass.compare(-2, -1)

        assertEquals(BOTH_NEGATIVE, result)
    }

    @Test
    fun `Should return BOTH_ZERO given (0,0)`() = runBlocking {
        val result = myMutationExampleClass.compare(0, 0)

        assertEquals(BOTH_ZERO, result)
    }

    @Test
    fun `Should return A_GRATER_THAN_B given (3,0)`() = runBlocking {
        val result = myMutationExampleClass.compare(3, 0)

        assertEquals(A_GRATER_THAN_B, result)
    }

    @Test
    fun `Should return B_GRATER_THAN_A given (0,4)`() = runBlocking {
        val result = myMutationExampleClass.compare(0, 4)

        assertEquals(B_GRATER_THAN_A, result)
    }

    @Test
    fun `Should return A_EQUALS_B given (7,7)`() = runBlocking {
        val result = myMutationExampleClass.compare(7, 7)

        assertEquals(A_EQUALS_B, result)
    }

    @Test
    fun `Should return B_GRATER_THAN_A given (-7,7)`() = runBlocking {
        val result = myMutationExampleClass.compare(-7, 7)

        assertEquals(BOTH_NEGATIVE, result)
    }

    @Test
    fun `Should return A_GRATER_THAN_B given (-7,7)`() = runBlocking {
        val result = myMutationExampleClass.compare(7, -7)

        assertEquals(BOTH_NEGATIVE, result)
    }

    @Test
    fun `Should return true given OPA`() = runBlocking {
        val result = myMutationExampleClass.jorge("OPA")

        assertEquals(true, result)
    }

    @Test
    fun `Should return true given EPA`() = runBlocking {
        val result = myMutationExampleClass.jorge("OPA2")

        assertEquals(false, result)
    }

    @Test
    fun `Should return true given null`() = runBlocking {
        val result = myMutationExampleClass.jorge(null)

        assertEquals(true, result)
    }
}