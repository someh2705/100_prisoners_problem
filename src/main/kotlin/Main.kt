import PrisonConfig.BENCH_TRIAL
import PrisonConfig.GAME_TRIAL
import kotlinx.coroutines.*

suspend fun main(): Unit = runBlocking {
    val result = trial()

    println("Bench result: ${result.average() / GAME_TRIAL * 100}%")
}

suspend fun trial() = coroutineScope {
    generateSequence {
        async(Dispatchers.Default) { play() }
    }.take(BENCH_TRIAL).awaitAll()
}

fun play() = generateSequence {
    Game().play()
}.take(GAME_TRIAL).count { it }