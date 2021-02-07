# applause-demo

### Prerequisites
* NodeJs
* npm
* mvn
* Java 8

### Execution
###### Default
To run application with default data execute **_run.sh_**

###### Custom
To run application with custom data execute from _/backend_ directory **_mvn spring-boot:run -Dspring-boot.run.arguments=_**...

And pass arguments separated by comma as follows:
* --file.testers_path=_pathToFile.csv_
* --file.devices_path=_pathToFile.csv_
* --file.bugs_path=_pathToFile.csv_
* --file.tester_device_path=_pathToFile.csv_

And then from _/frontend_ directory execute **_ng serve -o_**
