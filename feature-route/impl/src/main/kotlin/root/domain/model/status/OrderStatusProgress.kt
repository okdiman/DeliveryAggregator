package root.domain.model.status

enum class OrderStatusProgress(val status: String) {
    CREATED("created"),
    ASSIGNED("assigned"),
    CHANGED("changed"),
    DELIVERY("delivery"),
    DONE("done")
}