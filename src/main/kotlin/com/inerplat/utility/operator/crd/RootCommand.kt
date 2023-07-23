package com.inerplat.utility.operator.crd

import com.inerplat.utility.operator.model.crd.RootCommandSpec
import com.inerplat.utility.operator.model.crd.RootCommandStatus
import io.fabric8.kubernetes.api.model.Namespaced
import io.fabric8.kubernetes.client.CustomResource
import io.fabric8.kubernetes.model.annotation.Group
import io.fabric8.kubernetes.model.annotation.Version

@Group("inerplat.com")
@Version("v1alpha1")
class RootCommand : CustomResource<RootCommandSpec, RootCommandStatus>(), Namespaced {

}
