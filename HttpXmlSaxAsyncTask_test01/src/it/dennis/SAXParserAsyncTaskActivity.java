package it.dennis;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
 
public class SAXParserAsyncTaskActivity extends Activity implements
        OnClickListener, OnItemClickListener {
    Button button;
    ListView listView;
    List<Laptop> laptops;
    CustomListViewAdapter listViewAdapter;
 
//    static final String URL = "http://10.0.2.2:8080/laptops.xml";
  static final String URL = "http://localhost/laptops.xml";
//  static final String URL = "http://www.dennis.it/joomla/images/beneficiariquotauma.xml";
 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
 
        findViewsById();
        button.setOnClickListener(this);
        listView.setOnItemClickListener(this);
    }
 
    private void findViewsById() {
        button = (Button) findViewById(R.id.button);
        listView = (ListView) findViewById(R.id.laptopList);
    }
 
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
        long id) {
    }
 
    @Override
    public void onClick(View view) {
        GetXMLTask task = new GetXMLTask(this);
        task.execute(new String[] { URL });
    }
 
    //private inner class extending AsyncTask
    private class GetXMLTask extends AsyncTask<String, Void, List<Laptop>> {
        private Activity context;
        public GetXMLTask(Activity context) {
            this.context = context;
        }
 
        @Override
        protected void onPostExecute(List<Laptop> laptops) {
            listViewAdapter = new CustomListViewAdapter(context, laptops);
            listView.setAdapter(listViewAdapter);
        }
 
        /* uses HttpURLConnection to make Http request from Android to download
         the XML file */
        private String getXmlFromUrl(String urlString) {
            StringBuffer output = new StringBuffer("");
            try {
                InputStream stream = null;
                URL url = new URL(urlString);
                URLConnection connection = url.openConnection();
 
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                httpConnection.setRequestMethod("GET");
                httpConnection.connect();
 
                if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    stream = httpConnection.getInputStream();
 
                    BufferedReader buffer = new BufferedReader(
                            new InputStreamReader(stream));
                    String s = "";
                    while ((s = buffer.readLine()) != null)
                        output.append(s);
                }
 
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return output.toString();
 
            /* ---Using Apache DefaultHttpClient for applications targeting
             Froyo and previous versions --- */
            /*String xml = null;
 
            try {
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(url);
 
                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                xml = EntityUtils.toString(httpEntity);
 
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return xml;*/
        } 
 
        @Override
        protected List<Laptop> doInBackground(String... urls) {
            List<Laptop> laptops = null;
            String xml = null;
            for (String url : urls) {
                xml = getXmlFromUrl(url);
 
                InputStream stream = new ByteArrayInputStream(xml.getBytes());
                laptops = SAXXMLParser.parse(stream);
 
                for (Laptop laptop : laptops) {
                    String imageURL = laptop.getImageURL();
                    Bitmap bitmap = null;
                    BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                    bmOptions.inSampleSize = 1;
 
                    try {
                        bitmap = BitmapFactory
                                .decodeStream(new
                                URL(imageURL).openStream(),
                                null, bmOptions);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    laptop.setImageBitmap(bitmap);
                }
            }
            // stream.close();
            return laptops;
        }
    }
}