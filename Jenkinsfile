pipeline {
    agent any

    tools {
        gradle "Gradle 7.4.1"
    }

    parameters {
        choice(name: 'TASK', choices: ['test', 'regression', 'smoke'], description: 'Select the task to run')
        choice(name: 'BROWSER', choices: ['chrome', 'firefox'], description: 'Select the browser for testing')
        choice(name: 'API_URL', choices: ['https://demoqa.com'], description: 'Enter the API URL for testing')
        choice(name: 'WEB_URL', choices: ['https://demoqa.com'], description: 'Enter the web URL for testing')
        string(name: 'COMMENT', defaultValue: '', description: 'Enter a comment for the report')
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
                    sh 'gradle clean ${TASK} -DwebUrl=${WEB_URL} -DisRemote=true -DapiUrl=${API_URL} -Dbrowser=${BROWSER}'
                }
            }
        }
        stage('Prepare Post-Build') {
            steps {
                script {
                    def jarFile = '../allure-notifications-4.3.0.jar'
                    if (!fileExists(file: jarFile)) {
                        sh 'wget https://github.com/qa-guru/allure-notifications/releases/download/4.3.0/allure-notifications-4.3.0.jar -P ..'
                    }
                }
            }
        }
    }

    post {
        always {
            allure includeProperties: false, jdk: '', results: [[path: 'build/allure-results']]
        }
        success {
            script {
                 writeFile file: 'notifications/config.json', text: """
                                    {
                                      "base": {
                                        "logo": "",
                                        "project": "${JOB_NAME}",
                                        "environment": "${WEB_URL}",
                                        "comment": "${COMMENT}",
                                        "reportLink": "${BUILD_URL}",
                                        "language": "en",
                                        "allureFolder": "allure-report",
                                        "enableChart": true
                                      },
                                      "telegram": {
                                        "token": "6527213503:AAElKOjpWVtwqExymf5sxMActQIW2VwZNEQ",
                                        "chat": "-1001755259300",
                                        "replyTo": ""
                                      }
                                    }
                                    """
                sh 'java -DconfigFile=notifications/config.json -jar ../allure-notifications-4.3.0.jar'
            }
        }
    }
}
