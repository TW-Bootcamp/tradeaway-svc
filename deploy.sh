#!/bin/bash
kill -9 $(lsof -ti tcp:8080)
java -jar tradeaway-svc-1.0-SNAPSHOT.jar  &