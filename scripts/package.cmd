call scripts\compile.cmd

del /q build\SudachiServiceLauncher.jar
jar cvfm build\SudachiServiceLauncher.jar src\manifest.txt -C build net
