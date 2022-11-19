import MockedData.mockedPosts
import MockedData.mockedUser
import com.example.domain.repointerfaces.PostsRepository
import com.example.domain.repointerfaces.UserRepository
import com.example.domain.time.TimeStamp
import com.example.domain.usecases.AddPostResult
import com.example.domain.usecases.AddPostUseCaseImpl
import com.example.domain.usecases.AddPostUseCaseImpl.Companion.ONE_DAY_IN_EPOCH_TIME
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AddPostUseCaseImplTest {

    @MockK
    private lateinit var userRepository: UserRepository

    @MockK
    private lateinit var postsRepository: PostsRepository

    @MockK
    private lateinit var timeStamp: TimeStamp

    @InjectMockKs
    private lateinit var addPostUseCaseImplTest: AddPostUseCaseImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        coEvery { userRepository.getUser() } returns mockedUser
        every { timeStamp.getTimeStamp() } returns ONE_DAY_IN_EPOCH_TIME.toLong()
        every { postsRepository.getTimeOfFifthLastPost(mockedUser.userName) } returns null
    }

    @After
    fun teardown() {
        coVerify(exactly = 1) {
            userRepository.getUser()
        }
        verify(exactly = 1) {
            postsRepository.getTimeOfFifthLastPost(mockedUser.userName)
            timeStamp.getTimeStamp()
        }

        confirmVerified(userRepository, postsRepository, timeStamp)
    }

    @Test
    fun whenUserExceededDailyLimitShouldReturnError() = runBlocking {
        every { postsRepository.getTimeOfFifthLastPost(mockedUser.userName) } returns ONE_DAY_IN_EPOCH_TIME.toLong()

        val result = addPostUseCaseImplTest.invoke(mockedPosts.first())

        assertEquals(AddPostResult.EXCEEDED_DAILY_LIMIT, result)
    }

    @Test
    fun whenAddRepostPostShouldUpdateUserAndInsertPost() = runBlocking {
        every {
            postsRepository.insertPosts(
                listOf(mockedPosts[1].copy(userNameAuthor = mockedUser.userName))
            )
        } just runs
        every {
            userRepository.updateUser(mockedUser.copy(profileReposts = 1))
        } just runs

        val result =
            addPostUseCaseImplTest.invoke(mockedPosts[1])

        verify(exactly = 1) {
            postsRepository.insertPosts(
                listOf(mockedPosts[1].copy(userNameAuthor = mockedUser.userName))
            )
            userRepository.updateUser(mockedUser.copy(profileReposts = 1))
        }
        assertEquals(AddPostResult.SUCCEEDED, result)
    }

    @Test
    fun whenAddQuotePostShouldUpdateUserAndInsertPost() = runBlocking {
        every {
            postsRepository.insertPosts(
                listOf(mockedPosts[2].copy(userNameAuthor = mockedUser.userName))
            )
        } just runs
        every {
            userRepository.updateUser(mockedUser.copy(profileQuotePosts = 1))
        } just runs

        val result =
            addPostUseCaseImplTest.invoke(mockedPosts[2])

        verify(exactly = 1) {
            postsRepository.insertPosts(
                listOf(mockedPosts[2].copy(userNameAuthor = mockedUser.userName))
            )
            userRepository.updateUser(mockedUser.copy(profileQuotePosts = 1))
        }
        assertEquals(AddPostResult.SUCCEEDED, result)
    }

    @Test
    fun whenAddOriginalPostShouldUpdateUserAndInsertPost() = runBlocking {
        every {
            postsRepository.insertPosts(
                listOf(
                    mockedPosts.first().copy(
                        originalPostAuthor = mockedUser.userName,
                        userNameAuthor = mockedUser.userName
                    )
                )
            )
        } just runs
        every {
            userRepository.updateUser(mockedUser.copy(profileOriginalPosts = 1))
        } just runs

        val result = addPostUseCaseImplTest.invoke(mockedPosts.first())

        verify(exactly = 1) {
            postsRepository.insertPosts(
                listOf(
                    mockedPosts.first().copy(
                        originalPostAuthor = mockedUser.userName,
                        userNameAuthor = mockedUser.userName
                    )
                )
            )
            userRepository.updateUser(mockedUser.copy(profileOriginalPosts = 1))
        }
        assertEquals(AddPostResult.SUCCEEDED, result)
    }
}