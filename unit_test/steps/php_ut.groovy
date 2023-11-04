void call() {
    println "starting unit test for php"
    installdependencies()
    runUnitTestsAndSetGitHubStatus()
    
}

def installdependencies() {
        echo "install dependencies... "
        sh """
            apt update
            apt install lsb-release ca-certificates apt-transport-https software-properties-common -y
            add-apt-repository --remove ppa:ondrej/php
            add-apt-repository ppa:ondrej/php      
            apt update
            apt install php -y php8.0 php8.0-common
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