package org.verdeterre.words

import org.slf4j.LoggerFactory
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.InputStreamReader

@Service
class WordsService {
    private val logger = LoggerFactory.getLogger(javaClass)!!

    val words = mutableSetOf<String>()

    init {
        logger.info("Loading word file...")
        ClassPathResource("/enable.txt").inputStream.use { inputStream ->
            val reader = BufferedReader(InputStreamReader(inputStream))
            reader.forEachLine { word -> words += word }
        }
        logger.info("Finished loading word file.")
    }
}