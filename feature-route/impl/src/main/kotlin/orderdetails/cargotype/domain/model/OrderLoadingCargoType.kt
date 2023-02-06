package orderdetails.cargotype.domain.model

enum class OrderLoadingCargoType(val text: String) {
    Box("Монокороб"), Pallet("Монопаллет"), Mix("Микс"), Transit("Транзит")
}