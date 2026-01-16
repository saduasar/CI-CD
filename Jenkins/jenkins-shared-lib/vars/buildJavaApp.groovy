def call(Map config = [:]) {
    pipeline {
        agent any 
        
        // This 'tools' section is critical for Java/Maven
        tools {
            maven 'Maven-3.9.6' // Name must match Global Tool Config in Jenkins
            jdk 'JDK-17'        // Name must match Global Tool Config in Jenkins
        }

        stages {
            stage('Checkout') {
                steps {
                    checkout scm
                }
            }
            
            stage('Build & Test') {
                steps {
                    echo "Building ${config.appName}..."
                    // The standard command for vprofile
                    sh 'mvn clean install -DskipTests' 
                }
            }

            stage('Unit Tests') {
                steps {
                    sh 'mvn test'
                }
            }

            stage('Code Analysis') {
                steps {
                    echo "Running SonarQube analysis..."
                    // In a real enterprise, you'd add: withSonarQubeEnv('Sonar') { ... }
                }
            }
        }
    }
}
