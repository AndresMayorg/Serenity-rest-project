pipeline {
    agent any

    environment {
        MAVEN_HOME = '/usr/share/maven'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/AndresMayorg/Serenity-rest-project.git'
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

        stage('Gerenate Report') {
                    steps {
                        sh 'mvn Serenity:aggregate'
                    }
                }
    }
}
