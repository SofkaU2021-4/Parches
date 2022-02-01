package co.com.sofka.parches.utils;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.IOException;

public class Firebase {


    private Firebase() {
    }

    //TODO: Poner la ruta del archivo de firebase del proyecto a despliegue
    public static void inicializarFirebase() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream("service-account.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);
    }

}