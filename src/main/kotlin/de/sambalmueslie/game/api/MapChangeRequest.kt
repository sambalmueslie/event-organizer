package de.sambalmueslie.game.api

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import de.sambalmueslie.common.CrudObjectChangeRequest

@JsonIgnoreProperties(ignoreUnknown = true)
data class MapChangeRequest(
    @JsonProperty
    val name: String,
) : CrudObjectChangeRequest
