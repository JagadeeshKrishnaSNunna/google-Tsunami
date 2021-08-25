pipeline {
    agent any
    parameters{
        string(name:'IP',defaultValue:'')
    }
    stages {
        stage('Build') {
            steps {
                sh "bash runScript.sh params.IP"
            }
        }
        stage('test') {
            steps {
                sh '! grep "vulnerability" tsunami-output.json'
            }
        }
        stage('Final') {
            steps {
                echo 'Buld successful...!'
            }
        }
    }
}


