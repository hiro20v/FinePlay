LANG=en_US.UTF-8
./sbt -J-Xmx4g dist
cd ./target/universal/
unzip ./fineplay-2.7.2-b1-SNAPSHOT.zip
cd fineplay-2.7.2-b1-SNAPSHOT
./bin/fineplay -Dconfig.resource=application_prod.conf -Dlogger.resource=logback_prod.xml
