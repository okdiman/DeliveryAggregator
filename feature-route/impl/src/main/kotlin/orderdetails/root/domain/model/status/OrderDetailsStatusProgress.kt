package orderdetails.root.domain.model.status

enum class OrderDetailsStatusProgress(val status: String) {
    CREATED("created"),
    ACTIVE("assigned"),
    LOADING("loading"),
    DELIVERY("delivery"),
    DONE("done")
}