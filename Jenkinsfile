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

    }
    }