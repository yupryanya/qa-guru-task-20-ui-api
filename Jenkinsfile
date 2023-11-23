pipeline {
    agent any

    tools {
        gradle "Gradle 7.4.1"
    }

    parameters {
        choice(name: 'TASK',
                        choices: ['test', 'regression', 'smoke'],
                        description: 'Select the task to run')
        choice(name: 'BROWSER',
                        choices: ['chrome', 'firefox'],
                        description: 'Select the browser for testing')
        choice(name: 'BROWSER_VERSION',
                        choices: ['100', '99', '98'],
                        description: 'Select the browser version')
        choice(name: 'RESOLUTION',
                        choices: ['1920x1080', '1280x720'],
                        description: 'Select the screen resolution')
        choice(name: 'API_URL',
                        choices: ['https://demoqa.com'],
                        description: 'Enter the API URL')
        choice(name: 'WEB_URL',
                        choices: ['https://demoqa.com'],
                        description: 'Enter the web URL')
        string(name: 'COMMENT',
                        defaultValue: '',
                        description: 'Enter a comment for the report')
        credentials(name: 'TELEGRAM_TOKEN',
                        description: 'Telegram bot token for sending notifications in telegram chat',
                        defaultValue: '021-azovceva-telegram-token',
                        credentialType: "jenkins_secret_text_credentials",
                        required: true)
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
                    sh 'gradle clean ${TASK} -DisRemote=true -Dbrowser=${BROWSER} -DbrowserVersion=${BROWSER_VERSION} -DbrowserSize=${RESOLUTION} -DwebUrl=${WEB_URL} -DapiUrl=${API_URL}'
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

                                        "chat": "-1001755259300",
                                        "replyTo": ""
                                      }
                                    }
                                    """
                withCredentials([string(credentialsId: 'TELEGRAM_TOKEN', variable: 'TELEGRAM_TOKEN')]) {
                   sh 'java -DconfigFile=notifications/config.json -Dtelegram.token=${TELEGRAM_TOKEN} -jar ../allure-notifications-4.3.0.jar'
                }
            }
        }
    }
}
