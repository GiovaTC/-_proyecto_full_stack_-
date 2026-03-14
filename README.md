# -_proyecto_full_stack_- :.
📦 Proyecto Full Stack:
Dashboard de Galería de Fotos.

Aplicación Full Stack desarrollada con:
Backend: Java + Spring Boot
Frontend: React
API externa: API pública JSONPlaceholder (open source)
El backend funciona como API Proxy, consumiendo una API externa y exponiendo los datos al frontend.

Este patrón es ampliamente utilizado en arquitectura empresarial porque:
Protege la API externa
Permite agregar lógica propia
Facilita seguridad y caché

```
🧱 Arquitectura del Proyecto:
fullstack-photo-app
│
├── backend-java
│   └── Spring Boot API
│
└── frontend-react
    └── React App
Flujo de la aplicación
React Frontend
      │
      ▼
Java Spring Boot API
      │
      ▼
API Pública (JSONPlaceholder)

https://jsonplaceholder.typicode.com/photos
```
⚙️ BACKEND — Java (Spring Boot):
Dependencias Maven
pom.xml
```
<dependencies>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-webflux</artifactId>
    </dependency>

    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>

</dependencies>
```
📁 Estructura Backend:
```
backend-java
 ├── controller
 │      PhotoController.java
 │
 ├── service
 │      PhotoService.java
 │
 ├── model
 │      Photo.java
 │
 └── FullstackApiApplication.java
```
🧩 Modelo
Photo.java
```
package com.example.api.model;

public class Photo {

    private int albumId;
    private int id;
    private String title;
    private String url;
    private String thumbnailUrl;

    public int getAlbumId() { return albumId; }
    public void setAlbumId(int albumId) { this.albumId = albumId; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getThumbnailUrl() { return thumbnailUrl; }
    public void setThumbnailUrl(String thumbnailUrl) { this.thumbnailUrl = thumbnailUrl; }
}
```
🔌 Servicio (consume API externa)
PhotoService.java
```
package com.example.api.service;

import com.example.api.model.Photo;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Service
public class PhotoService {

    private final WebClient webClient;

    public PhotoService() {
        this.webClient = WebClient.create("https://jsonplaceholder.typicode.com");
    }

    public List<Photo> getPhotos() {

        Photo[] response = webClient.get()
                .uri("/photos?_limit=50")
                .retrieve()
                .bodyToMono(Photo[].class)
                .block();

        return Arrays.asList(response);
    }
}
```
🎮 Controller
PhotoController.java
```
package com.example.api.controller;

import com.example.api.model.Photo;
import com.example.api.service.PhotoService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/photos")
@CrossOrigin(origins = "*")
public class PhotoController {

    private final PhotoService service;

    public PhotoController(PhotoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Photo> getPhotos() {
        return service.getPhotos();
    }
}
```
🚀 Clase Principal
FullstackApiApplication.java
```
package com.example.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FullstackApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FullstackApiApplication.class, args);
    }
}
```
Servidor Backend
http://localhost:8080/api/photos

🎨 FRONTEND — React
Crear proyecto
npx create-react-app frontend-react
Instalar Axios
npm install axios
```
📁 Estructura React
src
 ├── components
 │      Gallery.js
 │      PhotoCard.js
 │
 ├── services
 │      api.js
 │
 ├── App.js
 └── App.css
```
🔌 Servicio API
api.js
```
import axios from "axios";

const API_URL = "http://localhost:8080/api/photos";

export const getPhotos = () => {
  return axios.get(API_URL);
};
```
🖼️ Componente Card
PhotoCard.js
```
import React from "react";

function PhotoCard({ photo }) {
  return (
    <div className="card">
      <img src={photo.thumbnailUrl} alt={photo.title} />
      <p>{photo.title}</p>
    </div>
  );
}

export default PhotoCard;
```
🖥️ Galería
Gallery.js
```
import React, { useEffect, useState } from "react";
import { getPhotos } from "../services/api";
import PhotoCard from "./PhotoCard";

function Gallery() {

  const [photos, setPhotos] = useState([]);

  useEffect(() => {

    getPhotos()
      .then(response => {
        setPhotos(response.data);
      });

  }, []);

  return (

    <div className="gallery">

      {photos.map(photo => (
        <PhotoCard key={photo.id} photo={photo} />
      ))}

    </div>

  );
}

export default Gallery;
```
🧭 App Principal
App.js
```
import React from "react";
import Gallery from "./components/Gallery";
import "./App.css";

function App() {

  return (

    <div>

      <h1>Galería de Fotos</h1>

      <Gallery />

    </div>

  );
}

export default App;
```
🎨 Tema Visual (CSS)
App.css
```
body {

  font-family: Arial;
  background: #f4f6f8;
  text-align: center;

}

.gallery {

  display: grid;
  grid-template-columns: repeat(auto-fit, 200px);
  gap: 20px;
  justify-content: center;
}

.card {

  background: white;
  padding: 10px;
  border-radius: 10px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.1);
}

.card img {

  width: 100%;
  border-radius: 6px;
}
```
▶️ Ejecutar el Proyecto:
Backend
mvn spring-boot:run

Puerto:
localhost:8080

Frontend
npm start

Puerto:
localhost:3000

📱 Resultado:
La aplicación mostrará una galería de fotos:
```
[ Imagen ]
Título

[ Imagen ]
Título

[ Imagen ]
Título
```
Datos obtenidos mediante el flujo:

API Pública → Backend Java → Frontend React
⭐ Mejoras (Nivel Profesional)

Se pueden agregar funcionalidades avanzadas:

- 1️⃣ Paginación
- 2️⃣ Búsqueda en tiempo real
- 3️⃣ Dark Mode
- 4️⃣ Autenticación JWT
- 5️⃣ Dockerización
- 6️⃣ Deploy en AWS / Render / Vercel / .
