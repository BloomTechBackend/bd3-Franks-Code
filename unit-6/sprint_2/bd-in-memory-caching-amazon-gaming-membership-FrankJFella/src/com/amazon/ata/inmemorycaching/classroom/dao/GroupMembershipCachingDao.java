package com.amazon.ata.inmemorycaching.classroom.dao;

import com.amazon.ata.inmemorycaching.classroom.dao.models.GroupMembershipCacheKey;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

// Define a LoadingCache object for the Cache
// We are using the Google Guava Framework for caching
//
// caching-key   - what we are looking for in the cache (userid and group-id)
// caching-value - the data to be returned from the request (boolean)
// define-the-cache(cache-key, cache-value)

public class GroupMembershipCachingDao {
    //                    caching-key-type      , cache-value-key
    private LoadingCache<GroupMembershipCacheKey, Boolean>    johnnyCache;   // reference to the cache

    @Inject
    public GroupMembershipCachingDao(final GroupMembershipDao delegateDao) {
        // instantiate the cache and assign to reference
        this.johnnyCache = CacheBuilder.newBuilder()
                .maximumSize(20000)  // max number of entries for the cache
                .expireAfterWrite(3, TimeUnit.HOURS)  // Evict after 3 hours since it was written to the cache
                .build(CacheLoader.from(delegateDao::isUserInGroup));  // Go build the cache with delegate method
                // delegateDao must have a method called isUserInGroup() that receives a "cache-key-object"
    }  // end of ctor

    // We need to define a method with same signature as the current Dao method so application doesn't change
    public boolean isUserInGroup(final String userId, final String groupId) {
        // Use the parameters passed to us to call the cache manager to see if the data is in the cache
        // by create a "caching-key-object" with the parameters passed in to use
        // .getUnchecked() - call cache manager  with a get that throws an unchecked exception in case of error
        // .get()          - call cache manager  with a get that throws an checked exception in case of error
        //                                       so you must either handle in try/catch or add throws to method signature

        return johnnyCache.getUnchecked(new GroupMembershipCacheKey(userId, groupId));
    }

    }
