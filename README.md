# GitHub Repository Access Report Service

## Overview

This project is a Spring Boot application that integrates with the GitHub REST API to generate a report showing which users have access to which repositories within a GitHub organization.

The service authenticates using a GitHub Personal Access Token, retrieves repositories from the specified organization, fetches collaborators for each repository, and aggregates the information into a structured access report.

The application exposes a REST API endpoint that returns the access report in JSON format.

---

## Features

* Secure authentication with GitHub using a Personal Access Token
* Retrieve all repositories belonging to a GitHub organization
* Identify collaborators who have access to each repository
* Generate an aggregated user-to-repositories mapping
* Expose a REST API endpoint returning the report in JSON format
* Clean and maintainable layered architecture (Controller → Service → Model)

---

## Technology Stack

* Java 21
* Spring Boot
* GitHub REST API
* Maven
* RESTful Web Services

---

## Project Structure

```
src/main/java/com/example/demo
│
├── controller
│      AccessReportController.java
│
├── service
│      GitHubService.java
│
├── model
│      AccessReport.java
│
└── GithubAccessReportApplication.java
```

---

## Prerequisites

Before running the project, ensure the following are installed:

* Java 21 or higher
* Maven
* Git
* A GitHub account
* A GitHub Personal Access Token

---

## GitHub Authentication Setup

The application authenticates with GitHub using a Personal Access Token.

### Steps to Generate Token

1. Go to GitHub Settings
2. Navigate to **Developer Settings**
3. Click **Personal Access Tokens**
4. Click **Generate new token (classic)**
5. Select the following permissions:

    * `repo`
    * `read:org`
6. Generate the token and copy it

---

## Configuration

Open the following file:

```
src/main/resources/application.properties
```

Add the following configuration:

```
github.token=github.token=YOUR_GITHUB_PERSONAL_ACCESS_TOKEN
github.org=babu-dev-org
```

Example:

```
github.token=ghp_xxxxxxxxxxxxxxxxxxxxx
github.org=babu-dev-org
```

---

## How to Run the Project

### Clone the repository

```
git clone https://github.com/your-username/github-access-report.git
```

### Navigate to project directory

```
cd github-access-report
```

### Run the Spring Boot application

Using Maven:

```
mvn spring-boot:run
```

Or run the main class:

```
GithubAccessReportApplication.java
```

---

## API Endpoint

### Get Access Report

```
GET /api/access-report
```

Example request:

```
http://localhost:8080/api/access-report
```

---
### Testing the API

Run the application and open the following URL in your browser:

http://localhost:8080/api/access-report
## Example Response

```
[
  {
    "user": "Babugv2003",
    "repositories": [
      "java_project",
      "data-api"
    ]
  }
]
```


This response indicates which repositories each user has access to.

---
## Architecture

Client
   |
   v
Spring Boot REST API
   |
   v
GitHubService
   |
   v
GitHub REST API
---

## How the Application Works

1. The service authenticates with GitHub using the configured token.
2. It retrieves repositories from the specified organization using the GitHub API.
3. For each repository, it fetches the list of collaborators.
4. It aggregates the data to map users to repositories they can access.
5. The REST API endpoint returns the aggregated data as JSON.

---

## Design Decisions

* **Layered Architecture**

    * Controller layer handles HTTP requests.
    * Service layer handles GitHub API interaction and business logic.
    * Model layer defines the response structure.

* **RESTful API Design**

    * The application exposes a simple endpoint returning JSON.

* **GitHub REST API Usage**

    * `/orgs/{org}/repos` endpoint is used to fetch organization repositories.
    * `/repos/{org}/{repo}/collaborators` endpoint is used to fetch collaborators.

---

## Scalability Considerations

The system is designed to support organizations with:

* 100+ repositories
* 1000+ users with repository access

To improve performance, the application can be extended to:

* Use parallel processing for repository collaborator fetching
* Implement caching
* Handle GitHub API rate limits

---

## Assumptions

* The GitHub token provided has sufficient permissions to access repositories and collaborators.
* Repositories belong to the configured GitHub organization.
* Collaborators represent users with repository access.

---

## Future Improvements

* Add asynchronous processing for faster API calls
* Add caching for frequently accessed reports
* Add logging and monitoring
* Add Swagger/OpenAPI documentation
* Add pagination support for large organizations
* Scalable design capable of supporting organizations with 100+ repositories and 1000+ users.

---

## Author

**Babu G V**

Java Developer

