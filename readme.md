# Merchant Solutions Trading signal

- we are writing a Trading application that process the signals. There is an algo team who provides a library (or class)
that we can use and we need to write the code that will use this library to process the signals.
- We have 3 signals as of now but it will grow significantly in the future.

### Task 
- Receive signals via HTTP instead of the SignalHandler interface. The code should be a running service with a single http endpoint for receiving the ‘signal’ to be processed
by your application.
-  Easier for your team to understand and debug
-  Easier for your team to maintain and add new signals
-  Easier to test
  - The code should have appropriate levels of testing to ensure that the stated
  requirements are met.

### Application setup:
- The application is built with springboot and gradle.
- The application is a simple springboot application with a single endpoint to receive the signals.

### How to run the application:
- The application can be run using the following command:
```shell
./gradlew bootRun
```
- The application can be tested using the following command:
```shell
./gradlew test
```


### curl:
```shell
curl --location 'http://localhost:8080/signals' \
--header 'Content-Type: application/json' \
--data '{
    "signal": 2
}'
```



### Notes:
- Here I try to use the strategy pattern to process the signals.
- It Helps scale adding new signals easily.
- I assume that the lib will handle rollback scenarios and also throw exceptions if something goes wrong.
- I tried to use the best practices and design patterns as much as I can.
- I believe there will be always improvements that we can do. Assuming the small time frame I have, I tried to do my best.
