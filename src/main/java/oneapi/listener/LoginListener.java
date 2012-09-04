package oneapi.listener;

import java.util.EventListener;

import oneapi.model.common.LoginResponse;

public interface LoginListener extends EventListener {
    public void onLogin(LoginResponse response);
}
