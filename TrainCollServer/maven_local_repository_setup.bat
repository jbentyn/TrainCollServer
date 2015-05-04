REM INSTALLING SPACEBASE LIBS IN LOCAL MVN REPOSITORY

REM before running this script create lib folder in your project root folder
REM remember to add MAVEN_HOME to your PATH variable 

set spaceBaseDir=d:\programowanie\spacebase-2.1\dist
set projectDir=D:\programowanie\workspace\TrainCollRepository\TrainCollServer

call mvn install:install-file -Dfile=%spaceBaseDir%\lib\quasar-core-0.4.0.jar -DgroupId=co.paralleluniverse -DartifactId=quasar-core -Dversion=0.4 -Dpackaging=jar -DlocalRepositoryPath=%projectDir%\lib
call mvn install:install-file -Dfile=%spaceBaseDir%\lib\quasar-actors-0.4.0.jar -DgroupId=co.paralleluniverse -DartifactId=quasar-actors -Dversion=0.4 -Dpackaging=jar -DlocalRepositoryPath=%projectDir%\lib
call mvn install:install-file -Dfile=%spaceBaseDir%\lib\spacebase-core-2.1.jar -DgroupId=co.paralleluniverse.spacebase -DartifactId=spacebase-core -Dversion=2.1 -Dpackaging=jar -DlocalRepositoryPath=%projectDir%\lib
call mvn install:install-file -Dfile=%spaceBaseDir%\lib\spacebase-galaxy-2.1.jar -DgroupId=co.paralleluniverse -DartifactId=spacebase-galaxy -Dversion=2.1 -Dpackaging=jar -DlocalRepositoryPath=%projectDir%\lib
call mvn install:install-file -Dfile=%spaceBaseDir%\lib\spacebase-geoqueries-2.1.jar -DgroupId=co.paralleluniverse -DartifactId=spacebase-geoqueries -Dversion=2.1 -Dpackaging=jar -DlocalRepositoryPath=%projectDir%\lib
call mvn install:install-file -Dfile=%spaceBaseDir%\lib\spacebase-native-2.1.jar -DgroupId=co.paralleluniverse -DartifactId=spacebase-native -Dversion=2.1 -Dpackaging=jar -DlocalRepositoryPath=%projectDir%\lib
call mvn install:install-file -Dfile=%spaceBaseDir%\lib\galaxy-1.3.jar -DgroupId=co.paralleluniverse -DartifactId=galaxy -Dversion=1.3 -Dpackaging=jar -DlocalRepositoryPath=%projectDir%\lib
call mvn install:install-file -Dfile=%spaceBaseDir%\lib\geodesy-2.1.jar -DgroupId=co.paralleluniverse.geodesy -DartifactId=geodesy -Dversion=2.1 -Dpackaging=jar -DlocalRepositoryPath=%projectDir%\lib

REM after running script add this to pom.xml
REM <repositories>
REM	 <repository>
REM		<!-- DO NOT set id to "local" because it is reserved by Maven -->
REM		<id>lib</id>
REM		<url>file://${project.basedir}/lib</url>
REM	 </repository>
REM </repositories>
REM now you can use libs like regular mvn dependency i.e.:
REM <dependency>
REM			<groupId>co.paralleluniverse</groupId>
REM			<artifactId>spacebase-core</artifactId>
REM			<version>2.1</version>
REM		</dependency>