package orderdetails.root.domain.model.status

enum class OrderDetailsStatusProgress(val status: String) {
    CREATED("created"),
    ACTIVE("assigned"),
    DELIVERY("delivery"),
    DONE("done")
}