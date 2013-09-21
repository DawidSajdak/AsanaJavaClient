/*
 * Copyright (c) 2013 Dawid Sajdak <sajdak.dawid@gmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package it.trzy.asana.java.client;

import com.github.kevinsawicki.http.HttpRequest;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.util.Map;

import static it.trzy.asana.java.client.AsanaRequest.RequestType.*;

public class AsanaRequest {

    /**
     * @var AsanaClient
     */
    private AsanaClient asanaClient;

    /**
     * @var String
     */
    private final String API_URL = "https://app.asana.com/api/1.0";

    /**
     * Constructor
     * @param asanaClient AsanaClient
     */
    public AsanaRequest(AsanaClient asanaClient) {
        this.asanaClient = asanaClient;
    }

    /**
     * Make request
     * @param url String
     * @param requestType RequestType
     * @param data Map<String, String>
     * @param callback Request
     */
    private void request(String url, RequestType requestType, Map<String, String> data, final Request callback) {

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
                httpRequest.form(data);
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
                httpRequest.form(data);
                if(httpRequest.ok()) {
                    callback.onSuccess(httpRequest.body());
                }
                break;
        }
    }

    /**
     * Make post
     * @param url String
     * @param data Map<String, String>
     * @param callback Request
     */
    private void post(String url, Map<String, String> data, Request callback) {
        this.request(url, POST, data, callback);
    }

    /**
     * Make get
     * @param url String
     * @param callback Request
     */
    private void get(String url, Request callback) {
        this.request(url, GET, null, callback);
    }

    /**
     * Make put
     * @param url String
     * @param data Map<String, String>
     * @param callback Request
     */
    private void put(String url, Map<String, String> data, Request callback) {
        this.request(url, PUT, data, callback);
    }

    /* == USERS == */

    /**
     * Get user me
     * @param callback Request
     */
    public void getUserMe(Request callback) {
        this.get("/users/me", callback);
    }

    /**
     * Get user
     * @param userID Integer
     * @param callback Request
     */
    public void getUser(Integer userID, Request callback) {
        this.get("/users/" + userID, callback);
    }

    /**
     * Get users
     * @param callback Request
     */
    public void getUsers(Request callback) {
        this.get("/users", callback);
    }

    /**
     * Get users from workspace
     * @param workspaceID Integer
     * @param callback Request
     */
    public void getUsersFromWorkspace(Integer workspaceID, Request callback) {
        this.get("/workspaces/" + workspaceID + "/users", callback);
    }

    /* == TASKS == */

    /**
     * Create task
     * @param data Map<String, String>
     * @param callback Request
     */
    public void createTask(Map<String, String> data, Request callback){
        this.post("/tasks", data, callback);
    }

    /**
     * Create workspace task
     * @param workspaceID Integer
     * @param data Map<String, String>
     * @param callback Request
     */
    public void createWorkspaceTask(Integer workspaceID, Map<String, String> data, Request callback){
        this.post("/workspaces/" + workspaceID + "/tasks", data, callback);
    }

    /**
     * Update task
     * @param taskID Integer
     * @param data Map<String, String>
     * @param callback Request
     */
    public void updateTask(Integer taskID, Map<String, String> data, Request callback){
        this.put("/tasks/" + taskID, data, callback);
    }

    /**
     * Add comment to task
     * @param taskID Integer
     * @param data Map<String, String>
     * @param callback Request
     */
    public void addCommentToTask(Integer taskID, Map<String, String> data, Request callback){
        this.post("/tasks/" + taskID + "/stories", data, callback);
    }

    /**
     * Get task
     * @param taskID Integer
     * @param callback Request
     */
    public void getTask(Integer taskID, Request callback){
        this.get("/tasks/" + taskID, callback);
    }

    /**
     * Get tasks
     * @param callback Request
     */
    public void getTasks(Request callback){
        this.get("/tasks", callback);
    }

    /**
     * Get task's project
     * @param projectID Integer
     * @param callback Request
     */
    public void getTasksByProject(Integer projectID, Request callback){
        this.get("/projects/" + projectID + "/tasks", callback);
    }

    /**
     * Get task's workspaces
     * @param workspaceID Integer
     * @param callback Request
     */
    public void getTasksWorkspace(Integer workspaceID, Request callback){
        this.get("/workspaces/" + workspaceID + "/tasks", callback);
    }

    /**
     * Get task activity
     * @param taskID Integer
     * @param callback Request
     */
    public void getTaskActivity(Integer taskID, Request callback){
        this.get("/tasks/" + taskID + "/stories", callback);
    }

    /**
     * Get task's projects
     * @param taskID Integer
     * @param callback Request
     */
    public void getTaskProjects(Integer taskID, Request callback){
        this.get("/tasks/" + taskID + "/projects", callback);
    }

    /**
     * Add project to task
     * @param taskID Integer
     * @param data Map<String, String>
     * @param callback Request
     */
    public void addProjectToTask(Integer taskID, Map<String, String> data, Request callback){
        this.post("/tasks/" + taskID + "/addProject", data, callback);
    }

    /**
     * Remove project from task
     * @param taskID Integer
     * @param data Map<String, String>
     * @param callback Request
     */
    public void removeProjectFromTask(Integer taskID, Map<String, String> data, Request callback){
        this.post("/tasks/" + taskID + "/removeProject", data, callback);
    }

    /**
     * Add tag to task
     * @param taskID Integer
     * @param data Map<String, String>
     * @param callback Request
     */
    public void addTagToTask(Integer taskID, Map<String, String> data, Request callback){
        this.post("/tasks/" + taskID + "/addTag", data, callback);
    }

    /**
     * Remove tag from task
     * @param taskID Integer
     * @param data Map<String, String>
     * @param callback Request
     */
    public void removeTagFromTask(Integer taskID, Map<String, String> data, Request callback){
        this.post("/tasks/" + taskID + "/removeTag", data, callback);
    }

    // ****** PROJECTS ***********

    /**
     * Create project
     * @param data Map<String, String>
     * @param callback Request
     */
    public void createProject(Map<String, String> data, Request callback){
        this.post("/projects", data, callback);
    }

    /**
     * Create project in workspace
     * @param workspaceID Integer
     * @param data Map<String, String>
     * @param callback Request
     */
    public void createWorkspaceProject(Integer workspaceID, Map<String, String> data, Request callback){
        this.post("/workspaces/" + workspaceID + "/projects", data, callback);
    }

    /**
     * Get project
     * @param projectID Integer
     * @param callback Request
     */
    public void getProject(Integer projectID, Request callback){
        this.get("/projects/" + projectID, callback);
    }

    /**
     * Update project
     * @param projectID Integer
     * @param data Map<String, String>
     * @param callback Request
     */
    public void updateProject(Integer projectID, Map<String, String> data, Request callback){
        this.put("/projects/" + projectID, data, callback);
    }

    /**
     * Get project's tasks
     * @param projectID Integer
     * @param callback Request
     */
    public void getProjectTasks(Integer projectID, Request callback){
        this.get("/projects/" + projectID + "/tasks", callback);
    }

    /**
     * Get projects
     * @param callback Request
     */
    public void getProjects(Request callback){
        this.get("/projects", callback);
    }

    /**
     * Get projects from workspace
     * @param workspaceID Integer
     * @param callback Request
     */
    public void getProjectsFromWorkspace(Integer workspaceID, Request callback){
        this.get("/workspaces/" + workspaceID + "/projects", callback);
    }

    // ****** TAGS ***********

    /**
     * Create tag
     * @param data Map<String, String>
     * @param callback Request
     */
    public void createTagWorkspace(Map<String, String> data, Request callback){
        this.post("/tags", data, callback);
    }

    /**
     * Get tag
     * @param tagID Integer
     * @param callback Request
     */
    public void getTag(Integer tagID, Request callback){
        this.get("/tags/" + tagID, callback);
    }

    /**
     * Update tag
     * @param tagID Integer
     * @param data Map<String, String>
     * @param callback Request
     */
    public void updateTag(Integer tagID, Map<String, String> data, Request callback){
        this.put("/tags/" + tagID, data, callback);
    }

    /**
     * Get tag's tasks
     * @param tagID Integer
     * @param callback Request
     */
    public void getTagTasks(Integer tagID, Request callback){
        this.get("/tags/" + tagID + "/tasks", callback);
    }

    /**
     * Get tags
     * @param callback Request
     */
    public void getTags(Request callback){
        this.get("/tags/", callback);
    }

    /**
     * Get tags from workspace
     * @param workspaceID Integer
     * @param callback Request
     */
    public void getTagsFromWorkspace(Integer workspaceID, Request callback){
        this.get("/workspaces/" + workspaceID + "/tags", callback);
    }

    // ****** STORIES ***********

    /**
     * Get stories from task
     * @param taskID Integer
     * @param callback Request
     */
    public void getStoriesFromTask(Integer taskID, Request callback){
        this.get("/tasks/" + taskID + "/stories", callback);
    }

    /**
     * Get stories from project
     * @param projectID Integer
     * @param callback Request
     */
    public void getStoriesFromProject(Integer projectID, Request callback){
        this.get("/projects/" + projectID + "/stories", callback);
    }

    /**
     * Get story
     * @param storyID Integer
     * @param callback Request
     */
    public void getStory(Integer storyID, Request callback){
        this.get("/stories/" + storyID, callback);
    }

    /**
     * Add comment to project
     * @param projectID Integer
     * @param data Map<String, String>
     * @param callback Request
     */
    public void addCommentToProject(Integer projectID, Map<String, String> data, Request callback){
        this.post("/projects/" + projectID + "/stories", data, callback);
    }

    // ****** WORKSPACES ***********

    /**
     * Get workspace
     * @param callback Request
     */
    public void getWorkspaces(Request callback){
        this.get("/workspaces", callback);
    }

    /**
     * Update workspace
     * @param workspaceID Integer
     * @param data Map<String, String>
     * @param callback Request
     */
    public void updateWorkspace(Integer workspaceID, Map<String, String> data, Request callback){
        this.put("/workspaces/" + workspaceID, data, callback);
    }

    public static enum RequestType {
        POST, GET, PUT
    }

}
