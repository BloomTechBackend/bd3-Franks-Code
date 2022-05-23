package com.amazon.ata.introthreads.classroom;

import com.google.common.collect.BoundType;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;    // Access the Google newConcurrentMap() thread safe Map
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A class to pre-compute hashes for all common passwords to speed up cracking the hacked database.
 *
 * Passwords are downloaded from https://github.com/danielmiessler/SecLists/tree/master/Passwords/Common-Credentials
 */
public class PasswordHasher {
    // should create the file in your workspace directory
    private static final String PASSWORDS_AND_HASHES_FILE = "./passwordsAndHashesOutput.csv";
    // a "salt" is a value used to make the hashed version of data harder to dehash
    // Normally the is a very long (64-128-512-1024) character String with random characters
    private static final String DISCOVERED_SALT = "salt";


    /**
     * Generates hashes for all of the given passwords.
     *
     * @param passwords List of passwords to hash
     * @return map of password to hash
     * @throws InterruptedException
     */
    public static Map<String, String> generateAllHashes(List<String> passwords) throws InterruptedException {
        // added final to the Map holding teh hashing results for concurrency (thread-safe)
        final Map<String, String> passwordToHashes = Maps.newConcurrentMap();// Hold the result hashing
                                                                             // Using a Google newConcurrentMap class
                                                                             //       for concurrency (thread)

        // Split the list of passwords into 4 sub-lists using Google Guava partition() method
        // returns a List of List of Strings =       partition(original-array, #-elems-in-each-sublist)
        List<List<String>> passwordSublists  = Lists.partition(passwords     , passwords.size() / 4);

        // Since a BatchPasswordHasher is on a Thread it will be destroy when the Thread completes
        // we don't lose teh Map of Hashed passwords eachone has, so we need to save each one
        List<BatchPasswordHasher> batchHashers = new ArrayList<>();  // hold the BatchPasswordHashers created

        // List of Threads we start so we can wait for them all to complete using the waitForThreadsToComplete() method below
        List<Thread> theThreads = new ArrayList<>();

        // Process each list through the BatchPasswordHasher
        // Loop through the List of sublists and give each to to a BatchPasswordHasher on its own Thread

        // Code was replaced by the for-loop to use the sublists rather the original single list
        // BatchPasswordHasher batchHasher = new BatchPasswordHasher(passwords, DISCOVERED_SALT);
        // batchHasher.hashPasswords();
        for(int i=0; i < passwordSublists.size(); i++) {
            // Instantiate a BatchPasswordHasher to process the current sublist
            BatchPasswordHasher theHasher = new BatchPasswordHasher(passwordSublists.get(i), DISCOVERED_SALT);
            batchHashers.add(theHasher);  // Save the BatchPasswordHasher so we don't lose it when Thread ends
            // Instantiate a Thread for the BatchPasswordHatcher
            Thread aThread = new Thread(theHasher);
            theThreads.add(aThread);  // Remember the Thread so we can wait for it to complete
            // Start the Thread
            aThread.start();          // this code continues to run once the Thread id started
        }  // end of loop to define and start thread

        // All Threads are started and running - we don't know when they complete
        // We need to wait for all Threads to complete before we can start copy of results from BatchPasswordHashers
        waitForThreadsToComplete(theThreads);

        // Copy the hashed password map into the map to hold results to be returned
        // Replaced to handle multiple BatchPasswordHashers we have to process a sublist
        // passwordToHashes.putAll(batchHasher.getPasswordToHashes());  // Use the one BatchPasswordHasher

        // Combine each hashed password Map into the result map to be returned
        // Loop through the List of BatchPasswordHasher to add there results to the result Map
        for(BatchPasswordHasher aHasher : batchHashers) {
            passwordToHashes.putAll(aHasher.getPasswordToHashes());  // putAll() copies all entries from one Map to another
        }

        return passwordToHashes;
    }

    /**
     * Makes the thread calling this method wait until passed in threads are done executing before proceeding.
     *
     * @param threads to wait on
     * @throws InterruptedException
     */
    public static void waitForThreadsToComplete(List<Thread> threads) throws InterruptedException {
        for (Thread thread : threads) { // Loop through a List of Threads making sure we don't continue until each on completes
            thread.join();              // .join() wait for the Thread to complete
        }
    }

    /**
     * Writes pairs of password and its hash to a file.
     */
    static void writePasswordsAndHashes(Map<String, String> passwordToHashes) {
        File file = new File(PASSWORDS_AND_HASHES_FILE);
        try (
            BufferedWriter writer = Files.newBufferedWriter(file.toPath());
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)
        ) {
            for (Map.Entry<String, String> passwordToHash : passwordToHashes.entrySet()) {
                final String password = passwordToHash.getKey();
                final String hash = passwordToHash.getValue();

                csvPrinter.printRecord(password, hash);
            }
            System.out.println("Wrote output of batch hashing to " + file.getAbsolutePath());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
