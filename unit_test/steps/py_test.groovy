void call () {
    install_requirements()
}

def install_requirements() {
    script{
        sh """
        pip3 install -r requirements.txt
        pytest .
        """
    }
}