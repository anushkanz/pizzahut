package com.weihua.services;

import java.util.List;
import com.weihua.model.Users;

public interface UserService {

    /**
     * userLogin
     */
    public Users userLogin(String loginName, String loginPwd);

    /**
     * Get all the Users
     */
    public List<Users> browseUser();

    /**
     * Load User
     */
    public Users loadUser(int userid);

    /**
     * Delete User
     */
    public boolean delUser(int userid);

    /**
     * Add new User
     */
    public boolean addUser(Users user);

    /**
     * Update User
     * @param user
     * @return 
     */
    public boolean updateUser(Users user);

    /**
     * Search User by name - fuzzy query (2010.6.9)*
     */
    public List<Users> searchUserByName(String userName);

    /**
     * Retrieve Users by user Role (2010.6.9) *
     */
    public List<Users> retrieveUserByRole(int adminType);
}
