package com.amazon.ata.introthreads.classroom;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class to hash a batch of passwords in a separate thread.
 */
// Implement the Runnable interface to allow this process to be run concurrently on multiple threads
//
//  1. Implement the Runnable interface for the class
//  2. Provide the run() method required by the Runnable interface

// Note: final on member variables to make them immutable as required for concurrency
//       (concurrency means running the same process in parallel (at the same time)
public class BatchPasswordHasher  implements Runnable {

    private final List<String> passwords;               // passwords to be hashed
    private final Map<String, String> passwordToHashes; // Hold the hashed passwords
    private final String salt;                          // used in hashing passords

    // Constructor receives a list of passwords to be hashed an the salt to use when hashing them
    public BatchPasswordHasher(List<String> passwords, String salt) {
        this.passwords = passwords;
        this.salt = salt;
        passwordToHashes = new HashMap<>();
    }

    // Provide the run() method required by the Runnable interface
    // run() is the method automatically called when the Thread is started
    // Think of run() as the main process for this application
    public void run() {
        hashPasswords(); // call this method to hash the list of passwords it is passed
    }

    /**
     *  Hashes all of the passwords, and stores the hashes in the passwordToHashes Map.
     */
    public void hashPasswords() {
        try {
            for (String password : passwords) {
                final String hash = PasswordUtil.hash(password, salt);
                passwordToHashes.put(password, hash);
            }
            System.out.println(String.format("Completed hashing batch of %d passwords.", passwords.size()));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Returns a map where the key is a plain text password and the key is the hashed version of the plaintext password
     * and the class' salt value.
     *
     * @return passwordToHashes - a map of passwords to their hash value.
     */
    public Map<String, String> getPasswordToHashes() {
        return passwordToHashes;
    }
}
