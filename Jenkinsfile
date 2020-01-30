pipeline {

    agent any
    environment {
        registry = "anujaharane/employee-service"
        registryCredential = 'dockerhub'
        dockerImage = ''
        qualityGate=''
      }
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
        stage('SonarQube analysis') {
              steps{
            script {
            withSonarQubeEnv('sonarqube') {
                bat 'mvn sonar:sonar'
                }
                   // Wait for 30 sec to generate sonarqube status report
                   sleep(time:30,unit:"SECONDS")
                            qualityGate = waitForQualityGate() 
                            if (qualityGate.status != 'OK') {
                                error "Pipeline aborted due to quality gate failure: ${qualityGate.status}"
                            }
                           }
            }
             }
      
       stage('Building image') {
             steps{
               script {
                  dockerImage = docker.build registry + ":$BUILD_NUMBER"
                  docker.withRegistry( '', registryCredential ) {
                              dockerImage.push()
               }
             }
           }
           }
    }
    }