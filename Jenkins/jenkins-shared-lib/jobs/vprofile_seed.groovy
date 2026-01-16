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
    
    // Define where the source code is
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/saduasar/vprofile-project.git')
                        branch('master')
                    }
                }
            }
            // Point to the Jenkinsfile inside the app repo (see Layer 3)
            scriptPath('Jenkinsfile') 
        }
    }
    
    // Configure Triggers (e.g., Run every night)
    triggers {
        cron('H H * * *')
    }
}
