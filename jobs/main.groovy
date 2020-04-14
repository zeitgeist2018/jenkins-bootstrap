//import static constants.Constants.GIT_CREDENTIALS

def GIT_CREDENTIALS = 'gitlab'
def BASE_FOLDER = 'test'
def NUM_TO_KEEP = 2

folder(BASE_FOLDER) {}

JOBS = [
        "common": [
                "jenkins-dummy": 'http://192.168.1.128:8082/cristianlm/jenkins-dummy.git'
        ]
]

println JOBS
/*JOBS.each{ projectKey, projectJobs ->
    folder("$BASE_FOLDER/$projectKey") {}

    projectJobs.each{ jobKey, jobRepository ->
        multibranchPipelineJob("$BASE_FOLDER/$projectKey/$jobKey") {
            branchSources {
                git {
                    #id("$BASE_FOLDER.$projectKey.$jobKey")
                    remote(jobRepository)
                    #credentialsId GIT_CREDENTIALS
                    includes('master feature/*')
                }
            }
            orphanedItemStrategy {
                discardOldItems {
                    daysToKeep(NUM_TO_KEEP)
                }
            }
            triggers {
                cron('*/30 * * * *')
            }
        }
    }
}
*/
