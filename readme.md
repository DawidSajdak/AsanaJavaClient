# Asana Java Api v. 1

> Java Asana Api (v1) Wrapper

## Installation
```bash
git clone https://github.com/DawidSajdak/AsanaJavaClient.git
```
## Usage

``` java
    AsanaClient asanaClient = new AsanaClient(AsanaClient.ConnectionType.API_KEY);
    asanaClient.setApiKey(YOUR_API_KEY);

    AsanaRequest asanaRequest = new AsanaRequest(asanaClient);
    asanaRequest.getUserMe(new Request() {

        @Override
        public void onSuccess(String response) {
            System.out.println(response);
        }
    });
```

## API Coverage

### Implemented

``` scala
GET   /users/user-id
GET   /users/me
GET   /users
GET   /workspaces/workspace-id/users

POST  /tasks
POST  /workspaces/workspace-id/tasks
GET   /tasks/task-id
PUT   /tasks/task-id
GET   /tasks
GET   /projects/project-id/tasks
GET   /workspaces/workspace-id/tasks
GET   /tasks/task-id/stories
POST  /tasks/task-id/stories
GET   /tasks/task-id/projects
POST  /tasks/task-id/addProject
POST  /tasks/task-id/removeProject
GET   /tasks/task-id/tags
POST  /tasks/task-id/addTag
POST  /tasks/task-id/removeTag

POST  /projects
POST  /workspaces/workspace-id/projects
GET   /projects/project-id
PUT   /projects/project-id
GET   /projects/project-id/tasks
GET   /projects
GET   /workspaces/workspace-id/projects

POST  /tags
GET   /tags/tag-id
PUT   /tags/tag-id
GET   /tags/tag-id/tasks
GET   /tags
GET   /workspaces/workspace-id/tags

GET   /projects/project-id/stories
GET   /stories/story-id
POST  /projects/project-id/stories

GET    /workspaces
PUT    /workspaces/workspace-id
```
### Author: [Dawid Sajdak][0]

[0]: https://github.com/DawidSajdak/

> List of components and tools used to create Asana Java Api
- [http-request](https://github.com/kevinsawicki/http-request)

## License (MIT)

Copyright (c) 2013, Dawid Sajdak

**