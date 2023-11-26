void call () {
   try { 
    install_requirements()                
    }
catch (Exception e) {
        echo "An exception occurred: ${e.getMessage()}"

        }
finally {
        echo "Test is finished completely."
        }
}

def install_requirements() {
    script{
        sh """
        pytest .
        """
    }
}

