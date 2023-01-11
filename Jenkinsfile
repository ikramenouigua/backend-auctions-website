node {
    def mvnHome = tool 'M3'
    def dockerImage
    def dockerImageTag = "devopsexample${env.BUILD_NUMBER}"
    stages {
    stage('Cleanup Workspace') {
                steps {
                    cleanWs()
                    sh """
                    echo "Cleaned Up Workspace for ${APP_NAME}"
                    """
                }
            }
    stage('Checkout') {
          steps {
            script {
               // The below will clone your repo and will be checked out to master branch by default.
               git credentialsId: 'houdajh', url: 'https://github.com/houdajh/Moroccan-estate-auction_Back_End.git'
               // Do a ls -lart to view all the files are cloned. It will be clonned. This is just for you to be sure about it.
               sh "ls -lart ./*"
               // List all branches in your repo.
               sh "git branch -a"
               // Checkout to a specific branch in your repo.
               sh "git checkout develop"
              }
           }
        }

    stage('Clone Repo') {
      git 'https://github.com/houdajh/Moroccan-estate-auction_Back_End.git'
    }

    stage('Build Project') {
      sh "'${mvnHome}/bin/mvn' -B -DskipTests clean package"
    }

    stage('Initialize Docker'){
	  def dockerHome = tool 'MyDocker'
	  env.PATH = "${dockerHome}/bin:${env.PATH}"
    }

    stage('Build Docker Image') {
      sh "docker -H tcp://192.168.8.100:2375 build -t devopsexample:${env.BUILD_NUMBER} ."
    }

    stage('Deploy Docker Image'){
      	echo "Docker Image Tag Name: ${dockerImageTag}"
	sh "docker -H tcp://192.168.8.100:2375 run --name devopsexample -d -p 2222:2222 devopsexample:${env.BUILD_NUMBER}"
    }
    }
}