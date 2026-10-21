pipeline {
    agent any

    stages {
        stage('Check Java and Maven') {
            steps {
                bat 'set "PATH=C:\\Users\\Gandla Vengasree\\Downloads\\apache-maven-3.9.16-bin\\apache-maven-3.9.16\\bin;%PATH%" && mvn -version'
            }
        }

        stage('Build Java Application') {
            steps {
                bat 'set "PATH=C:\\Users\\Gandla Vengasree\\Downloads\\apache-maven-3.9.16-bin\\apache-maven-3.9.16\\bin;%PATH%" && mvn clean package'
            }
        
        }

        stage('Build Docker Image') {
            steps {
                bat 'docker build -t faculty-cicd:latest .'
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                bat 'kubectl apply -f deployment.yaml'
                bat 'kubectl apply -f service.yaml'
                bat 'kubectl rollout status deployment/faculty-cicd --timeout=120s'
            }
        }
    }

    post {
        success {
            echo 'Faculty CI/CD pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed. Check the Jenkins console output.'
        }
    }
}