node {
    def WORKSPACE = "/var/lib/jenkins/workspace/DevHub"
    def dockerImageTag = "devhub${env.BUILD_NUMBER}"

    try {
        stage('Clone Repo') {
            git url: 'https://github.com/Craevan/DevHub.git',
                    branch: 'master'
        }

        stage('Test') {
            sh 'mvn test'
        }

        stage('Build docker') {
            dockerImage = docker.build("devhub:${env.BUILD_NUMBER}")
        }

        stage('Deploy docker') {
            sh "docker stop devhub || true && docker rm devhub || true"
            sh "docker run -d --name devhub -p 8081:8081 devhub:${env.BUILD_NUMBER}"
        }

    } catch (e) {
        currentBuild.result = "FAILED"
        throw e
    } finally {
        notifyBuild(currentBuild.result)
    }
}

def notifyBuild(String buildStatus) {
    buildStatus = buildStatus ?: 'SUCCESS'

    def subject = "${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'"
    def details = """<p>${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'</p>
    <p>Check console output at <a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a></p>"""

    emailext(
            subject: subject,
            body: details,
            recipientProviders: [[$class: 'DevelopersRecipientProvider']]
    )
}
