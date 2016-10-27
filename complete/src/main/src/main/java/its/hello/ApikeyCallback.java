package its.hello;

import javax.security.auth.callback.Callback;

/**
 * Created by eprst on 10/27/16.
 */
public class ApikeyCallback implements Callback {
    private Boolean ok;
    private String testKey;

    public String getTestKey() {
        return testKey;
    }

    public void setTestKey(String testKey) {
        this.testKey = testKey;
    }

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }
}
