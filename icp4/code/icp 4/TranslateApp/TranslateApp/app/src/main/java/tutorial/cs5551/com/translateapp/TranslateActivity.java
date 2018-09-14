package tutorial.cs5551.com.translateapp;

import android.content.Context;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TranslateActivity extends AppCompatActivity {

    private Spinner spinner1;
    private Spinner spinner2;
    private Button Logout;
    private Button Convert;

    //String API_URL = "https://api.fullcontact.com/v2/person.json?";
    //String API_KEY = "b29103a702edd6a ";
    String source;
    String target;
    String sourceText;
    TextView outputTextView;
    Context mContext;
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
        outputTextView = (TextView) findViewById(R.id.txt_Result);



        //spinner1 = (Spinner) findViewById(R.id.spinner1);
        //spinner2 = (Spinner) findViewById(R.id.spinner2);
        Logout = (Button) findViewById(R.id.bLogout);
        Convert = (Button)findViewById(R.id.bConvert);
/*
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(TranslateActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.languages));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(myAdapter);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(TranslateActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.languages));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter);
*/
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TranslateActivity.this, LoginActivity.class));

            }
        });



        ;

    }





    public void translateText(View view) {
        TextView sourceView =(TextView)findViewById(R.id.etsource);
        source = sourceView.getText().toString();

        TextView targetView = (TextView)findViewById(R.id.ettarget);
        target = targetView.getText().toString();

        TextView sourceTextView = (TextView) findViewById(R.id.ettext);

        sourceText = sourceTextView.getText().toString();
        String getURL = "https://translate.yandex.net/api/v1.5/tr.json/translate?" +
                "key=trnsl.1.1.20151023T145251Z.bf1ca7097253ff7e." +
                "c0b0a88bea31ba51f72504cc0cc42cf891ed90d2&text=" + sourceText +"&" +
                "lang="+source+"-"+target+"&[format=plain]&[options=1]&[callback=set]";

        //The API service URL
        final String response1 = "";
        OkHttpClient client = new OkHttpClient();
        try {
            Request request = new Request.Builder()
                    .url(getURL)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println(e.getMessage());
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final JSONObject jsonResult;
                    final String result = response.body().string();
                    try {
                        jsonResult = new JSONObject(result);
                        JSONArray convertedTextArray = jsonResult.getJSONArray("text");
                        final String convertedText = convertedTextArray.get(0).toString();
                        Log.d("okHttp", jsonResult.toString());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                outputTextView.setText(convertedText);
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });


        } catch (Exception ex) {
            outputTextView.setText(ex.getMessage());
            outputTextView.setVisibility(View.VISIBLE);

        }

    }
}
