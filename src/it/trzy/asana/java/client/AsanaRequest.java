/*
 * Copyright (c) 2013 Dawid Sajdak <sajdak.dawid@gmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package it.trzy.asana.java.client;

import com.github.kevinsawicki.http.HttpRequest;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import static it.trzy.asana.java.client.AsanaRequest.RequestType.*;

public class AsanaRequest {

    private final String API_URL = "https://app.asana.com/api/1.0";

    private AsanaClient asanaClient;

    public AsanaRequest(AsanaClient asanaClient) {
        this.asanaClient = asanaClient;
    }

    private void request(String url, RequestType requestType, final Request callback) {

        url = API_URL + url;

        String authorization = null;

        switch(asanaClient.getConnectionType()) {
            case API_KEY:
                authorization = "Basic " + Base64.encode(asanaClient.getApiKey().getBytes());
                break;
            case TOKEN:
                authorization = "Bearer " + asanaClient.getToken();
                break;
        }

        HttpRequest httpRequest;

        switch(requestType) {
            case POST:
                httpRequest = HttpRequest.post(url).header("Authorization", authorization);
                if(httpRequest.ok()) {
                    callback.onSuccess(httpRequest.body());
                }
                break;
            case GET:
                httpRequest = HttpRequest.get(url).header("Authorization", authorization);
                if(httpRequest.ok()) {
                    callback.onSuccess(httpRequest.body());
                }
                break;
            case PUT:
                httpRequest = HttpRequest.put(url).header("Authorization", authorization);
                if(httpRequest.ok()) {
                    callback.onSuccess(httpRequest.body());
                }
                break;
        }

    }

    private void post(String url, Request callback) {
        this.request(url, POST, callback);
    }

    private void get(String url, Request callback) {
        this.request(url, GET, callback);
    }

    private void put(String url, Request callback) {
        this.request(url, PUT, callback);
    }

    /* == USERS == */

    public void getUserMe(Request callback) {
        this.get("/users/me", callback);
    }

    public void getUserByID(Integer userID, Request callback) {
        this.get("/users/" + userID, callback);
    }

    public void getUsers(Request callback) {
        this.get("/users", callback);
    }

    public void getUsersFromWorkspace(Integer workspaceID, Request callback) {
        this.get("/workspaces/" + workspaceID + "/users", callback);
    }

    /* == TASKS == */

    public static enum RequestType {
        POST, GET, PUT
    }

}
