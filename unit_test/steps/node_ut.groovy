void call () {
    install_dependencies()
    node_unit_test()
}


def install_dependencies() {
    dir("./${BuildContext}/") { 
        println "***************************install packages and dependencies*****************************"
        sh  " npm  install "
    }
}


def node_unit_test() {
    dir("./${BuildContext}/") {    
        println "*************************** Run Unit Test *****************************"
        sh " npm run test "
    }
}