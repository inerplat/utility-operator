package com.inerplat.utility.operator.model.crd



data class RootCommandSpec(
    val command: List<String> = listOf(""),
    val targetPod: String = ""
)

data class RootCommandStatus(
    val stdout: String = "",
    val status: Boolean = false
)
