package org.quanqi.gitlab;

import android.util.Log;

import com.google.gson.Gson;

import junit.framework.TestCase;

import org.quanqi.gitlab.models.GitlabUser;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import retrofit.Callback;
import retrofit.ErrorHandler;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class GitLabServiceTest extends TestCase {

    public static final String TAG = "GitLabServiceTest";

    public static final String PRIVATE_TOKEN = "Ngz1rqMMSsomp4yttRm3";
    public static final String END_POINT = "https://gitlab.com/api/v3";

    RestAdapter restAdapter;
    private GitLabService gitLabService;

    public void setUp() throws Exception {
        super.setUp();
        Log.e(TAG, "Setting up GitLabServiceTest");

        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint(END_POINT);
        builder.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addQueryParam("private_token", PRIVATE_TOKEN);
            }
        });

        restAdapter = builder.build();
        gitLabService = restAdapter.create(GitLabService.class);

    }

    public void tearDown() throws Exception {

    }

    public void testListUsers() throws Exception {
        final CountDownLatch signal = new CountDownLatch(1);

        gitLabService.listUsers(new Callback<List<GitlabUser>>() {
            @Override
            public void success(List<GitlabUser> gitlabUsers, Response response) {
                signal.countDown();
                Log.i(TAG, "list users successfully.");
                for (GitlabUser user : gitlabUsers) {
                    Log.i(TAG, "user: " + user.getName() +
                            " id: " + user.getId() + " email: " + user.getEmail());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                signal.countDown();
                throw error;
            }
        });
        signal.await();
    }

    public void testSingleUser() throws Exception {
        final CountDownLatch signal = new CountDownLatch(1);

        gitLabService.singleUser(49506, new Callback<GitlabUser>() {
            @Override
            public void success(GitlabUser user, Response response) {
                Log.i(TAG, "get single user: " + user.getName());
                signal.countDown();
            }

            @Override
            public void failure(RetrofitError error) {
                signal.countDown();
                throw error;
            }
        });
        signal.await();
    }

    public void testCreateUser() throws Exception {

    }

    public void testModifyUser() throws Exception {

    }

    public void testDeleteUser() throws Exception {

    }

    public void testCurrentUser() throws Exception {

    }

    public void testListSshKeys() throws Exception {

    }

    public void testListSshKeysForUser() throws Exception {

    }

    public void testSingleSshKey() throws Exception {

    }

    public void testAddSshKey() throws Exception {

    }

    public void testAddSshKeyForUser() throws Exception {

    }

    public void testDeleteKeyForCurrentUser() throws Exception {

    }

    public void testDeleteKeyForGivenUser() throws Exception {

    }

    public void testSession() throws Exception {

    }

    public void testListProjects() throws Exception {

    }

    public void testListProjects1() throws Exception {

    }

    public void testListOwnedProjects() throws Exception {

    }

    public void testListAllProjects() throws Exception {

    }

    public void testGetSingleProjects() throws Exception {

    }

    public void testGetSingleProjects1() throws Exception {

    }

    public void testGetProjectEvents() throws Exception {

    }

    public void testCreateProject() throws Exception {

    }

    public void testCreateProjectForUser() throws Exception {

    }

    public void testDeleteProject() throws Exception {

    }

    public void testListTeamMembers() throws Exception {

    }

    public void testGetProjectTeamMember() throws Exception {

    }

    public void testAddProjectTeamMember() throws Exception {

    }

    public void testEditProjectTeamMember() throws Exception {

    }

    public void testRemoveProjectTeamMember() throws Exception {

    }

    public void testListProjectHooks() throws Exception {

    }

    public void testGetProjectHook() throws Exception {

    }


}