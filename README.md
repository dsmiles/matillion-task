# Matillion Task - main_branch
June 19, 2024

This is the original branch. There is a `to_do` branch containing the updates listed in the [TO-DO](#things-to-do) section below. Please switch to that branch.

## Platform
- CoderPad.io online Test Platform
- SpringBoot Application

## Details of task
Details of task to be added later (maybe).

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

## Things to-do
_See the todo_branch for the updates._
1. Move `parseResponse()` from repository class to service class (it's in the wrong location, better separation of concerns, etc)
2. Refactor `parseResponse()` into smaller methods as its too complicated (cleaner)
3. Add more exception and error handling
4. Add more application logging using log4J or something similar (better for debugging)
5. Add more tests to each level (controller, service, repository)
6. Add some E2E API level test cases
7. Add some NFR tests (performance, security, etc. as appropriate)

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
2024-06-19T20:09:53.612+01:00  INFO 59417 --- [BestImage] [           main] c.g.d.b.BestImageApplicationTests        : Started BestImageApplicationTests in 2.534 seconds (process running for 8.028)
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 3.254 s -- in com.github.dsmiles.bestimage.BestImageApplicationTests
[INFO] Running com.github.dsmiles.bestimage.BestImageControllerTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.086 s -- in com.github.dsmiles.bestimage.BestImageControllerTest
[INFO] Running com.github.dsmiles.bestimage.BestImageServiceTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.067 s -- in com.github.dsmiles.bestimage.BestImageServiceTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  15.769 s
[INFO] Finished at: 2024-06-19T20:09:53+01:00
[INFO] ------------------------------------------------------------------------
david@Davids-Mac-mini BestImage % mvn clean test
```

## Gitflow
I might add a GitFlow CI profile to build this on a push to GitHub.