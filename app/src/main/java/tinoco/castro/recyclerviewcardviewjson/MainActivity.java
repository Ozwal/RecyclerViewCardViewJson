package tinoco.castro.recyclerviewcardviewjson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String URL_DATA = "https://simplifiedcoding.net/demos/view-flipper/heroes.php";


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListaItem> listaItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        //Cada item del recyclerview tendra fixed size
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listaItems = new ArrayList<>();

        /*for (int i =0;i<=10;i++){
            ListaItem listaItem = new ListaItem(
                    "Encabezado" + (i+1),
                    "textooo"
            );
            listaItems.add(listaItem);
        }

        //Inicializa el adaptador con la lista de items
        adapter=new MiAdaptador(listaItems, this);
        //Inserta el adaptador al recyclerview
        recyclerView.setAdapter(adapter);*/
        cargarDatosRecyclerView();
    }

    private void cargarDatosRecyclerView(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando datos...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray arr = jsonObject.getJSONArray("heroes");

                    for (int i = 0; i < arr.length(); i++){
                        JSONObject obj = arr.getJSONObject(i);
                        ListaItem item = new ListaItem(
                                obj.getString("name"),
                                obj.getString("imageurl")
                        );
                        listaItems.add(item);
                    }

                    adapter = new MiAdaptador(listaItems, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
