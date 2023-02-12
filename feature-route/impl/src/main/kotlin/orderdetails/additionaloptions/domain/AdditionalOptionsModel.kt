package orderdetails.additionaloptions.domain

enum class AdditionalOptionsModel(val text: String) {
    Pallet("Паллет (250 ₽)"),
    Palletizing("Паллетирование (300 ₽)"),
    Loading("Погрузка (50 ₽/шт до 20 кг)"),
    NotNeed("Не требуются")
}