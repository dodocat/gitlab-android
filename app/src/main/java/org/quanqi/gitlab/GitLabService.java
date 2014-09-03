package org.quanqi.gitlab;

import org.quanqi.gitlab.models.GitlabSession;
import org.quanqi.gitlab.models.GitlabSnippet;
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
    Allows modification of the forked relationship between existing projects.
    Available only for admins.
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
     *
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
     *
     * @param query    (required) - A string contained in the project name
     * @param per_page (optional) - number of projects to return per page
     * @param page     (optional) - the page to retrieve
     */
    @GET("/projects/search/{query}")
    public void searchProjectByName(@Path("query") String query,
                                    @Query("per_page") int per_page,
                                    @Query("page") int page);

    /**
     * List snippets
     * <p/>
     * Get a list of project snippets.
     * <p/>
     * GET /projects/:id/snippets
     * Parameters:
     * <p/>
     *
     * @param id (required) - The ID of a project
     */
    @GET("/projects/{id}/snippets")
    public void listSnippets(@Path("id") int id,
                             @Query("per_page") int perPage,
                             @Query("page") int page,
                             Callback<List<GitlabSnippet>> callback);

    /**
     * Single snippet
     * <p/>
     * Get a single project snippet.
     * <p/>
     * GET /projects/:id/snippets/:snippet_id
     * Parameters:
     * <p/>
     * id (required) - The ID of a project
     * snippet_id (required) - The ID of a project's snippet
     */
    @GET("projects/{id}/snippets/{snippet_id}")
    public void getSnippet(@Path("id") int id,
                           @Path("snippet_id") int snippet_id,
                           Callback<GitlabSnippet> callback);


    /**
     * Create new snippet
     * <p/>
     * Creates a new project snippet. The user must have permission to create new snippets.
     * <p/>
     * POST /projects/:id/snippets
     * Parameters:
     * <p/>
     * id (required) - The ID of a project
     * title (required) - The title of a snippet
     * file_name (required) - The name of a snippet file
     * code (required) - The content of a snippet
     */
    @POST("/projects/{id}/snippets")
    public void createSnippet(@Path("id") int id,
                              @Field("title") String title,
                              @Field("file_name") String file_name,
                              @Field("code") String Code,
                              Callback callback);

    /**
     * Update snippet
     * <p/>
     * Updates an existing project snippet.
     * The user must have permission to change an existing snippet.
     * <p/>
     * PUT /projects/:id/snippets/:snippet_id
     * Parameters:
     * <p/>
     *
     * @param id         (required) - The ID of a project
     * @param snippet_id (required) - The ID of a project's snippet
     * @param title      (optional) - The title of a snippet
     * @param file_name  (optional) - The name of a snippet file
     * @param code       (optional) - The content of a snippet
     */
    @PUT("/projects/{id}/snippets/{snippet_id}")
    public void updateSnippet(@Path("id") int id,
                              @Path("snippet_id") int snippet_id,
                              @Field("title") String title,
                              @Field("file_name") String file_name,
                              @Field("code") String code,
                              Callback callback);

    /**
     * Delete snippet
     * <p/>
     * Deletes an existing project snippet.
     * This is an idempotent function and deleting a non-existent snippet still
     * returns a 200 OK status code.
     * <p/>
     * DELETE /projects/:id/snippets/:snippet_id
     * Parameters:
     * <p/>
     *
     * @param id         (required) - The ID of a project
     * @param snippet_id (required) - The ID of a project's snippet
     */
    @DELETE("/projects/{id}/snippets/{snippet_id}")
    public void deleteSnippet(@Path("id") int id,
                              @Path("snippet_id") int snippet_id);

    /**
     * Snippet content
     * <p/>
     * Returns the raw project snippet as plain text.
     * <p/>
     * GET /projects/:id/snippets/:snippet_id/raw
     * Parameters:
     * <p/>
     *
     * @param id         (required) - The ID of a project
     * @param snippet_id (required) - The ID of a project's snippet
     */
    @GET("/projects/{id}/snippets/{snippet_id}/raw")
    public void getSnippetContent(@Path("id") int id, @Path("snippet_id") int snippet_id);

    /**
     * List project repository tags
     * <p/>
     * Get a list of repository tags from a project, sorted by name in reverse alphabetical order.
     * <p/>
     * GET /projects/:id/repository/tags
     * Parameters:
     * <p/>
     * id (required) - The ID of a project
     */
    @GET("/projects/{id}/repository/tags")
    public void listRepoTags(@Path("id") int id);

    /**
     * Create a new tag
     * <p/>
     * Creates new tag in the repository that points to the supplied ref.
     * <p/>
     * POST /projects/:id/repository/tags
     * Parameters:
     * <p/>
     * id (required) - The ID of a project
     * tag_name (required) - The name of a tag
     * ref (required) - Create tag using commit SHA, another tag name, or branch name.
     */
    @POST("/projects/{id}/repository/tags")
    public void createTag(@Path("id") int id,
                          @Field("tag_name") String tag_name,
                          @Field("ref") String ref);

    /**
     * List repository tree
     * <p/>
     * Get a list of repository files and directories in a project.
     * <p/>
     * GET /projects/:id/repository/tree
     * Parameters:
     * <p/>
     *
     * @param id       (required) - The ID of a project
     * @param path     (optional) - The path inside repository.
     *                 Used to get contend of subdirectories
     * @param ref_name (optional) - The name of a repository branch or tag or
     *                 if not given the default branch
     */
    @GET("@/projects/{id}/repository/tree")
    public void listRepository(int id, String path, String ref_name);

    /**
     * Raw file content
     * <p/>
     * Get the raw file contents for a file by commit SHA and path.
     * <p/>
     * GET /projects/:id/repository/blobs/:sha
     * Parameters:
     * <p/>
     *
     * @param id       (required) - The ID of a project
     * @param sha      (required) - The commit or branch name
     * @param filepath (required) - The path the file
     */
    @GET("/projects/{id}/repository/blobs/{sha}")
    public void getRawFileContent(@Path("id") int id,
                                  @Path("sha") String sha,
                                  @Query("filepath") String filepath);

    /**
     * Raw blob content
     * <p/>
     * Get the raw file contents for a blob by blob SHA.
     * <p/>
     * GET /projects/:id/repository/raw_blobs/:sha
     * Parameters:
     * <p/>
     *
     * @param id  (required) - The ID of a project
     * @param sha (required) - The blob SHA
     */
    @GET("GET /projects/{id}/repository/raw_blobs/{sha}")
    public void getRawBlobContent(@Path("id") int id, @Path("sha") String sha);

    /**
     * Get file archive
     * <p/>
     * Get an archive of the repository
     * <p/>
     * GET /projects/:id/repository/archive
     * Parameters:
     * <p/>
     * id (required) - The ID of a project
     * sha (optional) - The commit SHA to download defaults to the tip of the default branch
     */
    @GET("/projects/{id}/repository/archive")
    public void getFileArchive(@Path("id") int id, @Query("sha") String sha);

    /**
     * Compare branches, tags or commits
     * <p/>
     * GET /projects/:id/repository/compare
     * Parameters:
     * <p/>
     *
     * @param id   (required) - The ID of a project
     * @param from (required) - the commit SHA or branch name
     * @param to   (required) - the commit SHA or branch name
     *             GET /projects/:id/repository/compare?from=master&to=feature
     *             Response:
     *             <p/>
     *             {
     *             "commit": {
     *             "id": "12d65c8dd2b2676fa3ac47d955accc085a37a9c1",
     *             "short_id": "12d65c8dd2b",
     *             "title": "JS fix",
     *             "author_name": "Dmitriy Zaporozhets",
     *             "author_email": "dmitriy.zaporozhets@gmail.com",
     *             "created_at": "2014-02-27T10:27:00+02:00"
     *             },
     *             "commits": [{
     *             "id": "12d65c8dd2b2676fa3ac47d955accc085a37a9c1",
     *             "short_id": "12d65c8dd2b",
     *             "title": "JS fix",
     *             "author_name": "Dmitriy Zaporozhets",
     *             "author_email": "dmitriy.zaporozhets@gmail.com",
     *             "created_at": "2014-02-27T10:27:00+02:00"
     *             }],
     *             "diffs": [{
     *             "old_path": "files/js/application.js",
     *             "new_path": "files/js/application.js",
     *             "a_mode": null,
     *             "b_mode": "100644",
     *             "diff": "--- a/files/js/application.js\n+++ b/files/js/application.js\n@@ -24,8 +24,10 @@\n //= require g.raphael-min\n //= require g.bar-min\n //= require branch-graph\n-//= require highlightjs.min\n-//= require ace/ace\n //= require_tree .\n //= require d3\n //= require underscore\n+\n+function fix() { \n+  alert(\"Fixed\")\n+}",
     *             "new_file": false,
     *             "renamed_file": false,
     *             "deleted_file": false
     *             }],
     *             "compare_timeout": false,
     *             "compare_same_ref": false
     *             }
     */

    @GET("/projects/{id}/repository/compare")
    public void compare(@Path("id") int id,
                        @Query("from") String from,
                        @Query("to") String to);

    /**
     * Contributors
     * <p/>
     * Get repository contributors list
     * <p/>
     * GET /projects/:id/repository/contributors
     * Parameters:
     * <p/>
     *
     * @param id (required) - The ID of a project
     */
    @GET("/projects/{id}/repository/contributors")
    public void getContributors(@Path("id") int id);

    /**
     * Get file from repository
     * <p/>
     * Allows you to receive information about file in repository like name, size, content. Note that file content is Base64 encoded.
     * <p/>
     * GET /projects/:id/repository/files
     * Example response:
     * <p/>
     * {
     * "file_name": "key.rb",
     * "file_path": "app/models/key.rb",
     * "size": 1476,
     * "encoding": "base64",
     * "content": "IyA9PSBTY2hlbWEgSW5mb3...",
     * "ref": "master",
     * "blob_id": "79f7bbd25901e8334750839545a9bd021f0e4c83",
     * "commit_id": "d5a3ff139356ce33e37e73add446f16869741b50"
     * }
     * Parameters:
     * <p/>
     * file_path (required) - Full path to new file. Ex. lib/class.rb
     * ref (required) - The name of branch, tag or commit
     */
    @GET("/projects/{id}/repository/files")
    public void getFile(@Path("id") int id,
                        @Query("file_path") String file_path,
                        @Query("ref") String ref);

    /**
     * Create new file in repository
     * <p/>
     * POST /projects/:id/repository/files
     * Example response:
     * <p/>
     * {
     * "file_name": "app/project.rb",
     * "branch_name": "master"
     * }
     * Parameters:
     * <p/>
     * file_path (required) - Full path to new file. Ex. lib/class.rb
     * branch_name (required) - The name of branch
     * encoding (optional) - 'text' or 'base64'. Text is default.
     * content (required) - File content
     * commit_message (required) - Commit message
     */
    @POST("/projects/:id/repository/files")
    public void createFile(@Path("id") int id,
                           @Field("file_name") String file_name,
                           @Field("branch_name") String branch_name);

    /**
     * Update existing file in repository
     * <p/>
     * PUT /projects/:id/repository/files
     * Example response:
     * <p/>
     * {
     * "file_name": "app/project.rb",
     * "branch_name": "master"
     * }
     * Parameters:
     * <p/>
     * file_path (required) - Full path to file. Ex. lib/class.rb
     * branch_name (required) - The name of branch
     * encoding (optional) - 'text' or 'base64'. Text is default.
     * content (required) - New file content
     * commit_message (required) - Commit message
     * If the commit fails for any reason we return a 400 error with a non-specific error message. Possible causes for a failed commit include:
     * <p/>
     * the file_path contained /../ (attempted directory traversal);
     * the new file contents were identical to the current file contents, i.e. the user tried to make an empty commit;
     * the branch was updated by a Git push while the file edit was in progress.
     * Currently gitlab-shell has a boolean return code, preventing GitLab from specifying the error.
     */
    @PUT("/projects/{id}/repository/files")
    public void updateFile(
            @Path("id") int id,
            @Field("file_name") String file_name,
            @Field("branch_name") String branch_name,
            @Field("content") String content,
            @Field("encoding") String encoding,
            @Field("commit_message") String commit_message);

    /**
     * Delete existing file in repository
     * <p/>
     * DELETE /projects/:id/repository/files
     * Example response:
     * <p/>
     * {
     * "file_name": "app/project.rb",
     * "branch_name": "master"
     * }
     * Parameters:
     * <p/>
     * file_path (required) - Full path to file. Ex. lib/class.rb
     * branch_name (required) - The name of branch
     * commit_message (required) - Commit message
     */
    @DELETE("/projects/{id}/repository/files")
    public void deleteFile(
            @Path("id") int id,
            @Field("file_path") String file_path,
            @Field("commit_message") String commit_message);

    /**
     * List repository commits
     * <p/>
     * Get a list of repository commits in a project.
     * <p/>
     * GET /projects/:id/repository/commits
     * Parameters:
     * <p/>
     * id (required) - The ID of a project
     * ref_name (optional) - The name of a repository branch or tag or if not given the default branch
     * [
     * {
     * "id": "ed899a2f4b50b4370feeea94676502b42383c746",
     * "short_id": "ed899a2f4b5",
     * "title": "Replace sanitize with escape once",
     * "author_name": "Dmitriy Zaporozhets",
     * "author_email": "dzaporozhets@sphereconsultinginc.com",
     * "created_at": "2012-09-20T11:50:22+03:00",
     * "message": "Replace sanitize with escape once"
     * },
     * {
     * "id": "6104942438c14ec7bd21c6cd5bd995272b3faff6",
     * "short_id": "6104942438c",
     * "title": "Sanitize for network graph",
     * "author_name": "randx",
     * "author_email": "dmitriy.zaporozhets@gmail.com",
     * "created_at": "2012-09-20T09:06:12+03:00",
     * "message": "Sanitize for network graph"
     * }
     * ]
     */
    @GET("/projects/{id}/repository/commits")
    public void listCommits(@Path("id") int id, @Query("ref") String ref);

    /**
     * Get a single commit
     * <p/>
     * Get a specific commit identified by the commit hash or name of a branch or tag.
     * <p/>
     * GET /projects/:id/repository/commits/:sha
     * Parameters:
     * <p/>
     * id (required) - The ID of a project
     * sha (required) - The commit hash or name of a repository branch or tag
     * {
     */
    @GET("/projects/{id}/repository/commits/{sha}")
    public void getCommit(@Path("id") int id, @Path("sha") String sha);

    /**
     * Get the diff of a commit
     * <p/>
     * Get the diff of a commit in a project.
     * <p/>
     * GET /projects/:id/repository/commits/:sha/diff
     * Parameters:
     * <p/>
     * id (required) - The ID of a project
     * sha (required) - The name of a repository branch or tag or if not given the default branch
     */
    @GET("/projects/{id}/repository/commits/{sha}/diff")
    public void getDiffOfCommit(@Path("id") int id, @Path("sha") String sha);

    /**
     * List repository branches
     * <p/>
     * Get a list of repository branches from a project, sorted by name alphabetically.
     * <p/>
     * GET /projects/:id/repository/branches
     * Parameters:
     * <p/>
     * id (required) - The ID of a project
     */
    @GET("/projects/{id}/repository/branches")
    public void listBranches(@Path("id") int id);


    /**
     * Get single repository branch
     * <p/>
     * Get a single project repository branch.
     * <p/>
     * GET /projects/:id/repository/branches/:branch
     * Parameters:
     * <p/>
     * id (required) - The ID of a project
     * branch (required) - The name of the branch
     *
     * @param id
     * @param branch
     */
    @GET("/projects/{id}/repository/branches/{branch}")
    public void getBranch(@Path("id") int id, @Path("branch") String branch);

    /**
     * Protect repository branch
     * <p/>
     * Protects a single project repository branch. This is an idempotent function, protecting an already protected repository branch still returns a 200 OK status code.
     * <p/>
     * PUT /projects/:id/repository/branches/:branch/protect
     * Parameters:
     * <p/>
     * id (required) - The ID of a project
     * branch (required) - The name of the branch
     */
    @PUT("/projects/{id}/repository/branches/{branch}/protect")
    public void protectBranch(@Path("id") int id, @Path("branch") String branch);

    /**
     * Unprotect repository branch
     * <p/>
     * Unprotects a single project repository branch. This is an idempotent function, unprotecting an already unprotected repository branch still returns a 200 OK status code.
     * <p/>
     * PUT /projects/:id/repository/branches/:branch/unprotect
     * Parameters:
     * <p/>
     * id (required) - The ID of a project
     * branch (required) - The name of the branch
     */
    @PUT("projects/{id}/repository/branches/{branch}/unprotect")
    public void unprotectBranch(@Path("id") int id, @Path("branch") String branch);


    /**
     * Create repository branch
     * <p/>
     * POST /projects/:id/repository/branches
     * Parameters:
     * <p/>
     * id (required) - The ID of a project
     * branch_name (required) - The name of the branch
     * ref (required) - Create branch from commit SHA or existing branch
     */
    @POST("/projects/:id/repository/branches")
    public void createBranch(@Path("id") int id,
                             @Field("branch_name") String branch_name,
                             @Field("ref") String ref);

    /**
     * Delete repository branch
     * <p/>
     * DELETE /projects/:id/repository/branches/:branch
     * Parameters:
     * <p/>
     * id (required) - The ID of a project
     * branch (required) - The name of the branch
     * It return 200 if succeed or 405 if failed with error message explaining reason.
     */
    @DELETE("/projects/{id}/repository/branches/{branch}")
    public void deleteBranch(@Path("id") int id, @Path("branch") String branch);
}
