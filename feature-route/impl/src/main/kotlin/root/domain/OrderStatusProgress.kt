package root.domain

enum class OrderStatusProgress(val status: String) {
    CREATED("created"),
    ACTIVE("assigned"),
    INPROGRESS("in-progress"),
    DONE("done")
}