void call() {
    println "hello world"
    
}


dir ("/home/jenkins") {

def inject_ssh(){
    println "inject authentication for composer to build image"
    withCredentials([sshUserPrivateKey(credentialsId: 'Bitbucket', keyFileVariable: 'SSH_KEY')]) {
        script {
            // Write the SSH key to the file
            writeFile file: 'ssh.txt', text: SSH_KEY.replaceAll("\\\\n", "\n")
            sh 'echo "" >> ssh.txt'
        }    
    }
    
}

def install_open_shh() {
    script {
        bash """
        apt update
        apt install openssh -y
        """
    }
}

def authenticate_bitbucket(){
   script{ sh """
    mkdir -p ~/.ssh
    mv ssh.txt ~/.ssh/id_ed25519
    chmod 400 ~/.ssh/id_ed25519
    ssh -o StrictHostKeyChecking=no -T git@bitbucket.org
    """
   }

}


}