package org.quanqi.gitlab.login;

/**
 * Created by cindy on 10/22/14.
 */
public interface OnLoginFinishListener {

    public void onUserNameError(String userName);

    public void onPasswordError(String password);

    public void onUserNameOrPasswordError(String username, String password);

    public void onSuccess(String token);
}
