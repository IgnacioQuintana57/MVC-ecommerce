package com.backend.backend.firebase;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import jakarta.annotation.PostConstruct;

@Service // Se inicia apenas arranca el proyecto
public class FirebaseInitializer {

    @PostConstruct
    private void initFirebase() throws IOException {
        InputStream serviceAccount;
        String credentialsPath = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");
        System.out.println("HOLAAAAAAAAAAAAAAAAAAAAAAA");
        if (credentialsPath != null) {
            serviceAccount = Files.newInputStream(Paths.get(credentialsPath));
        } else {
            serviceAccount = getClass().getClassLoader().getResourceAsStream("firebaseApiKey.json");
        }
        System.out.println(serviceAccount);
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://mvc-ecommerce-c56e5.firebaseio.com/")
                .build();

        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }

    }

    public Firestore getFirestore() {
        return FirestoreClient.getFirestore();
    }
}
