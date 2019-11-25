## How to run

Please go to the project root directory and then build the project using gradle as shown below:

Step 1: 

If you are on Linux then do this:
```bash
  ./gradlew clean build
```
If you are on windows then do this:
```powershell
  gradlew.bat clean build
```

Step 2:

```bash
 java -jar build/libs/interview_narendra_kumar-0.1.0.jar
```

Note: please adjust the path separator on windows in **step 2** if required.

Step 3:

To check the api documentation and try those endpoints, open your browser and enter the following url to open swagger ui:

**http://localhost:8080/swagger-ui.html**

Thanks for reading.
