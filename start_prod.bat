sbt dist
cd target\universal
powershell Expand-Archive fineplay-2.6.12-β2-SNAPSHOT.zip
cd fineplay-2.6.12-β2-SNAPSHOT
bin\fineplay -Dconfig.resource=application_prod.conf -Dlogger.resource=logback_prod.xml
