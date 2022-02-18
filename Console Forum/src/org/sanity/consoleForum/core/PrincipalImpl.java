package org.sanity.consoleForum.core;

import org.sanity.consoleForum.models.User;

public class PrincipalImpl implements Principal
{
    private User user;

    @Override
    public User getUser() {
        return this.user;
    }

    @Override
    public void signIn(User user)
    {
        this.user = user;
    }

    @Override
    public void signOut()
    {
        this.user = null;
    }
}
