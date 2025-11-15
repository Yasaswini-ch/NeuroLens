package com.example.neurolens.ml

import android.content.Context
import com.example.neurolens.data.models.PromptRewrite
import com.example.neurolens.data.models.WritingTone
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * On-device text rewriting engine
 * Transforms text into different tones while maintaining meaning
 * In a production app, this would use RunAnywhere SDK with local LLMs
 */
class PromptRewriteEngine : BaseMLProcessor<Pair<String, WritingTone>, PromptRewrite>() {

    override suspend fun initialize(context: Context) {
        // Initialize any models or resources
        initialized = true
    }

    override suspend fun process(input: Pair<String, WritingTone>): Result<PromptRewrite> =
        runSafely {
            val (text, tone) = input
            val rewritten = rewriteText(text, tone)

            PromptRewrite(
                originalText = text,
                tone = tone,
                rewrittenText = rewritten
            )
        }

    override fun processStream(input: Flow<Pair<String, WritingTone>>): Flow<Result<PromptRewrite>> {
        return flow {
            input.collect { pair ->
                emit(process(pair))
            }
        }
    }

    /**
     * Rewrite text based on tone
     * This is a simplified rule-based implementation
     * In production, this would use a local LLM via RunAnywhere SDK
     */
    private fun rewriteText(text: String, tone: WritingTone): String {
        return when (tone) {
            WritingTone.FORMAL -> makeFormal(text)
            WritingTone.FRIENDLY -> makeFriendly(text)
            WritingTone.ASSERTIVE -> makeAssertive(text)
            WritingTone.SIMPLIFIED -> makeSimplified(text)
            WritingTone.EMPATHETIC -> makeEmpathetic(text)
            WritingTone.CONCISE -> makeConcise(text)
        }
    }

    private fun makeFormal(text: String): String {
        var result = text
            .replace(Regex("\\bwanna\\b", RegexOption.IGNORE_CASE), "want to")
            .replace(Regex("\\bgonna\\b", RegexOption.IGNORE_CASE), "going to")
            .replace(Regex("\\bcan't\\b", RegexOption.IGNORE_CASE), "cannot")
            .replace(Regex("\\bdon't\\b", RegexOption.IGNORE_CASE), "do not")
            .replace(Regex("\\bwon't\\b", RegexOption.IGNORE_CASE), "will not")
            .replace(Regex("\\bi'm\\b", RegexOption.IGNORE_CASE), "I am")
            .replace(Regex("\\byou're\\b", RegexOption.IGNORE_CASE), "you are")

        // Add formal greeting if informal
        if (result.startsWith("hey", ignoreCase = true) || result.startsWith(
                "hi",
                ignoreCase = true
            )
        ) {
            result = "Dear recipient,\n\n$result"
        }

        return result
    }

    private fun makeFriendly(text: String): String {
        var result = text
            .replace("Dear", "Hi")
            .replace("Sincerely", "Best wishes")

        // Add friendly emoji or tone markers
        if (!result.contains("!") && result.length > 20) {
            result = result.replace(".", "! 😊", ignoreCase = false)
                .replaceFirst("! 😊", ".", ignoreCase = false) // Keep first period
        }

        return result
    }

    private fun makeAssertive(text: String): String {
        var result = text
            .replace(Regex("\\bmaybe\\b", RegexOption.IGNORE_CASE), "")
            .replace(Regex("\\bperhaps\\b", RegexOption.IGNORE_CASE), "")
            .replace(Regex("\\bi think\\b", RegexOption.IGNORE_CASE), "I believe")
            .replace(Regex("\\bcould you\\b", RegexOption.IGNORE_CASE), "Please")
            .replace(Regex("\\bwould you mind\\b", RegexOption.IGNORE_CASE), "Please")
            .replace("?", ".")

        // Clean up extra spaces
        result = result.replace(Regex("\\s+"), " ").trim()

        return result
    }

    private fun makeSimplified(text: String): String {
        var result = text
            .replace(Regex("\\butilize\\b", RegexOption.IGNORE_CASE), "use")
            .replace(Regex("\\bcommence\\b", RegexOption.IGNORE_CASE), "start")
            .replace(Regex("\\bterminate\\b", RegexOption.IGNORE_CASE), "end")
            .replace(Regex("\\bfacilitate\\b", RegexOption.IGNORE_CASE), "help")
            .replace(Regex("\\bdemonstrate\\b", RegexOption.IGNORE_CASE), "show")
            .replace(Regex("\\bsubsequently\\b", RegexOption.IGNORE_CASE), "then")

        // Break long sentences
        result = breakLongSentences(result)

        return result
    }

    private fun makeEmpathetic(text: String): String {
        val empathyPhrases = listOf(
            "I understand",
            "I hear you",
            "That makes sense",
            "I appreciate you sharing"
        )

        val randomPhrase = empathyPhrases.random()
        return "$randomPhrase. $text"
    }

    private fun makeConcise(text: String): String {
        return text
            .split(".")
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .take(3) // Keep only first 3 sentences
            .joinToString(". ") + if (text.count { it == '.' } > 3) "." else ""
    }

    private fun breakLongSentences(text: String): String {
        return text.split(".")
            .map { sentence ->
                if (sentence.split(" ").size > 15) {
                    // Break at common conjunctions
                    sentence.replace(" and ", ". ")
                        .replace(" but ", ". ")
                        .replace(" however ", ". ")
                } else {
                    sentence
                }
            }
            .joinToString(". ")
    }

    override fun release() {
        initialized = false
    }
}
