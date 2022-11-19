import com.example.models.domain.Post
import com.example.models.domain.User

object MockedData {
    val mockedUser = User(
        userName = "Mocked User Name",
        profileDataJoined = "March 23, 2021",
        profileOriginalPosts = 0,
        profileReposts = 0,
        profileQuotePosts = 0
    )

    val mockedPosts = listOf(
        Post(
            originalPostText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce vel tellus sed sem viverra lobortis. Aliquam nec mollis nisl. Ut varius maximus bibendum. Sed in venenatis orci. Nulla orci metus, ultrices nec vestibulum vitae, placerat ac erat. In at ornare enim, nec pretium nisl. Donec volutpat nisi ligula.",
            originalPostAuthor = "Rodrigo Barbacovi",
            type = Post.Companion.PostType.ORIGINAL_POST,
            userNameAuthor = "Rodrigo Barbacovi"
        ),
        Post(
            originalPostText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce vel tellus sed sem viverra lobortis. Aliquam nec mollis nisl. Ut varius maximus bibendum. Sed in venenatis orci. Nulla orci metus, ultrices nec vestibulum vitae, placerat ac erat. In at ornare enim, nec pretium nisl. Donec volutpat nisi ligula.",
            originalPostAuthor = "Rodrigo Barbacovi",
            userNameAuthor = "Jorge",
            type = Post.Companion.PostType.REPOST
        ),
        Post(
            originalPostText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce vel tellus sed sem viverra lobortis. Aliquam nec mollis nisl. Ut varius maximus bibendum. Sed in venenatis orci. Nulla orci metus, ultrices nec vestibulum vitae, placerat ac erat. In at ornare enim, nec pretium nisl. Donec volutpat nisi ligula.",
            originalPostAuthor = "Rodrigo Barbacovi",
            userNameAuthor = "João",
            additionalQuoteText = "Ohh I really loved that post!!!!",
            type = Post.Companion.PostType.QUOTE_POST
        ),
    )
}