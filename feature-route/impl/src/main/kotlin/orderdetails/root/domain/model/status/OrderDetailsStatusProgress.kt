package orderdetails.root.domain.model.status

enum class OrderDetailsStatusProgress(val status: String) {
    CREATED("created"),
    ACTIVE("assigned"),
    LOADING("orderloading"),
    DELIVERY("delivery"),
    DONE("done")
}