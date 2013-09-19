/*
 * Copyright (c) 2013 Dawid Sajdak <sajdak.dawid@gmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
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
