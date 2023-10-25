void call(){
   
    def installdependencies() {
        println "install dependencies... "
        installpack = sh (
            returnStdout: true,
            script: """
            apt update && apt upgrade -y && apt install composer -y && composer install
            """
    )
    }
   
    def runUnitTestsAndSetGitHubStatus() {
        println "run unit test"
        catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
            sh 'php artisan test'
        }
        script {
            def currentBuildResult = currentBuild.resultIsBetterOrEqualTo('SUCCESS') ? 'SUCCESS' : 'FAILURE'
            updateGitHubStatus(currentBuildResult)
        }
    }


}