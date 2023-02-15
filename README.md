# Delivery Aggregator

## Стек технологий

* Kotlin
* Coroutines
* MVI
* Jetpack Compose
* Odyssey
* KViewModel
* Retrofit
* Koin

## Вариации сборок

* В проекте используются BuildFlavors для создания отдельных сборок для клиента (client) и исполнителя (contractor).
* Все модули могут быть на самом верхнем уровне разбиты на пакеты: client, contractor и main при необходимости.
* Main - содержит общий код и ресурсы для обоих типов сборок
* Client - содержит уникальный код и ресурсы для сборки клиента
* Contractor - содержит уникальный код и ресурсы для сборки исполнителя

### Именование файлов

* Классы/Интерфейсы имеющие разные реализации в зависимости от сборки должны иметь одинаковые имена и пути в обоих
  flavor

## Устройство модулей

* Модули в проекте делятся на технические (core, core-ui, navigation) и фича-модули. Все модули в свою очередь
  разделены на пакеты по мини-фичам, а внутри по слоям согласно принципам чистой архитектуры.
* Фича модули так же имеют разделение на подмодули Api/Impl - в api лежат файлы (интерфейсы, модели и параметры
  экранов в основном), которые необходимо шарить между модулями.
* Чтобы организовать взаимодействие между модулями подключать ТОЛЬКО подмодуль Api. В подмодуле Impl лежит информация,
  недоступная извне (исключение - для модуля app)

### Схема модулей

```
├── app                        # Главный модуль приложения
├── buildSrc                   # Содержит все используемые сторонние библиотеки и их версии
├── core                       # Общий модуль для работы с бизнес логикой. 
├── core_ui                    # Общий модуль для работы с ui. Содержит тему, утилиты, базовые вьюшки, модифайеры
├── feature-address            # Модуль для работы с адресами
│   ├── api                    
│   ├── impl      
├── feature-auth               # Модуль авторизации
│   ├── api                    
│   ├── impl    
├── feature-image-uploading    # Модуль для выгрузки фото на сервер
│   ├── api                    
│   ├── impl    
├── feature-profile            # Модуль для работы с профилем пользователя
│   ├── api                    
│   ├── impl    
├── feature-registration       # Модуль регистрации пользователя
│   ├── api                    
│   ├── impl    
├── feature-splash-screen      # Модуль сплэш скрина
│   ├── api                    
│   ├── impl     
├── navigation                 # Модуль навигации
```

### Разбиение классов по слоям

Типичный экран может иметь следующие файлы в зависимости от сложности:

```
feature-module
├── api 
│  ├── minifeature1
│  │  ├── data                 
│  │  │  ├── model - MinifeatureModel             
│  │  │  ├── usecase                 
│  │  │  │  └── GetMinifeatureDate1UseCase                 
│  │  ├── presentation   
│  │  │  └── Minifeature1ScreenParameters                 
├── impl   
│  ├── minifeature1
│  │  ├── data
│  │  │  ├── mapper - Minifeature1Mapper
│  │  │  ├── model
│  │  │  │  ├── request - Minifeature1Dto
│  │  │  │  └── response - Minifeature1Request
│  │  │  ├── repository - Minifeature1RepositoryImpl
│  │  │  └── Minifeature1Api
│  │  ├── di - minifeature1Module
│  │  ├── domain
│  │  │  ├── usecases - GetMinifeatureDate1UseCaseImpl
│  │  │  ├── interactor - GetMinifeature1Interactor
│  │  │  └──  repository - Minifeature1Repository
│  │  ├── presentation
│  │  │  ├── compose 
│  │  │  │  ├── model - Minifeature1UiModel
│  │  │  │  ├── screen - Minifeature1Screen
│  │  │  │  └── view - Minifeature1View
│  │  │  ├── mapper - Minifeature1UiMapper
│  │  │  ├── viewmodel
│  │  │  │  └── model - Minifeature1State
│  │  │  │  └── Minifeature1ViewModel
```

## Работа с API

### Авторизация

При авторизации на тестовых стендах можно ввести любой номер телефона и 1234 в качестве кода подтверждения.

### Документация по API

* http://51.250.79.83:8080/doc

## GIT

### Именование веток

* Для задач применяется префикс task/
* Для багов применяется префикс bugfix/
* Для веток регресса применяется префикс regress/
* Для веток релиза применяется префикс release/
* Для хот-фиксов применяется префикс hotfix/
* В имени ветки для задачи/бага **обязательно** должен содержаться идентификатор из JIRA

Пример именования ветки: `task/DEL-111_client_profile_screen`

### Push и создание Merge Request

Перед пушем в репозиторий обязательно запустить таску *detekt*. Она проверяет синтаксис в соответствии с
кодстайлом проекта

* Ветки делаются от master если это не обговорено заранее
* Мерж реквесты делаются на master если этого не обговорено заранее
* Имя Merge Request должно состоять из идентификатора и названия из JIRA

Пример именования Merge Request: DEL-111. *Название задачи из JIRA*