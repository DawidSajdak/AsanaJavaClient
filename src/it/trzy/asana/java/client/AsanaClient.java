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

public class AsanaClient {

    /**
     * @var String
     */
    private String apiKey;

    /**
     * @var String
     */
    private String token;

    /**
     * @var ConnectionType
     */
    private ConnectionType connectionType;

    /**
     * Constructor
     * @param connectionType ConnectionType
     */
    public AsanaClient(ConnectionType connectionType) {
        setConnectionType(connectionType);
    }

    /**
     * Set API Key
     * @param apiKey String
     * @return AsanaClient
     */
    public AsanaClient setApiKey(String apiKey) {
        this.apiKey = apiKey;

        return this;
    }

    /**
     * Get client ID
     * @return String
     */
    public String getApiKey() {
        return this.apiKey + ":";
    }

    /**
     * Set token
     * @param token String
     * @return AsanaClient
     */
    public AsanaClient setToken(String token) {
        this.token = token;

        return this;
    }

    /**
     * Get token
     * @return String
     */
    public String getToken() {
        return this.token;
    }

    /**
     * Set connection type
     * @param connectionType ConnectionType
     * @return AsanaClient
     */
    public AsanaClient setConnectionType(ConnectionType connectionType) {
        this.connectionType = connectionType;

        return this;
    }

    /**
     * Get connection type
     * @return ConnectionType
     */
    public ConnectionType getConnectionType() {
        return this.connectionType;
    }

    /**
     * Connection type
     */
    public static enum ConnectionType {
        API_KEY, TOKEN
    }
}
