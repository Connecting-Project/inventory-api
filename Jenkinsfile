pipeline {
  agent {
    docker {
      image 'node:14-alpine'
    }

  }
  stages {
    stage('source') {
      steps {
        git(url: 'https://github.com/Connecting-Project/inventory-api.git', branch: 'main', changelog: true)
      }
    }

  }
}