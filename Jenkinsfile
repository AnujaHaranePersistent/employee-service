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
                  bat "mvn clean -DskipTests install"
             }
        }
         stage("Run Tests and Static Code Analysis") {
              steps {
                   bat "mvn test"
                   bat "mvn sonar:sonar"
              }
         }

   
    }
    }