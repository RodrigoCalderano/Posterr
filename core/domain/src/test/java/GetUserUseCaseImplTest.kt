import StubData.mockedUser
import com.example.domain.repointerfaces.UserRepository
import com.example.domain.usecases.GetUserUseCaseImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetUserUseCaseImplTest {

    @MockK
    private lateinit var userRepository: UserRepository

    @InjectMockKs
    private lateinit var getUserUseCase: GetUserUseCaseImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @After
    fun teardown() {
        confirmVerified(userRepository)
    }

    @Test
    fun shouldGetUserFromRepo() = runBlocking {
        coEvery { userRepository.getUser() } returns mockedUser

        val result = getUserUseCase.invoke()

        coVerify(exactly = 1) { userRepository.getUser() }
        assertEquals(mockedUser, result)
    }
}