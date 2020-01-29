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
                  timeout(time: 1, unit: 'HOURS') {
                   // Just in case something goes wrong, pipeline will be killed after a timeout
                            qualityGate = waitForQualityGate() // Reuse taskId previously collected by withSonarQubeEnv
                            if (qg.status != 'OK') {
                                error "Pipeline aborted due to quality gate failure: ${qg.status}"
                            }
                            }
            } // SonarQube taskId is automatically attached to the pipeline context
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