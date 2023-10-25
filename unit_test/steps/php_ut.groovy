void call() {
    def installdependencies() {
        echo "install dependencies... "
        sh """
            apt update
            apt upgrade -y
            apt install composer -y
            composer install
        """
    }

    def runUnitTestsAndSetGitHubStatus() {
        echo "run unit test"
        catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
            sh 'php artisan test'
        }
    }
}
