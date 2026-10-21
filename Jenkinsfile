
pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                echo 'Source code is available in Jenkins workspace'
            }
        }

        stage('Check Java and Maven') {
            steps {
                bat 'java -version'
                bat 'mvn -version'
            }
        }

        stage('Build Java Application') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                bat 'docker build -t faculty-cicd:latest .'
            }
        }

        stage('Run Docker Container') {
            steps {
                bat 'docker run --rm faculty-cicd:latest'
            }
        }
    }

    post {
        success {
            echo 'Faculty CI/CD pipeline completed successfully!'
        }

        failure {
            echo 'Pipeline failed. Please check the Console Output.'
        }
    }
}
