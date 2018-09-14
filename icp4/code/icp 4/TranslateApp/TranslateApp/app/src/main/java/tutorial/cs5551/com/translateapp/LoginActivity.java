package tutorial.cs5551.com.translateapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText Username;
    private EditText Password;
    private Button Login;
    private int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Username = (EditText)findViewById(R.id.txt_uname);
        Password = (EditText)findViewById(R.id.txt_Pwd);
        Login = (Button) findViewById(R.id.btn_login);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Username.getText().toString(),Password.getText().toString());
            }
        });
    }

    private void validate(String userUsername, String userPassword){
        if((userUsername.equals("kalyan")) && (userPassword.equals("1234"))){
            Intent intent = new Intent(LoginActivity.this, TranslateActivity.class);
            startActivity(intent);
        }else {
            counter--;

            if(counter==0){
                Login.setEnabled(false);
            }
        }
    }
}