pipeline {
    agent any
    parameters{
        string(name:'IP',defaultValue:'')
    }
    stages {
        stage('Build') {
            steps {
                sh "bash runScript.sh ${IP}"
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
    post{
        failure{
            script{
                def sc=load "script.groovy"
                sc.jsonparser()
            }
        }
    
    }
    
}


