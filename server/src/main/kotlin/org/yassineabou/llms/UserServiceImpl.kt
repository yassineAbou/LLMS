package org.yassineabou.llms


import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

class UserServiceImpl() : UserService {

    override suspend fun hello(platform: String, userData: UserData): String {
        return "$platform: Nice to see you ${userData.lastName}, " +
                "from ${userData.address}. What would you like to search today?"
    }



    // Removed suspend modifier to match interface
    override fun subscribeToNews(): Flow<String> {
        return flow {
            repeat(10) { count ->
                emit(
                    "${count+1}. " +
                            articleTitles[Random.nextInt(articleTitles.size)]
                )
                delay(300) // OK - inside flow builder
            }
        }
    }

    // Removed suspend modifier to match interface
    override fun subscribeToTopic(topic: String): Flow<String> {
        return flow {
            repeat(10) { count ->
                val articles = articleTitles.filter { it.contains(topic) }
                if(articles.size <= count) {
                    emit("No more articles on \"$topic\"")
                    return@flow
                }
                emit("${count+1}. " + articles[count])
                delay(100) // OK - inside flow builder
            }
        }
    }

    var count = 0
    override suspend fun ping() : String {
        return "pong ${count+1}"
    }

    // Simulate a database of articles
    companion object {
        val articleTitles = listOf(
            "The Day the Earth Stood Still",
            "The Birth of a New Species",
            "The Origin Story of Our World",
            "The Power of Positive Thinking",
            "The Science of Love",
            "The Magic of Sleep",
            "The Art of Reading",
            "The Future of Work",
            "The Science of Happiness",
            "The Mystery of the Old Man",
            "The Power of Positive Energy",
            "The Science of Time",
            "The Art of Music",
            "The Magic of Art",
            "The Science of Space",
        )
    }

}