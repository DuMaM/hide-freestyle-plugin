#!/bin/bash

mvn -ntp verify && mvn hpi:run -Djava.util.logging.config.file=logging.properties
