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
void jsonparser(){
    def projects = readJSON file: "${env.WORKSPACE}//Tsunami-output.json"
    def summ=projects.scanFindings[0].vulnerability.title
    def desc=projects.scanFindings[0].vulnerability.description
    issue(summ,desc)
}

void issue(summ,desc){
    def res=jiraJqlSearch failOnError: false, jql: "project=\'TEST\' AND summary~'${summ}' AND description ~ '${desc}' ", site: 'jiraconn'
    if(res.code !=200){
        create_newjira_issue(summ,desc)
    }
}
 
return this
