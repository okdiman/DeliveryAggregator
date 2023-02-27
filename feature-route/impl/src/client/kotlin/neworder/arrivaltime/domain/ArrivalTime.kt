package neworder.arrivaltime.domain

enum class ArrivalTime(val range: String) {
    Morning("С 8:00 до 11:00"),
    Dinner("С 11:00 до 14:00"),
    Afternoon("С 14:00 до 17:00"),
    Evening("С 17:00 до 20:00"),
    Night("С 20:00 до 23:00")
}