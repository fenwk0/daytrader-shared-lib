def call(directory,app, destination, imageName, imageTag, version, port, params = '') {
  container(name: 'kaniko', shell: '/busybox/sh') {
    withEnv(['PATH+EXTRA=/busybox:/kaniko']) {
      sh """#!/busybox/sh
      /kaniko/executor -v debug -f `pwd`/${directory}/${app}/Dockerfile -c `pwd`/${directory}/${app} |
        --insecure --skip-tls-verify --destination=${destination}/${imageName}:${iamgeTag} \
        --build-arg WAR_ARTIFACTID=${app} \
        --build-arg APP_VERSION=${version} \
        --build-arg APP_ARTIFACTID=${directory} \
        --build-arg EXPOSE_PORT=${port} ${params}
      """
    }
  }
}
