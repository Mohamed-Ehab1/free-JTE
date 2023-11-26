void call () {
    install_requirements()
}

def install_requirements() {
    script{
        sh """
        pytest .
        """
    }
}