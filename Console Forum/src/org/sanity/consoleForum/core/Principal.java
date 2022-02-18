package org.sanity.consoleForum.core;

import org.sanity.consoleForum.models.User;

public interface Principal
{
    User getUser();

    void signIn(User user);

    void signOut();
}
