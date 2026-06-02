# Log Harness FME Java SDK Activity to Harness

- Create a "logging" flag 

Save the Java API key from the Syntax menu (...) of your flag.

- Clone the repository.

- Edit the environment to supply your own API key

export HARNESS_API_KEY=<server-side Harness FME API key>

You can get the key from the Syntax generator of a flag; choose Java and copy the included key.

- Compile

mvn clean install

- Run

java \
  -Dorg.slf4j.simpleLogger.defaultLogLevel=debug \
  -jar target/harness-flag-test-1.0.0.jar


** Expected behavior

When the flag is off, the program will sleep for 10 minutes.  Debug is turned on, so all SDK activity (including streaming connections) are mentioned in readout.

david.martin@harness.io

