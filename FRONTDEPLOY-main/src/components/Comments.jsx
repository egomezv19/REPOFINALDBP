import React from 'react';

const Comments = ({ comentarios }) => {
  return (
    <div>
      <h3>Comments</h3>
      <ul>
        {comentarios.map((comentario) => (
          <li key={comentario.id}>{comentario.content}</li>
        ))}
      </ul>
    </div>
  );
};

export default Comments;
