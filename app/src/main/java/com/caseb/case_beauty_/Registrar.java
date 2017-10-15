package com.caseb.case_beauty_;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Registrar extends AppCompatActivity {
    private Usuario usuario;
    private EditText editTextNome;
    private EditText editTextLogin;
    private EditText editTextEmail;
    private EditText editTextTelFixo;
    private EditText editTextTelCel;
    private EditText editTextSenha;
    private EditText editTextConfirmaSenha;

    private LoginActivity.UserLoginTask mAuthTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        this.usuario = new Usuario();
        this.editTextNome = (EditText) findViewById(R.id.editTextNome);
        this.editTextLogin = (EditText) findViewById(R.id.editTextLogin);
        this.editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        this.editTextTelFixo = (EditText) findViewById(R.id.editTextTelFixo);
        this.editTextTelCel = (EditText) findViewById(R.id.editTextTelCel);
        this.editTextSenha = (EditText) findViewById(R.id.editTextSenha);


        Intent intent = getIntent();
        if (intent != null){
            Bundle bundle = intent.getExtras();
            if (bundle != null){
                this.usuario.setId(bundle.getInt("id"));
                this.editTextNome.setText(bundle.getString("nome"));
                this.editTextLogin.setText(bundle.getString("login"));
                this.editTextEmail.setText(bundle.getString("email"));
                this.editTextTelFixo.setText(bundle.getString("telefone_fixo"));
                this.editTextTelCel.setText(bundle.getString("celular"));
                this.editTextSenha.setText(bundle.getString("senha"));
            }
        }
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextEmail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button btnSalvarPerfilR = (Button) findViewById(R.id.btnSalvarPerfilR);
        btnSalvarPerfilR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_novo, menu);
        // Action View
        //MenuItem searchItem = menu.findItem(R.id.action_search);
        //SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        // Configure the search info and add any event listeners
        //return super.onCreateOptionsMenu(menu);
        return true;

    }

    // Determines if Action bar item was selected. If true then do corresponding action.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //handle presses on the action bar items
        switch (item.getItemId()) {

            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            /*
            case R.id.action_search_barcode:
                startActivity(new Intent(this, TabbedActivity.class));
                return true;

            case R.id.action_add_current_contact:
                startActivity(new Intent(this, ContactsView.class));
                return true;
            //TODO change icon

            case R.id.action_my_contacts:
                startActivity(new Intent(this, ContactsView.class));
                return true;



            case R.id.action_settings:
                startActivity(new Intent(this, MobileAds.Settings.class));
                return true;
            */
        }
        return super.onOptionsItemSelected(item);
    }

    public void salvar (View view){
        this.usuario.setNome(this.editTextNome.getText().toString());
        this.usuario.setLogin(this.editTextLogin.getText().toString());
        this.usuario.setEmail(this.editTextEmail.getText().toString());
        this.usuario.setTelefone_fixo(this.editTextTelFixo.getText().toString());
        this.usuario.setCelular(this.editTextTelCel.getText().toString());
        this.usuario.setSenha(this.editTextSenha.getText().toString());
        this.usuario.salvar();

        Toast.makeText(this,this.usuario.get_mensagem(),Toast.LENGTH_LONG).show();
        if (usuario.is_status())
            finish();
    }

    public void cancelar (View view){
        finish();
    }
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        editTextNome.setError(null);
        editTextEmail.setError(null);


        // Store values at the time of the login attempt.
        String nome = editTextNome.getText().toString();
        String email = editTextEmail.getText().toString();
        String senha = editTextSenha.getText().toString();


        boolean cancel = false;
        View focusView = null;


        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            editTextEmail.setError(getString(R.string.error_field_required));
            editTextNome.setError(getString(R.string.error_field_required));
            editTextLogin.setError(getString(R.string.error_field_required));
            editTextTelFixo.setError(getString(R.string.error_field_required));
            editTextTelCel.setError(getString(R.string.error_field_required));
            editTextSenha.setError(getString(R.string.error_field_required));
            focusView = editTextEmail;
            cancel = true;
        } else if (!isEmailValid(email)) {
            editTextEmail.setError(getString(R.string.error_invalid_email));
            focusView = editTextEmail;
            cancel = true;
        }   else if  (!TextUtils.isEmpty(senha) && !isPasswordValid(senha)) {
            editTextSenha.setError(getString(R.string.error_invalid_password));
            editTextConfirmaSenha.setError(getString(R.string.error_invalid_password));
            focusView = editTextSenha;
            cancel = true;
        }      else {
            this.usuario.setNome(this.editTextNome.getText().toString());
            this.usuario.setLogin(this.editTextLogin.getText().toString());
            this.usuario.setEmail(this.editTextEmail.getText().toString());
            this.usuario.setTelefone_fixo(this.editTextTelFixo.getText().toString());
            this.usuario.setCelular(this.editTextTelCel.getText().toString());
            this.usuario.setSenha(this.editTextSenha.getText().toString());
            this.usuario.salvar();

            Toast.makeText(this,this.usuario.get_mensagem(),Toast.LENGTH_LONG).show();
            if (usuario.is_status())
                finish();

        }
    }
    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }
}
