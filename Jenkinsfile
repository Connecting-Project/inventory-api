pipeline {
  agent {
    docker {
      image 'openjdk:11'
    }

  }
  stages {
    stage('source') {
      steps {
        git(url: 'https://github.com/Connecting-Project/inventory-api.git', branch: 'main', changelog: true)
      }
    }

    stage('build') {
      steps {
        tool 'gradle'
      }
    }

  }
}