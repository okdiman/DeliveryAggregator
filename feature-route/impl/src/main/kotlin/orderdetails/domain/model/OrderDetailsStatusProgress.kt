package orderdetails.domain.model

enum class OrderDetailsStatusProgress(val status: String) {
    CREATED("created"),
    ACTIVE("assigned"),
    IN_PROGRESS("in-progress"),
    DONE("done")
}