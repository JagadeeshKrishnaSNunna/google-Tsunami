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
            jsonparser()
        }
    
    }
    
}

void jsonparser(){
    def projects = readJSON file: "${env.WORKSPACE}//tsunami-output.json"
    def summ=projects.scanFindings[0].vulnerability.title
    def desc=projects.scanFindings[0].vulnerability.description
    create_newjira_issue(summ,desc)
}


void create_newjira_issue(summ,desc) {
    node {
      stage('JIRA') {
        def NewJiraIssue = [fields: [project: [key: 'TEST'],
            summary: summ,
            description: desc,
            issuetype: [name:'Bug']]]
        response = jiraNewIssue issue: NewJiraIssue, site: 'jiraconn'
        echo response.successful.toString()
        echo response.data.toString()
    }
  }
}

