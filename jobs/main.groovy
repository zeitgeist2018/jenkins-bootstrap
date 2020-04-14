//def GIT_CREDENTIALS = 'gitlab'
def BASE_FOLDER = 'infrastructure-build'
def NUM_TO_KEEP = 2

folder(BASE_FOLDER) {}
println env
JOBS = [
        "test": [
                "jenkins-dummy": "${env['GIT_URL']}/infrastructure-build/jenkins-dummy.git"
        ]
]

JOBS.each{ projectKey, projectJobs ->
    folder("$BASE_FOLDER/$projectKey") {}

    projectJobs.each{ jobKey, jobRepository ->
        multibranchPipelineJob("$BASE_FOLDER/$projectKey/$jobKey") {
            branchSources {
                git {
                    id("$BASE_FOLDER.$projectKey.$jobKey")
                    remote(jobRepository)
//                    credentialsId GIT_CREDENTIALS
                    includes('master feature/*')
                }
            }
            orphanedItemStrategy {
                discardOldItems {
                    daysToKeep(NUM_TO_KEEP)
                }
            }
        }
    }
}

setBuildResult('SUCCESS')
