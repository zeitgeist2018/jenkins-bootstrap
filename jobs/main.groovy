//def GIT_CREDENTIALS = 'gitlab'
def BASE_FOLDER = 'infrastructure-build'
def NUM_TO_KEEP = 1

folder(BASE_FOLDER) {}
JOBS = [
        "test": [
                "jenkins-dummy": "infrastructure-build/jenkins-dummy.git"
        ],
        "plugins": [
                "gradle-plugin": "infrastructure-build/gradle-plugin.git"
        ]
]

JOBS.each { projectKey, projectJobs ->
    folder("$BASE_FOLDER/$projectKey") {}

    projectJobs.each { jobKey, jobRepository ->
        multibranchPipelineJob("$BASE_FOLDER/$projectKey/$jobKey") {
            branchSources {
                git {
                    id("$BASE_FOLDER.$projectKey.$jobKey")
                    remote("${GIT_URL}/${jobRepository}")
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

//setBuildResult('SUCCESS')
