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
```
### Author: [Dawid Sajdak][0]

[0]: https://github.com/DawidSajdak/
