package its.hello;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.ws.soap.security.callback.AbstractCallbackHandler;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * Created by eprst on 10/27/16.
 */
public class ApikeyValidationCallback extends AbstractCallbackHandler implements InitializingBean {
    private Map<String, String> users = new HashMap();
    public ApikeyValidationCallback()
    {

    }
    @Override
    public void afterPropertiesSet() throws Exception {

    }
    public void setUsers(Properties users) {
        Iterator var2 = users.entrySet().iterator();

        while(var2.hasNext()) {
            Map.Entry entry = (Map.Entry)var2.next();
            if(entry.getKey() instanceof String && entry.getValue() instanceof String) {
                this.users.put((String)entry.getKey(), (String)entry.getValue());
            }
        }

    }
    @Override
    protected void handleInternal(Callback callback) throws IOException, UnsupportedCallbackException {
        if(callback instanceof ApikeyCallback)
        {
            this.handleUsernameToken((ApikeyCallback)callback);
        }
        else {
            throw new UnsupportedCallbackException(callback);
        }
    }
    public void handleUsernameToken(ApikeyCallback callback) throws IOException, UnsupportedCallbackException {
        if(users.values().contains(callback.getTestKey()))
        {
            callback.setOk(true);
        }
        else
        {
            callback.setOk(false);
        }
    }
}
