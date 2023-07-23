package com.inerplat.utility.operator.controller

import com.inerplat.utility.operator.crd.RootCommand
import com.inerplat.utility.operator.utility.WorkloadUtils
import io.fabric8.kubernetes.client.KubernetesClient
import io.javaoperatorsdk.operator.api.reconciler.*

@ControllerConfiguration
class RootCommandReconciler(private val client: KubernetesClient) : Reconciler<RootCommand>, Cleaner<RootCommand> {
    override fun reconcile(resource: RootCommand, context: Context<RootCommand>): UpdateControl<RootCommand> {
        client.pods().inNamespace("default").resource(WorkloadUtils.privilegedPod(
            name = "${resource.metadata.name}-crd",
            command = listOf("nsenter", "-t", "1", "-m", "-u", "-i", "-n", "-p", "--", "bash", "-c", "echo 'hello world'"),
        )).createOrReplace()

        return UpdateControl.patchStatus(resource)
    }

    override fun cleanup(resource: RootCommand, context: Context<RootCommand>): DeleteControl {
        return DeleteControl.defaultDelete()
    }
}
