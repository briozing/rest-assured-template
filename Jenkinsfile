node {
    try {

        stage("Initialize") {
            compileCode()
        }

        stage("Checkout") {
            checkoutRepository()
        }

        stage('Build and Test') {
            runAutomationTest()
        }



    } catch (err) {
        echo "$err"
        currentBuild.result = 'FAILURE'
        throw err
    } finally {
        if (currentBuild.result == 'FAILURE' || currentBuild.result == 'UNSTABLE') {
            currentBuild.result = 'FAILURE'
        } else {
            currentBuild.result = 'SUCCESS'
        }
    }
}

def checkoutRepository() {

    repoUrl = "https://github.com/briozing/rest-assured-template.git"
    targetDir = "rest-assured-template"
    branchName = "feature/jenkins-pipeline"

    checkout([$class                           : 'GitSCM',
              branches                         : [[name: branchName]],
              doGenerateSubmoduleConfigurations: false,
              extensions                       : [[$class           : 'RelativeTargetDirectory',
                                                   relativeTargetDir: targetDir]],
              submoduleCfg                     : [],
              userRemoteConfigs                : [[credentialsId: '	MyGitHubCredentials', url: repoUrl]]
    ])

}

def compileCode() {
    dir('ngp-ui-automation') {
        withMaven() {
            bat "mvn clean compile"
        }
    }

def runAutomationTest() {
    dir('ngp-ui-automation') {
        withMaven() {
            bat "mvn clean test
        }
    }
}
