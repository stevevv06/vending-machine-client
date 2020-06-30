# VENDING MACHINE CLIENT TECHNICAL CHALLENGE APPLICATION


##Running from sources

### Prerequsites
Before running the application, you must install

1. Java JDK 1.8 or later

2. If running in linux or mac set execution access to mvnw

    chmod +x mvnw

### Run application
In order to run the application execute the command:

    ./mvnw spring-boot:run
    

## Testing with the client
The application will run in a shell like environment.

Once the prompt displays `shell:>` you can issue the command `help` to see a full list of available commands
    
Some examples:

    >items
    >cash-total
    >profit
    >transactions-per-day 2020-06029
    >open 123
    >sale 103 1 CASH --bills 1 1
    >sale 103 1 CREDIT_CARD
    
