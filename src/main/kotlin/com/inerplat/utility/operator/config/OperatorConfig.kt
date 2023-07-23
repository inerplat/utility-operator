package com.inerplat.utility.operator.config

import com.inerplat.utility.operator.controller.DemoReconciler
import io.fabric8.kubernetes.client.KubernetesClient
import io.fabric8.kubernetes.client.KubernetesClientBuilder
import io.javaoperatorsdk.operator.Operator
import io.javaoperatorsdk.operator.api.reconciler.Reconciler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OperatorConfig {
    @Bean
    fun demoReconciler(client: KubernetesClient): DemoReconciler {
        return DemoReconciler(client)
    }

    @Bean
    fun client(): KubernetesClient {
        return KubernetesClientBuilder().build()
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    fun operator(controllers: List<Reconciler<*>>): Operator {
        val operator = Operator()
        controllers.forEach {
            operator.register(it)
        }
        return operator
    }

}
