def call(containerName, appName, pipelineNameOrId) {
  container(name: containerName, shell: '/busybox/sh') {
    withCredentials([string(credentialsId: 'spin-pipeline', variable: 'secretUrl')]) {
      sh "curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{}' '${secretUrl}/${appName}/${pipelineNameOrId}'"
    }
  }
}
