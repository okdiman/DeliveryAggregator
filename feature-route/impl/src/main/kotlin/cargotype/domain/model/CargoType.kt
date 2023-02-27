package cargotype.domain.model

enum class CargoType(val text: String) {
    Box("Монокороб"), Pallet("Монопаллет"), Mix("Микс"), Transit("Транзит")
}