sbt dist
cd target\universal
C:\Windows\System32\WindowsPowerShell\v1.0\powershell Expand-Archive fineplay-2.7.2-b3-SNAPSHOT.zip
cd fineplay-2.7.2-b3-SNAPSHOT
bin\fineplay.bat -Dconfig.resource=application_prod.conf -Dlogger.resource=logback_prod.xml
