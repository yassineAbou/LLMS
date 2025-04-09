package org.yassineabou.playground.feature.chat.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerationRequest(
    @SerialName("prompt") val prompt: String,
    @SerialName("params") val params: GenerationParams = GenerationParams(),
    @SerialName("softprompt") val softPrompt: String? = null,
    @SerialName("trusted_workers") val trustedWorkers: Boolean = false,
    @SerialName("validated_backends") val validatedBackends: Boolean = true,
    @SerialName("slow_workers") val slowWorkers: Boolean = true,
    @SerialName("worker_blacklist") val workerBlacklist: Boolean = false,
    @SerialName("models") val models: List<String>? = null,
    @SerialName("dry_run") val dryRun: Boolean = false,
    @SerialName("disable_batching") val disableBatching: Boolean = false,
    @SerialName("allow_downgrade") val allowDowngrade: Boolean = false,
    @SerialName("extra_slow_workers") val extraSlowWorkers: Boolean = false
)

@Serializable
data class GenerationParams(
    // Formatting parameters
    @SerialName("frmtadsnsp") val formatAdsNSpace: Boolean = false,
    @SerialName("frmtrmblln") val formatRemoveBlankLines: Boolean = false,
    @SerialName("frmtrmspch") val formatRemoveSpeech: Boolean = false,
    @SerialName("frmttriminc") val formatTrimIncomplete: Boolean = false,

    // Repetition parameters
    @SerialName("rep_pen") val repetitionPenalty: Double = 3.0,
    @SerialName("rep_pen_range") val repetitionPenaltyRange: Int = 4096,
    @SerialName("rep_pen_slope") val repetitionPenaltySlope: Double = 10.0,

    // Generation controls
    @SerialName("singleline") val singleLine: Boolean = false,
    @SerialName("temperature") val temperature: Double = 5.0,
    @SerialName("tfs") val tailFreeSampling: Double = 1.0,
    @SerialName("top_a") val topA: Double = 1.0,
    @SerialName("top_k") val topK: Int = 100,
    @SerialName("top_p") val topP: Double = 1.0,
    @SerialName("typical") val typicalP: Double = 1.0,
    @SerialName("use_default_badwordsids") val useDefaultBadWordsIds: Boolean = true,

    // Sequence controls
    @SerialName("stop_sequence") val stopSequence: List<String>? = null,

    // Advanced parameters
    @SerialName("min_p") val minP: Double = 0.0,
    @SerialName("smoothing_factor") val smoothingFactor: Double = 0.0,
    @SerialName("dynatemp_range") val dynamicTempRange: Double = 0.0,
    @SerialName("dynatemp_exponent") val dynamicTempExponent: Double = 1.0,

    // Length controls
    @SerialName("n") val numGenerations: Int = 1,
    @SerialName("max_context_length") val maxContextLength: Int = 2048,
    @SerialName("max_length") val maxLength: Int = 80
)

// Keep your existing response classes
@Serializable
data class GenerationResponse(
    @SerialName("id") val id: String,
    @SerialName("kudos") val kudos: Double,
    @SerialName("message") val message: String? = null,
    @SerialName("warnings") val warnings: List<ApiWarning>? = null
)

@Serializable
data class ApiWarning(
    @SerialName("code") val code: String,
    @SerialName("message") val message: String
)

@Serializable
data class GenerationStatus(
    @SerialName("done") val done: Boolean,
    @SerialName("faulted") val faulted: Boolean,
    @SerialName("generations") val generations: List<GenerationResult>? = null
)

@Serializable
data class GenerationResult(
    @SerialName("text") val text: String
)

@Serializable
data class ApiError(
    @SerialName("message") val message: String,
    @SerialName("rc") val errorCode: String
)

/*
{
  "prompt": "what is the best anime of all time?",
  "params": {
    "frmtadsnsp": false,
    "frmtrmblln": false,
    "frmtrmspch": false,
    "frmttriminc": false,
    "rep_pen": 3,
    "rep_pen_range": 4096,
    "rep_pen_slope": 10,
    "singleline": false,
    "temperature": 5,
    "tfs": 1,
    "top_a": 1,
    "top_k": 100,
    "top_p": 1,
    "typical": 1,
    "use_default_badwordsids": true,
    "stop_sequence": [
      "string"
    ],
    "min_p": 0,
    "smoothing_factor": 0,
    "dynatemp_range": 0,
    "dynatemp_exponent": 1,
    "n": 1,
    "max_context_length": 2048,
    "max_length": 80
  },
  "softprompt": "string",
  "trusted_workers": false,
  "validated_backends": true,
  "slow_workers": false,

  "worker_blacklist": false,
  "models": [

  ],
  "dry_run": false,
  "disable_batching": false,
  "allow_downgrade": false,
  "extra_slow_workers": false
}

 */