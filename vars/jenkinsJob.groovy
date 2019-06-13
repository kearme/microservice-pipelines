def call(){
    node {
        stage('Checkout') {
            checkout scm
        }

        // Execute different stages depending on the job
        if(env.JOB_NAME.contains("deploy")){
            packageArtifact()
        } else if(env.JOB_NAME.contains("test")) {
            buildAndTest()
        }
    }
}

def packageArtifact(){
    stage("Package artifact") {
        try {
            sh "mvn package"
        }
        catch(Exception e) {
            echo "Error"
        }        
    }
}

def buildAndTest(){
    stage("Backend tests"){
        try {
            sh "mvn test"
        }
        catch(Exception e) {
            echo "Error"
        }
    }
}
