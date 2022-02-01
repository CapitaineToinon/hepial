# HEPIAL

This project was installed using gradle but you don't need to install it thanks to the graddle wrapper.

All dependecies are also already in the project for convinience:

- `app/src/lib/jasmin.jar`
- `app/src/lib/java-cup-11b.jar`
- `app/src/lib/jflex-full-1.8.2.jar`

# Build

## Build jflex

This command will build the file:

- `app/src/main/java/hepial/Lexer.java`.

```
./gradlew flex
```

## Build java_cup

This command will build the files:

- `app/src/main/java/hepial/Lexer.java`
- `app/src/main/java/hepial/parser.java`
- `app/src/main/java/hepial/sym.java`.

```
./gradlew cup
```

## Build the Hepial compiler

This command will build the app into a jar file at `app/build/libs/app.jar`.

```
./gradlew build
```

# Run the Fibonacci example

After you've run the gralde commands `flex`, `cup` and `build`, execute a program (for example the Fibonacci code) like with the following commands.

```
java -jar app/build/libs/app.jar examples/Fibonacci.hepial
java -jar app/lib/jasmin.jar Fibonacci.j
java Fibonacci
```

# Test

After you've run the `flex` and `cup` commands, run the unit tests will the following command.

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
