package com.inerplat.utility.operator.model.crd


data class DemoSpec(
    val image: String = "",
    val replicas: Int = 1
)

data class DemoStatus(
    val availableReplicas: Int = 1
)
