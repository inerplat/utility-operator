package com.inerplat.utility.operator.model.crd

import io.fabric8.kubernetes.api.model.Namespaced
import io.fabric8.kubernetes.client.CustomResource
import io.fabric8.kubernetes.model.annotation.Group
import io.fabric8.kubernetes.model.annotation.Version

@Group("inerplat.com")
@Version("v1alpha1")
class RootCommand : CustomResource<RootCommandSpec, RootCommandStatus>(), Namespaced {

}

data class RootCommandSpec(
    val command: List<String> = listOf(""),
    val targetPod: String = ""
)

data class RootCommandStatus(
    val stdout: String = "",
    val status: Boolean = false
)
