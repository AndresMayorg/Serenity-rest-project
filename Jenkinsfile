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
                        sh 'mvn serenity:aggregate'
                    }
        }
        stage('Publish Report') {
                    steps {
                        publishHTML(target: [
                            allowMissing: false,
                            alwaysLinkToLastBuild: true,
                            keepAll: true,
                            reportDir: 'target/site/serenity',
                            reportFiles: 'index.html',
                            reportName: 'Serenity BDD Report'
                        ])
                    }
                }
    }
}
