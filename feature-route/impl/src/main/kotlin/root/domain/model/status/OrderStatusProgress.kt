package root.domain.model.status

enum class OrderStatusProgress(val status: String) {
    CREATED("created"),
    ASSIGNED("assigned"),
    DELIVERY("delivery"),
    DONE("done")
}
