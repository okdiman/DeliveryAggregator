package neworder.arrivaltime.domain

enum class ArrivalTime(val range: String) {
    One("С 8:00 до 10:00"),
    Two("С 10:00 до 12:00"),
    Three("С 12:00 до 14:00"),
    Four("С 14:00 до 16:00"),
    Five("С 16:00 до 18:00"),
    Six("С 18:00 до 20:00"),
    Seven("С 20:00 до 22:00"),
    Day("С 9:00 до 18:00"),
    Evening("С 16:00 до 21:00"),
}