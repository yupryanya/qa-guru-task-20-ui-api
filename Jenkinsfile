pipeline {
    agent any

    tools {
        gradle "Gradle 7.4.1"
    }

    parameters {
        choice(name: 'TASK',
                choices: ['test', 'regression', 'smoke'])
        choice(name: 'API_URL',
                choices: ['https://demoqa.com'])
        choice(name: 'WEB_URL',
                choices: ['https://demoqa.com'])

    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/yupryanya/qa-guru-task-20-ui-api'
            }
        }
        stage('Test') {
            steps {
                withAllureUpload(name: '${JOB_NAME} - #${BUILD_NUMBER}', projectId: '3838', results: [[path: 'build/allure-results']], serverId: 'allure-server', tags: 'nightly') {
                sh 'gradle clean ${TASK} -DewbUrl=$WEB_URL -DisRemote=true'
                }
            }
        }
    }
    post {
        always {
            allure includeProperties: false, jdk: '', results: [[path: 'build/allure-results']]
        }
    }
}