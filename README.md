# Matillion Task - ToDo_Branch
June 19, 2024

## Platform
- CoderPad.io online Test Platform
- SpringBoot Application

## Details of task
Details of task to be added later (maybe).

## Updates to original task
This branch contains the updates to my original code implemented for the task. This version is much better, includes cleaner code, more tests and fixes a number of issues with Mockito that I hadn't noticed (`spring-boot-starter-test` already includes the dependencies for JUnit5 and Mockito).

_Note to self: always check your dependencies ..._

The list of updates are mostly done. Item 7 will require some additional time, but I'll do that next week.

## Result

I wasn't able to complete the task within the given timeframe, but I was able to get the SpringBoot application to retrieve data from the Pexels REST API, extract the "best image" using the given success criteria and return a response.

```http response
{
  "url": "https://images.pexels.com/photos/132477/pexels-photo-132477.jpeg",
  "photographer": "Anthony 🙂",
  "alt": "Clear Water Drops"
}
```

I created some basic test classes for the controller, service and repository levels, but these were not compiling due to problems with dependencies in the POM file.

However, I was able to recreate the Spring Boot project on my local machine in the evening and fixed those problems (incompatible library versions). I also forgot to add the `spring-boot-starter-test` to the dependencies (which didn't help).

## Things to-do (Mostly done now)
1. Move `parseResponse()` from repository class to service class (it's in the wrong location, better separation of concerns, etc.) - DONE
2. Refactor `parseResponse()` into smaller methods as its too complicated (cleaner) - DONE 
3. Add more exception and error handling - DONE
4. Add more application logging using log4J or something similar (better for debugging) - DONE
5. Add more tests to each level (controller, service, repository) - DONE
6. Add some E2E API level test cases - DONE
7. Add some NFR tests (performance, security, etc. as appropriate) - TBD

## Additional features
1. Add ability to get the "best image" for a given subject. This would involve adding methods to the controller, service and repository classes to `findBy(subject)`. For example, Ocean, Tigers, Nature, Science, etc.
2. Add ability to search for more than 10 items per page

## API Key
The API key has been removed from the code. It can be set via system environment variable, or from the command line. In my case, I've configured a profile within IntelliJ to set the key via System Env.

## Usage
To run the test framework, follow these steps:

1. Clone the repository:
    ```
    git clone https://github.com/dsmiles/matillion-task
    cd matillion-task
    ```
2. Set the API_KEY environment variable (as per your OS or IDE)

3. Run the Maven test command:
    ```
    mvn clean test
    ```

## Output

```console
2024-06-23T23:29:08.538+01:00  INFO 39396 --- [BestImage] [           main] c.g.d.b.BestImageApplicationTest         : Starting BestImageApplicationTest using Java 21.0.3 with PID 39396 (started by david in /Volumes/Macintosh HDD/MyCode/BestImage)
2024-06-23T23:29:08.539+01:00  INFO 39396 --- [BestImage] [           main] c.g.d.b.BestImageApplicationTest         : No active profile set, falling back to 1 default profile: "default"
2024-06-23T23:29:09.169+01:00  WARN 39396 --- [BestImage] [           main] ion$DefaultTemplateResolverConfiguration : Cannot find template location: classpath:/templates/ (please add some templates, check your Thymeleaf configuration, or set spring.thymeleaf.check-template-location=false)
2024-06-23T23:29:09.222+01:00  INFO 39396 --- [BestImage] [           main] c.g.d.b.BestImageApplicationTest         : Started BestImageApplicationTest in 0.741 seconds (process running for 9.987)
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.782 s -- in com.github.dsmiles.bestimage.BestImageApplicationTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 12, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  15.887 s
[INFO] Finished at: 2024-06-23T23:29:09+01:00
[INFO] ------------------------------------------------------------------------
```
