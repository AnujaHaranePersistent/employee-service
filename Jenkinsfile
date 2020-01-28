pipeline {

    agent any
      tools {
            maven 'Maven 3.3.9'
            jdk 'jdk8'
        }
    stages {
    stage ('Initialize') {
                steps {
                    sh '''
                        echo "PATH = ${PATH}"
                        echo "M2_HOME = ${M2_HOME}"
                    '''
                }
            }

        stage ('Git checkout branch') {
            steps {
                git branch: 'jenkins-cicd', credentialsId: 'Github', url: 'https://github.com/AnujaHaranePersistent/employee-service.git'
            }
        }

        stage("Build Project") {
                    steps {
                        sh "mvn clean -Dmaven.test.failure.ignore=true install"
                    }
                }
   
    }
    }