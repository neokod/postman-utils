package com.neokod.postman.data;

import java.util.List;

public class PostmanAuth {

    private String type;

    private List<PostmanBearer> bearer;

    private List<PostmanBearer> basic;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<PostmanBearer> getBearer() {
        return bearer;
    }

    public void setBearer(List<PostmanBearer> bearer) {
        this.bearer = bearer;
    }

    public List<PostmanBearer> getBasic() {
        return basic;
    }

    public void setBasic(List<PostmanBearer> basic) {
        this.basic = basic;
    }

    public static final class PostmanBearer extends PostmanVariable {
        private String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
