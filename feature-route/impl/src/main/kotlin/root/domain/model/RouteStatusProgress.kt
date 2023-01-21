package root.domain.model

enum class RouteStatusProgress(val status: String) {
    NEW("new"),
    ASSIGNED("assigned"),
    DONE("done")
}