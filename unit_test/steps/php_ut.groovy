void call(){
   
    def installdependencies() {
        println "install dependencies... "
          sh (
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
    }


}