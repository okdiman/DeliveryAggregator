package cargotype.domain.model

enum class CargoType(val text: String) {
    Box("Короб"), Pallet("Монопаллет"), FBS("ФБС"), Transit("Транзит")
}