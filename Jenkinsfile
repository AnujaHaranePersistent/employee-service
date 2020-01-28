pipeline {

    agent any
    tools {
        maven 'maven'
    }
    stages {

        stage ('Git checkout branch') {
            steps {
                git branch: 'jenkins-cicd', credentialsId: 'Github', url: 'https://github.com/AnujaHaranePersistent/employee-service.git'
            }
        }

        stage("Build Project") {
                    steps {
                        sh "mvn clean install"
                    }
                }
        stage("Test Code Coverage") {
                    steps {
                        sh "mvn test"

                    }
                }

    }
    }