void call(){

    println "checking out code ..."
    
    deleteDir()
    try {
        checkout scm
    } catch(AbortException ex){
        error "scm not presented, skipping source code checkout"
        stageFailure = false
        throw ex
    }
}