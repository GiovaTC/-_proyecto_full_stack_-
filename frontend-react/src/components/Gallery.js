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