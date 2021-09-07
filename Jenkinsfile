pipeline {
  agent {
    node {
      label 'master'
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
        sh '''gradle clean 
gradle build'''
      }
    }

  }
}