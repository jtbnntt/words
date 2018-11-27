package org.verdeterre.words

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/words")
class WordsController(val wordsService: WordsService, val anagramService: AnagramService) {
    @GetMapping("/test")
    @ResponseBody
    fun test() = "Up and running!"

    @GetMapping("/all")
    @ResponseBody
    fun all() = object {
        val words = wordsService.words
    }

    @GetMapping("/anagrams/{word}")
    @ResponseBody
    fun anagrams(@PathVariable("word") word: String) = object {
        val word = word
        val anagrams = anagramService.getAnagrams(word)
    }
}