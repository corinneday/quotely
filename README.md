# Quotely

Command line program to get a random quote in English or Russian

## Getting Started

### Dependencies

Requires Java and Maven to be installed to run

### Executing program
Build:
```
mvn compile
```

Russian:
```
mvn exec:java -Dexec.mainClass=org.quotely.Main -Dexec.args="Russian"
```
English:
```
mvn exec:java -Dexec.mainClass=org.quotely.Main -Dexec.args="English"
```
