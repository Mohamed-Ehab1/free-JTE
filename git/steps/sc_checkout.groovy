import hudson.AbortException
void call(){
    checkout()
}

def checkout() {
     deleteDir()
    try {
        checkout scm
    } catch(AbortException ex){
        error "scm not presented, skipping source code checkout"
        stageFailure = false
        throw ex
    }
}