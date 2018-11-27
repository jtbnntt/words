package org.verdeterre.words

import org.slf4j.LoggerFactory
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.InputStreamReader

@Service
class AnagramService {
    val logger = LoggerFactory.getLogger(javaClass)!!
    val anagramMap = mutableMapOf<String, List<String>>()

    init {
        ClassPathResource("/enable.txt").inputStream.use { inputStream ->
            logger.info("Processing word list...")

            val reader = BufferedReader(InputStreamReader(inputStream))
            var count = 0

            reader.forEachLine { word ->
                val key = wordKey(word)
                val wordList = anagramMap[key] ?: emptyList()
                anagramMap[key] = wordList + word

                count++
                if (count % 10000 == 0) {
                    logger.info("Processed $count words")
                }
            }

            logger.info("Finished processing $count words.")
        }
    }

    private fun wordKey(word: String) = word.toCharArray().sorted().joinToString("")

    fun getAnagrams(word: String) = (anagramMap[wordKey(word)] ?: emptyList()) - word
}