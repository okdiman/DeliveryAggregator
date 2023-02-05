package orderdetails.loadingstate.model

enum class OrderLoadingCargoType(val text: String) {
    Box("Монокороб"), Pallet("Монопаллет"), Mix("Микс"), Transit("Транзит")
}