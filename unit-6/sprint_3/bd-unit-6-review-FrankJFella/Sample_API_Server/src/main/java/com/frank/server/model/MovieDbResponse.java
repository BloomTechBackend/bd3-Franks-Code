package com.frank.server.model;

import java.util.Arrays;
import java.util.Objects;

public class MovieDbResponse {

        // hold the JSON data returned from the API call
        // Two attributes are expected in the JSON returend
        long     page;     // An attribute called page containing the page number returned
        Movie [] results;  // An attribute called results which is an array of Movies

        public MovieDbResponse() {}

        public long getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public Movie[] getResults() {
            return results;
        }

        public void setResults(Movie[] results) {
            this.results = results;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof MovieDbResponse)) return false;
            MovieDbResponse that = (MovieDbResponse) o;
            return getPage() == that.getPage() && Arrays.equals(getResults(), that.getResults());
        }
    @Override
    public int hashCode() {
        int result1 = Objects.hash(getPage());
        result1 = 31 * result1 + Arrays.hashCode(getResults());
        return result1;
    }

    @Override
    public String toString() {
        return "MovieDbResponse{" +
                "pageNumber=" + page +
                ", result=" + Arrays.toString(results) +
                '}';
    } // end of MoveDbResponse class
}

