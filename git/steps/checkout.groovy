import hudson.AbortException
void call(){
    checkout()
}

def checkout() {
    script {
        checkout scmGit(branches: [[name: '*/main']], extensions: [cloneOption(noTags: false, reference: '', shallow: false)], userRemoteConfigs: [[url: 'https://github.com/Mohamed-Ehab1/newlaraveluntitest']])
    }
}