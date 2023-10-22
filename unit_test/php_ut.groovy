def installdependencies {
    println "install dependencies... "
    installpack = sh (
        returnStdout: true,
        script: """
        apt update && apt upgrade -y && apt install composer -y && composer install
        """
    )
}

def runUnitTestsAndSetGitHubStatus() {
    catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
        sh 'php artisan test'
    }
    script {
        def currentBuildResult = currentBuild.resultIsBetterOrEqualTo('SUCCESS') ? 'SUCCESS' : 'FAILURE'
        updateGitHubStatus(currentBuildResult)
    }
}

def updateGitHubStatus(buildStatus) {
    def github = getGitHub()
    def pr = github.getPullRequest(env.CHANGE_ID)
    pr.createReview('Automated Jenkins Build', buildStatus, 'Automated Jenkins build result')
}
