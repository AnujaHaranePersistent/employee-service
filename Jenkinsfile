pipeline {

    agent any
      tools {
            maven 'Maven'
            jdk 'jdk8'
        }
    stages {

        stage ('Git checkout branch') {
            steps {
                git branch: 'jenkins-cicd',
                credentialsId: 'Github',
                url: 'https://github.com/AnujaHaranePersistent/employee-service.git'
            }
        }

        stage("Build Project") {
             steps {
                  bat "mvn clean -Dmaven.test.failure.ignore=true install"
                  jacoco(
                        execPattern: 'target/*.exec',
                        classPattern: 'target/classes',
                        sourcePattern: 'src/main/java',
                        exclusionPattern: 'src/test*'
                  )
             }
        }


   
    }
    }