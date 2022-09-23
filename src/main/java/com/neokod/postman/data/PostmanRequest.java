package com.neokod.postman.data;

import java.util.List;

public class PostmanRequest {

    private String method;

    private List<PostmanHeader> header;

    private PostmanUrl url;

    private RequestBody body;

    private PostmanAuth auth;

    public PostmanUrl getUrl() {
        return url;
    }

    public void setUrl(PostmanUrl url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public List<PostmanHeader> getHeader() {
        return header;
    }

    public void setHeader(List<PostmanHeader> header) {
        this.header = header;
    }

    public PostmanAuth getAuth() {
        return auth;
    }

    public void setAuth(PostmanAuth auth) {
        this.auth = auth;
    }

    public RequestBody getBody() {
        return body;
    }

    public void setBody(RequestBody body) {
        this.body = body;
    }

    public static final class RequestBody {
        private String mode;

        private String raw;

        private BodyOptions options;

        public String getMode() {
            return mode;
        }

        public void setMode(String mode) {
            this.mode = mode;
        }

        public String getRaw() {
            return raw;
        }

        public void setRaw(String raw) {
            this.raw = raw;
        }

        public BodyOptions getOptions() {
            return options;
        }

        public void setOptions(BodyOptions options) {
            this.options = options;
        }

        public static final class BodyOptions {

            private String mode;

            private OptionsRaw raw;

            public String getMode() {
                return mode;
            }

            public void setMode(String mode) {
                this.mode = mode;
            }

            public OptionsRaw getRaw() {
                return raw;
            }

            public void setRaw(OptionsRaw raw) {
                this.raw = raw;
            }
        }
    }

    public static final class OptionsRaw {
        private String language;

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }
    }

}
