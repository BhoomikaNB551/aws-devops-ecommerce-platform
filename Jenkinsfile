pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                dir('app') {
                    sh 'mvn clean package'
                }
            }
        }

        stage('Test') {
            steps {
                dir('app') {
                    sh 'mvn test'
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    withCredentials([string(credentialsId: 'sonarqube-token', variable: 'SONAR_TOKEN')]) {
                        dir('app') {
                       sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.projectKey=aws-devops-ecommerce-platform -Dsonar.token=$SONAR_TOKEN'                        }
                    }
                }
            }
        }

        stage('Quality Gate') {
          steps {
           timeout(time: 5, unit: 'MINUTES') {
            waitForQualityGate abortPipeline: true
          }
      }
  }

        stage('Docker Build') {
            steps {
                sh 'docker build -t bhoomika98/ecommerce-app:jenkins ./app'
            }
        }

        stage('Docker Push') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-credentials',
                    usernameVariable: 'DOCKER_USERNAME',
                    passwordVariable: 'DOCKER_PASSWORD'
                )]) {
                    sh '''
                        echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
                        docker push bhoomika98/ecommerce-app:jenkins
                        docker logout
                    '''
                }
            }
        }
    }
}
