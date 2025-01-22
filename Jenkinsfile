pipeline {
    agent any

    environment {
        MAVEN_HOME = '/usr/share/maven'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/tu_usuario/tu_proyecto.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn verify'
            }
        }
    }
}
