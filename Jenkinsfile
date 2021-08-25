pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh "bash runScript.sh 192.168.56.125"
            }
        }
        stage('test') {
            steps {
                sh '$I=cat tsunami-output.json|grep -e "vulnerability"'
                echo "${I}"
            }
        }
    }
}


