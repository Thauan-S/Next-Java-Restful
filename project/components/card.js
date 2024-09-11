import React, { useState } from "react";
import styles from "../styles/card.module.css";
import Link from "next/link";
import Image from "next/image";
import CreateReserveModal from "./createReserveModal";
const Card = ({
  image,
  title,
  description,
  btnText,
  pacotesNacionais,
  pacotesInternacionais,
}) => {
  console.log(pacotesNacionais);
  const [modal, setModal] = useState(false);
  const [packageId, setPackageId] = useState();
  const handleClick = (id) => {
    setModal((prevModal) => !prevModal);
    setPackageId(id);
  };
  if (title) {
    //card da página home
    return (
      <div className="card h-100">
        <Image
          className={`${styles.img} rounded card-img-top`}
          src={image}
          alt="..."
          layout="responsive"
          width={800}
          height={600}
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
                <Image
                  src={i.image}
                  className="card-img-top"
                  alt="teste"
                  layout="responsive"
                  width={800}
                  height={600}
                />
                <div className="card-body">
                  <h5 className="card-title">{i.destiny}</h5>
                  <p className="card-text">{i.description}</p>
                  <p className="card-text"> dias de viagem {i.days}</p>
                </div>
                <div className="card-footer">
                  <small className="text-body-secondary">
                    <button
                      className={`btn btn-primary ${styles.btnpromo} text-end`}
                      onClick={() => handleClick(i.id)}
                    >
                      Comprar R${i.price}
                    </button>
                  </small>
                </div>
              </div>
            </div>
          ))}
        </div>
        {modal && (
          <CreateReserveModal
            modal={{ modal, setModal }}
            packageId={packageId}
          />
        )}
      </>
    );
  } else if (pacotesInternacionais) {
    return (
      <>
        <div className="row row-cols-1 row-cols-md-3 text-center g-4 pt-2">
          {pacotesInternacionais.map((i) => (
            <div key={i.id} className="col">
              <div className="card h-100">
                <Image
                  src={i.image}
                  className="card-img-top"
                  alt="..."
                  layout="responsive"
                  width={800}
                  height={600}
                />
                <div className="card-body">
                  <h5 className="card-title">{i.destiny}</h5>
                  <p className="card-text">{i.description}</p>
                  <p className="card-text"> dias de viagem{i.days}</p>
                </div>
                <div className="card-footer">
                  <small className="text-body-secondary">
                    <button
                      className={`btn btn-primary ${styles.btnpromo} text-end`}
                      onClick={() => handleClick(i.id)}
                    >
                      Comprar R${i.price}
                    </button>
                  </small>
                </div>
              </div>
            </div>
          ))}
        </div>
        {modal && (
          <CreateReserveModal
            modal={{ modal, setModal }}
            packageId={packageId}
          />
        )}
      </>
    );
  }
};

export default Card;
