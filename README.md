# Currency Exchange

Small application write in Java, using Spring Boot and Gradle. 
It will exchange money that are on account from PLN to EUR based on NBP current ratings.

# Endpoints

There is one endpoint `/exchange/{accountNumber}` that will exchange money based on current rating and print as response, for example:
```json
{
    "amount": 22.82,
    "currency": "EUR"
}
```

# How to run

* From preferred IDE by Run As on class `CurrencyExchangeApplication`
* From command line by `./gradlew bootRun`

# How to use

* From command line by `curl http://localhost:8080/exchange/123` where `123` is account number
* You can change account numbers and money exist in application by editing `InMemoryAccountRepository`
