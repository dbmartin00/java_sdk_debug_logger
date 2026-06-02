#!/bin/zsh
set x
java \
  -Dorg.slf4j.simpleLogger.defaultLogLevel=debug \
  -jar target/harness-flag-test-1.0.0.jar
