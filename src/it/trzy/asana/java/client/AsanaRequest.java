/*
 * Copyright (c) 2013 Dawid Sajdak <sajdak.dawid@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
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
