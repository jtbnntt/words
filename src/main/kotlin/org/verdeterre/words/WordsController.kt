package org.verdeterre.words

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/words")
class WordsController(val anagramService: AnagramService) {
    @GetMapping("/test")
    @ResponseBody
    fun test() = "Up and running!"

    @GetMapping("/anagrams/{word}")
    @ResponseBody
    fun anagrams(@PathVariable("word") word: String) = object {
        val word = word
        val anagrams = anagramService.getAnagrams(word)
    }
}