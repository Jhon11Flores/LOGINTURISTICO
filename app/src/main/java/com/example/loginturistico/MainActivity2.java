package com.example.loginturistico;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity2 extends AppCompatActivity implements Asynchtask {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Map<String, String> datos = new HashMap<String, String>();

        WebService ws = new
                WebService("https://uealecpeterson.net/turismo/lugar_turistico/json_getlistadoGrid",
                datos, MainActivity2.this, MainActivity2.this);
        ws.execute("GET", "Public-Merchant-Id", "84e1d0de1fbf437e9779fd6a52a9ca18");
    }
    @Override
    public void processFinish(String result) throws JSONException {
        Log.d("API_RESPONSE", result);  // Imprime la respuesta en la consola
        TextView txtBancos = findViewById(R.id.txtLugares);
        StringBuilder lugaresStringBuilder = new StringBuilder();
        JSONObject jsonResult = new JSONObject(result);
        JSONArray lugaresArray = jsonResult.getJSONArray("data");
        for (int i = 0; i < lugaresArray.length(); i++) {
            JSONObject lugar = lugaresArray.getJSONObject(i);
            String categoria = lugar.optString("categoria", "");
            String nombreLugar = lugar.optString("nombre_lugar", "");
            String telefono = lugar.optString("telefono", "");
            lugaresStringBuilder.append(categoria).append("\n");
            lugaresStringBuilder.append(nombreLugar).append("\n");
            lugaresStringBuilder.append(telefono).append("\n\n");
        }
        txtBancos.setText("Repuesta del servidor:\n" + lugaresStringBuilder.toString());
    }
}