package br.com.project.emailservice.application.Services;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CommentService {

    public List<String> getComments() throws IOException {
        List<String> comments = new ArrayList<>();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("https://jsonplaceholder.typicode.com/comments");

        try (CloseableHttpResponse response = httpClient.execute(request);
             BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {

            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            JSONArray commentsArray = new JSONArray(result.toString());
            for (int i = 0; i < commentsArray.length(); i++) {
                JSONObject commentObject = commentsArray.getJSONObject(i);
                String comment = commentObject.getString("name") + " - " + commentObject.getString("email") + ": " + commentObject.getString("body");
                comments.add(comment);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return comments;
    }
}