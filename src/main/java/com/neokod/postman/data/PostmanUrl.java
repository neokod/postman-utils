package com.neokod.postman.data;

import java.util.List;

public final class PostmanUrl {
        private String raw;

        private String protocol;

        private String port;

        private List<String> host;

        private List<String> path;

        private List<PostmanVariable> query;

        private List<PostmanVariable> variable;

        public String getRaw() {
            return raw;
        }

        public void setRaw(String raw) {
            this.raw = raw;
        }

        public List<String> getHost() {
            return host;
        }

        public void setHost(List<String> host) {
            this.host = host;
        }

        public List<String> getPath() {
            return path;
        }

        public void setPath(List<String> path) {
            this.path = path;
        }

        public String getProtocol() {
            return protocol;
        }

        public void setProtocol(String protocol) {
            this.protocol = protocol;
        }

        public String getPort() {
            return port;
        }

        public void setPort(String port) {
            this.port = port;
        }

    public List<PostmanVariable> getQuery() {
        return query;
    }

    public void setQuery(List<PostmanVariable> query) {
        this.query = query;
    }

    public List<PostmanVariable> getVariable() {
        return variable;
    }

    public void setVariable(List<PostmanVariable> variable) {
        this.variable = variable;
    }
}