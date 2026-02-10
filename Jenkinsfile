pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main',
                url: 'https://github.com/AtulShinde1996/Jenkins.git',
                credentialsId: 'github-creds'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
    }

    post {
        success {
            echo 'Build Successful 🎉'
        }
        failure {
            echo 'Build Failed ❌'
        }
    }
}
