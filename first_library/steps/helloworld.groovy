void call() {
    println "hello world"
    inject_ssh()
}




def inject_ssh(){
     
        println "inject authentication for composer to build image"
        withCredentials([sshUserPrivateKey(credentialsId: 'Bitbucket', keyFileVariable: 'SSH_KEY',passphraseVariable: 'SSH_CONTENT')]) {
            script {
                // Write the SSH key to the file
                //writeFile file: 'ssh.txt', text: SSH_CONTENT
                
                sh """
                echo $SSH_KEY >> ssh.txt
                echo "" >> ssh.txt
                """
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


