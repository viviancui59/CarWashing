package com.example.task;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.JsonSyntaxException;

public class AddFeedbackTask extends AsyncTask<String, Void, String> {

    Context context;
    String userJSON;

    public AddFeedbackTask(Context context){
        this.context=context;
    }
    @Override
    protected String doInBackground(String... arg0) {
        String url = "http://10.0.2.2:8080/CarWashing/AddFeedbackServlet";
        HttpClient client = new DefaultHttpClient();
        HttpPost request = new HttpPost(url);

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("content", arg0[0]));
        params.add(new BasicNameValuePair("userid", arg0[1]));

        try {

            request.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));
            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

                userJSON = EntityUtils.toString(response.getEntity());
                return userJSON;

            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
    }

}
