pipeline {

    agent any
    tools {
        maven 'maven'
    }
    stages {

        stage ('Git checkout branch') {
            steps {
                git branch: 'branch', credentialsId: '****', url: 'https://projectRepo'
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