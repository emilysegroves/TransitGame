javac -d classes ./src/*.java ./src/*/*.java
java -classpath classes TransitGame "$@"
