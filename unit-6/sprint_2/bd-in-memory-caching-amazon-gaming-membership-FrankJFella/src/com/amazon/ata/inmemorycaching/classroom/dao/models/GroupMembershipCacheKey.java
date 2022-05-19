package com.amazon.ata.inmemorycaching.classroom.dao.models;

import java.util.Objects;

// Used in the implementation of caching
// Required because we need more than one value in the cache-key (OOP requires this)

// It's unique within the cache
// cache-key is usually a POJO
//    has a ctor, getters, setters, equals(), hashCode() and maybe a toString()
//        Remember: equals() and hashCode() should only immutable values

// This class is immutable so it may be used in multi-threading environment (thread-safe)
// Since it's immutable ,the class has no setters

public final class GroupMembershipCacheKey {

    private final String userId;  // an individual
    private final String groupId; // group they belong to

    // final on a parameter means they cannot be changed by the method (immutable)
    public GroupMembershipCacheKey(final String userId, final String groupId) {
        this.userId = userId;
        this.groupId = groupId;
    }

    public String getUserId() {
        return userId;
    }

    public String getGroupId() {
        return groupId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, groupId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        GroupMembershipCacheKey request = (GroupMembershipCacheKey) obj;

        return userId.equals(request.userId) && groupId.equals(request.groupId);
    }
}
