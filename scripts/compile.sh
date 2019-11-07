#!/bin/sh -x

rm -fr build && mkdir build
javac -d build -classpath ./src:./libs/sudachi-0.3.0/sudachi-0.3.0.jar:./libs/commons-daemon-1.2.2/commons-daemon-1.2.2.jar src/net/params/SudachiService/*.java
