node {
    def WORKSPACE = "/var/lib/jenkins/workspace/DevHub"
    def dockerImageTag = "devhub${env.BUILD_NUMBER}"

    try {
        stage('Clone Repo') {
            git url: 'https://github.com/Craevan/DevHub.git',
                    branch: 'master'
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Build docker') {
            dockerImage = docker.build("devhub:${env.BUILD_NUMBER}")
        }

        stage('Deploy docker') {
            sh "docker stop devhub || true && docker rm devhub || true"
            sh "docker run --name devhub -d -p 8081:8081 devhub:${env.BUILD_NUMBER}"
        }
    } catch (e) {
        notifyFailed()
        throw e
    } finally {
        notifySuccessful()
    }
}

def notifySuccessful() {

    emailext (
            to: "a.siverskii@gmail.com",
            subject: "SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
            body: """<p>SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
        <p>Check console output at &QUOT;<a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>&QUOT;</p>""",
            recipientProviders: [[$class: 'DevelopersRecipientProvider']]
    )
}

def notifyFailed() {
    emailext (
            to: "a.siverskii@gmail.com",
            subject: "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
            body: """<p>FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
        <p>Check console output at &QUOT;<a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>&QUOT;</p>""",
            recipientProviders: [[$class: 'DevelopersRecipientProvider']]
    )
}
