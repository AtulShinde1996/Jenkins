pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/AtulShinde1996/Jenkins.git'
            }
        }

        stage('Build - Maven') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('Docker Build') {
            steps {
                bat 'docker build -t userservice:latest .'
            }
        }

        stage('Stop Old Container') {
            steps {
                bat 'docker stop userservicecontainer || exit 0'
                bat 'docker rm userservicecontainer || exit 0'
            }
        }

        stage('Manual Approval to Deploy') {
            steps {
                script {
                    timeout(time: 2, unit: 'MINUTES') {
                        input message: "Deploy to UAT environment?", ok: "Approve"
                    }
                }
            }
        }

        stage('Deploy Container') {
            when {
                branch 'main'
            }
            steps {
                bat 'docker run -d -p 9090:9090 --name userservicecontainer userservice:latest'
            }
        }
    }

    post {
        success {
            echo 'Application deployed successfully!'
        }
        failure {
            echo 'Build failed! Check console output.'
        }
    }
}
