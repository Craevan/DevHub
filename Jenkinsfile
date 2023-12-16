node {
    def WORKSPACE = "/var/lib/jenkins/workspace/DevHub"
    def dockerImageTag = "devhub${env.BUILD_NUMBER}"

    try {
//          notifyBuild('STARTED')
         stage('Clone Repo') {
            // for display purposes
            // Get some code from a GitHub repository
            git url: 'https://github.com/Craevan/DevHub.git',
                branch: 'master'
         }
         stage('Build docker') {
                 dockerImage = docker.build("devhub:${env.BUILD_NUMBER}")
         }

         stage('Deploy docker') {
                  echo "Docker Image Tag Name: ${dockerImageTag}"
                  sh "docker stop devhub || true && docker rm devhub || true"
                  sh "docker run --name devhub -d -p 8081:8081 devhub:${env.BUILD_NUMBER} --link devhubdb"
         }
    } catch(e) {
//         currentBuild.result = "FAILED"
        throw e
    } finally {
//         notifyBuild(currentBuild.result)
    }
}

def notifyBuild(String buildStatus = 'STARTED') {

// build status of null means successful
  buildStatus =  buildStatus ?: 'SUCCESSFUL'
  // Default values
  def colorName = 'RED'
  def colorCode = '#FF0000'
  def now = new Date()
  // message
  def subject = "${buildStatus}, Job: ${env.JOB_NAME} FRONTEND - Deployment Sequence: [${env.BUILD_NUMBER}] "
  def summary = "${subject} - Check On: (${env.BUILD_URL}) - Time: ${now}"
  def subject_email = "DevHub Deployment"
  def details = """<p>${buildStatus} JOB </p>
    <p>Job: ${env.JOB_NAME} - Deployment Sequence: [${env.BUILD_NUMBER}] - Time: ${now}</p>
    <p>Check console output at "<a href="${env.BUILD_URL}">${env.JOB_NAME}</a>"</p>"""


  // Email notification
    emailext (
         to: "a.siverskii@gmail.com",
         subject: subject_email,
         body: details,
         recipientProviders: [[$class: 'DevelopersRecipientProvider']]
       )
}
