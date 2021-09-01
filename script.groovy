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
//     def projects = readJSON file: "${env.WORKSPACE}//tsunami-output.json"
//     def summ=projects.scanFindings[0].vulnerability.title
//     def desc=projects.scanFindings[0].vulnerability.description
    def projects = readJSON file: "${env.WORKSPACE}//tsunami-output.json"
    def map=[:]
    def temp=[:]
    for(v in projects.scanFindings){
        temp['title']=v.vulnerability.title.replaceAll("'","")
        temp[v.vulnerability.description]=v.vulnerability.description.replaceAll("'","")
        map[v.vulnerability.mainId.value.replaceAll("'","")]=temp
        temp=[:]
    }
    map.each{entry -> 
    def summ=entry
    def nmap=entry
    def des=''
    nmap.each{ent ->
    des=des+ent.value+"\n"
    }
        print "------------------------------"
        print summ
        print "------------------------------"
        print desc
        //issue(summ,des)
    }
}

void issue(summ,desc){
    def res=jiraJqlSearch failOnError: false, jql: "project=\'TEST\' AND summary~\'${summ}\' AND description ~ \'${desc}\' ", site: 'jiraconn'
    print '-------------------------------------------------------------------------------'
    print res
    print '---------------------------------------------'
    print res.data.issues.size()
    if(res.data.issues.size()==0){
        create_newjira_issue(summ,desc)
    }
}
 
return this
