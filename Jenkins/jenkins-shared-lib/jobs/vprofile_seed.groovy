// Define the folder structure first
folder('Projects') {
    description('Folder for all application projects')
}

folder('Projects/vProfile') {
    description('The vProfile application components')
}

// Create the Pipeline Job
pipelineJob('Projects/vProfile/vProfile-CI-Pipeline') {
    description('Continuous Integration pipeline for vProfile App')
    
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/saduasar/vprofile-project.git')
                        // ADDED: references the Jenkins Credential ID "git"
                        credentials('git') 
                    }
                    // MOVED: 'branch' is usually defined outside the 'remote' block in standard DSL
                    branches('master')
                }
            }
            scriptPath('Jenkinsfile') 
        }
    }
    
    triggers {
        cron('H H * * *')
    }
}
