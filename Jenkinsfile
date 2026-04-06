pipeline {
    agent any

    tools {
        maven 'Maven 3.9.12'
    }

    environment {
        PATH = "C:\\Program Files\\Docker\\Docker\\resources\\bin;${env.PATH}"
        DOCKER_HOST = "npipe:////./pipe/docker_engine"
        DOCKERHUB_CREDENTIALS_ID = 'Docker_Hub'
        DOCKERHUB_REPO = 'irakump/otp-week2-localization'
        DOCKER_IMAGE_TAG = 'v1'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'database', url: 'https://github.com/irakump/otp-week2-localization.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('Generate Report') {
            steps {
                bat 'mvn jacoco:report'
            }
        }

        stage('Publish Coverage Report') {
            steps {
                jacoco()
            }
        }

        stage('Build Docker Image') {
            steps {
                bat "docker build -t %DOCKERHUB_REPO%:%DOCKER_IMAGE_TAG% ."
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                    withCredentials([usernamePassword(
                        credentialsId: 'Docker_Hub',
                        usernameVariable: 'DOCKER_USER',
                        passwordVariable: 'DOCKER_PASS'
                    )]) {
                        bat "docker login -u %DOCKER_USER% -p %DOCKER_PASS%"
                        bat "docker push %DOCKERHUB_REPO%:%DOCKER_IMAGE_TAG%"
                        bat "docker logout"
                    }
                }
        }

    }
}
