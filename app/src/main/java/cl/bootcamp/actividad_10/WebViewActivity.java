package cl.bootcamp.actividad_10;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        WebView webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // Verificar si el esquema de la URL es algo que WebView no maneja.
                if (url.startsWith("http://") || url.startsWith("https://")) {
                    return false; // No sobrecargar, dejar que WebView cargue la URL.
                }

                // Manejar otros esquemas aquí (ej. "intent:", "mailto:", etc.).
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // Mostrar un mensaje de error o manejarlo según sea necesario.
                    Toast.makeText(WebViewActivity.this, "No se puede manejar esta acción", Toast.LENGTH_SHORT).show();
                }

                return true; // Señalar que hemos manejado esta URL.
            }
        });

        String url = getIntent().getStringExtra("url");
        webView.getSettings().setJavaScriptEnabled(true);  // Si el sitio web requiere JavaScript
        webView.loadUrl(url);
    }
}
