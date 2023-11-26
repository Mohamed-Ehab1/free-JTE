void call() {
    println "hello world"
    inject_ssh()
}




def inject_ssh(){
     
        println "inject authentication for composer to build image"
        withCredentials([sshUserPrivateKey(credentialsId: 'Bitbucket', keyFileVariable: 'SSH_KEY')]) {
            script {

                def tempKeyFile = "$SSH_KEY"

                        // Read SSH key content
                        def sshKeyContent = readFile(file: tempKeyFile).trim()

                        // Write the SSH key content to a new file
                        writeFile file: 'ssh.txt', text: sshKeyContent
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
         cp ssh.txt ~/.ssh/id_ed25519
         chmod 400 ~/.ssh/id_ed25519
         ssh -o StrictHostKeyChecking=no -T git@bitbucket.org
         """
        }

}


