pipeline {
agent any

tools {
    maven 'Maven3'
}

stages {

    stage('Checkout') {
        steps {
            checkout scm
        }
    }

    
    stage('Build') {
        steps {
            bat 'mvn -B -DskipTests clean package'
        }
    }

    
    stage('Quality Checks') {
        parallel {

            stage('Unit Tests') {
                steps {
                    bat 'mvn test'
                }
            }

            stage('Verify') {
                steps {
                    bat 'mvn -q verify'
                }
            }
        }
    }

    stage('Package') {
        steps {
            bat 'mvn package'
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

    stage('Deploy') {
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
        echo 'Build Successful '
    }
    failure {
        echo 'Build Failed '
    }
}


}
