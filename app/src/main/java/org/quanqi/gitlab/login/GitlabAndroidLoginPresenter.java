package org.quanqi.gitlab.login;

import org.quanqi.gitlab.GitlabApplication;
import org.quanqi.gitlab.models.GitlabSession;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import se.emilsjolander.sprinkles.Model;

/**
 * Created by cindy on 10/22/14.
 */
public class GitlabAndroidLoginPresenter implements LoginPresenter,
        Callback<GitlabSession> {

    public static final String TAG = "GitlabAndroidLoginPresenter";

    private LoginView loginView;

    public GitlabAndroidLoginPresenter(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void login(String userName, String password) {
        GitlabApplication.gitLabService.session(null, userName, password, this);
    }

    @Override
    public void success(GitlabSession gitlabSession, Response response) {
        gitlabSession.saveAsync(new Model.OnSavedCallback() {
            @Override
            public void onSaved() {
                loginView.navigateToHome();
            }
        });
    }

    @Override
    public void failure(RetrofitError error) {
        error.printStackTrace();
        loginView.showProgress(false);

        if (error.isNetworkError()) {
            loginView.setNetworkError();
            return;
        }

        if (error.getResponse() == null) {
            loginView.setServerError();
            return;
        }

        int status = error.getResponse().getStatus();
        if (status >= 500) {
            loginView.setServerError();
        } else if (status == 401) {
            loginView.setUsernameOrPasswordError();
        } else {
            loginView.setClientError();
        }
    }
}
