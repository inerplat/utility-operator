package com.inerplat.utility.operator.utility

import io.fabric8.kubernetes.api.model.*
import io.fabric8.kubernetes.api.model.apps.Deployment
import io.fabric8.kubernetes.api.model.apps.DeploymentBuilder
import io.fabric8.kubernetes.api.model.apps.DeploymentSpecBuilder

class WorkloadUtils {
    companion object {
        fun simpleDeployment(name: String, image: String, replicas: Int = 1, containerPort: Int = 80): Deployment {
            return DeploymentBuilder()
                .withMetadata(ObjectMetaBuilder()
                    .withName(name)
                    .withLabels<String, String>(mapOf("app" to name))
                    .withLabels<String, String>(mapOf("inerplat.com/app" to name))
                    .withNamespace("default")
                    .build())
                .withSpec(DeploymentSpecBuilder()
                    .withReplicas(replicas)
                    .withTemplate(PodTemplateSpecBuilder()
                        .withMetadata(ObjectMetaBuilder()
                            .withLabels<String, String>(mapOf("app" to name))
                            .build())
                        .withSpec(PodSpecBuilder()
                            .withContainers(ContainerBuilder()
                                .withName(name)
                                .withImage(image)
                                .withPorts(ContainerPortBuilder().withContainerPort(80).build())
                                .build())
                            .build())
                        .build())
                    .withSelector(LabelSelectorBuilder()
                        .withMatchLabels<String, String>(mapOf("app" to name))
                        .build())
                    .build())
                .build()
        }
    }
}
