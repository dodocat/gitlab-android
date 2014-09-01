package org.quanqi.gitlab;

import org.quanqi.gitlab.models.GitlabSession;
import org.quanqi.gitlab.models.GitlabSshKey;
import org.quanqi.gitlab.models.GitlabUser;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Gitlab Api implement with retrofit.
 * <p/>
 * Created by cindy on 8/29/14.
 */
public interface GitLabService {

    /**
     * List users
     * <p/>
     * Get a list of users.
     * <p/>
     * This function takes pagination parameters page and per_page to restrict the list of users.
     */
    @GET("/users")
    public void listUsers(Callback<List<GitlabUser>> callback);

    /**
     * Single user
     * <p/>
     * Get a single user.
     *
     * @param userId (required) - The ID of a user
     */
    @GET("/users")
    public void singleUser(int userId, Callback<GitlabUser> callback);

    /**
     * User creation
     * <p/>
     * Creates a new user. Note only administrators can create new users.
     * <p/>
     * POST /users
     */
    @POST("/users")
    public void createUser(@Body GitlabUser user, Callback callback);


    /**
     * User modification
     * <p/>
     * Modifies an existing user. Only administrators can change attributes of a user.
     * <p/>
     * PUT /users/:id
     */
    @PUT("/users/{id}")
    public void modifyUser(@Body GitlabUser user,
                           @Path("id") int userId,
                           Callback callback);

    /**
     * User deletion
     * <p/>
     * Deletes a user. Available only for administrators.
     * This is an idempotent function, calling this function for
     * a non-existent user id still returns a status code 200 OK.
     * The JSON response differs if the user was actually deleted or not.
     * In the former the user is returned and in the latter not.
     * <p/>
     * DELETE /users/:id
     *
     * @param userId (required) - The ID of the user
     */
    @DELETE("/users/{id}")
    public void deleteUser(@Path("id") int userId, Callback callback);

    /**
     * Current user
     * <p/>
     * Gets currently authenticated user.
     */
    @GET("/user")
    public void currentUser(Callback callback);

    /**
     * List SSH keys
     * <p/>
     * Get a list of currently authenticated user's SSH keys.
     */
    @GET("/user/keys")
    public void listSshKeys(Callback<List<GitlabSshKey>> callback);

    /**
     * List SSH keys for user
     * <p/>
     * Get a list of a specified user's SSH keys. Available only for admin
     * <p/>
     * GET /users/:uid/keys
     *
     * @param userId (required) - id of specified user
     */
    @GET("users/{userId}/keys")
    public void listSshKeysForUser(@Path("userId") int userId,
                                   Callback<List<GitlabSshKey>> callback);

    /**
     * Single SSH key
     * <p/>
     * Get a single key.
     *
     * @param keyId (required) - The ID of an SSH key
     */
    @GET("/user/keys/{keyId}")
    public void singleSshKey(@Path("keyId") int keyId, Callback<GitlabSshKey> callback);

    /**
     * Add SSH key
     * <p/>
     * Creates a new key owned by the currently authenticated user.
     * <p/>
     * POST /user/keys
     *
     * @param title (required) - new SSH Key's title
     * @param key   (required) - new SSH key
     */
    public void addSshKey(String title, String key, Callback callback);

    /**
     * Add SSH key for user
     * <p/>
     * Create new key owned by specified user. Available only for admin
     * <p/>
     * Will return created key with status 201 Created on success, or 404 Not found on fail.
     * <p/>
     * POST /users/:id/keys
     *
     * @param title  (required) - new SSH Key's title
     * @param key    (required) - new SSH key
     * @param userId (required) - id of specified user
     */
    @POST("/users/{id}/keys")
    public void addSshKeyForUser(@Field("title") String title,
                                 @Field("key") String key,
                                 @Field("id") int userId,
                                 Callback callback);

    /**
     * Delete SSH key for current user
     * <p/>
     * Deletes key owned by currently authenticated user.
     * This is an idempotent function and calling it on a key that is already deleted or
     * not available results in 200 OK.
     * <p/>
     * DELETE /user/keys/:id
     *
     * @param keyId (required) - SSH key ID
     */
    @DELETE("/user/keys/{id}")
    public void deleteKeyForCurrentUser(@Path("id") int keyId, Callback callback);

    /**
     * Delete SSH key for given user
     * <p/>
     * Deletes key owned by a specified user. Available only for admin.
     * <p/>
     * DELETE /users/:uid/keys/:id
     * <p/>
     * Will return 200 OK on success, or 404 Not found if either user or key cannot be found.
     */
    @DELETE("/users/{userId}/keys/{keyId}")
    public void deleteKeyForGivenUser(@Path("userId") int userId,
                                      @Path("keyId") int keyId,
                                      Callback callback);

    /**
     * Login to get private token
     * <p/>
     * POST /session
     * <p/>
     * You can login with both GitLab and LDAP credentials now
     *
     * @param login    (required) - The login of user
     * @param email    (required if login missing) - The email of user
     * @param password (required) - Valid password
     */
    @POST("/session")
    public void session(@Field("login") String login,
                        @Field("email") String email,
                        @Field("password") String password,
                        Callback<GitlabSession> callback);


    /**
     * List projects
     * <p/>
     * Get a list of projects accessible by the authenticated user.
     * <p/>
     * GET /projects
     */
    @GET("/projects")
    public void listProjects();

    /**
     * List projects
     * <p/>
     * Get a list of projects accessible by the authenticated user.
     * <p/>
     * GET /projects
     *
     * @param archived (optional) - if passed, limit by archived status
     */
    public void listProjects(boolean archived, Callback callback);


    /**
     * List owned projects
     * <p/>
     * Get a list of projects which are owned by the authenticated user.
     * <p/>
     * GET /projects/owned
     */
    @GET("/projects/owned")
    public void listOwnedProjects();

    /**
     * List ALL projects
     * <p/>
     * Get a list of all GitLab projects (admin only).
     * <p/>
     * GET /projects/all
     */
    @GET("/projects/all")
    public void listAllProjects();

    /**
     * Get single project
     * <p/>
     * Get a specific project, identified by project ID or NAMESPACE/PROJECT_NAME,
     * which is owned by the authenticated user.
     * If using namespaced projects call make sure that the NAMESPACE/PROJECT_NAME
     * is URL-encoded, eg. /api/v3/projects/diaspora%2Fdiaspora (where / is represented by %2F).
     * <p/>
     * GET /projects/:id
     *
     * @param projectId (required) - The ID or NAMESPACE/PROJECT_NAME of a project
     */
    @GET("/projects/{id}")
    public void getSingleProjects(@Path("id") String projectId);

    /**
     * Get single project
     * <p/>
     * Get a specific project, identified by project ID or NAMESPACE/PROJECT_NAME,
     * which is owned by the authenticated user.
     * If using namespaced projects call make sure that the NAMESPACE/PROJECT_NAME is URL-encoded,
     * eg. /api/v3/projects/diaspora%2Fdiaspora (where / is represented by %2F).
     * <p/>
     * GET /projects/:id
     *
     * @param projectId (required) - The ID or NAMESPACE/PROJECT_NAME of a project
     */
    public void getSingleProjects(@Path("id") int projectId);

    /**
     * Get project events
     * <p/>
     * Get the events for the specified project. Sorted from newest to latest
     * <p/>
     * GET /projects/:id/events
     *
     * @param id (required) - The ID or NAMESPACE/PROJECT_NAME of a project
     */
    @GET("projects/{id}/events")
    public void getProjectEvents(String id);


    /**
     * <h2>Create project</h2>
     * <p/>
     * Creates a new project owned by the authenticated user.
     * <p/>
     * <code>POST /projects</code>
     * Parameters:
     * <p/>
     *
     * @param name                (required) - new project name
     * @param namespaceId         (optional) - namespace for the new project (defaults to user)
     * @param description         (optional) - short project description
     * @param issuesEnabled       (optional)
     * @param mergeRequestEnabled (optional)
     * @param wikiEnabled         (optional)
     * @param snippetsEnabled     (optional)
     * @param isPublic            (optional) - if true same as setting visibility_level = 20
     * @param visibilityLevel     (optional)
     * @param importUrl           (optional)
     */
    public void createProject(
            @Field("name") String name,
            @Field("namespace_id") String namespaceId,
            @Field("description") String description,
            @Field("issues_enabled") boolean issuesEnabled,
            @Field("merge_request_enabled") boolean mergeRequestEnabled,
            @Field("wiki_enabled") boolean wikiEnabled,
            @Field("snippets_enabled") boolean snippetsEnabled,
            @Field("public") boolean isPublic,
            @Field("visibility_level") int visibilityLevel,
            @Field("import_url") String importUrl);

    /**
     * Create project for user
     * <p/>
     * Creates a new project owned by the specified user. Available only for admins.
     * <p/>
     * POST /projects/user/:user_id
     * Parameters:
     * <p/>
     *
     * @param userId              (required) - user_id of owner
     * @param name                (required) - new project name
     * @param namespaceId         (optional) - namespace for the new project (defaults to user)
     * @param description         (optional) - short project description
     * @param issuesEnabled       (optional)
     * @param mergeRequestEnabled (optional)
     * @param wikiEnabled         (optional)
     * @param snippetsEnabled     (optional)
     * @param isPublic            (optional) - if true same as setting visibility_level = 20
     * @param visibilityLevel     (optional)
     * @param importUrl           (optional)
     */
    @POST("/projects/user/{userId}")
    public void createProjectForUser(
            @Path("userId") String userId,
            @Field("name") String name,
            @Field("namespace_id") String namespaceId,
            @Field("description") String description,
            @Field("issues_enabled") boolean issuesEnabled,
            @Field("merge_request_enabled") boolean mergeRequestEnabled,
            @Field("wiki_enabled") boolean wikiEnabled,
            @Field("snippets_enabled") boolean snippetsEnabled,
            @Field("public") boolean isPublic,
            @Field("visibility_level") int visibilityLevel,
            @Field("import_url") String importUrl);


    /**
     * Remove project
     * <p/>
     * Removes a project including all associated resources (issues, merge requests etc.)
     * <p/>
     * DELETE /projects/:id
     *
     * @param id (required) - The ID of a project
     */
    @DELETE("/projects/{id}")
    public void deleteProject(@Path("id") int id);

    /**
     * List project team members
     * <p/>
     * Get a list of a project's team members.
     * <p/>
     * GET /projects/:id/members
     *
     * @param id    (required) - The ID or NAMESPACE/PROJECT_NAME of a project
     * @param query (optional) - Query string to search for members
     */
    @GET("/projects/{id}/members")
    public void listTeamMembers(@Path("id") String id, @Query("query") String query);

    /**
     * Get project team member
     * <p/>
     * Gets a project team member.
     * <p/>
     * GET /projects/:id/members/:user_id
     * <p/>
     *
     * @param id     (required) - The ID or NAMESPACE/PROJECT_NAME of a project
     * @param userId (required) - The ID of a user
     */
    @GET("/projects/{id}/members/{userId}")
    public void getProjectTeamMember(@Path("id") String id, @Path("userId") int userId);

    /**
     * Add project team member
     * <p/>
     * Adds a user to a project team. This is an idempotent method and can be called
     * multiple times with the same parameters. Adding team membership to a user
     * that is already a member does not affect the existing membership.
     * <p/>
     * POST /projects/:id/members
     *
     * @param id           (required) - The ID or NAMESPACE/PROJECT_NAME of a project
     * @param user_id      (required) - The ID of a user to add
     * @param access_level (required) - Project access level
     */
    @POST("/projects/{id}/members")
    public void addProjectTeamMember(@Path("id") String id,
                                     @Field("user_id") int user_id,
                                     @Field("access_level") int access_level);

    /**
     * Edit project team member
     * <p/>
     * Updates a project team member to a specified access level.
     * <p/>
     * PUT /projects/:id/members/:user_id
     * Parameters:
     *
     * @param id           (required) - The ID or NAMESPACE/PROJECT_NAME of a project
     * @param user_id      (required) - The ID of a team member
     * @param access_level (required) - Project access level
     */
    @PUT("/projects/{id}/members/{user_id}")
    public void editProjectTeamMember(@Path("id") String id,
                                      @Path("user_id") int user_id,
                                      @Field("access_level") int access_level);

    /**
     * Remove project team member
     * <p/>
     * Removes a user from a project team.
     * <p/>
     * DELETE /projects/:id/members/:user_id
     * Parameters:
     * <p/>
     * id (required) - The ID or NAMESPACE/PROJECT_NAME of a project
     * user_id (required) - The ID of a team member
     * This method is idempotent and can be called multiple times with the same parameters.
     * Revoking team membership for a user who is not currently a team member
     * is considered success. Please note that the returned JSON currently differs slightly.
     * Thus you should not rely on the returned JSON structure.
     */
    @DELETE("projects/{id}/members/{user_id}")
    public void removeProjectTeamMember(@Path("id") String id, @Path("user_id") int user_id);

    /**
     * List project hooks
     * <p/>
     * Get a list of project hooks.
     * <p/>
     * GET /projects/:id/hooks
     *
     * @param id (required) - The ID or NAMESPACE/PROJECT_NAME of a project
     */
    @GET("/projects/{id}/hooks")
    public void listProjectHooks(@Path("id") String id);

    /**
     * Get project hook
     * <p/>
     * Get a specific hook for a project.
     * <p/>
     * GET /projects/:id/hooks/:hook_id
     * Parameters:
     * <p/>
     *
     * @param id      (required) - The ID or NAMESPACE/PROJECT_NAME of a project
     * @param hook_id (required) - The ID of a project hook
     */
    @GET("/projects/{id}/hooks/{hook_id}")
    public void getProjectHook(String id, int hook_id);

    /**
     * Add project hook
     * <p/>
     * Adds a hook to a specified project.
     * <p/>
     * POST /projects/:id/hooks
     * <p/>
     *
     * @param id                    (required) - The ID or NAMESPACE/PROJECT_NAME of a project
     * @param url                   (required) - The hook URL
     * @param push_events           - Trigger hook on push events
     * @param issues_events         - Trigger hook on issues events
     * @param merge_requests_events - Trigger hook on merge_requests events
     */
    @POST("/projects/{id}/hooks")
    public void addProjectHook(
            @Path("id") String id,
            @Field("url") String url,
            @Field("push_events") boolean push_events,
            @Field("issues_events") boolean issues_events,
            @Field("merge_request_events") boolean merge_requests_events);

    /**
     * Edit project hook
     * <p/>
     * Edits a hook for a specified project.
     * <p/>
     * PUT /projects/:id/hooks/:hook_id
     * Parameters:
     * <p/>
     * id (required) - The ID or NAMESPACE/PROJECT_NAME of a project
     * hook_id (required) - The ID of a project hook
     * url (required) - The hook URL
     * push_events - Trigger hook on push events
     * issues_events - Trigger hook on issues events
     * merge_requests_events - Trigger hook on merge_requests events
     */
    @PUT("/projects/{id}/hooks/{hook_id}")
    public void editProjectHook(
            @Path("id") String id,
            @Path("hook_id") int hook_id,
            @Field("url") String url,
            @Field("push_events") boolean push_events,
            @Field("issues_events") boolean issues_events,
            @Field("merge_requests_events") boolean merge_requests_events);

    /**
     * Delete project hook
     * <p/>
     * Removes a hook from a project.
     * This is an idempotent method and can be called multiple times.
     * Either the hook is available or not.
     * <p/>
     * DELETE /projects/:id/hooks/:hook_id
     * <p/>
     * Note the JSON response differs if the hook is available or not.
     * If the project hook is available before it is returned in the JSON response
     * or an empty response is returned.
     *
     * @param id      (required) - The ID or NAMESPACE/PROJECT_NAME of a project
     * @param hook_id (required) - The ID of hook to delete
     */
    @DELETE("/projects/{id}/hooks/{hook_id}")
    public void deleteProjectHook(@Path("id") String id, @Path("hook_id") int hook_id);

    /**
     * List branches
     * <p/>
     * Lists all branches of a project.
     * <p/>
     * GET /projects/:id/repository/branches
     * Parameters:
     * <p/>
     *
     * @param id (required) - The ID or NAMESPACE/PROJECT_NAME of a project
     */
    @GET("/projects/{id}/repository/branches")
    public void listBranches(@Path("id") String id);

    /**
     * List single branch
     * <p/>
     * Lists a specific branch of a project.
     * <p/>
     * GET /projects/:id/repository/branches/:branch
     * Parameters:
     * <p/>
     *
     * @param id     (required) - The ID or NAMESPACE/PROJECT_NAME of a project
     * @param branch (required) - The name of the branch.
     */
    @GET("/projects/{id}/repository/branches/{branch}")
    public void listSingleBranch(@Path("id") String id, @Path("branch") String branch);

    /**
     * Protect single branch
     * <p/>
     * Protects a single branch of a project.
     * <p/>
     * PUT /projects/:id/repository/branches/:branch/protect
     * Parameters:
     * <p/>
     *
     * @param id     (required) - The ID or NAMESPACE/PROJECT_NAME of a project
     * @param branch (required) - The name of the branch.
     */
    @PUT("projects/{id}/repository/branches/{branch}/protect")
    public void protectSingleBranch(@Path("id") String id, @Path("branch") String branch);

    /**
     * Unprotect single branch
     * <p/>
     * Unprotect a single branch of a project.
     * <p/>
     * PUT /projects/:id/repository/branches/:branch/unprotect
     * Parameters:
     * <p/>
     * id (required) - The ID or NAMESPACE/PROJECT_NAME of a project
     * branch (required) - The name of the branch.
     */
    @PUT("projects/{id}/repository/branches/{branch}/unprotect")
    public void unprotectSingleBranch(@Path("id") String id, @Path("branch") String branch);

    /*
    Admin fork relation
    Allows modification of the forked relationship between existing projects. Available only for admins.
    */

    /**
     * Create a forked from/to relation between existing projects.
     * <p/>
     * POST /projects/:id/fork/:forked_from_id
     * Parameters:
     * <p/>
     * id (required) - The ID of the project
     * forked_from_id: (required) - The ID of the project that was forked from
     */
    @POST("/projects/{id}/fork/{forked_from_id}")
    public void createForkRelation(@Path("id") int id, @Path("forked_from_id") int forked_from_id);

    /**
     * Delete an existing forked from relationship
     * <p/>
     * DELETE /projects/:id/fork
     * Parameter:
     * <p/>
     * @param id (required) - The ID of the project
     */
    @DELETE("/projects/{id}/fork")
    public void deleteForkRelation(@Path("id") int id);

    /**
     * Search for projects by name
     * <p/>
     * Search for projects by name which are accessible to the authenticated user.
     * <p/>
     * GET /projects/search/:query
     * Parameters:
     * <p/>
     * @param query (required) - A string contained in the project name
     * @param per_page (optional) - number of projects to return per page
     * @param page (optional) - the page to retrieve
     */
    @GET("/projects/search/{query}")
    public void searchProjectByName(@Path("query") String query,
                                    @Query("per_page") int per_page,
                                    @Query("page") int page);

}
