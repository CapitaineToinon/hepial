# HEPIAL

This project was installed using gradle but you don't need to install it thanks to the graddle wrapper.

All dependecies are also already in the project for convinience:

- app/src/lib/jasmin.jar
- app/src/lib/java-cup-11b.jar
- app/src/lib/jflex-full-1.8.2.jar

# Build

## Build jflex

```
./gradlew flex
```

## Build java_cup

```
./gradlew cup
```

## Build the Hepial compiler

```
./gradlew build
```

# Run the Fibonacci example

```
./gradlew run --args="../examples/Fibonacci.hepial"
java -jar app/lib/jasmin.jar app/Fibonacci.j
java Fibonacci
```

# Test

```
./gradlew test
```

# Stop the gradle deamons

```
./gradlew --stop
```

# Clean the project

```
./gradlew clean
```
