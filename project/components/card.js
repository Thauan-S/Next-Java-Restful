import React from "react";
import styles from "../styles/card.module.css";
import Link from "next/link";
const Card = ({
  image,
  title,
  description,
  btnText,
  pacotesNacionais,
  pacotesInternacionais,
}) => {
  console.log(pacotesNacionais);
  if (title) {
    return (
      <div className="card h-100">
        <img
          className={`${styles.img} rounded card-img-top`}
          src={image}
          alt="..."
        />
        <div className="card-body">
          <h5 className="card-title">{title}</h5>
          <p className="card-text">{description}</p>
        </div>
        {btnText ? (
          <div className="card-footer">
            <Link
              className={`btn btn-primary ${styles.btnpromo} text-end`}
              href="/destinos"
            >
              {btnText}
            </Link>
          </div>
        ) : null}
      </div>
    );
  } else if (pacotesNacionais) {
    return (
      <>
        <div className="row row-cols-1 row-cols-md-3 text-center g-4 pt-2">
          {pacotesNacionais.map((i) => (
            <div key={i.id} className="col">
              <div className="card h-100">
                <img src={i.imagem} className="card-img-top" alt="teste" />
                <div className="card-body">
                  <h5 className="card-title">{i.destino}</h5>
                  <p className="card-text">{i.descricao}</p>
                </div>
                <div className="card-footer">
                  <small className="text-body-secondary">
                    <Link
                      className={`btn btn-primary ${styles.btnpromo} text-end`}
                      href={`/reserva/criar/${i.id}`}
                    >
                      {" "}
                      Comprar R${i.preco}
                    </Link>
                  </small>
                </div>
              </div>
            </div>
          ))}
        </div>
      </>
    );
  } else if (pacotesInternacionais) {
    return (
      <>
        <div className="row row-cols-1 row-cols-md-3 text-center g-4 pt-2">
          {pacotesInternacionais.map((i) => (
            <div key={i.id} className="col">
              <div className="card h-100">
                <img src={i.imagem} className="card-img-top" alt="..." />
                <div className="card-body">
                  <h5 className="card-title">{i.destino}</h5>
                  <p className="card-text">{i.descricao}</p>
                </div>
                <div className="card-footer">
                  <small className="text-body-secondary">
                    <Link
                      className={`btn btn-primary ${styles.btnpromo} text-end`}
                      href={`/reserva/criar/${i.id}`}
                    >
                      {" "}
                      Comprar R${i.preco}
                    </Link>
                  </small>
                </div>
              </div>
            </div>
          ))}
        </div>
      </>
    );
  }
};

export default Card;
