import com.example.domain.exampleformutation.MyMutationExampleClass
import com.example.domain.exampleformutation.MyMutationExampleClass.Companion.A_EQUALS_B
import com.example.domain.exampleformutation.MyMutationExampleClass.Companion.A_GRATER_THAN_B
import com.example.domain.exampleformutation.MyMutationExampleClass.Companion.AT_LEAST_ONE_NUMBER_NEGATIVE
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

        assertEquals(AT_LEAST_ONE_NUMBER_NEGATIVE, result)
    }

    @Test
    fun `Should return BOTH_ZERO given (0,0)`() = runBlocking {
        val result = myMutationExampleClass.compare(0, 0)

        assertEquals(BOTH_ZERO, result)
    }

    @Test
    fun `Should return A_GRATER_THAN_B given (3,1) `() = runBlocking {
        val result = myMutationExampleClass.compare(3, 1)

        assertEquals(A_GRATER_THAN_B, result)
    }

    @Test
    fun `Should return B_GRATER_THAN_A given (1,4) `() = runBlocking {
        val result = myMutationExampleClass.compare(1, 4)

        assertEquals(B_GRATER_THAN_A, result)
    }

    @Test
    fun `Should return A_EQUALS_B given (7,7)`() = runBlocking {
        val result = myMutationExampleClass.compare(7, 7)

        assertEquals(A_EQUALS_B, result)
    }

//    @Test
//    fun `Should return B_GRATER_THAN_A given (-7,7)`() = runBlocking {
//        val result = myMutationExampleClass.compare(-7, 7)
//
//        assertEquals(AT_LEAST_ONE_NUMBER_NEGATIVE, result)
//    }
//
//    @Test
//    fun `Should return A_GRATER_THAN_B given (-7,7)`() = runBlocking {
//        val result = myMutationExampleClass.compare(7, -7)
//
//        assertEquals(AT_LEAST_ONE_NUMBER_NEGATIVE, result)
//    }
}