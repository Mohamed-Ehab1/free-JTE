void call() {
    println "starting unit test for php"
    installdependencies()
    runUnitTestsAndSetGitHubStatus()
    
}

def installdependencies() {
        echo "install dependencies... "
        sh """
            composer install
        """
    }

def runUnitTestsAndSetGitHubStatus() {
        echo "run unit test"
        catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
            sh 'php artisan test'
        }
    }