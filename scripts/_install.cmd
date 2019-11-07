set EXEC_DIR=%~dp0
set INSTALL_PATH=%EXEC_DIR%SudachiServiceLauncher.exe
set CLASSPATH=%CLASSPATH%;.;%EXEC_DIR%\..\sudachi-0.3.0.jar;%EXEC_DIR%\commons-daemon-1.2.2.jar;%EXEC_DIR%SudachiServiceLauncher.jar
set JVM_PATH=auto
set START_PARAMS=C:\sudachi\sudachi_fulldict.json;4343

SudachiServiceLauncher install SudachiServiceLauncher --DisplayName="SudachiServiceLauncher" --Install=%INSTALL_PATH% --Startup=auto --Jvm=%JVM_PATH% --StartMode=jvm --StopMode=jvm --Classpath=%CLASSPATH% --StartClass=net.params.SudachiService.SudachiServiceLauncher --StartMethod=startService --StartParams=%START_PARAMS% --StopClass=net.params.SudachiService.SudachiServiceLauncher --StopMethod=stopService --LogPath=%EXEC_DIR%\logs --LogLevel=DEBUG --StdOutput=auto --StdError=auto
pause
