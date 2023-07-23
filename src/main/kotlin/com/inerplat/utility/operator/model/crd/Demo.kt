package com.inerplat.utility.operator.model.crd

import io.fabric8.kubernetes.api.model.Namespaced
import io.fabric8.kubernetes.client.CustomResource
import io.fabric8.kubernetes.model.annotation.Group
import io.fabric8.kubernetes.model.annotation.Version

@Group("inerplat.com")
@Version("v1alpha1")
class Demo : CustomResource<DemoSpec, DemoStatus>(), Namespaced {

}

data class DemoSpec(
    val image: String = "",
    val replicas: Int = 1
)

data class DemoStatus(
    val availableReplicas: Int = 1
)
