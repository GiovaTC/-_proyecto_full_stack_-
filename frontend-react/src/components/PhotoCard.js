import React from "react";

function PhotoCard({ photo }) {
    return (
        <div className="card">
          <img src={ photo.thumbnailUrl } alt = {photo.title} />
          <p>{photo.title}</p>
        </div>
    );
}

export default PhotoCard;