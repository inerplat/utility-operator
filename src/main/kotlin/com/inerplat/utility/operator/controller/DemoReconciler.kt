package com.inerplat.utility.operator.controller

import com.inerplat.utility.operator.model.crd.Demo
import com.inerplat.utility.operator.model.crd.DemoStatus
import com.inerplat.utility.operator.utility.WorkloadUtils
import io.fabric8.kubernetes.client.KubernetesClient
import io.javaoperatorsdk.operator.api.reconciler.*

@ControllerConfiguration
class DemoReconciler(
    private val client: KubernetesClient
) : Reconciler<Demo>, Cleaner<Demo> {
    override fun reconcile(resource: Demo, context: Context<Demo>): UpdateControl<Demo> {
        client.apps().deployments().resource(
            WorkloadUtils.simpleDeployment(
                name = "${resource.metadata.name}-crd",
                image = resource.spec.image,
                replicas = resource.spec.replicas,
                containerPort = 80
            )
        ).createOrReplace()
        val replicas = client.apps().deployments().inNamespace("default").withName("${resource.metadata.name}-crd")?.get()?.status?.availableReplicas ?: 0
        resource.status = DemoStatus(replicas)
        return UpdateControl.patchStatus(resource)
    }

    override fun cleanup(resource: Demo, context: Context<Demo>): DeleteControl {
        if(client.apps().deployments().inNamespace("default").withName("${resource.metadata.name}-crd")?.get() != null) {
            client.apps().deployments().inNamespace("default").withName("${resource.metadata.name}-crd").delete()
        }
        return DeleteControl.defaultDelete()
    }
}
