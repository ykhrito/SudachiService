#!/bin/sh -x

`dirname $0`/compile.sh

rm -f build/SudachiServiceLauncher.jar
jar cvfm build/SudachiServiceLauncher.jar src/manifest.txt -C build net
