# Matillion Task
June 19, 2024

## Table of Contents
1. [Platform](#platform)
2. [Details of Task](#details-of-task)
3. [Testing](#testing)
4. [Result](#result)
5. [Things To-Do](#things-to-do)
6. [Additional Features To-Do](#additional-features-to-do)
7. [API Key](#api-key)
8. [GitHub Actions](#github-actions)
9. [Usage](#usage)
10. [Output](#output)

## Platform
- **CoderPad.io**: Online Test Platform
- **Application type**: SpringBoot Application REST API
- **Duration**: 2 hours

## Details of task
You are required to implement an API endpoint `/best-image` in Java. The task involves fetching data from the Pexels API endpoint: `https://api.pexels.com/v1/search?query=science&per_page=10`.

The API docs for this endpoint is here: https://www.pexels.com/api/documentation/#photos-search

You can use the following API Key for the Pexels endpoint: `API_KEY`: (REDACTED)

### Requirements:
The endpoint should have the following characteristics:

- **Path**: `/best-image`
- **Method**: `GET`
- **Response**: JSON with the following fields:
```json
{
   "url": "URL of the best image",
   "photographer": "Photographer’s name of the best image",
   "alt": "Alt text of the best image"
}
```

### The definition of the “best” image:
1. The image with the shorted `alt` length.
2. If multiple images have the same `alt` length, select the one with the shortest photographer’s name.
3. The endpoint should handle errors properly in case the API request fails and provide appropriate error messages.

### Consider extensions to the endpoint:
- How to get the ‘best’ image for something that isn’t related to science?
- How to widen the pool of images that the endpoint determines the ‘best’ image from?
- Identify and discuss other potential extensions.

### Testing
Document your testing approach and levels, ensuring coverage of various scenarios, including edge cases and error handling.

- Test Scenarios:
  - Valid response with image data.
  - No images returned from the API.
  - Network errors or API failures.
  - Different query parameters and larger datasets.

## Result
The Spring Boot application successfully retrieves data from the Pexels API, identifies the "best image" using the given criteria, and returns a response:

```http response
{
  "url": "https://images.pexels.com/photos/132477/pexels-photo-132477.jpeg",
  "photographer": "Anthony 🙂",
  "alt": "Clear Water Drops"
}
```
Basic test classes were created, but I had issues with dependencies in the POM file. These were fixed later.

Note: Always check your dependencies.

## Things to-do
Most updates are done. Item 7 requires additional time.

1. Move `parseResponse()` from repository class to service class - DONE
2. Refactor `parseResponse()` into smaller methods - DONE 
3. Add more exception and error handling - DONE
4. Add more application logging - DONE
5. Add more tests (controller, service, repository) - DONE
6. Add integration API level tests - DONE
7. Add NFR tests (performance, security, etc.) - TBD

## Additional features to-do
1. Ability to get the "best image" for a given subject.
2. Increase the search limit beyond 10 items per page.

## API Key
The API key has been removed from the code and can be set via system environment variables or command line. In IntelliJ, 
configure a profile to set the key via System Env.

## GitHub Actions
A GitHub Actions workflow builds and runs tests with Maven on each push and pull request. The API key is stored securely 
within GitHub Secrets, ensuring it is not visible in logs and is obscured automatically.

## Usage
To run the test framework:

1. Clone the repository:
    ```
    git clone https://github.com/dsmiles/matillion-task
    cd matillion-task
    ```
2. Set the API_KEY environment variable.
3. To run the unit tests:
    ```
    mvn clean test
    ```
4. Run the unit and integration tests:
    ```
    mvn clean verify
    ```
 
## Output

Example output of the unit test phase:

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

