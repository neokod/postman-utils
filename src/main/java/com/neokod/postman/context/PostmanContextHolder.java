package com.neokod.postman.context;

import com.neokod.postman.data.PostmanCollection;
import com.neokod.postman.data.PostmanEnvironment;

public class PostmanContextHolder {

    private static PostmanContextHolder instance;

    private final PostmanCollection collection;

    private final PostmanEnvironment environment;

    private PostmanContextHolder(PostmanCollection collection, PostmanEnvironment environment) {
        this.collection = collection;
        this.environment = environment;
    }

    public PostmanCollection getCollection() {
        return collection;
    }

    public PostmanEnvironment getEnvironment() { return environment; }

    public static PostmanContextHolder getContext() {
        return instance;
    }

    public static void build(PostmanCollection collection, PostmanEnvironment environment) {
        instance = new PostmanContextHolder(collection, environment);
    }

}
