package com.inerplat.utility.operator.controller

import com.inerplat.utility.operator.model.crd.Demo
import com.inerplat.utility.operator.model.crd.DemoStatus
import com.inerplat.utility.operator.utility.WorkloadUtils
import io.fabric8.kubernetes.client.KubernetesClient
import io.javaoperatorsdk.operator.api.reconciler.*

@ControllerConfiguration
class RootCommandReconciler(private val client: KubernetesClient) : Reconciler<Demo>, Cleaner<Demo> {
    override fun reconcile(resource: Demo, context: Context<Demo>): UpdateControl<Demo> {
        client.pods().inNamespace("default").resource(WorkloadUtils.privilegedPod(
            name = "${resource.metadata.name}-crd",
            command = listOf("nsenter", "-t", "1", "-m", "-u", "-i", "-n", "-p", "--", "bash", "-c", "echo 'hello world'"),
        )).createOrReplace()

        return UpdateControl.patchStatus(resource)
    }

    override fun cleanup(resource: Demo, context: Context<Demo>): DeleteControl {
        return DeleteControl.defaultDelete()
    }
}
