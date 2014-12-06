package org.quanqi.gitlab.login;

/**
 * Created by cindy on 10/22/14.
 */
public interface LoginView {

    public void showProgress(boolean show);

    public void setNetworkError();

    public void setServerError();

    public void setUserNameError();

    public void setPasswordError();

    public void setClientError();

    public void setUsernameOrPasswordError();

    public void navigateToHome();
}
