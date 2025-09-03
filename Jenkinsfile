pipeline {
    agent any
    
    tools {
        maven 'Maven 3.9.9'
        jdk 'Java 17'
    }
    
    environment {
        // Environment variables
        PROJECT_NAME = 'BDD Testing Framework'
        SLACK_CHANNEL = '#test-automation'
        
        // Test configuration
        PARALLEL_THREADS = '4'
        TEST_TIMEOUT = '30'
        
        // Docker configuration
        DOCKER_COMPOSE_FILE = 'docker-compose.yml'
        
        // Report paths
        CUCUMBER_REPORTS = '**/cucumber-reports/*.json'
        ALLURE_RESULTS = '**/allure-results'
        JACOCO_REPORTS = '**/target/site/jacoco'
    }
    
    options {
        // Pipeline options
        timeout(time: 2, unit: 'HOURS')
        timestamps()
        ansiColor('xterm')
        
        // Build options
        buildDiscarder(logRotator(numToKeepStr: '10'))
        disableConcurrentBuilds()
    }
    
    triggers {
        // SCM polling
        pollSCM('H/15 * * * *')
    }
    
    stages {
        stage('Checkout') {
            steps {
                script {
                    // Clean workspace
                    cleanWs()
                    
                    // Checkout code
                    checkout scm
                    
                    // Set git commit info
                    env.GIT_COMMIT = sh(script: 'git rev-parse --short HEAD', returnStdout: true).trim()
                    env.GIT_BRANCH = sh(script: 'git rev-parse --abbrev-ref HEAD', returnStdout: true).trim()
                    
                    echo "Building ${env.GIT_BRANCH} branch, commit: ${env.GIT_COMMIT}"
                }
            }
        }
        
        stage('Setup Environment') {
            steps {
                script {
                    // Start Docker services
                    sh 'docker-compose -f ${DOCKER_COMPOSE_FILE} up -d selenium-hub chrome firefox edge'
                    
                    // Wait for services to be ready
                    sh 'sleep 30'
                    
                    // Verify services
                    sh '''
                        echo "Checking Selenium Grid..."
                        curl -f http://localhost:4444/wd/hub/status || exit 1
                        
                        echo "Checking Appium..."
                        curl -f http://localhost:4723/status || exit 1
                    '''
                }
            }
        }
        
        stage('Build') {
            steps {
                script {
                    // Build all modules
                    sh 'mvn clean compile -DskipTests=true'
                    
                    // Run static analysis
                    sh 'mvn spotbugs:check'
                    sh 'mvn checkstyle:check'
                }
            }
            post {
                always {
                    // Archive build artifacts
                    archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
                }
            }
        }
        
        stage('Unit Tests') {
            steps {
                script {
                    // Run unit tests with coverage
                    sh '''
                        mvn test \
                            -Dparallel.threads=${PARALLEL_THREADS} \
                            -Dtest.timeout=${TEST_TIMEOUT} \
                            -Djacoco.skip=false
                    '''
                }
            }
            post {
                always {
                    // Publish test results
                    publishTestResults testResultsPattern: '**/surefire-reports/*.xml'
                    
                    // Publish coverage reports
                    publishCoverage adapters: [
                        jacocoAdapter('**/target/site/jacoco/jacoco.xml')
                    ], sourceFileResolver: sourceFiles('STORE_LAST_BUILD')
                }
            }
        }
        
        stage('Integration Tests') {
            parallel {
                stage('API Tests') {
                    steps {
                        script {
                            dir('api_testing') {
                                sh '''
                                    mvn test \
                                        -Dparallel.threads=${PARALLEL_THREADS} \
                                        -Dtest.timeout=${TEST_TIMEOUT} \
                                        -Dcucumber.filter.tags="@api"
                                '''
                            }
                        }
                    }
                }
                
                stage('Web Tests') {
                    steps {
                        script {
                            dir('web_testing') {
                                sh '''
                                    mvn test \
                                        -Dparallel.threads=${PARALLEL_THREADS} \
                                        -Dtest.timeout=${TEST_TIMEOUT} \
                                        -Dcucumber.filter.tags="@web"
                                '''
                            }
                        }
                    }
                }
                
                stage('Mobile Tests') {
                    steps {
                        script {
                            dir('mobile_testing') {
                                sh '''
                                    mvn test \
                                        -Dparallel.threads=${PARALLEL_THREADS} \
                                        -Dtest.timeout=${TEST_TIMEOUT} \
                                        -Dcucumber.filter.tags="@mobile"
                                '''
                            }
                        }
                    }
                }
            }
            post {
                always {
                    // Publish Cucumber reports
                    publishCucumberReports(
                        jsonReportDirectory: '**/cucumber-reports',
                        htmlReportDirectory: '**/cucumber-html-reports',
                        fileIncludePattern: '*.json',
                        fileExcludePattern: '',
                        classifications: [
                            [key: 'Browser', value: 'Chrome'],
                            [key: 'Environment', value: 'CI']
                        ]
                    )
                }
            }
        }
        
        stage('Performance Tests') {
            when {
                branch 'main'
            }
            steps {
                script {
                    // Run performance tests
                    sh '''
                        mvn test \
                            -Dtest=PerformanceTest \
                            -Dparallel.threads=1 \
                            -Dtest.timeout=300
                    '''
                }
            }
        }
        
        stage('Security Tests') {
            when {
                branch 'main'
            }
            steps {
                script {
                    // Run security scans
                    sh 'mvn dependency:check'
                    sh 'mvn org.owasp:dependency-check-maven:check'
                }
            }
        }
        
        stage('Generate Reports') {
            steps {
                script {
                    // Generate Allure reports
                    sh 'mvn allure:report'
                    
                    // Generate JaCoCo reports
                    sh 'mvn jacoco:report'
                    
                    // Generate custom reports
                    sh '''
                        echo "Generating test summary..."
                        echo "Total Tests: $(find . -name "*.xml" -path "*/surefire-reports/*" | xargs grep -h "testsuite" | wc -l)" > test-summary.txt
                        echo "Passed: $(find . -name "*.xml" -path "*/surefire-reports/*" | xargs grep -h "passed" | wc -l)" >> test-summary.txt
                        echo "Failed: $(find . -name "*.xml" -path "*/surefire-reports/*" | xargs grep -h "failed" | wc -l)" >> test-summary.txt
                    '''
                }
            }
            post {
                always {
                    // Publish reports
                    publishHTML([
                        allowMissing: false,
                        alwaysLinkToLastBuild: true,
                        keepAll: true,
                        reportDir: 'target/allure-report',
                        reportFiles: 'index.html',
                        reportName: 'Allure Report'
                    ])
                    
                    publishHTML([
                        allowMissing: false,
                        alwaysLinkToLastBuild: true,
                        keepAll: true,
                        reportDir: 'target/site/jacoco',
                        reportFiles: 'index.html',
                        reportName: 'JaCoCo Coverage Report'
                    ])
                    
                    // Archive test summary
                    archiveArtifacts artifacts: 'test-summary.txt'
                }
            }
        }
        
        stage('Deploy') {
            when {
                branch 'main'
                expression { currentBuild.resultIsBetterOrEqualTo('SUCCESS') }
            }
            steps {
                script {
                    // Deploy to test environment
                    sh 'docker-compose -f ${DOCKER_COMPOSE_FILE} up -d test-results'
                    
                    // Update deployment status
                    env.DEPLOYMENT_URL = "http://localhost:8081"
                    env.DEPLOYMENT_STATUS = "SUCCESS"
                }
            }
        }
    }
    
    post {
        always {
            script {
                // Cleanup Docker services
                sh 'docker-compose -f ${DOCKER_COMPOSE_FILE} down'
                
                // Clean workspace
                cleanWs()
            }
        }
        
        success {
            script {
                // Send success notification
                slackSend(
                    channel: env.SLACK_CHANNEL,
                    color: 'good',
                    message: "✅ ${env.PROJECT_NAME} build #${env.BUILD_NUMBER} succeeded!\n" +
                            "Branch: ${env.GIT_BRANCH}\n" +
                            "Commit: ${env.GIT_COMMIT}\n" +
                            "Build URL: ${env.BUILD_URL}"
                )
            }
        }
        
        failure {
            script {
                // Send failure notification
                slackSend(
                    channel: env.SLACK_CHANNEL,
                    color: 'danger',
                    message: "❌ ${env.PROJECT_NAME} build #${env.BUILD_NUMBER} failed!\n" +
                            "Branch: ${env.GIT_BRANCH}\n" +
                            "Commit: ${env.GIT_COMMIT}\n" +
                            "Build URL: ${env.BUILD_URL}\n" +
                            "Console: ${env.BUILD_URL}console"
                )
            }
        }
        
        unstable {
            script {
                // Send unstable notification
                slackSend(
                    channel: env.SLACK_CHANNEL,
                    color: 'warning',
                    message: "⚠️ ${env.PROJECT_NAME} build #${env.BUILD_NUMBER} is unstable!\n" +
                            "Branch: ${env.GIT_BRANCH}\n" +
                            "Commit: ${env.GIT_COMMIT}\n" +
                            "Build URL: ${env.BUILD_URL}"
                )
            }
        }
    }
} 